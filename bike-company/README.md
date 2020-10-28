# Building Web App:  Spring Boot + PostgreSQL + Angular

### Overview
This is cheat sheet about developing a full-stack application from scratch by using Spring Boot, PostgreSQL (as a database) 
and Angular.  


#
#### Basic Steps: 
**Note:** You could start with the *user interface* or your *database* or the *server code*. There's no right or wrong choice. 
   1) build out the Spring Boot server
   2) build up the persistence tier and set up the database
   3) build the Angular front end
 
    
#
#### App Description
Create a registry system for Bike Company that can keep track of all of the users' purchases. When a customer purchases
a bike from the company, they can come to this website and enter their purchase details on this form and complete their 
bike registration to enable their warranty. 

> ***Registry form:***  
> * buyer name
> * email
> * phone number
> * model of bike
> * bike serial number
> * purchase price 
> * purchase date 
> * boolean contact that says whether we can contact that person or not
>  
> **Submit** button  &#8594;  after it is clicked, the form will clear itself and there will pop up a success message at 
 the top that will say the registration has been submitted.  
 
 That will be the end user portion of the system. There will also be an admin section of the system, for company employees
 to see all of the registrations. Employees will be able to access the url "**/admin**" and the app will prompt with 
 a login. Once the employee enters their credential, they will get to the admin screen and it will show all of the 
 different bikes that have been registered.  
 
> ***Login + Admin screen:***  
> * all registered bikes and the information about them
 
 
#
#### Prerequisites  
**Note:**  I use Linux operating system
* Java 11  
* Maven 3.6.3 
* Postman - to help you test and try out your REST API endpoints   <br/><br/>


---  
---  

# Creating a Spring Boot App
Spring initializer: https://start.spring.io  
Terminal command: 
```
spring init -l=java -g=com.dmilusheva -a=bikecompany -n=bikecompany --description="Bike company application" --package-name=com.dmilusheva.bikecompany -d=web,jpa,postgresql --build=maven -p=jar -j=11
```
Run the app and browse http://localhost:8080. You will see a ***Whitelabel Error Page***. This actually means Spring Boot is
up and running, we just haven't defined any root or home page that it can respond to.  


#
## Adding a Basic Model
* Create a new package: ***package com.dmilusheva.bikecompany.models***   
* Create a new class: ***Bike.java***  
* Add the following attributes: ***name, an email, a phone, a model, and a serial number, a purchase price, 
purchase date and contact*** 
* Add getters and setters for this class. 


#
# Creating a REST Controller
* Create a REST controller that will serve out some bike information via an API.
* Create an API endpoint to pass the bike information around. 
* Create a new package:  ***package com.dmilusheva.bikecompany.controllers***  
* Create a new class: ***BikesController.java*** 
* Add annotations: ***@RestController***  and ***@RequestMapping***   
BikesController is a Spring MVC controller so we should add a couple of annotations so that Spring will pick it up 
and process it correctly.   


To test out our endpoints we simply need to start up our Spring Boot server and then make a call to the endpoints. 
Browse ****http://localhost:8080/api/v1/bikes**** or open Postman set method to ***GET*** and url to 
***localhost:8080/api/v1/bikes*** and you will see empty JSON array ***[]***. That means that our Spring Boot controller
is working. We're simply returning an empty array, which when it comes to JSON is the square brackets.   <br/><br/>


---  
---  
<br>

#### Adding a Persistence Layer
* Turn the basic Bike object into a JPA entity that can be persisted to a database. Once persistence is added, 
the basic server API portion of the app will be complete.
* We need the ability to save bike registrations and in order to do that we'll need a database. We could pick just 
about any database out there, but I am going to use PostgreSQL. Once the database is installed, we can then add 
persistent support to the Spring Boot app. Spring provides a great library called Spring Data JPA that enhances the 
basic JPA object relational persistence framework. We'll be using this in our application. We can then convert the bike
basic java model class that we have already created and turn that into a JPA entity persistable class. 
* Add a Spring Data JPA repository and this will act as a data access layer for the Bike entity.   


<br/>

