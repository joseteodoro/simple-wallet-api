# Simple Wallet Application

REST API to manage accounts and transactions for those accounts.

This projects uses H2 (as test database), Lombok, flyway (to run migrations),
jdk11, swagger ui, springboot, and springboot jdbc template.

## Testing

You can testing using maven

```
mvn clean test
```

It will run all the junit tests (including integration ones using H2 database).

## Running the project

If you have a mysql running, you can update the `src/main/resources/application.properties` to match your mysql config, or you can use our docker-compose file.

The docker-compose will brings up a mysql 5.7 (because flyway is crashing at 8.x for some reason) and after that start the springnboot web application at port 8080.

### Running with docker-compose

Using docker-compose I strongly recommend to call a build first and after that the docker-compose command

```
mvn clean install
```

```
docker-compose up mysqldb
```

Wait until the server comes up, stop the docker-compose with ^C, and after that call the

```
docker-compose up
```

Why? Because mysql docker image brings up a temp server, shutdown that server and start a definitive one in the first startup. If you does not give enough time to it, you can connect on the temp server and got a disconnection right after you application comes up.

If you catch erros related with flyway with failed migrations, I suggest to remove the mysql image, the mysql container and run it again.

### Running with maven

You can run it locally using an already online mysql (update the `src/main/resources/application.properties` to match your credentials) and calling

```
mvn clean compile spring-boot:run
```

It will bring the application app using your mysql database (be aware about the flyway running on every startup to make sure the database model is properly configured);

If you want to run it using the H2 in memory database (just to see how the project works), you can uncomment all the lines at `src/main/resources/application.properties` and call the command

```
mvn clean compile spring-boot:run
```