The API Documentation and the requirements for this challenge can be found here.
https://github.com/fetch-rewards/receipt-processor-challenge/

PreRequisite: Docker need to installed and set up in your machine.
Here, I am providing the docker image 
you can download it from this link

Once it is downloaded you need to load the docker image by using any of the following commands that best suits your systems.
1) Open command prompt or terminal
2) Navigate to the directory where the tar file located
3) Run any one of the following commands
   docker load -i sai_docker.tar

docker import sai_docker.tar

docker load < sai_docker.tar

4) It might take few seconds to complete the process. Once process is completed verify whether it is available in local images.
   docker images
5) Here you should see an image with name sai-docker.
6) Lastly run the image in container with the below command
   docker run --publish 8080:8080 sai-docker
7) It should start a spring boot project and run in your local environment.

Sample Test results 
POST: ![image](https://github.com/sainaveenbysani/receipt_processor/assets/135251127/6afd8352-0227-4b8e-a606-99892d0d0f01)
GET : ![image](https://github.com/sainaveenbysani/receipt_processor/assets/135251127/72c51052-9c9b-4bf3-a82f-4a8d657aecda)




