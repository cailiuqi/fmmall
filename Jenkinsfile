pipeline {
  agent any
  stages {
    stage('Deploy') {
     steps {
       script {
         withCredentials([sshUserPrivateKey(credentialsId: 'ssh-keyid', keyFileVariable: 'ssh-keyus')]) {
           // some block
           def remote = [name: "what", host: "192.168.3.40", allowhost: true, user: "what", identityFile: "${ssh-keyus}"]
           writeFile file: 'abc.sh', text: 'ls -lrt'
           sshCommand remote: remote, command: "ls -lrt"
           sshPut remote: remote, from: 'abc.sh', into: '.'
         }
       }
           }

    }

  }
}