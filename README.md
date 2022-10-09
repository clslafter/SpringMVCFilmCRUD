## Spring MVC Film C.R.U.D. Project


## Description of Project
This project utilizes a series of web forms to allow a user to perform CRUD (Create, Read, Update, Delete) functions in a SQL Relational Database with no coding experience. All logic is performed through a Java back end. The database is pre-populated with a series of films with a combination of varchar, int, set and enum properties forcing us to be deliberate in how the CRUD functions were handled. The user can add a new film, update a film, read all stored info about a film or delete a film. Each action will result in a confirmation or error page.


## Entity Description
There are a series of Web Forms the user will interact with. These forms are either HTML or JSP and operate in a loop either taking or delivering data to/from the user. All forms loop back to the main menu.

The Java portion of the project consists of a controller that connects the Database Accessor Object and the Web Forms. As the submit button is clicked, the controller handles the information and passes it to the DAO to update the database as required.

The Database Accessor Object and it's parent define the methods required to handle each of the CRUD operations.

There are also a Film and Actor class which define the fields of both Film and Actor as well as the setters and getters allowing for proper encapuslation.


## What We Learned
This was the first full-stack project in the Skill Distillery process. There were many challenges getting the Database Accessor, Controller and Web Forms to interact as intended. Ultimately there were a lot of "push ups" when it came to syntax but eventually we were able to create a fluid map of pages to allow the user to easily perform CRUD operations in a SQL database.

In addition to being the first full-stack project, this was also the first collaborative assignment. We learned how to push and pull to allow for simultaneous coding which reinforced the value of communication to avoid branch conflicts. 


## How To Run
This program can be downloaded an run on an Apache Tomcat server. However it requires a film database.
This program will also be uploaded to Amazon EC2. A link will be updated so it can be ran in the Cloud when available.
If downloaded, remember to refresh Gradle if errors occur.


## Technologies Used
Java, Apache Tomcat, SQL, Git, Gradle, MAMP/MySQL, HTML, Mac OS
