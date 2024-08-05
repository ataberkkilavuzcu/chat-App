# chat-App

Welcome to the chat-App! This project demonstrates a simple chat application built with Spring Boot, Kafka, and Thymeleaf.
![image](https://github.com/user-attachments/assets/52d983f1-df85-455b-9683-80b8138bfda8)
![image](https://github.com/user-attachments/assets/9d330c73-b80d-401e-83c1-f0fc5123391a)

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup Instructions](#setup-instructions)
- [Usage](#usage)
- [License](#license)

## Features

- **Real-time Messaging**: Users can send and receive messages in real-time.
- **Online Users**: Displays a list of currently online users.
- **Join and Leave Notifications**: Notifies when users join or leave the chat.
- **In-memory Session Storage**: Stores session data in memory for simplicity.
- **Kafka Integration**: Utilizes Kafka for efficient message streaming and processing.

## Technologies Used

- **Spring Boot**: Backend framework for Java.
- **Kafka**: Distributed messaging system.
- **Thymeleaf**: Template engine for rendering dynamic web pages.
- **Java**: Programming language.
- **Docker**: Containerization platform.
- **Postman**: API testing tool.
- **Virtual Machine (Ubuntu)**: Development environment.


### Workflow

1. **User Joins**: A user provides a username and joins the chat.
2. **Message Sending**: Users send messages that are processed and stored by Kafka.
3. **Message Receiving**: Messages are fetched from Kafka and displayed to all users in real-time.
4. **User Leaves**: Users can leave the chat, and their session is terminated.

## Setup Instructions

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/ataberkkilavuzcu/chat-App.git
    ```

2. **Run Kafka and Zookeeper**
    
3. **Import This Project To An IDE**:
   
4. ""Configure application.properties File With Correct Kafka Server IP""

5. **Access the Application**:
    Open your browser and go to `http://localhost:8080`.

## Usage

1. **Join the Chat**:
    - Enter a username and click "Join".

2. **Send a Message**:
    - Type your message in the input box and click "Send".

3. **View Online Users**:
    - See the list of currently online users on the right side of the chat interface.

4. **Sign Out**:
    - Click the "Sign Out" button to leave the chat.


## License

This project is licensed under the MIT License.

