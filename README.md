# Blogger Web Application

This is a web application that allows users to create, read, update, and delete blog posts and comments. It also allows users to follow other users and view their blog posts.

## Frameworks and Languages Used


- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- MySQL
- Swagger

## Data Flow

### Controllers

- `UserController`: Handles user-related requests, such as creating a user, updating a user, and deleting a user.
- `PostController`: Handles post-related requests, such as creating a post, updating a post, and deleting a post.
- `CommentController`: Handles comment-related requests, such as creating a comment, updating a comment, and deleting a comment.

### Services

- `UserService`: Implements business logic related to user-related requests.
- `PostService`: Implements business logic related to post-related requests.
- `CommentService`: Implements business logic related to comment-related requests.


### Repository

- `IUserRepository`: Handles user-related database operations.
- `IPostRepository`: Handles post-related database operations.
- `ICommentRepository`: Handles comment-related database operations.


### Database Design

The application uses a MySQL database with the following entities:

- `User`: Represents a user of the application, with attributes such as name, email, and password.
- `Post`: Represents a blog post, with attributes such as title, body, and author.
- `Comment`: Represents a comment on a blog post, with attributes such as body, author, and post.


## Data Structures Used

- ArrayList: Used to store lists of objects such as posts, comments, and users.

## Project Summary

The Blogger Web Application is a full-stack web application that allows users to create, read, update, and delete blog posts and comments. It also allows users to follow other users and view their blog posts. The application uses Spring Boot, Spring MVC, and Spring Data JPA to implement the backend, and MySQL for the database. Swagger is used for API documentation. The frontend is built using HTML, CSS, and JavaScript. 

## Deployment

The application has been deployed on an AWS EC2 instance, and can be accessed using the following URL: http://13.233.203.246:9000/swagger-ui/index.html#/
