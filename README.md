# SpringDemoApplication
A simple demo application to test the key functionalities of the Spring Framework. 

## Purpose of this project
The reason behind the creation of this project was to test with a more "hands-on" approach the capabilities of the Spring Framework.
In other words, there was little to no design for the application architecture and the implementation may lack consistency across multiple sections of the code.

## Sources 
Tha main sources for the realization of this application were:
- [Spring Boot Tutorial](https://www.youtube.com/watch?v=9SGDpanrc8U) by Amigoscode on YouTube;
- [Spring Boot Reference](https://spring.io/guides/tutorials/rest) from the Official Spring Documentation;

The YouTube video provided the main architecture idea and was used to clarify how some of the task to perform were to be implemented, while the reference guide was used as
a support during the whole "development".
For the Unit Tests, the main source was a collection of different articles, some from the official documentation and some from standalone users. These guides were chaotic
to say the least, and most of them were conflicting over the definition of what a Unit Test is. Therefore, none of them is reported here, and the only use I could find for them
was to showcase how to practically implement a generic Unit Test, allowing me to experiment with JUnit and Mockito features and their integration with the Spring framework. 

## Known problems
The application is flawed. The architecture is essentially a dummy one, and this single choice led to several problems when it came to Unit Tests. Some of them could
not be performed properly, and some others were performed with the wrong approach.
It is also important to state that the PostgreSQL Database that served as a Persistency Layer for the application was managed using a Docker container. That should not 
be a real problem since the whole application does not need to have this information, but it might be useful to know that it was not tested using a PostgreSQL instance 
running on bare metal. \
UPDATE: most of the testing problems were solved using the `verify` method from the Mockito library. This allowed to verify (hihi) that the actual methods of the repository
layer were called after the specific Service Layer method call. This solved almost all the problems that were found after the first implementation of the tests.

## Database configuration
In order for the application to work properly, PostgreSQL must be configured with a `student` table and by giving the `postgre` (admin on the database) user all the
privileges on that table. 
The `application.properties` file must be modified in order for the application to work with different database users.  