pipeline {
    agent any

    environment {
        GRADLE_OPTS = '-Dorg.gradle.daemon=false'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Start Kafka') {
            steps {
                sh 'docker compose up -d Kafka'
                sh '''
                    for i in $(seq 1 30); do
                      if docker compose exec -T Kafka kafka-topics --bootstrap-server localhost:9092 --list >/dev/null 2>&1; then
                        exit 0
                      fi
                      sleep 2
                    done
                    echo "Kafka did not become ready in time"
                    exit 1
                '''
            }
        }

        stage('Build And Test') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean test bootJar'
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: 'build/test-results/test/*.xml'
            archiveArtifacts allowEmptyArchive: true, artifacts: 'build/libs/*.jar'
            sh 'docker compose down || true'
        }
    }
}
