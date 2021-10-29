pipeline {
  agent any
  stages {
    stage('Deploy') {
      steps {
        withCredentials([sshUserPrivateKey(credentialsId: 'ssh-key', keyFileVariable: 'MAC-SSH_KEY', usernameVariable: 'MAC-SSH_KEY')]) {
          // some block
          cd /Users/what
          touch textfile.txt
          echo "this is testing" >> textfile.txt
        }
      }
    }

  }
}