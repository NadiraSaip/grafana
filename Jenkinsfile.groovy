import hudson.EnvVars

def app

node {

  // Pooling the docker image
  checkout scm

  stage('Build docker image') {

      // Build the docker image
      app = docker.build("rameca231190/grafana3", "-f ${WORKSPACE}/Dockerfile .")
  }


  stage('Push image') {

     // Push docker image to the Docker hub
      docker.withRegistry('', 'my-dockerhub') {
          app.push("0.${BUILD_NUMBER}")
          app.push("latest")
      }
  }
}
