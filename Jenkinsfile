pipeline {
  agent any
  stages {
    stage('Deploy') {
      steps {
        withCredentials(bindings: [sshUserPrivateKey(credentialsId: 'ssh-key', keyFileVariable: 'MAC-SSH_KEY')]) {
          cd /Users/what
          touch textfile.txt
          echo "this is testing" >> textfile.txt
        }
      }
    }

  }
}