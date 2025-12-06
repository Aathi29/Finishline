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
**POST** `/reading/progress`  

**Request Body:**
```json
{
  "bookId": 12,
  "pagesRead": 25
}

**Response (Success):**
```json
{
  "status": "success",
  "message": "Progress logged successfully"
}

**Response (Error Example):**
```json
{
  "status": "error",
  "message": "Pages read must be positive"
}

### 2. Get Reading Forecast
**GET** `/reading/forecast?bookId=12`  

**Request Body:**
```json
{
  "bookId": 12,
  "pagesRead": 25
}
