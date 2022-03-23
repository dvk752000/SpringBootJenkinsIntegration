pipeline {
    agent any
    
    environment {
		DOCKERHUB_CREDENTIALS=credentials('dockerhub')
	}

    stages {
    
    	stage('Clean') {
            steps {
                
    			sh './gradlew clean'
            }
        }
    	
	    
        stage('Build') {
            steps {
                
    			sh './gradlew build'
            }
        }
        
        stage('Test') {
            steps {
                
    			sh './gradlew test'
            }
        }


        stage('Build Docker Image') {
            steps {
                sh 'docker build --build-arg  JAR_FILE=build/libs/*.jar -t ${imageName} .'
            }
        }
        
        stage('Login') {

			steps {
				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
			}
		}

		
		stage('Stop docker container'){
            steps{
                script{
                
                    def doc_containers = sh(returnStdout: true, script: "docker ps --filter name=${imageName} --format '{{.Names}}'").replaceAll("\n", " ") 
                    if (doc_containers) {
                        sh "docker stop ${doc_containers}"
                    }
                    
                }
            }
        }
		
		stage('Remove docker containers'){
            steps{
                script{
                    def doc_containers = sh(returnStdout: true, script: "docker ps --filter status=exited --filter name=${imageName} --format '{{.Names}}'").replaceAll("\n", " ") 
                    if (doc_containers) {
                        sh "docker rm ${doc_containers}"
                    }
                    
                    def doc_volumes = sh(returnStdout: true, script: "docker volume ls --filter name=${imageVolume} --format '{{.Name}}'").replaceAll("\n", " ")
                    if (doc_volumes) {
                    	sh "docker volume rm ${imageVolume}"
                    }
                }
            }
        }
        
		stage('Push and Run') {

			steps {
				sh 'docker image tag ${imageName} "$user"/${imageName}'
				
				sh 'docker push "$user"/${imageName}'
				
				sh 'docker volume create "${imageVolume}"'
				
				sh '''docker run -d  -p ${portToRun}:${portToRun} --name ${imageName} 
																	-v ${imageVolume}
																	--network jendoc 
																	-e spring.datasource.url=${hsqlSource}
																	-e dataToBeUpdated="${dataToBeUpdated}" 
																	"$user"/${imageName}'''.replaceAll("\n", " ") 
			}
		}
		
		stage('Update the Database'){
			steps{
				
				httpRequest "${httpUserName}:${httpPassword}@localhost:8081/locations"
				
			}
		}
    }
}