# 🌎🌍🌏 __COVID INCIDENCE SERVICE__ 🦠😷📝
## A digital version of the classic 2 player game with the same name [(wiki/Connect_Four)](https://en.wikipedia.org/wiki/Connect_Four). Learning project in Java.
___

![GitHub contributors](https://img.shields.io/github/contributors/DRBondyaleJuez/CovidIncidenceService)
![GitHub repo size](https://img.shields.io/github/repo-size/DRBondyaleJuez/CovidIncidenceService)
___

## __DESCRIPTION__
This code was used as a training exercise to practice coding in java and spring.

To develop this simple service this code uses [Spring Boot](https://spring.io/projects/spring-boot). It has only 3 endpoints 1 get and 2 posts.
The persistence is for now a placeholder and only stores the information in memory while running. an improved performance
using a database would be useful however the purpose was to build a service using spring.

The service allows clients to create and update city information regarding name, country and population and also report the number of infected people at a particular date.
This data will be stored in the form of incidence rate at that particular date. Then the client can also retrieve  
incidence rate information for a particular city and particular date interval if there is any information for the requested city.


<div style="text-align: center;">

![Diagram](https://user-images.githubusercontent.com/98281752/229177577-cca645d0-9bf1-4e34-8476-4ecb02684b37.png)

</div>

___
___

## __USAGE__
This is a simple service designed based on exercise to train and showcase java and spring capabilities. The service has the following endpoints:

### _EndPoints:_
<!-- OL -->
- #### _/requestCityIncidence_

> Here a __GET__ method can be called providing the parameters city name, country and initial and final date to retrieve the incidence rate 
values in that city at a particular date interval.

- #### _/updateCityIncidence_

>  Here a __POST__ method can be called providing a PostCityIncidenceRequest object which basically contains city name, country, date and number 
of infected people the client is reporting. This will be then converted to incidence rate using the city's population.

- #### _/updateCityInfo_

> Here a __POST__ method can be called providing a PostCityInfoRequest
 
- #### _/swagger-ui/index.html_

> Here the API documentation can be consulted in a swagger format

The responses of all these endpoints will depend on the status of the action of the request mainly returning 200 status, but also it is
coded to return 202 when a post is accepted and awaiting review, 204 if everything in the request is correct but there aren't 
any results, 400 in case the date or city have not been properly chosen and 404 when the city is absent from the database.

For further comprehension of the service you can consult this [API Documentation](https://app.swaggerhub.com/apis-docs/DANFL4_1/CovidIncidenceService/1.0.0) formatted using [Swagger](https://swagger.io/) editor.


<div style="text-align: center;">

![API Doc image](https://user-images.githubusercontent.com/98281752/229496766-6d0f8b2a-5667-43ba-8cc5-da9a174be474.png)

</div>

___
___

## __INSTALLATION INSTRUCTIONS__
### __For IDE:__
<!-- OL -->
1. Clone the repository in your local server
2. Run the project's Main Class in your IDE

### __For Ubuntu (In terminal):__
<!-- OL -->
1. If necessary [install java version 11 or higher](https://stackoverflow.com/questions/52504825/how-to-install-jdk-11-under-ubuntu)

    ```bash
        sudo apt-get install openjdk-11-jdk
    ```


2. If necessary [install maven version 3.6.3 or higher](https://phoenixnap.com/kb/install-maven-on-ubuntu)

   ```bash 
       sudo apt install maven
   ``` 

3. If necessary [install git](https://www.digitalocean.com/community/tutorials/how-to-install-git-on-ubuntu-20-04)

   ```bash 
       apt install git
   ```

4. Clone the repository

   ```bash 
       git clone https://github.com/DRBondyaleJuez/CovidIncidenceService.git
   ```

5. Go to the project folder. Make sure the [pom.xml](https://maven.apache.org/guides/introduction/introduction-to-the-pom.html) is there.

6.  Create [.jar file](https://en.wikipedia.org/wiki/JAR_(file_format)) executable in target folder using the following code:

    ```bash
		mvn install 
    ```

7. This code uses javafxml so we recommend the use of the following code  to run the program :

   ([*Source*](https://github.com/openjfx/javafx-maven-plugin))

   ```bash 
       mvn javafx:run
   ```

___
___
## __INSTRUCTIONS FOR CONTRIBUTORS__
The objective of the project was to practice and apply java knowledge. No further contributions will be needed all of this is just a training excercise.

Hope you may find the code useful and please acknowledge its origin and authorship if used for any other purpose.