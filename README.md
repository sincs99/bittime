# BitTime Timerecording Tool


## Usage


How to import and start the Recording Tool
----------------------------------

Note: Use IntelliJ for easiest way to use.

- Clone or Import the Project 
- Link for gitlab repo: https://gitlab.fhnw.ch/pascal.konezciny/bittime

IntelliJ:
*******************************************************************************************************************************************************
- 	Import Project
- 	Make sure you have all the needed Framworks installed.
- 	Please see all used Frameworks under the Task frameworks
- 	The Project is tested with jdk 11 other may not work.
- 	We have found some Problems with the lombok Framework - make sure you have installed the Lombok Frame work and its runing in the engine
- 	Build the Project and run it.
*******************************************************************************************************************************************************

Spring Tool Suite 4
*******************************************************************************************************************************************************
- 	Import Project
- 	Make sure you have all the needed Framworks installed.
- 	Please see all used Frameworks under the Task frameworks 
- 	The Project is tested with jdk 11 other may not work.
- 	With STS4 we had some trouble with Lombok. You have to install Lombok manually to 
	the STS4 please follow this Link: https://projectlombok.org/setup/eclipse
-	Build the Project and run it.
*******************************************************************************************************************************************************

- Open a incognito Google Chrome Tab and open http://127.0.0.1:8080/
- You will now land on the Loginpage.


```html

Admin Login:
Username: Admin
PW: 12345

User Login:
Username: user
PW: user
```

Have fun!


Please Note that we still have a Bug -> Do not delete a User with a Vacation, Sick or Timerecording entry. 
Users without an DB entry can get deleted without Problems.


##Framework
----------------------------------

>> spring-boot-starter-data-jpa
>> spring-boot-starter-mail
>> spring-boot-starter-security
>> spring-boot-starter-web
>> spring-boot-starter-thymeleaf
>> mysql-connector-java
>> org.projectlombok
>> spring-boot-starter-test
>> org.springframework.security
>> spring-boot-maven-plugin

----------------------------------

##Developer
----------------------------------

Pascal Konezciny	pascal.konezciny@students.fhnw.ch
André Kaufmann		andre.kaufmann@students.fhnw.ch
Dominic Richner		dominic.richner@students.fhnw.ch
Robin Weis		    robin.weis@students.fhnw.ch

----------------------------------

## License
[MIT](https://choosealicense.com/licenses/mit/)
