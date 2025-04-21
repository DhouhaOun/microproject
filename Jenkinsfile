pipeline {
    agent any

    environment {
        COMPOSE_PROJECT_NAME = 'microproject' // Namespace for containers
        COMPOSE_FILE = 'docker-compose.yml'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/DhouhaOun/microproject.git'
            }
        }

        stage('Stop Containers') {
            steps {
                sh "docker compose -f ${COMPOSE_FILE} -p ${COMPOSE_PROJECT_NAME} down"
            }
        }

        stage('Build & Start Containers') {
            steps {
                sh "docker compose -f ${COMPOSE_FILE} -p ${COMPOSE_PROJECT_NAME} up -d --build"
            }
        }

        stage('Check Running Containers') {
            steps {
                sh "docker ps -f name=${COMPOSE_PROJECT_NAME}"
            }
        }
    }

    post {
        always {
            echo "Pipeline finished."
        }
    }
}
