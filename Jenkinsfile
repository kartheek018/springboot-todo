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
            agent {
                docker {
                    image 'maven:3.9.2-eclipse-temurin-21'  // Maven + JDK
                    args '-v /var/run/docker.sock:/var/run/docker.sock -v /root/.m2:/root/.m2'
                }
            }
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Docker Build & Push') {
            steps {
                sh "docker build -t $IMAGE_NAME ."
                withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                    sh "docker push $IMAGE_NAME"
                }
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker rm -f todo-app || true'
                sh "docker run -d -p 8081:8080 --name todo-app $IMAGE_NAME"
            }
        }
    }
}
