pipeline {
    agent any

    environment {
        COMPOSE_PROJECT_NAME = 'microproject' // Namespace for containers
        COMPOSE_FILE = 'docker-compose.yml'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/DhouhaOun/microproject.git'
            }
        }

        stage('Build All Modules in Parallel') {
          parallel {
            discovery: {
              dir('discovery-service') {
                sh 'mvn clean package -DskipTests'
              }
            }
            config: {
              dir('config-service') {
                sh 'mvn clean package -DskipTests'
              }
            }
            customer: {
               dir('customer-service') {
                 sh 'mvn clean package -DskipTests'
               }
            }
            inventory: {
               dir('inventory-service') {
                 sh 'mvn clean package -DskipTests'
               }
            }
            billing: {
               dir('billing-service') {
                 sh 'mvn clean package -DskipTests'
               }
            }
            gateway: {
               dir('gatewey-service') {
                 sh 'mvn clean package -DskipTests'
               }
            }
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
