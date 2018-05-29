FROM java:8

CMD java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /sample-blog-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ADD *.jar /sample-blog-0.0.1-SNAPSHOT.jar
