import com.sun.tools.internal.xjc.model.CDefaultValue

pipeline {
    agent any

    options {
        timestamps()
        skipDefaultCheckout(true)
        disableConcurrentBuilds()
        buildDiscarder(logRotator(daysToKeepStr: '10', numToKeepStr: '10'))
    }

    parameters {
        gitParameter branch : '', name: 'branch' , branchFilter: 'origin/(.*)', defaultValue: 'master' ,
        listSize: '5' ,
        selectedValue: 'DEFAULT' , sortMode: 'ASCENDING_SMART' , description: 'branch to build' ,
        type: 'PT_BRANCH'
        string(name: 'releaseVersion',defaultValue: '1.0.0',description: 'version to release')
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