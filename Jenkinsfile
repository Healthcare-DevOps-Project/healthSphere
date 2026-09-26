pipeline {
    agent any

    stages {
        stage('GitHub Trigger Test') {
            steps {
                echo 'GitHub push successfully triggered Jenkins!'
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
                      --data '{"text":"GitHub → Jenkins SUCCESS"}' \
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
                      --data '{"text":"GitHub → Jenkins FAILURE"}' \
                      "$SLACK_WEBHOOK"
                '''
            }
        }
    }
}
