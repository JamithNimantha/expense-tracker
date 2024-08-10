# ExpenseTracker

## Overview
ExpenseTracker is a web application designed to help users track their expenses. It uses Spring Boot for the backend, Thymeleaf for the frontend, and H2 as the database.

## Features
- User authentication and authorization
- Expense tracking and categorization
- Reporting and analytics
- H2 database console for easy database management

## Technologies Used
- Spring Boot
- Thymeleaf
- H2 Database
- Hibernate
- HikariCP

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6.0 or higher

### Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/JamithNimantha/ExpenseTracker.git
   cd ExpenseTracker
   ```

2. Build the project using Maven:
   ```sh
   mvn clean install
   ```

3. Run the application:
   ```sh
   mvn spring-boot:run
   ```

### Configuration
The application can be configured using the `src/main/resources/application.properties` file. Key configuration properties include:

- \`spring.datasource.url\`: URL for the H2 database.
- \`spring.datasource.username\`: Database username.
- \`spring.datasource.password\`: Database password.
- \`server.port\`: Port on which the application runs.

### Accessing the Application
Once the application is running, you can access it at \`http://localhost:8080\`.

### H2 Console
The H2 database console can be accessed at \`http://localhost:8080/h2-console\`. Use the following credentials:
- **JDBC URL**: \`jdbc:h2:file:./data/expense_tracker\`
- **Username**: \`root\`
- **Password**: \`root\`
