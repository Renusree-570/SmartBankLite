# 💼 SmartBankLite – Core Banking Backend System

**SmartBankLite** is a lightweight backend application that simulates the core functionalities of a digital banking system. It handles operations such as customer management, account creation, fixed deposits, fund transfers, and transaction tracking – built with clean architecture and real-world logic.

---

## 📌 Features

- 👤 **Customer Module** – Create, update, and retrieve customer details  
- 💰 **Account Module** – Open new accounts, link them to customers, and fetch details  
- 🏦 **Fixed Deposit (FD)** – Create FDs, calculate maturity amounts, and view active deposits  
- 🔁 **Fund Transfer** – Transfer funds between accounts with proper validations  
- 📄 **Transaction Module** – Record and retrieve transaction histories  
- ❌ **Exception Handling** – Centralized error responses with appropriate status codes  
- 🧪 **Unit Testing** – Service layer tested using JUnit and Mockito  
- 📘 **API Documentation** – Swagger UI for visualizing and testing API endpoints  

---

## ⚙️ Tech Stack

- **Backend**: Java 8, Spring Boot, Spring Data JPA  
- **Database**: H2 (in-memory, for demo purposes)  
- **Testing**: JUnit 5, Mockito  
- **Documentation**: Swagger (SpringFox)  
- **Build Tool**: Maven  

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/SmartBankLite.git
cd SmartBankLite
````

### 2. Run the Application

You can run the application using your IDE or via Maven:

```bash
mvn spring-boot:run
```
---

## 📁 Project Structure

```
SmartBankLite/
├── controller/      → REST endpoints
├── service/         → Business logic
├── repository/      → Data access layer
├── model/           → Entity classes
├── dto/             → Data Transfer Objects
├── exception/       → Custom exceptions & handlers
├── config/          → Swagger configuration
├── test/            → Unit tests
```

---

## 👩‍💻 Developed By

**Renu Sree Kanchanapally**
Backend Developer | Java | Spring Boot
🔗 [LinkedIn](https://www.linkedin.com/in/renusreekanchanapally/)

---

## 📜 License

This project is open source and available under the [MIT License](LICENSE).

```
