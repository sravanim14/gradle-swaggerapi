node {

   stage ('Checkout') {
    checkout scm
   }

   stage('SonarQube analysis') {
	 if (isUnix()) {
      sh './gradlew sonarqube -Dsonar.host.url=http://localhost:9000'
     } 
	 else {
      bat './gradlew sonarqube -Dsonar.host.url=http://localhost:9000'
     }
   }
	
   stage ('Build') {  
    if (isUnix()) {
      sh './gradlew clean build' 
    } 
	else {
      bat './gradlew.bat clean build'
    }
   }
   
   stage ('Nexus Upload') {
    nexusArtifactUploader(
      nexusVersion: 'nexus3',
      protocol: 'http',
      nexusUrl: 'localhost:8081',
      groupId: 'hello',
      version: '0.0.1-SNAPSHOT',
      repository: 'gradle-springboot-repo',
      credentialsId: 'nexus-admin-creds',
      artifacts: [
        [artifactId: 'springbootapp-pipeline',
         classifier: '',
         file: 'build/libs/springbootapp-pipeline-0.0.1-SNAPSHOT.jar',
         type: 'jar']
      ]
    )  
   }
   
   stage ('Docker Build') {
    if (isUnix()) {
      sh 'docker build -t sravanimadireddy/springboot-restapi:latest .'
    } 
    else {
      bat 'docker build -t sravanimadireddy/springboot-restapi:latest .'
    }
   }
   
   stage('Publish') {
        withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) 
		{
		  bat "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
          bat 'docker push sravanimadireddy/springboot-restapi:latest'
        }
   }
   
   stage('Deploy Application') {
        bat "set KUBECONFIG=\"C:\\Users\\VenkataSatyaSravaniM\\.kube\\config\" & kubectl config set-context docker-for-desktop-cluster & kubectl create -f kub-deploy-files/mongo-service.yaml"
		bat "set KUBECONFIG=\"C:\\Users/VenkataSatyaSravaniM\\.kube\\config\" & kubectl config set-context docker-for-desktop-cluster & kubectl create -f kub-deploy-files/mongo-controller.yaml"
	    bat "set KUBECONFIG=\"C:\\Users\\VenkataSatyaSravaniM\\.kube\\config\" & kubectl config set-context docker-for-desktop-cluster & kubectl create -f kub-deploy-files/deployment.yaml"
	    bat "set KUBECONFIG=\"C:\\Users\\VenkataSatyaSravaniM\\.kube\\config\" & kubectl config set-context docker-for-desktop-cluster & kubectl create -f kub-deploy-files/service.yaml"
		bat "set KUBECONFIG=\"C:\\Users\\VenkataSatyaSravaniM\\.kube\\config\" & kubectl config set-context docker-for-desktop-cluster & kubectl get services/spring-boot-service -o go-template=\"{{(index .spec.ports 0).nodePort}}\""
		/*bat set PORT=kubectl get services/spring-boot-service -o go-template="{{(index .spec.ports 0).nodePort}}"
	    bat "powershell.exe \$Portvalue=Get-Variable PORT -ValueOnly"
        bat "powershell.exe \$message="My services are running on http://127.0.0.1:" + \$Portvalue"
        bat "\$message"*/
   }
}