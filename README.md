# ResearcherPro-Microservice1
Microservice1 for ResearcherPro Software

# 📊 Usage Analytics Microservice  

This microservice is responsible for **processing usage data** from the main application. It consumes messages from a **RabbitMQ queue**, stores structured usage data in a database, and provides insights into how users interact with the product over time.  

---

## 🚀 Features  

- 📨 **Queue Consumer** – Reads user request usage data from RabbitMQ.  
- 🗄️ **Data Storage** – Persists all usage events into a database for historical tracking.  
- 📈 **Usage Insights** – Analyzes stored data to generate metrics such as:  
  - Number of requests per user per day  
  - Trends across different days of usage  
  - Aggregate statistics for product usage patterns  
- ⚡ **Scalable Design** – Built to handle growing traffic and data volumes.  

---

## 📂 Data Flow  

1. **Main App → RabbitMQ Queue**  
   - Main application pushes user request usage data into the queue.  

2. **Microservice Consumer**  
   - Listens to RabbitMQ, processes each incoming message.  

3. **Database Storage**  
   - Saves structured usage events for later retrieval and analysis.  

4. **Analytics Engine**  
   - Runs daily/periodic jobs to compute insights (e.g., requests per user per day).  

---

The main application can be found here: https://github.com/dakshp13/ResearcherPro
