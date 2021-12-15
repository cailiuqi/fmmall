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
                git url: "${gitUrl}",
                    branch: "${params.branch}",
                    credentialsId: 'fc7d9f00-2e91-4177-afb9-82cfba3507a1'
            }
        }
        stage('Building Project'){
            steps{
                echo 'Building...'
                sh 'mvn -U clean install -Dmaven.test.skip=true'

            }
        }

        stage('Deploy') {
            steps {
                script {
                    withCredentials([sshUserPrivateKey(credentialsId: 'mackey', keyFileVariable: 'KEYFILE')]) {
                        def remote = [name:"what",host:"192.168.43.205",allowAnyHosts: true,user:"what",identityFile:"$KEYFILE"]
                        sshCommand remote: remote, command: 'mkdir test1215'
                        //writeFile file: 'abc.sh', text: 'ls -lrt'
                        sshPut remote: remote, from: 'service/target/service-2.0.1.jar', into: '.'
                        //sshCommand remote: remote, command: 'cat abc.sh'
                    }
                }
            }

        }

    }
}