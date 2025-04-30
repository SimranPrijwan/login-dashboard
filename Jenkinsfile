pipeline {
    agent any

    tools {
        maven 'Maven_3.8.6'
        jdk 'JDK_17'
    }

    environment {
        DOCKER_IMAGE = 'myapp_image'
        CONTAINER_NAME = 'myapp_container'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE .'
            }
        }

        stage('Docker Run') {
            steps {
                sh 'docker run -d --name $CONTAINER_NAME -p 8080:8080 $DOCKER_IMAGE'
            }
        }
    }

    post {
        always {
            echo 'Cleaning up Docker container if it exists...'
            sh 'docker rm -f $CONTAINER_NAME || echo "Container not running"'
        }
    }
}
