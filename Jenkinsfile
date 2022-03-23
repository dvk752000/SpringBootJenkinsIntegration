pipeline {
    agent any
    
    environment {
		DOCKERHUB_CREDENTIALS=credentials('dockerhub')
		HTTP_CREDENTIALS=credentials('jenkinssbCredentials')
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
				
				sh '''docker run -d  -p 8081:8081 --name ${imageName} 
												-v ${imageVolume} 
												--network jendoc 
												-e spring.datasource.url=${hsqlSource} 
												-e dataToBeUpdated="${dataToBeUpdated}" 
												"$user"/${imageName}'''.replaceAll("\n", " ") 
				sh 'sleep 5'
			}
		}
		
		stage('Update the Database'){
			steps{
				script{
					def isURLRunning = sh(script: "curl -s --retry-connrefused --retry 10 --retry-delay 20 http://192.168.0.101:8081/locations || true", returnStdout: true).trim()
					def response = httpRequest authentication: 'jenkinssbCredentials', url: "http://192.168.0.101:8081/locationsUpdate/${primaryKey}/${updatedValue}"
				}
			}
		}
    }
}

/*
def executeHttpGet(apiUrl, token){
    echo "Executing GitHub API Call, ${apiUrl}"
    def response = httpRequest url: apiUrl, authentication: "${token}"
    if (response.status != 200) {
        echo "API call failed, ${apiUrl}"
        error("Unable to execute API call, StatusCode=${response.status}, Content=${response.content}")
    } else {
        echo "API call success, ${apiUrl}"
        return response
    }
}
*/