pipeline {
    agent any

    environment {
        // ⚠️ CHANGE THIS to your own Docker Hub username/repo
        DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub'
        DOCKERHUB_REPO           = 'chenyicheng1998/calculator-fx-db'
        DOCKER_IMAGE_TAG         = 'latest'
        PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"
    }

    tools {
        maven 'Maven3'
    }

    stages {

        stage('Check Docker') {
            steps {
                bat 'docker --version'
            }
        }

        stage('Checkout') {
            steps {
                // ⚠️ CHANGE THIS to your own GitHub repository URL
                git branch: 'main', url: 'https://github.com/chenyicheng1998/week7_calculator_fx_db-main.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat "docker build -t %DOCKERHUB_REPO%:%DOCKER_IMAGE_TAG% ."
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                withCredentials([usernamePassword(
                        credentialsId: "${DOCKERHUB_CREDENTIALS_ID}",
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS')]) {
                    bat '''
                        docker login -u %DOCKER_USER% -p %DOCKER_PASS%
                        docker push %DOCKERHUB_REPO%:%DOCKER_IMAGE_TAG%
                    '''
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
        success {
            echo '✅ Build and push succeeded!'
        }
        failure {
            echo '❌ Pipeline failed. Check logs above.'
        }
    }
}

