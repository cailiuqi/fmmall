import com.sun.tools.internal.xjc.model.CDefaultValue

pipeline {
    agent any

    environment {
        hostname = '192.168.43.205';
        gitUrl = 'git@github.com:cailiuqi/fmmall.git'
    }
    options {
        timestamps()
        skipDefaultCheckout(true)
        disableConcurrentBuilds()
        buildDiscarder(logRotator(daysToKeepStr: '10', numToKeepStr: '10'))
    }

    parameters {
        gitParameter branch : '', name: 'branch' , branchFilter: 'origin/(.*)', defaultValue: 'main' ,
        listSize: '5' ,
        selectedValue: 'DEFAULT' , sortMode: 'ASCENDING_SMART' , description: 'branch to build' ,
        type: 'PT_BRANCH'
        string(name: 'releaseVersion',defaultValue: '1.0.0',description: 'version to release')
    }

    stages {
        stage('checkOut Code'){
            steps{
                echo 'Checking out ...'
                deleteDir()
                git url: "${gitUrl}"
                    branch: "${params.branch}"
                    credentialsId: '1212'
            }
        }
        stage('Building Project'){
            steps{
                echo 'Building...'
                sh """
                    mvn version: set -DgenerateBackUpPoms=false -DnewVersion=${params.releaseVersion}
                    mvn -U clean install -Dmaven.test.skip[true
                   """

            }
        }

        stage('Deploy') {
            steps {
                script {
                    withCredentials([sshUserPrivateKey(credentialsId: 'mackey', keyFileVariable: 'KEYFILE')]) {
                        def remote = [name:"what",host:"192.168.43.205",allowAnyHosts: true,user:"what",identityFile:"$KEYFILE"]
                        sshCommand remote: remote, command: 'pwd;ls -ltr'
                        writeFile file: 'abc.sh', text: 'ls -lrt'
                        sshPut remote: remote, from: 'abc.sh', into: '.'
                        sshCommand remote: remote, command: 'cat abc.sh'
                    }
                }
            }

        }

    }
}