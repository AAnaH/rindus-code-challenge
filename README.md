# 🌟 RINDUS CODE CHANLLENGE 🌟

This is a simple bidding agent implemented in Spring Boot. The agent accepts bid requests as JSON through HTTP and responds with a prediction from a logistic regression model stored in a Redis database.


## 📁 Project Structure

```plaintext
my-spring-boot-app
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── myapp
│   │   │               ├── MySpringBootApplication.java
│   │   │               ├── config
│   │   │               │   └── RedisConfig.java
│   │   │               └── controller
│   │   │                   ├── HelloWorldController.java
│   │   │                   └── BiddingController.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       ├── java
│       │   └── com
│       │       └── example
│       │           └── myapp
│       │               └── MySpringBootApplicationTests.java
│       └── resources
├── mvnw
├── mvnw.cmd
├── pom.xml
├── redis-init.sh
└── README.md
```

## 📋 Requirements

To run and test this application, you need to have the following installed:

1. **Java Development Kit (JDK)**: Version 8 or higher (Java 11 or Java 17 recommended).
2. **Apache Maven**: Version 3.6.0 or higher.
3. **Redis Server**: Ensure that Redis is installed and running on your local machine or accessible from your application.

### Setting Up Redis

1. **Install Redis**: Follow the instructions on the [Redis website](https://redis.io/download) to install Redis on your machine.
2. **Start Redis Server**: Run the Redis server using the command:
   ```sh
   redis-server
   ```
3. **Verify Redis Installation**: You can verify that Redis is running by connecting to it using the Redis CLI:
   ```sh
   redis-cli
   ```
   Then, run the `ping` command. You should receive a `PONG` response.

## 🚀 How to Run

1. Ensure you have Java and Maven installed.
2. Navigate to the project directory.
3. Run the application using the command:
   ```sh
   ./mvnw spring-boot:run
   ```
4. Access the application at [http://localhost:8080/hello](http://localhost:8080/hello) to see the "Hello, World!" message.
7. Make a bid request: 
   ```sh
   curl -X POST -H "Content-Type: application/json" -d '{
     "deviceExtBrowser": "Firefox",
     "bannerExtSize": "300x250",
     "deviceLanguage": "de",
     "deviceExtType": "tablet"
   }' http://localhost:8080/bid
   ```

## 📦 Dependencies

This project uses Spring Boot and its dependencies for building web applications. The dependencies are managed in the `pom.xml` file.

## 🧪 Testing

### Unit Tests
Test cases are included in the `src/test` directory to ensure the application context loads correctly. You can run the tests using:
```sh
./mvnw test
```
### Integration Tests
Integration tests have been added to verify the functionality of the application with a Redis server in memory. This is useful in case you cannot test with a local Redis server.

## 🛠️ Obstacles and Solutions

### Obstacles

1. **Calculating Logistic Regression**: Implementing the logistic regression calculation required careful handling of coefficients and bias.
2. **Mathematical Context of Regression**: Understanding the mathematical context of the requested regression and the lack of context in this field, as it is not an area of expertise, took additional time.
3. **Time Constraints**: Due to time constraints, I was unable to implement more exception handling. I have included one example of necessary exception handling in a project. With more time, I would have added validation and DTO layers. In a professional integration, a security layer should also be added.
4. **Setting Up Local Redis**: Setting up a local Redis server took more time than expected, so I used an embedded Redis server for testing purposes.

### Solutions

1. **Logistic Regression**: Implemented a helper method to calculate the logistic function and ensured coefficients were correctly retrieved from Redis.
2. **Mathematical Context of Regression**: Researched and studied the basics of logistic regression to understand the requirements and implement the solution correctly.
3. **Time Constraints**: Focused on implementing core functionality and provided an example of exception handling to demonstrate the approach.
4. **Embedded Redis**: Used an embedded Redis server to facilitate testing and ensure that the application could be tested without relying on a local Redis server.