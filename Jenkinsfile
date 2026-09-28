pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Maven Build') {
            steps {
                dir('backend') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Unit Tests') {
            steps {
                dir('backend') {
                    sh 'mvn test'
                }
            }
        }

stage('SonarQube Analysis') {
    steps {
        dir('backend') {
            withSonarQubeEnv('SonarQube') {
                sh '''
                    mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \
                      -Dsonar.projectKey=HealthSphere-Backend
                '''
            }
        }
    }
}

        stage('Docker Build') {
            steps {
                dir('backend') {
                    sh '''
                        docker build \
                          -t healthsphere-backend:1.0.0 \
                          .
                    '''
                }
            }
        }

        stage('Docker Image Validation') {
            steps {
                sh '''
                    docker image inspect healthsphere-backend:1.0.0
                '''
            }
        }

        stage('Docker Tag') {
            steps {
                sh '''
                    docker tag \
                      healthsphere-backend:1.0.0 \
                      host.docker.internal:5100/healthsphere-backend:1.0.0
                '''
            }
        }

        stage('Docker Push') {
            steps {
                sh '''
                    docker push \
                      host.docker.internal:5100/healthsphere-backend:1.0.0
                '''
            }
        }
    }

    post {

        success {
            withCredentials([
                string(
                    credentialsId: 'slack-webhook',
                    variable: 'SLACK_WEBHOOK'
                )
            ]) {
                sh '''
                    curl -fsS \
                      -X POST \
                      -H "Content-Type: application/json" \
                      --data '{"text":"HealthSphere CI/CD -> SUCCESS"}' \
                      "$SLACK_WEBHOOK"
                '''
            }
        }

        failure {
            withCredentials([
                string(
                    credentialsId: 'slack-webhook',
                    variable: 'SLACK_WEBHOOK'
                )
            ]) {
                sh '''
                    curl -fsS \
                      -X POST \
                      -H "Content-Type: application/json" \
                      --data '{"text":"HealthSphere CI/CD -> FAILURE"}' \
                      "$SLACK_WEBHOOK"
                '''
            }
        }
    }
}