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
Run the app with ```mvn spring-boot:run ``` and browse http://localhost:8080. You will see a ***Whitelabel Error Page***. This actually means Spring Boot is
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
* Check the app at ***localhost:4200*** .
#
* Go to  ***~/bike-company/bike-company-ui/src/app/app.component.html*** and edit the HTML.   
#
* Setup a proxy.   
The proxy will forward requests from the ***Angular*** server running on port ***4200***, 
to the ***Spring Boot*** server running on port ***8080***. To do that, create a new file for the proxy configuration: 
***~/bike-company/bike-company-ui/proxy.conf.json*** 
<br/>
The file says to the ng JavaScript server, that if it sees any requests that come into it, that begin with "/server", 
it should forward them on ***http://localhost:8080***.   <br/>
Now, we should tell the Angular server to use the new proxy server that we just set up.
Go to ***~/bike-company/bike-company-ui/proxy.conf.json*** and replace line ```"start": "ng serve",``` with 
```"start": "ng serve --proxy-config proxy.conf.json",```. <br/>
#
### To check everything work fine:
   * Open a new terminal and start your Spring Boot app:
```
cd ~/bike-company/bike-company 
kill -9 $(lsof -t -i:8080)
mvn spring-boot:run
```

   * Open a new terminal and start your Angular app:
```
cd ~/bike-company/bike-company-ui
kill -9 $(lsof -t -i:4200)
npm start
```
   * Brows ***http://localhost:4200/server/api/v1/bikes***. You should see all registered bikes.

#
* Add a services to your Angular app (this is easy to be done with Angular CLI). <br/>
In the folder ***bike-company-ui***, generate (***g*** is for generate) service with name ```bike-company``` and 
put everything in the ```services``` package: 
```
ng g service services/bike-company
```
Now we have ***~/bike-company/bike-company-ui/src/app/services/*** where a two new files are created. <br/>
Go to ***~/bike-company-ui/src/app/app.module.ts*** and import the new BikeCompanyService by adding 
```import { BikeCompanyService } from './services/bike-company.service';```. <br/>
Add providers array ```providers: [BikeCompanyService],```. <br/>
Import also ```import { HttpClientModule } from '@angular/common/http';```, it is the new HttpClientModule, which is 
rewritten in Angular 4, and it is what allows us to talk with calls to our server back-end. We should also add it to 
```
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
```   
#
* Go to ***bike-company.service.ts*** and add the two imports:
```
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
```
The HttpClient is responsible for making the calls out. Observable is going to return an observable, which is like 
a promise that will come back with the information from the server. To make the HttpClient usable in our code we are 
simply going to inject it into the BikeCompanyService class by adding it to the constructor 
parameter list ```constructor(private http:HttpClient)```. <br/>
Next let's create a function that will allow us to get all of the bikes from our database. So the service function name
will be ***getBikes()*** and it is going to return the payload from this following url ***/server/api/v1/bikes***. 
When we set up the proxy- anything that begins with ***/server/..*** is going to get proxied over to Spring Boot and 
then anything on the following path after ***/server/..***, will be the path that is used on the Spring Boot server. 
So we are just going to take the server and forward on ***/api/v1/bikes*** to Spring Boot. This will get returned to the
***getBikes()*** method and will be returned as an observable. 
#
* Set up some HTTP options as a constant by adding:
```
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}; 
```
#
* Add a components to your Angular app (this is easy to be done with Angular CLI). <br/>
In the folder ***bike-company-ui***, generate (***g*** is for generate) component with name ```admin``` and 
put everything in the ```components``` package: 
```
ng g component components/admin
```
Now we have ***~/bike-company/bike-company-ui/src/app/components/admin*** where a new files are created. <br/>
Go to ***admin.component.ts*** and import the new BikeCompanyService by adding 
```import { BikeCompanyService } from '../../services/bike-company.service';```. <br/>
To make the BikeCompanyService usable in our code we are going to inject it by adding it to the constructor 
parameter list ```constructor(private bikeCompanyService: BikeCompanyService)```. <br/>
#
* Create a public variable to hold all of the bikes that are returned from the back-end by adding ```public bikes;``. 
At this point we can now make a call over to the BikeCompanyService to retrieve all the bikes and store them in this 
public bikes variable. So let's add a new function to do that:
```
  getBikes() {
    this.bikeCompanyService.getBikes().subscribe(
      data => { this.bikes = data},
      err => console.error(err),
      () => console.log('bikes loaded')
    );
  }
```
What is happening in this function? We are going to call the bikeCompanyService that we have just created. We will call 
the getBikes() method, which returns an observable. We will subscribe to that. We can then either get the 
data ```data => { this.bike = data},``` or we will get an error back ```err => console.error(err),```. We can log out to 
the console that our bikes were loaded ```() => console.log('bikes loaded')```. And finally, this method is not going to get called
unless we tell the component to call it. Just by defining it doesn't mean that it will get called. We need to tell it
when and how to be called and we can do that in the ngOnInIt() function by adding ```this.getBikes();```. At this point 
the component should be wired up and ready to display the bikes. 
#
* Update the HTML to actually loop through all of the bikes and display them in HTML format. The HTML is located in 
***admin.component.html*** and a general basic HTML stub was put in place when we created this component, but we want to
 replace it with something that will display our bikes. So edit it. <br/>
 <br/>
 At this point we are ready to test the UI. We have wired in a component to the service, which talks to the back-end 
 through our proxy. The last thing we need to set up is the router so that we can navigate to it correctly from the browser.
#
* Setting up the Router. <br/> 
We need to set up the Angular routing portion of the application. When we specified the --routing switch when we created
this project, it created the ***~/bike-company-ui/src/app/app-routing.module.ts*** file. This is where your routes will 
go andconstants a defined routes for your application in ```const routes: Routes = [];``` . Creat the admin route which 
is consists of an admin path with the component, which is our admin component. 
```
{
path: 'admin',
component: AdminComponent
}
```
Import that as well by adding ```import { AdminComponent} from './components/admin/admin.component';```. <br/> 
Finally, to finish setting up the router properly you need to make sure that in the ***app.component.html*** file we have 
the router outlet set up. At the bottom of this file we will specify the router-outlet tag by adding
```<router-outlet></router-outlet>```. This sets up the basic app component as the layout or a template, which means that
we will always have the header ***Bike Registry System***, for all files or all URLs. <br/>
<br/>
To check everything work fine:
```
npm install --save rxjs-compat 
cd ~/bike-company/bike-company-ui
kill -9 $(lsof -t -i:4200)
npm start
```
Brows ***http:/localhost:4200/admin*** and you should see this: <br/>
![localhost:4200/admin](https://github.com/desi109/bike-company-app/blob/master/images/localhost:4200-admin.jpg?raw=true)

