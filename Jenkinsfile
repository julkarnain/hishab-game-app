pipeline {
    agent any
    tools {
        maven 'maven-3.5.0' 
    }
    environment {
        DATE = new Date().format('yy.M')
        TAG = "${DATE}.${BUILD_NUMBER}"
    }
    stages {
        stage ('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Docker Build') {
            steps {
                script {
                    docker.build("hishab/hishab-game-app:latest")
                }
            }
        }
	    stage('Pushing Docker Image to Dockerhub') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker_credential') {
                        docker.image("hishab/hishab-game-app:latest").push()
                        docker.image("hishab/hishab-game-app:latest").push("latest")
                    }
                }
            }
        }
        stage('Deploy'){
            steps {
                sh "docker stop hishab-game-app | true"
                sh "docker rm hishab-game-app | true"
                sh "docker run --name hishab-game-app -d -p 8000:8000 hishab/hishab-game-app:latest"
            }
        }
    }
}