node{
    stage("Git CheckOut"){
        git url: 'https://https://github.com/shekar55/SpringBoot-Rest1.git',branch: 'master'
    }
    
    stage(" Maven Clean Package"){
      def mavenHome =  tool name: "Maven-3.6.1", type: "maven"
      def mavenCMD = "${mavenHome}/bin/mvn"
      sh "${mavenCMD} clean package"
    } 
    
    stage("Build Dokcer Image") {
         sh "docker build -t chandu5562/docker-spring-rest ."
    }
    
    stage("Docker Push"){
        withCredentials([string(credentialsId: 'Docker_Hub_Pwd', variable: 'Docker_Hub_Pwd')]) {
          sh "docker login -u chandu5562 -p ${Docker_Hub_Pwd}"
        }
        sh "docker push chandu5562/docker-spring-rest"
        
    }
    
    // Remove local image in Jenkins Server
    stage("Remove Local Image"){
        sh "docker rmi -f chandu5562/docker-spring-rest"
    }
    
    stage("Deploy to docker swarm cluster"){
        sshagent(['Docker_Swarm_Manager_Dev']) {
		    sh 'scp -o StrictHostKeyChecking=no  docker-compose.yml ubuntu@172.31.40.71:'
		    //sh 'ssh -o StrictHostKeyChecking=no ubuntu@172.31.40.71 docker stack rm springboot'
                    sh 'ssh -o StrictHostKeyChecking=no ubuntu@172.31.40.71 docker stack deploy --prune --compose-file docker-compose.yml springboot'
        }
    }
}
