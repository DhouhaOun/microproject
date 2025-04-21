pipeline {
    agent any

    environment {
        COMPOSE_PROJECT_NAME = 'microproject'
        COMPOSE_FILE = 'docker-compose.yml'
    }

    stages {

        stage('Checkout Source Code') {
            steps {
                git branch: 'main', url: 'https://github.com/DhouhaOun/microproject.git'
            }
        }

        stage('Build Services - Parallel') {
            parallel {
                discovery: {
                    steps {
                        dir('discovery-service') {
                            sh 'mvn clean package -DskipTests'
                        }
                    }
                }
                config: {
                    steps {
                        dir('config-service') {
                            sh 'mvn clean package -DskipTests'
                        }
                    }
                }
                customer: {
                    steps {
                        dir('customer-service') {
                            sh 'mvn clean package -DskipTests'
                        }
                    }
                }
                inventory: {
                    steps {
                        dir('inventory-service') {
                            sh 'mvn clean package -DskipTests'
                        }
                    }
                }
                billing: {
                    steps {
                        dir('billing-service') {
                            sh 'mvn clean package -DskipTests'
                        }
                    }
                }
                gateway: {
                    steps {
                        dir('gateway-service') {
                            sh 'mvn clean package -DskipTests'
                        }
                    }
                }
            }
        }

        stage('Stop Existing Containers') {
            steps {
                sh "docker compose -f ${COMPOSE_FILE} -p ${COMPOSE_PROJECT_NAME} down || true"
            }
        }

        stage('Build and Start Containers') {
            steps {
                sh "docker compose -f ${COMPOSE_FILE} -p ${COMPOSE_PROJECT_NAME} up -d --build"
            }
        }

        stage('Check Running Containers') {
            steps {
                sh "docker ps --filter name=${COMPOSE_PROJECT_NAME}"
            }
        }
    }

    post {
        always {
            echo "✅ Pipeline execution complete."
        }
    }
}
