# 2024-DEV3-008-BerlinClock

# Description 
This project implements the Berlin Clock, representing the current time with illuminated colored blocks. It includes a backend API built with Spring Boot and a frontend UI developed with Angular 18.


## Requirements
- Java 17+
- Maven (for the backend)
- Node.js (for the frontend)

## Setup Instructions

### Backend
1. Navigate to the backend directory.
2. Build the application:
  ```bash
mvn clean install 
mvn spring-boot:run # to run the backend
```

### Frontend
1. Navigate to the frontend directory. 
2. Install the dependencies:
````bash
npm install
ng serve -o
````
Access the UI at http://localhost:4200.

### Testing with http client

Endpoint to Call: http://localhost:8080/berlinClock

- You can use the http directory in the project to able to make GET request to server. (optional)
- To be able to test the application, you should first start the backend and after wards you can create request
- Server will respond to your request string representation of the currentTime.