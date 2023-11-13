# Todo List Application

This is a simple RESTful web service built with Spring Boot, Hibernate, and MySQL for managing a todo list. The application allows users to perform CRUD (Create, Read, Update, Delete) operations on todo items, each having a title and a description.

## Prerequisites

Before running this application, ensure you have the following installed:

- Java JDK
- Spring Boot
- MySQL Database

## Configuration

1. Clone this repository:

   ```bash
   git clone https://github.com/your-username/todo-list-app.git

## Running the Application

1. Navigate to the project directory:

   ```bash
   ./mvnw spring-boot:run
   ```
   
2. The application will start, and you can access the API at  `http://localhost:8080/api/todos`.

## API Endpoints
- `GET /api/todos` - Get all todos
- `GET /api/todos/{id}` - Get a todo by id
- `POST /api/todos` - Create a new todo
- `PUT /api/todos/{id}` - Update a todo by id
- `DELETE /api/todos/{id}` - Delete a todo by id


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.