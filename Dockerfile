
# ---------- BUILD STAGE ----------
FROM maven:3.9.6-eclipse-temurin-24 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# ---------- RUNTIME STAGE ----------
FROM tomcat:9.0-jdk24
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=builder /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
