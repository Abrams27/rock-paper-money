FROM adoptopenjdk/maven-openjdk11

WORKDIR /rock_paper_money

COPY src/ src

COPY gradle/ gradle
COPY gradlew gradlew
COPY gradlew.bat gradlew.bat
COPY build.gradle build.gradle
COPY settings.gradle settings.gradle

COPY entrypoint.sh entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]
