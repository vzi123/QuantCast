FROM openjdk:8-jdk-alpine
RUN apk add --no-cache curl tar bash procps


FROM maven:3.6-jdk-11 as maven_build
VOLUME . /app
WORKDIR /app
COPY . /app

#copy pom
COPY pom.xml .

#copy source
COPY src ./src

# build the app and download dependencies only when these are new (thanks to the cache)
RUN --mount=type=cache,target=/root/.m2  mvn clean package -Dmaven.test.skip

# split the built app into multiple layers to improve layer rebuild
RUN mkdir -p target/docker-packaging && cd target/docker-packaging && jar -xf ../Quant*.jar

########JRE run stage########
FROM openjdk:11.0-jre
WORKDIR /app

#copy built app layer by layer
ARG DOCKER_PACKAGING_DIR=/app/target
COPY --from=maven_build ${DOCKER_PACKAGING_DIR} /app
#COPY --from=maven_build ${DOCKER_PACKAGING_DIR}/BOOT-INF/classes /app/classes
#COPY --from=maven_build ${DOCKER_PACKAGING_DIR}/META-INF /app/META-INF
COPY /target/QuantCast_Assignment-2.3-SNAPSHOT.jar ./
COPY /target ./
COPY /target/classes /classes
COPY /target/original-QuantCast_Assignment-2.3-SNAPSHOT.jar ./
COPY entry.sh /
COPY dockerentry.sh /
COPY . ./

ENTRYPOINT ["sh", "/dockerentry.sh"]

#CMD ["java","-jar","/app/QuantCast_Assignment-2.0-SNAPSHOT.jar","-f"]