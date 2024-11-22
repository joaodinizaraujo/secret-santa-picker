# secret-santa-picker: API

This is a **personal project** for managing a Secret Santa system using Neo4j as the database and Java for the backend. It was created for experimentation and learning purposes and is not designed for public use or large-scale deployment.  

## 🚀 Features  

- Allows users to register participants for a Secret Santa event.  
- Randomly assigns Secret Santa pairs while avoiding self-matching.  
- Stores participant data and pair assignments in a Neo4j graph database.  
- Provides RESTful endpoints for managing participants and generating assignments.  

## ⚠️ Important Notes  

1. **Personal Project**:  
   This API was developed for educational purposes. The implementation may include specific solutions tailored to personal needs and has not been extensively tested for general usage.  

2. **Limitations**:  
   - Relies on Neo4j for data storage; ensure the database is properly configured.  
   - No advanced error handling or security measures are included (e.g., user authentication).  

3. **API Usage**:  
   - Users are responsible for running their Neo4j database instance.  
   - Designed for local or controlled environments, not for production-level use.  

## 🛠️ Technologies Used  

- **Java**: Main programming language.  
- **Spring Boot**: Framework for building the RESTful API.  
- **Neo4j**: Graph database for storing participant data and relationships.  
- **Lombok**: Simplifies Java code with annotations.  

## 📋 Prerequisites  

- Java 17 or higher.  
- Neo4j database instance (version 4.x or higher).  
- Maven for dependency management.  