## Setting up the Database
Check: [***PostgreSQL (install and setup)***](https://github.com/desi109/linux-cheat-sheet/blob/master/spring_boot_cmds)

> I have ***user1234***, so I just create a new ***bike_company*** database, which is going to be owned by ***user1234*** :
>      
> ```  
> sudo -u postgres createdb bike_company -O user1234
> sudo service postgresql restart
> psql -U user1234 -d bike_company -W
>      Password: 1234
>      psql (12.4 (Ubuntu 12.4-1))
>      Type "help" for help.
>       
>      bike_company=> \q    
>  ```
> 
> Let's create tables and insert data from existing .sql files. The files are into ***db_scripts***:
> ```
>  cd ~/bike-company/db_scripts
>  psql -U user1234 -d bike_company -f create_tables.sql
>  psql -U user1234 -d bike_company -f insert_data.sql
> ```
> Note: To see the app in browser: ****http://localhost:8080/api/v1/bikes****

 
Check:  [***pgAdmin4 (install and setup)***](https://github.com/desi109/linux-cheat-sheet/blob/master/spring_boot_cmds)
>To start pgAdmin4: 
>``` 
> cd ~/pgAdmin4/pgAdmin4/
> source bin/activate
> python lib/python3.8/site-packages/pgadmin4/pgAdmin4.py
>```
> Now, go to:   ***http://localhost:5050***    
    
<br/>
   
#### Configuring Persistence Properties
* The application still does not know how to connect to the database. Go to ***src/main/resources*** folder and open
***application.properties*** file. This file is typically used for any kind of configuration in the Spring Boot 
environment and most of the libraries that Spring Boot integrates with, have a lot of customized configurable options. 
* Add properties.  

#
# Creating a JPA Entity
* Annotate ***Bike.java*** model class as an actual entity - ***@Entity***
* Create a new attribute called ***id***, which represents the primary key id for this class and I'm going to set it to
 the Long data type. Add getter and setter. Add annotations ***@Id*** and ***@GeneratedValue***.   
 
 
# 
# Creating a JpaRepository
The JPA repository in Spring Data JPA acts as a data access object (DAO).
* Create an API endpoint to pass the bike information around. 
* Create a new package:  ***package com.dmilusheva.bikecompany.repositories***  
* Create a new interface: ***BikeRepository.java***  

<br/>

#### Test the API
In Postman we can test the API all the way down to the database layer by calling the endpoints. 
* Open Postman.
* Set the HTTP verb to ***GET*** 
* Call ***http://localhost:8080/api/v1/bikes***. This endpoint should list all of the bikes in our system. 
* Click ***Send***, the output should be the three records that we loaded when we created the PostgreSQL database. 
* To get a specific bike, call ***http://localhost:8080/api/v1/bikes/1***     <br/><br/>
                                                                           
                                                                           
---  
---  
                                                                           
# Creating the Angular Project
  
* Install ***Node.js***.   
```
sudo apt update && sudo apt install npm
```   
Simply, ***Node*** is a JavaScript server.   
NPM - Node Package Manager  
To check npm version:  
```
npm --version
```    
#  
   
* Install ***Yarn***. It is also a package manager as NPM.
```
sudo apt update &&  sudo npm install --global yarn
```  
To check yarn version:  
```
yarn --version
```   
#
  
* Install ***Angular CLI***
```
sudo npm install -g @angular/cli
```  
To check if Angular CLI is available:  
```
ng -v
``` 
Set Angular CLI to use the Yarn tool as the main dependency manager:  
```
sudo ng config -g cli.packageManager yarn
``` 
#
   
* Generate a new Angular app.    
The project structure should look like this:
```
bike-company                    
   └── bike-company       # Spring Boot App   
   └── bike-company-ui    # Angular App
```    

Create Angular bike-company-ui app, as a stand-alone project. 
```
sudo ng new bike-company-ui -routing
sudo chmod -R 777 bike-company-ui/
```  
    
**Note:** If you have a problem with the last command, try to do next two steps and run the command again.
```
npm cache clean --force
npm i -g @angular/cli
```   
   
    
To check if every thing is ok:  
```
cd bike-company-ui
ng -v
```     
#
   
   
* Start up the app:  
```
npm start
```  
 ***OR***  
```
ng serve
```    
#

* Go to  ***~/bike-company/bike-company-ui/src/app/app.component.html*** and edit the HTML.

* Setup a proxy
* Add a service and components

