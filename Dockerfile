FROM gradle:jdk11-alpine
RUN mkdir /opt/app
COPY build/libs/VacationCalculator-1.0.jar /opt/app/japp.jar
EXPOSE 8080
CMD ["java", "-jar", "/opt/app/japp.jar"]