pipeline 
{
    agent none
    stages 
    {
        stage('SCM Poll') 
        {
            agent any
            steps 
            {  	
				sh '''ls -la'''
				sh '''pwd'''
				sh '''ssh \'318356@10.10.196.130\' docker volume create volDbManager'''
				
               echo "********Waiting for SCM event - IN PROGRESS***********"
                /*
				script 
				{
				    properties([pipelineTriggers([pollSCM('* * * * *')])])
				}
				echo "Waiting for SCM event - Done"
				git branch: 'master', credentialsId: 'nandu', url: 'https://github.com/nandunarayanan/Android_Demo.git'
				*/
				
				/*
				
				checkout([$class: 'GitSCM', 
				branches: [[name: "origin/master"]], 
				userRemoteConfigs: [[
                url: 'https://github.com/nandunarayanan/Android_Demo.git']]
				])
				*/
				echo "Testing for SCM event - Done"
				echo "************copying the git update to the volume- IN PROGRESS***********"
				sh '''ssh \'318356@10.10.196.130\' /home/318356/copy_db.sh'''
				echo  "************copying the git update to the volume - DONE ****************"
				sh '''ls -la'''
				sh '''pwd'''
				
            }
        }
        
        stage('DbManager')
        {   
            agent any  
            steps
            {
                sh '''ssh \'318356@10.10.196.130\' docker pull localhost:5000/db_manager:v3'''
                sh '''ssh \'318356@10.10.196.130\' docker run --name DbManager -p 27017 -v volDbManager:/src localhost:5000/db_manager:v3'''
            }
        }
        
        stage('Removal of DbManager container and volume')
        {   
            agent any
            steps
            {
                sh '''ssh \'318356@10.10.196.130\' docker stop DbManager'''
                sh '''ssh \'318356@10.10.196.130\' docker rm DbManager'''
                sh '''ssh \'318356@10.10.196.130\' docker volume rm -f volDbManager'''
            }
        }
    
    }
}
