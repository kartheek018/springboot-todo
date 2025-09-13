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
                sh 'mvn clean package'
            }
        }
        stage('Docker Build') {
            steps {
                sh "docker build -t $IMAGE_NAME ."
            }
        }
        stage('Docker Run') {
            steps {
                sh 'docker rm -f todo-app || true'
                sh "docker run -d -p 8081:8080 --name todo-app $IMAGE_NAME"
            }
        }
    }
}
