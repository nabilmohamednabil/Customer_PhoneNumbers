# Customer_PhoneNumbers
 implementation of a basic business logic related to the validation of international phone numbers.

- to boot up project :

1- Eclipse  --> java build path --> Alternate JRE --> we use "JDK 1.8.0_311"

2- Eclipse  -->  java compiler --> 1.8

3- Maven  --> 	pom.xml file --> <java.version>8</java.version>

4- local windows machine --> 

java -version
java version "1.8.0_311"
Java(TM) SE Runtime Environment (build 1.8.0_311-b11)
Java HotSpot(TM) 64-Bit Server VM (build 25.311-b11, mixed mode)

5- download project from GitHub 

6-using windows power shell --> 
   1- cd <project path>        , example --> cd E:\jumis\customer
   2- docker build -t exercise-sample  .    , this to create an docker image from my docker file 
   3- netstat -ano | findstr 8080           , make sure no app using port 8080
   4- docker run -p 8080:8080 -t exercise-sample        , run the docker image on port 8080
   5- docker ps       , show the running docker conatiner based on our image 
      
     example :

         PS C:\WINDOWS\system32> docker ps
                 CONTAINER ID   IMAGE             COMMAND                  CREATED         STATUS         PORTS                    NAMES
             9eddac8b2278   exercise-sample   "java -jar customer.…"   3 minutes ago   Up 2 minutes   0.0.0.0:8080->8080/tcp   busy_matsumoto

7- open Google chrome 
8- Application Endpoints are below :

Endpoint without pagination   --->  http://localhost:8080/customers/list  
Endpoint with pagination   ----> http://localhost:8080/customers/list/page/1



