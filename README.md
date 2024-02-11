The API Documentation and the requirements for this challenge can be found here.
https://github.com/fetch-rewards/receipt-processor-challenge/

PreRequisite: Docker need to be installed and running in your machine.
Here, I have provided the docker image so that you can download it directly and run it in your machine.
Download Docker Image here:https://github.com/sainaveenbysani/Files
Download the Files_Main folder into local and Extract the folder. It will contain the receipt_docker.tar zip file.

Now you need to load the docker image from tar file by using any of the following commands that best suits your systems.
1) Open command prompt or terminal.
2) Navigate to the directory where the downloaded tar file located.
3) Run any one of the following commands to load the docker image to your local docker
   `docker load -i receipt_docker.tar`
    Or
   `docker import receipt_docker.tar`
   
5) It might take few seconds to complete the process. Once process is completed verify whether it is available in local images.
6) Use below command to check whether the docker image is available in your local images.
   `docker images`
7) Here you should see an image with name receipt_docker.
8) Lastly run the image in container with the below command, As of now the application server is using embedded Apache tomcat that will be intialized at port 8080. Make sure to run it only at port 8080.
   `docker run --publish 8080:8080 receipt-docker`
9) It should start a spring boot project and run in your local environment.
10) Test the API's using local host at port number 8080.

Sample Test results 
POST: ![image](https://github.com/sainaveenbysani/receipt_processor/assets/135251127/6afd8352-0227-4b8e-a606-99892d0d0f01)
GET : ![image](https://github.com/sainaveenbysani/receipt_processor/assets/135251127/72c51052-9c9b-4bf3-a82f-4a8d657aecda)




