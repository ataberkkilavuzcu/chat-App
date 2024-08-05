Chat Application
Welcome to the Chat Application! This project demonstrates a simple chat application built with Spring Boot, Kafka, and Thymeleaf. The application showcases the use of Domain-Driven Design (DDD) principles, microservices architecture, and Kafka for messaging.

Table of Contents
Features
Technologies Used
Architecture
Setup Instructions
Usage
Contributing
License
Features
Real-time Messaging: Users can send and receive messages in real-time.
Online Users: Displays a list of currently online users.
Join and Leave Notifications: Notifies when users join or leave the chat.
In-memory Session Storage: Stores session data in memory for simplicity.
Microservices Architecture: Modular design using microservices principles.
Kafka Integration: Utilizes Kafka for efficient message streaming and processing.
Technologies Used
Spring Boot: Backend framework for Java.
Kafka: Distributed messaging system.
Thymeleaf: Template engine for rendering dynamic web pages.
Java: Programming language.
Docker: Containerization platform.
Postman: API testing tool.
Virtual Machine (Ubuntu): Development environment.
Architecture
The Chat Application follows Domain-Driven Design (DDD) principles and is structured into several packages:

Controller: Handles HTTP requests and responses.
Service: Contains business logic.
Model (Entity): Represents the application's data and domain objects.
Workflow
User Joins: A user provides a username and joins the chat.
Message Sending: Users send messages that are processed and stored by Kafka.
Message Receiving: Messages are consumed from Kafka and displayed to all connected users.
User Leaves: Users can sign out, and the online users list is updated accordingly.
Setup Instructions
Clone the Repository:

bash
Kodu kopyala
git clone https://github.com/yourusername/chat-app.git
cd chat-app
Set Up Kafka:

Ensure Kafka and Zookeeper are running. You can use Docker for this purpose.
Build and Run the Application:

bash
Kodu kopyala
./mvnw spring-boot:run
Access the Application:

Open your web browser and navigate to http://localhost:8080/join.
Usage
Join the Chat:

Enter a username and click "Join" to enter the chat room.
Send a Message:

Type a message in the input field and click "Send".
View Online Users:

Online users are displayed on the right side of the chat room.
Sign Out:

Click the "Sign Out" button to leave the chat.
Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes. Ensure your code follows the project's coding standards and includes appropriate tests.
