# Chat Application

A web-based chat application that allows users to send and receive messages in real-time using WebSockets. The app is built using Java Spring Boot for the backend.

# Steps To Run The Backend Using Docker

To run the backend using Docker, follow these steps:

<h3>Prerequisites</h3> 

+ **Docker**: Ensure Docker is installed and running on your machine. 

<h3>Steps To Run</h3> 

<h4> 1. Build the JAR File </h4>

You can generate the JAR file either using Maven or IntelliJ IDEA.

<h5>Option 1: Using Maven (Command Line)</h5>

If Maven is installed, run:

 `mvn clean package`

 This command creates a JAR file in the target directory.

<h5>Option 2: Using IntelliJ</h5>

+ Open your project in IntelliJ IDEA.
+   Open the Maven Panel:
   - Go to View > Tool Windows > Maven.
+ In the Maven panel, locate the Lifecycle section.
+ Cick on package.
+ The generated JAR file will be located in the `target/` directory.

<h4> 2. Build the Docker Image </h4>

Build the Docker image using the provided Dockerfile:

 `docker build -t chat-app-backend .`
 
<h4> 3. Run the Docker Container </h4>

 Run the Docker container:

  `docker run -p 8080:8080 chat-app-backend`

  Once the container is running, you can access the application at localhost:8080.
 


 


