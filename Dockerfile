# First stage: build the application
FROM maven:3.8.7-openjdk-17 AS builder

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download dependencies (for caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code and build the WAR file
COPY . .
RUN mvn clean package -DskipTests

# Second stage: use a Tomcat server to run the WAR file
FROM tomcat:9.0-jdk17-openjdk-slim

# Set the working directory for Tomcat webapps
WORKDIR /usr/local/tomcat/webapps/

# Copy the built WAR file from the builder stage to the Tomcat webapps directory
# Assuming the WAR file is named "mondolibri-0.0.1-SNAPSHOT.war" based on your project name
COPY --from=builder /app/target/mondolibri-0.0.1-SNAPSHOT.war ./ROOT.war

# Expose port 8080 for the Tomcat server
EXPOSE 8080

# Tomcat will automatically deploy and run the WAR file placed in the webapps directory
