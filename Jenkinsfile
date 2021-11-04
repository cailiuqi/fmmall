pipeline {
  agent any
  stages {
    stage('Deploy') {
     steps {

       withCredentials([sshUserPrivateKey(credentialsId: 'ssh-keyid', keyFileVariable: 'ssh-keyus')]) {
         def remote = [:]
         remote.name = 'test'
         remote.host = '192.168.3.40'
         remote.user = 'what'
         remote.identityFile = '${ssh-keyus}'
         remote.allowAnyHosts = true
           writeFile file: 'abc.sh', text: 'ls -lrt'
           sshCommand remote: remote, command: "ls -lrt"
           sshPut remote: remote, from: 'abc.sh', into: '.'
         }
           }

    }

  }
}