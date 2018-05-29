# Sample Blog

Sample-blog is a backend is a sample Spring-boot application that users MySQL as its persistent datastore, Elasticsearch for searching Articles and Redis as cache solution.


## Prerequisites:
	Any IDE
	Java 8
    Docker (follow this link to install https://store.docker.com/search?type=edition&offering=community)


## Development Environment:
It is assumed that no MySQL, Elasticsearch or Redis server are running in your machine, if it is running please stop it to avoid port conflicts.

Use following command to run pre-configured environment `docker-compose -f env.yml up`
   
#### Sampleblog Application:
To start the application run SampleBlogApplication.java main method from your IDE
    and to check that it is running open `http://localhost:8080/articles/1` from your browser.

## Production Environment:

  1) Dockerize SampleBlog:
    - in application.properties set database host to be "sampleblog-mysql" as below
      `spring.datasource.url: jdbc:mysql://sampleblog-mysql:3306/sampleblog?useSSL=false`
    - create app image using below command
      `./gradlew clean bootJar buildDocker`

  2) Start whole environemnt using below command and make sure that everything is healty
    `docker-compose up`    // To terminate everything you can use ctrl+c