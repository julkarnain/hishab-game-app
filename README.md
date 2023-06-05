# ********* hishab-game-app **************

## Maven:
- mvn clean install 
- mvn clean package 
- ./mvnw install -DskipTests 
- ./mvnw spring-boot:run 

## Docker:
## Build Command :
- docker build -t hishab/hishab-game-app .
## Run Command :
- docker run -p 8000:8000 hishab/hishab-game-app

## Swagger UI :
- Select definition : players,games and all-apis
- http://localhost:8000/swagger-ui/index.html