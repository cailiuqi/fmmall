pipeline {
  agent any
  stages {
    stage('Deploy') {
     steps {
//        withCredentials([sshUserPrivateKey(credentialsId: 'ssh-key', keyFileVariable: 'keyfile', passphraseVariable: '123456')]) {
//          echo 'Connect server'
////          sh """
////              ssh -o $MAC-SSH_KEY what@192.168.43.205
////             """
//          sh "ssh -i 'keyfile:$keyfile' -o StrictHostKeyChecking=no what@192.168.43.205"
//
//          sh 'cd /Users/what'
//          sh 'touch textfile.txt'
//          sh 'echo "this is testing" >> textfile.txt'
//        }
       echo 'Connect server'
       withCredentials([sshUserPrivateKey(credentialsId: 'ssh-keyid', keyFileVariable: 'ssh-keyus')]) {
         // some block
         sshPublisher(publishers:
                 [sshPublisherDesc(
                         configName: '12',
                         sshCredentials: [ key: '$ssh-keyus', username: 'ssh-keyus'],
                         transfers:
                                 [sshTransfer
                                          (cleanRemote: false,
                                                  excludes: '',
                                                  execCommand: 'touch test12.txt',
                                                  execTimeout: 120000,
                                                  flatten: false,
                                                  makeEmptyDirs: false,
                                                  noDefaultExcludes: false,
                                                  patternSeparator: '[, ]+',
                                                  remoteDirectory: '',
                                                  remoteDirectorySDF: false,
                                                  removePrefix: '', sourceFiles: '')],
                         usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
       }
           }

    }

  }
}