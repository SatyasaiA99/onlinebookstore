pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = 'Docker' // Replace with your Jenkins DockerHub credentials ID
        IMAGE_NAME = 'satyasaia99/onlinebookstore'
    }

    stages {
        stage('Checkout') {
            steps {
               git branch: 'master', url: 'https://github.com/SatyasaiA99/onlinebookstore.git'
            }
        }

        stage('Build WAR') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${IMAGE_NAME}:latest ."
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: "${DOCKERHUB_CREDENTIALS}", usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh '''
                        echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                        docker push ${IMAGE_NAME}:latest
                        docker logout
                    '''
                }
            }
        }

        stage('Run Container') {
            steps {
                sh '''
                    docker stop onlinebookstore || true
                    docker rm onlinebookstore || true
                    docker run -d -p 7979:8080 --name onlinebookstore ${IMAGE_NAME}:latest
                '''
            }
        }
    }
}
