# Tuesday: REST Design, CRUD, Validation and Errors

Complete source for the live lecture. It matches the code on the slides.

## New today

- `spring-boot-starter-validation` in `pom.xml`
- `dto/TaskRequest.java`: a record DTO with `@NotBlank` and `@Size` (no id field)
- Full CRUD in `TaskController`: POST (201 + Location), PUT, DELETE (204)
- `TaskService` throws `TaskNotFoundException` instead of returning `Optional`
- `exception/ApiError` + `GlobalExceptionHandler`: one error shape for 404 and 400
- `requests.http` covers success, validation failure, broken JSON, 404, 405 and 415

## Run it

You need **JDK 17 or newer**.

- **IntelliJ IDEA:** File > Open, pick this folder (the one with `pom.xml`), wait for Maven to finish importing, then press the green run button next to `main` in `TaskManagerApplication.java`.
- **VS Code:** open the folder with the Extension Pack for Java installed, then run `TaskManagerApplication`.
- **Terminal:** `mvn spring-boot:run` (needs Maven installed). Tests: `mvn test`.

Look for `Tomcat started on port 8080` in the console.

> **About `./mvnw`:** the slides use the Maven wrapper that Spring Initializr adds. These demo folders do not include it. To add it, copy `mvnw`, `mvnw.cmd` and the `.mvn/` folder from any project generated at start.spring.io, or run `mvn wrapper:wrapper` once.

Try the requests in `requests.http` (VS Code REST Client or IntelliJ HTTP Client: click **Send Request**), or paste them into Postman.

## Suggested live-coding order

1. `pom.xml`: add the validation starter
2. `dto/TaskRequest.java`: the DTO record and its rules
3. `controller/TaskController.java`: POST with `@Valid @RequestBody`, then PUT and DELETE
4. `exception/TaskNotFoundException.java` and the service's `findById`
5. `exception/ApiError.java` + `exception/GlobalExceptionHandler.java`
6. `requests.http`: test every path, not only the happy one
