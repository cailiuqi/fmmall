pipeline {
  agent any
  stages {
    stage('Deploy') {
      steps {
        withCredentials([sshUserPrivateKey(credentialsId: 'ssh-key', keyFileVariable: 'keyfile', passphraseVariable: '123456')]) {
          echo 'Connect server'
//          sh """
//              ssh -o $MAC-SSH_KEY what@192.168.43.205
//             """
          sh "ssh -i ${keyfile} -o StrictHostKeyChecking=no what@192.168.43.205"

          sh 'cd /Users/what'
          sh 'touch textfile.txt'
          sh 'echo "this is testing" >> textfile.txt'
        }
      }
    }

  }
}