pipeline {
    agent any

    environment {
        IMAGE_NAME = 'kartheek/todo-springboot:latest'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/kartheek018/springboot-todo.git'
            }
        }

        stage('Build JAR') {
            steps {
                // Run Maven directly in Jenkins container
                sh 'mvn clean package'
            }
        }

        stage('Docker Build') {
            steps {
                // Build Docker image on Windows host
                sh "docker build -t $IMAGE_NAME ."
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                    sh "docker push $IMAGE_NAME"
                }
            }
        }

        stage('Deploy') {
            steps {
                // Stop old container if exists
                sh 'docker rm -f todo-app || true'
                // Run new container
                sh "docker run -d -p 8081:8080 --name todo-app $IMAGE_NAME"
            }
        }
    }
}
