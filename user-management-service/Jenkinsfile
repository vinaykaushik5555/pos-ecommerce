pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'user-management-service:latest'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'feature-user-service', url: 'https://github.com/vinaykaushik5555/pos-ecommerce.git'
            }
        }

        stage('Build Maven Project') {
            steps {
                script {
                    cd 'user-management-service'
                    sh 'mvn clean package'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t ${DOCKER_IMAGE} .'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-credentials-id') {
                        sh "docker push ${DOCKER_IMAGE}"
                    }
                }
            }
        }
    }
}
