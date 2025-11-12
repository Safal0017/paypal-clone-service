## Built entirely for learning and exploring microservices architecture.

# PayPal Clone – Microservices-Based Payment Application

A complete backend system inspired by PayPal, built using **Spring Boot**, **Spring Cloud Gateway**, **Microservices architecture**, **Kafka**, and **H2 Database**.  
This project demonstrates building a scalable, resilient, distributed system featuring **wallet transactions**, **asynchronous communication**, and **event-driven architecture**.

***

## 📸 Architecture Overview

The project is designed as six distinct microservices—User, Wallet, Transaction, Reward, Notification, and API Gateway—that interact via REST calls and Kafka messaging events.

***

## ⚙️ Tech Stack

- **Java 17**, **Spring Boot 3**  
- **Spring Cloud Gateway** (API Routing & Gateway)  
- **Kafka** (Event-driven Communication)  
- **H2** (Persistence)  
- **Spring Data JPA**  
- **Spring Security + JWT**  
- **Docker** (optional for containerization)  

***

## 🧩 Microservices Overview

| Service | Port | Description |
|----------|------|-------------|
| **API Gateway** | 8080 | Central entry point that routes requests to upstream microservices |
| **User Service** | 8081 | Handles authentication, authorization, and user management |
| **Wallet Service** | 8088 | Manages wallet creation, credit/hold/release, balance checks |
| **Transaction Service** | 8085 | Orchestrates transactions between users |
| **Reward Service** | 8082 | Assigns rewards for successful transactions |
| **Notification Service** | 8083 | Sends out Kafka-based asynchronous notifications |

***

## 🧠 Key Concepts Implemented

- **Microservices design and separation of concerns**
- **API Gateway routing and load balancing**
- **Inter-service communication**  
  - *Synchronous:* REST APIs  
  - *Asynchronous:* Kafka topics and event producers/consumers
- **Saga pattern and distributed transaction management**
- **Wallet hold–release–capture mechanism**
- **Circuit Breakers** and **Rate Limiters**
- **Two-phase commit rollback and eventual consistency**
- **DTO and serialization/deserialization best practices**
- **Global Exception Handling and Response Wrapping**

***

## 🚀 Core Flow Example (End-to-End)

### Step 1: User Registration

**POST** `http://localhost:8080/auth/signup`

**Request:**
```json
{
  "name": "Safal",
  "email": "test@gmail.com",
  "password": "test@pass"
}
```

**Response:**
```json
{
  "message": "User registered successfully with ID: 1"
}
```

Repeat for another user (`Zeel`, ID: 2).

***

### Step 2: User Login and Generate Token

**POST** `http://localhost:8080/auth/login`
```json
{
  "email": "test@gmail.com",
  "password": "test@pass"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

JWT token is used for authorization across all subsequent API calls.

***

### Step 3: Transaction Attempt (Initial Failure)

**POST** `http://localhost:8080/api/transactions/create`
```json
{
  "senderId": 1,
  "receiverId": 2,
  "amount": 500.0
}
```

**Response:**
```json
{
  "id": 1,
  "status": "FAILED",
  "reason": "Insufficient funds"
}
```

***

### Step 4: Add Funds to Wallet

**POST** `http://localhost:8088/api/v1/wallets/credit`
```json
{
  "userId": 1,
  "currency": "INR",
  "amount": 1000.0
}
```

**Response:**
```json
{
  "balance": 1000,
  "availableBalance": 1000
}
```

***

### Step 5: Reattempt Transaction – Success Flow

**POST** `http://localhost:8080/api/transactions/create`
```json
{
  "senderId": 1,
  "receiverId": 2,
  "amount": 500.0
}
```

**Response:**
```json
{
  "id": 2,
  "senderId": 1,
  "receiverId": 2,
  "amount": 500.0,
  "status": "SUCCESS"
}
```

**Logs (Transaction Service):**
```
🚀 Entered createTransaction()
📥 Transaction PENDING saved
🛑 Hold placed in Wallet
💸 Amount captured and debited
💰 Receiver credited successfully
✅ Transaction SUCCESS
📤 Pushed to Kafka Topic: txn-initiated
```

***

## 🧵 Event Flow Summary

1. TransactionService initiates a transaction → sends REST call to WalletService (**hold** funds).  
2. WalletService confirms availability and places a hold.  
3. On success, TransactionService **debites sender** and **credits receiver**.  
4. Transaction completion triggers a **Kafka event**:
   - Reward Service consumes the event and allocates points.
   - Notification Service listens and sends transaction confirmation.

***

## 🧑‍💻 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/paypal-clone.git
   cd paypal-clone
   ```
2. Start **Kafka** and **Zookeeper**.  
3. Run services in this order:
   ```
   api-gateway → user-service → wallet-service → transaction-service → reward-service → notification-service
   ```

***

## 📊 Kafka Topics

| Topic Name | Purpose |
|-------------|----------|
| `txn-initiated` | Broadcasts successful transactions |
| `wallet-updated` | Publishes wallet balance updates |
| `reward-assigned` | Communicates reward assignments to Notification Service |

***

## 📬 Future Enhancements

- Integration with third-party payment gateways  
- Role-based access with Admin dashboards  
- Redis caching for performance optimization  
- Docker Compose setup for full environment orchestration  

***

## 🧡 Acknowledgment

Special thanks to **Mrunmai Dahare** for creating a deeply insightful Spring Boot microservices series.  
This project was inspired by and evolved from applying those architectural principles into a real-world system.

***
