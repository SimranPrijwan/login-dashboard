pipeline {
    agent any

    stages {
        stage('Clone') {
            steps {
                echo 'Cloning from GitHub...'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'  // or ./gradlew build if using Gradle
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    docker.build('my-app-image')
                }
            }
        }

        stage('Docker Run') {
            steps {
                script {
                    docker.image('my-app-image').run('-d -p 8080:8080')
                }
            }
        }
    }
}
