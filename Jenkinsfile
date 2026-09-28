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
                          -t healthsphere-backend:${IMAGE_TAG} \
                          .
                    '''
                }
            }
        }

        stage('Docker Image Validation') {
            steps {
                sh '''
                    docker image inspect healthsphere-backend:${IMAGE_TAG}
                '''
            }
        }

        stage('Docker Tag') {
            steps {
                sh '''
                    docker tag \
                      healthsphere-backend:${IMAGE_TAG} \
                      host.docker.internal:5100/healthsphere-backend:${IMAGE_TAG}
                '''
            }
        }

        stage('Docker Push') {
            steps {
                sh '''
                    docker push \
                      host.docker.internal:5100/healthsphere-backend:${IMAGE_TAG}
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