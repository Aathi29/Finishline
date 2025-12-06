# Personalized Book Reading Pace & Completion Predictor

## Overview
This project is a RESTful API built with **Spring Boot** that helps users track their reading progress and predicts the completion date of their books based on reading patterns. The API logs pages read per session and calculates an estimated completion date along with the average reading speed.

---

## Features
- **Log Reading Progress:** Users can record the number of pages they read in a session.  
- **Predict Completion Date:** The system predicts when a book will be finished based on historical reading data.  
- **Track Reading Speed:** Provides insights into reading velocity and burst/slow phases.  

---

## Technologies Used
- **Java 17**  
- **Spring Boot**  
- **Spring Web** (REST API)  
- **Maven** (build tool)  
- **JUnit & Mockito** (unit testing)  
- **Postman** (API testing)  

---

## API Endpoints

### 1. Log Reading Progress
POST /reading/progress  
Logs pages read in a reading session.

Request Body:
```json
{
  "bookId": 12,
  "pagesRead": 25
}
```

Response (Success):
```json
{
  "status": "success",
  "message": "Progress logged successfully"
}
```

Response (Error Example):
```json
{
  "status": "error",
  "message": "Pages read must be positive"
}
```

---

### 2. Get Reading Forecast
GET /reading/forecast?bookId=12  
Predicts estimated completion date and average reading speed for the given book.

Response (Success):
```json
{
  "bookId": 12,
  "estimatedCompletionDate": "2025-12-15",
  "averagePagesPerDay": 10
}
```

Response (Error Example):
```json
{
  "status": "error",
  "message": "Book not found"
}
```

---

### 3. Add New Book
POST /books  
Adds a new book to the system.

Request Body:
```json
{
  "title": "The Trial",
  "author": "Franz Kafka",
  "totalPages": 200
}
```

Response (Success):
```json
{
  "status": "success",
  "message": "Book added successfully",
  "bookId": 13
}
```

Response (Error Example):
```json
{
  "status": "error",
  "message": "Book title is required"
}
```

---

### 4. Get All Books
GET /books  
Fetches all books stored in the system.

Response (Success):
```json
[
  {
    "bookId": 12,
    "title": "The Metamorphosis",
    "author": "Franz Kafka",
    "totalPages": 150
  },
  {
    "bookId": 13,
    "title": "The Trial",
    "author": "Franz Kafka",
    "totalPages": 200
  }
]
```

Response (Error Example):
```json
{
  "status": "error",
  "message": "No books found"
}
```

---