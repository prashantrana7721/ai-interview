# AI-Interview – AI-Powered Interview Evaluator

AI-Interview Is A Real-Time AI-Powered Interview Evaluation Platform Built Using Spring Boot , Docker , And FastAPI Microservices . It Provides Semantic Scoring , Sentiment Detection And Transcribed Feedback To Help Recruiters Evaluate Candidates Fairly And Efficiently Via A Clean HTML Interface .

---

## Problem Statement

Traditional Interviews Are Resource-Intensive , Subjective And Inconsistent . Recruiters Often Face Challenges In Evaluating Large Volumes Of Candidate Responses With Fairness And Accuracy .

**AI-Interview** Addresses This Gap By Offering A Scalable , AI-Driven Evaluation System That :
- Scores Candidate Answers Based On Semantic Similarity To Ideal Responses .
- Provides Insights Into Sentiment And Emotions .
- Delivers Structured , Automated Feedback In Real Time .

---

## Why This Project?

- Recruiters Need **Faster** , **Unbiased** And **Consistent** Evaluation Tools .
- Candidates Deserve **Objective Feedback** And Practice Opportunities .
- Organizations Benefit From **Reduced Hiring Cycles** And **Higher-Quality Filtering**.

---

## Key Features

- Upload Or Record Candidate Interview Responses ( Audio Or Text )
- Real-Time Transcription Using OpenAI Whisper
- Semantic Similarity Scoring Using SBERT
- Sentiment And Emotion Detection
- Structured Feedback Display In HTML Frontend
- Fully Containerized Deployment With Docker
- PDF Download Of Results ( Optional )
- Modular Backend With Spring Boot And FastAPI

---

## 🛠️ Tech Stack

| Layer        | Technology                             |
|-------------|----------------------------------------|
| Backend      | Spring Boot ( Java 17 )                |
| ML Service   | FastAPI ( Python )                     |
| Transcription| OpenAI Whisper API                     |
| NLP Models   | Sentence-BERT , Sentiment Classifier   |
| Frontend     | HTML , CSS , JavaScript                |
| DevOps       | Docker , Docker Compose                |
| Optional DB  | PostgreSQL ( For Future Enhancements ) |

---

## Folder Structure

```
ai-interview/
├── backend/                  # Spring Boot App ( API & logic )
│   └── src/main/resources/static/evaluation.html
├── ml-service/              # FastAPI Microservice ( Semantic Score , Sentiment Etc. )
├── audio/                   # ( Optional ) Stores Audio Responses
├── docker-compose.yml       # Multi-Container Orchestration
└── README.md
```

---

## How to Run

### Prerequisites

- Java 17+
- Python 3.10+
- Docker & Docker Compose
- npm (if expanding frontend)

### 🔁 Option 1: Dockerized Setup (Recommended)

```bash
git clone https://github.com/yourusername/ai-interview.git
cd ai-interview
docker-compose up --build
```

Access the app at:  
`http://localhost:8080/evaluation.html`

### Option 2 : Manual Run ( Dev Mode )

1. **Start ML Microservice**
   ```bash
   cd ml-service
   uvicorn main:app --host 0.0.0.0 --port 8001
   ```

2. **Run Spring Boot App**
   ```bash
   cd backend
   ./mvnw spring-boot:run
   ```

3. **Open In Browser**
   ```
   http://localhost:8080/evaluation.html
   ```

---

## Sample Evaluation Flow

1. Candidate Provides An answer ( Text / Audio )
2. Whisper API Transcribes The Audio ( If Provided )
3. Backend Forwards Question And Answer To ML Service
4. ML Service Returns :
   - Semantic Score ( SBERT )
   - Sentiment Label ( Positive , Negative , Neutral )
   - Emotion Label ( Happy , Calm , Anxious Etc. )
   - Feedback Summary
5. Results Displayed In Browser With **PDF Download** Option

---

## Sample API Call

### POST `/api/evaluate`

**Request:**
```json
{
  "question": "Describe A Challenging Project You Worked On",
  "candidateAnswer": "I Led A Cross-Functional Team To Build A Data Pipeline..."
}
```

**Response:**
```json
{
  "semanticScore": 0.87,
  "sentiment": "Positive",
  "emotion": "Focused",
  "feedback": "Strong Technical Depth And Good Leadership Demonstration"
}
```

---

## Future Enhancements

- Live Voice Interview Module
- GPT-Based Feedback Generator
- Resume & JD Integration For Smarter Prompts
- PDF Reports Auto-Email To HR
- Admin Dashboard For Bulk Scoring

---

## PDF Download

After Evaluation Users Can **Download A PDF Report** With :
- Semantic Score
- Sentiment / Emotion Labels
- Full Q&A Transcript
- Feedback Summary

( Feature Already Integrated In Evaluation Page )

---
