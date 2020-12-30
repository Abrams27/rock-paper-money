FROM adoptopenjdk/maven-openjdk11

WORKDIR /rock_paper_money

COPY api/ api
COPY app/ app
COPY connectors/ connectors
COPY persistence/ persistence

COPY gradle/ gradle
COPY gradlew gradlew
COPY gradlew.bat gradlew.bat
COPY build.gradle build.gradle
COPY settings.gradle settings.gradle

COPY entrypoint.sh entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]
