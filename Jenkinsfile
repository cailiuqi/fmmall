pipeline {
  agent any
  stages {
//    stage('Deploy') {
//      steps {
//        withCredentials([sshUserPrivateKey(credentialsId: 'ssh-key', keyFileVariable: 'MAC-SSH_KEY')]) {
//          echo 'Connect server'
//          sh """
//              ssh -o $MAC-SSH_KEY what@192.168.43.205
//             """
//          sh 'cd /Users/what'
//          sh 'touch textfile.txt'
//          sh 'echo "this is testing" >> textfile.txt'
//        }
//      }
//    }
    stage('deploy') {
      sshagent(credentials: ['ssh-key']) {
        sh 'ssh what@192.168.43.205'
        sh 'echo hello'
        sh 'cd /Users/what'
          sh 'touch textfile.txt'
          sh 'echo "this is testing" >> textfile.txt'
      }
    }
  }
}