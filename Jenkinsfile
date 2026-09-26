```groovy
pipeline {
    agent any

    stages {

        stage('GitHub Trigger Test') {
            steps {
                echo 'GitHub push successfully triggered Jenkins!'
            }
        }

        stage('Terraform Init') {
            steps {
                sh 'terraform init'
            }
        }

        stage('Terraform Validate') {
            steps {
                sh 'terraform validate'
            }
        }

        stage('Terraform Plan') {
            steps {
                sh 'terraform plan'
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
                      --data '{"text":"GitHub → Jenkins → Terraform PLAN SUCCESS"}' \
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
                      --data '{"text":"GitHub → Jenkins → Terraform PLAN FAILURE"}' \
                      "$SLACK_WEBHOOK"
                '''
            }
        }
    }
}
```
