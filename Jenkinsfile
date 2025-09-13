pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "kartheek/todo-springboot:latest"
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout your Spring Boot project from GitHub
                git branch: 'main', url: 'https://github.com/kartheek018/springboot-todo.git'
            }
        }

        stage('Build') {
            steps {
                // Build Spring Boot project with Maven
                sh 'mvn clean package'
            }
        }

        stage('Archive Artifact') {
            steps {
                // Save the built JAR as Jenkins artifact
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Docker Build') {
            steps {
                // Build Docker image from Dockerfile in repo root
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Docker Push') {
            steps {
                // Push Docker image to Docker Hub using credentials stored in Jenkins
                withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials',
                                                 usernameVariable: 'DOCKER_USER',
                                                 passwordVariable: 'DOCKER_PASS')]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                    sh "docker push ${DOCKER_IMAGE}"
                }
            }
        }
    }
}
