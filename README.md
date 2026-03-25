# 🤖 WhatsApp Chatbot — Spring Boot Backend Simulation

A simple REST API that simulates a WhatsApp chatbot webhook, built with **Java 17** and **Spring Boot 3.2**.

---

## 📁 Project Structure

```
whatsapp-chatbot/
├── src/
│   ├── main/java/com/chatbot/
│   │   ├── WhatsAppChatbotApplication.java   ← Entry point
│   │   ├── controller/WebhookController.java ← REST endpoints
│   │   ├── service/ChatbotService.java        ← Reply logic + logging
│   │   └── model/
│   │       ├── MessageRequest.java            ← Incoming message DTO
│   │       └── MessageResponse.java           ← Outgoing reply DTO
│   ├── main/resources/application.properties
│   └── test/java/com/chatbot/service/ChatbotServiceTest.java
├── Dockerfile
├── pom.xml
└── README.md
```

---

## 🚀 Run Locally

### Prerequisites
- Java 17+
- Maven 3.8+

### Steps

```bash
# 1. Clone the repo
git clone https://github.com/YOUR_USERNAME/whatsapp-chatbot.git
cd whatsapp-chatbot

# 2. Build
mvn clean package

# 3. Run
mvn spring-boot:run
# OR
java -jar target/whatsapp-chatbot-1.0.0.jar
```

Server starts at: `http://localhost:8080`

---

## 📡 API Endpoints

### POST `/webhook` — Send a Message

**Request:**
```json
{
  "from": "+91-9876543210",
  "message": "Hi",
  "timestamp": "2026-03-25T10:00:00"
}
```

**Response:**
```json
{
  "to": "+91-9876543210",
  "reply": "Hello! 👋 How can I help you today?",
  "status": "sent",
  "timestamp": "2026-03-25 10:00:00"
}
```

### GET `/webhook` — Health Check

```bash
curl http://localhost:8080/webhook
```

---

## 💬 Supported Messages

| Input         | Reply                                          |
|---------------|------------------------------------------------|
| `hi`          | Hello! 👋 How can I help you today?            |
| `hello`       | Hey there! 😊 What can I do for you?           |
| `bye`         | Goodbye! 👋 Have a great day!                  |
| `goodbye`     | See you later! Take care! 😊                   |
| `help`        | Lists all supported commands                   |
| `about`       | Bot description                                |
| `hours`       | Availability info                              |
| `thanks`      | You're welcome! 😊                             |
| *anything else* | Friendly fallback message                    |

---

## 🧪 Test with cURL

```bash
# Hi → Hello
curl -X POST http://localhost:8080/webhook \
  -H "Content-Type: application/json" \
  -d '{"from":"+91-9876543210","message":"Hi","timestamp":"2026-03-25T10:00:00"}'

# Bye → Goodbye
curl -X POST http://localhost:8080/webhook \
  -H "Content-Type: application/json" \
  -d '{"from":"+91-9876543210","message":"Bye","timestamp":"2026-03-25T10:01:00"}'

# Unknown
curl -X POST http://localhost:8080/webhook \
  -H "Content-Type: application/json" \
  -d '{"from":"+91-9876543210","message":"What is your name?","timestamp":"2026-03-25T10:02:00"}'
```

---

## 🐳 Deploy on Render (Bonus)

1. Push this repo to GitHub
2. Go to [render.com](https://render.com) → New → Web Service
3. Connect your GitHub repo
4. Settings:
   - **Environment**: Docker
   - **Port**: 8080
5. Click **Deploy**

Your API will be live at: `https://whatsapp-chatbot-xxxx.onrender.com/webhook`

---

## 🧪 Run Tests

```bash
mvn test
```

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.2**
- **Maven**
- **SLF4J** (logging)
- **JUnit 5** (testing)
- **Docker** (deployment)
