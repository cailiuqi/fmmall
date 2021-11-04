pipeline {
  agent any
  stages {
    stage('Deploy') {
     steps {
       def remote = [name: "what", host: "192.168.3.40", allowhost: true, user: "what", identityFile: "${ssh-keyus}"]

       withCredentials([sshUserPrivateKey(credentialsId: 'ssh-keyid', keyFileVariable: 'ssh-keyus')]) {
           // some block
           writeFile file: 'abc.sh', text: 'ls -lrt'
           sshCommand remote: remote, command: "ls -lrt"
           sshPut remote: remote, from: 'abc.sh', into: '.'
         }
           }

    }

  }
}