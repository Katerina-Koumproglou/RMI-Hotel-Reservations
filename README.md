# Hotel Reservation System (Java RMI)

This project is a Hotel Room Booking System implemented using Java RMI (Remote Method Invocation). It allows clients to remotely view available rooms, make bookings, view current guest reservations, and (partially implemented) cancel bookings.

All messages in the application are in Greek.

The project includes:
- **HRServer.java:**     Launches the RMI server and binds the hotel service for remote access.
  
- **HRImpl.java:**       Implements hotel operations: listing rooms, bookings, guests, and more.
  
- **HRInterface.java:**  Defines the remote interface used by clients.
  
- **HRClient.java:**     Command-line client to interact with the remote hotel server.

## Features
- List available rooms with types and nightly rates

- Book rooms of a specific type under a customer's name

- List all guests and their bookings

- Cancel bookings (partially implemented)

## How to run the project:
1. Start `rmiregistry`
Open a terminal (Command Prompt), navigate to your project directory, and run `rmiregistry &` as shown below:
![image](https://github.com/user-attachments/assets/8af03656-4373-44b5-a280-e2cdfd5f5efc)

2. Compile all Java files
Open a different terminal and compile the codes using `javac HRClient.java HRImpl.java HRInterface.java HRServer.java -encoding utf-8`.

3. Start the Server using `java HRServer`.
![image](https://github.com/user-attachments/assets/4635ec54-0a57-4af6-b979-1f213e78471f)

4. Use the Client
Again open a separate terminal and run any of the following commands:
```
java HRClient list <hostname>
java HRClient book <hostname> <type> <number> <name>
java HRClient guests <hostname>
java HRClient cancel <hostname> <type> <number> <name>
```
Replace `<hostname>` with `localhost if` testing locally.

## Few examples:
![image](https://github.com/user-attachments/assets/272c9726-8c8d-479b-af12-87925d3ae46b)
