pipeline {
    agent any

    tools {
        jdk 'jdk21'
    }

    environment {
        STUDENT_NAME = 'Alua Makpyr'
        IMAGE_NAME   = 'makpyr-alua-student-api'
        IMAGE_TAG    = 'v1.0-makpyr-alua'
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Student: ${STUDENT_NAME} - checking out source code from GitHub"
                checkout scm
                sh 'git log -1 --oneline'
            }
        }

        stage('Build') {
            steps {
                echo "Building Spring Boot application of ${STUDENT_NAME}"
                sh 'java -version'
                sh 'chmod +x gradlew'
                sh './gradlew clean bootJar --no-daemon'
                sh 'ls -l build/libs/'
            }
        }

        stage('Test') {
            steps {
                echo "Running automated tests for ${STUDENT_NAME}"
                sh './gradlew test --no-daemon'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: 'build/test-results/test/*.xml'
                }
            }
        }

        stage('Docker Build') {
            steps {
                echo "Building Docker image ${IMAGE_NAME}:${IMAGE_TAG}"
                sh 'docker build -t $IMAGE_NAME:$IMAGE_TAG .'
            }
        }

        stage('Verify') {
            steps {
                sh 'docker image inspect $IMAGE_NAME:$IMAGE_TAG --format "Image {{.RepoTags}} created {{.Created}} size {{.Size}} bytes"'
                sh 'docker images $IMAGE_NAME'
                echo "Verified: image ${IMAGE_NAME}:${IMAGE_TAG} exists (student ${STUDENT_NAME})"
            }
        }
    }

    post {
        success {
            echo "SUCCESS: pipeline of ${STUDENT_NAME} finished"
        }
        failure {
            echo "FAILURE: pipeline of ${STUDENT_NAME} stopped, later stages were skipped"
        }
    }
}
