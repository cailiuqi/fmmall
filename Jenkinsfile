pipeline {
    agent any

    options {
        timestamps()
        skipDefaultCheckout(true)
        disableConcurrentBuilds()
        buildDiscarder(logRotator(daysToKeepStr: '10', numToKeepStr: '10'))
    }

    environment {
        hostname = '192.168.43.205';
    }
    stages {
        stage('Deploy') {
            steps {

                script {

                    withCredentials([sshUserPrivateKey(credentialsId: 'mackey', keyFileVariable: 'KEYFILE')]) {
                        def remote = [name:"what",host:"192.168.43.205",allowAnyHosts: true,user:"what",identityFile:"$KEYFILE"]
                        sshCommand remote: remote, command: 'pwd;ls -ltr'
                    }
                }
            }

        }

    }
}