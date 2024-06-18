
# Rent Office Management Website

## Overview

 - The office management website is built using Java and the Spring Framework.
 - The website includes the following technologies: Spring MVC, Spring Boot, Spring Security, Restful Web Service, jQuery, Bootstrap, Ajax, JSP, JPA, Spring Data JPA.

## Building Management Functions Include:
 1. Add, edit buildings with various fields, and delete one or multiple buildings as user requirements.
 2. Search buildings with multiple conditions and various fields.
 3. Assign buildings to management staff.
 4. Pagination and photo upload for buildings.
 5. Display all buildings for management, admin role. Staff roles only see the buildings assigned to them.
 - Uses the Builder design pattern in the task: Building search.

## Customer Management Functions Include: 
 1. Search for customers using various fields.
 2. Add, edit, delete customers.
 3. Assign customers to management staff.
 4. Pagination, display all customers for manager, admin roles, staff roles only see the customers assigned to them. 

## Employee Transaction Functions with Customers:
 1. Display transaction information.
 2. Manage transactions; one transaction can have multiple types of transactions, and management staff conduct transactions with customers.

## User Management Functions Include:
 1. Search for users.
 2. Add users by role (roles: manager or staff or admin).
 3. Update user information and passwords.

## Note
 1. In the building management function, the staff role is only allowed to view building information and cannot edit or delete building information.
 2. In the customer management function, the staff role is only allowed to view customer information and cannot edit or delete customer information.
 3. In the user management function, only the admin role can use this function when logged in.

## Installation
- Clone the repo to your machine:
> [https://github.com/Nqvinh7603/Rent-Office-Management.git](https://github.com/Nqvinh7603/Rent-Office-Management.git)
- IDE: Recommended Intellij IDEA.
- Install JDK 22 and Maven.
- Install MYSQL and create a database named `estateadvance`
- Edit the `application.properties`  file to configure the database, username, and password.
- Run the command `mvn clean install` to the build project
- Run the command `mvn spring-boot:run` to the start server
  
## Usage
 - Access the website at `http://localhost:8080` (depending on your setup).
 - Log in with an admin or staff account.
 - Admin can add, edit, delete buildings, customers, and users. Admin can also assign buildings and customers to management staff.
 - Management staff can view and update information on the buildings and customers assigned to them.
 
## DATABASE 
 - Uses MYSQL.
 - After cloning the project, go to the database package and import the .sql file into the database.

## Set up the path for the file upload function
 1. Navigate to the `application.properties` file and find the line `dir.default` to change the path.
 2. Navigate to the `BuildingService.java` file and find the line with `@Value` to change the path.
 - Suggested paths for different operating systems: Windơw ( `C://` ), Linux (`\home`)

## Demo
 - Link demo: https://rentofficevn.site/
 - Account: `usertest`.
 - Password: `123456`
## NGUYEN QUANG VINH   
### Not only try but try your best. good luck everyone !
