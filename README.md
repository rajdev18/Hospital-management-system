# AI-Powered Hospital Management System

A microservices-based hospital management system with integrated AI features.

## Architecture
- **Eureka Server** — Service Discovery (port 8761)
- **API Gateway** — Central routing + JWT Authentication (port 8080)
- **Patient Service** — Patient registration, login, AI symptom checker (port 8081)
- **Doctor Service** — Doctor management (port 8082)
- **Appointment Service** — Appointment booking with Feign (port 8083)
- **Medical Record Service** — Medical records + AI discharge summary (port 8084)
- **AI Service** — Flask + LangChain + RAG (port 5000)

## AI Features
- Symptom Checker — recommends doctor specialization
- Patient Q&A — RAG-based medical knowledge assistant
- Discharge Summary Generator — AI-generated professional summaries

## Tech Stack
**Backend:** Java, Spring Boot, Spring Security, Spring Cloud Gateway, OpenFeign, JPA/Hibernate, MySQL, JWT, Swagger

**AI:** Python, Flask, LangChain, FAISS, Sentence Transformers, Groq API (LLaMA 3.3)

**Tools:** Git, Maven, Docker, Postman, Eureka

## Setup
1. Start MySQL and create database `hospital_db`
2. Start services in order: Eureka → API Gateway → Patient → Doctor → Appointment → Medical Record
3. Start AI service: `python app.py`
4. Access Swagger UI: `http://localhost:8080/swagger-ui/index.html`

## API Documentation
Available at `http://localhost:8080/swagger-ui/index.html` after starting all services.
