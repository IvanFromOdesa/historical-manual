# historical-manual
This is my project for team paper. Used technologies: Spring Boot, Thymeleaf, PostgreSQL, Spring Security, Spring Data JPA. Also used some minor tools like Maven, Bootstrap to increase perfomance efficiency. Gained expereince on: SQL, Spring Boot, Spring Data JPA, Hibernate, PostgreSQL, HTML, CSS, Thymeleaf, Bootstrap, Spring Security.

This is a web-app for working with 10 related to the subject area (History) database tables in a RDBMS. The architectural pattern is MVC (Java POJO's; HTML web pages; Repository/Service/Controller layers that manage the flow of the app).
Esentially, the interaction with the client is achieved by RESTful architecture: 
1. The front-end that is built on HTML5, CSS and native JS and uses Thymeleaf as an engine. The process of sending requests is stateless. The client sends a request from here.
2. The back-end that is built on Java 17 within the Spring Boot Framework. Here the actual 'logic' is presented. The server handles the request.
3. The back-end interaction with the RDBMS (PostgreSQL) that is achieved by Hibernate. The server fetches the data from here and also is capable of changing the db state with any modifying query.

In this web-app I have implemented the simple concept of having 3 types of a client:
1. User - the user can view and add the new content to the tables.
2. Editor - the editor can view, add and edit the contents.
3. Admin - the admin can view, add, edit and delete the contents.

There is also an option to perform different custom queries, rather than CRUD. Every type of client is capable to perform any query.

How server knows which type of client is using the web-app? An authentication and authorization system is implemeted using Spring Security.
It is simply achieved by Form-Login type of auth (which lets the user to logout in contrast to Basic Auth).
For simplicity reasons, the csrf protection is also disabled.
The credentials are stored in distinct db tables and then are fetched by the server when needed.

Worth noticing the implementation of PDF exports from the web pages. It was achieved using an external library [librepdf](https://github.com/LibrePDF).

This is how it all looks in action:

# 1. Authentication

![image](https://user-images.githubusercontent.com/103036130/180002773-01fa9932-f44a-4b3e-a32a-69a7c471356d.png)

# 2. Main menu

![image](https://user-images.githubusercontent.com/103036130/180003140-093e09d3-251a-45b4-a8a7-55ea28dbf682.png)

# 3. Authorization

## User:

![image](https://user-images.githubusercontent.com/103036130/180003308-5fcec66e-bb37-4d3f-a7a6-4a5b60a060c0.png)

## Editor:

![image](https://user-images.githubusercontent.com/103036130/180003579-a0f27904-83d0-4902-82de-52cf721c1b47.png)

## Admin:

![image](https://user-images.githubusercontent.com/103036130/180003685-b21c7c62-090a-4619-866d-ed8466958209.png)

# 4. The simple request of searching the total num of countries where given languages are spoken: 

![image](https://user-images.githubusercontent.com/103036130/180004658-af145f14-fc98-48c4-9ad6-5f3b1adbb3aa.png)

# 5. And we can export this table as PDF document:

![image](https://user-images.githubusercontent.com/103036130/180004867-121860c7-0a39-4584-95cd-ae892f4794d1.png)

Feel free to explore the project!


