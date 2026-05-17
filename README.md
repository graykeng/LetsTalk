# LetsTalk

LetsTalk is a Java Swing desktop chat and photo-sharing application backed by a MySQL relational database. The repository includes the application source code, database scripts, ERD/report artifacts.

## Tech Stack

| Layer | Technology |
| --- | --- |
| UI | Java Swing |
| Application | Java 8, Maven |
| Persistence | JDBC |
| Database | MySQL |
| Media handling | Java ImageIO, BLOB storage |

## Repository Structure

```text
.
+-- src/main/java
|   +-- Constants
|   +-- Helper
|   +-- JDBC
|   +-- Panels
|   +-- TableStruture
|   +-- Thread
|   +-- Main.java
+-- src/main/resources/Image
+-- document
|   +-- LetsTalk Data Script.sql
|   +-- LetsTalk Query List.pdf
|   +-- LetsTalk Tables Script.pdf
|   +-- LetsTalkERD.pdf
|   +-- report.pdf
|   +-- screenshots
+-- pom.xml
+-- LetsTalk_Executable.jar
```

## Core Features

- User registration and login
- Profile editing with avatar upload
- Friend management and group creation
- One-to-one chat history retrieval
- Text and image message sending
- Emoji ownership and selection
- Photo post upload and browsing
- Analytical SQL queries used for project reporting, such as average user age by gender and users with complete friendship coverage

## Application Walkthrough

| Registration | Chat Workspace |
| --- | --- |
| <img src="document/screenshots/screenshot-02.png" width="420" alt="Register new user screen"> | <img src="document/screenshots/screenshot-04.png" width="420" alt="Chat screen with friend list and message composer"> |
| New users enter profile details, choose a headshot image, select gender, and submit to create a database-backed account. | The main chat view combines navigation, friend selection, conversation history, message input, and attachment actions. |

| Profile And Friendship | Friend Discovery |
| --- | --- |
| <img src="document/screenshots/screenshot-05.png" width="420" alt="User profile screen"> | <img src="document/screenshots/screenshot-08.png" width="420" alt="Add friend query result dialog"> |
| Users can view profile information, interests, birthday, age, and friendship actions from the profile panel. | The add-friend panel demonstrates database-backed user lookup and reporting-style queries from the course requirements. |

## ERD
 <img src="document/screenshots/screenshot-01.png" width="700" alt="LetsTalk ERD"> 

For the full usage report, see `document/report.pdf`.

## Architecture

The project is organized around three main layers:

- `Panels`: Swing UI screens and interaction flows.
- `JDBC`: database connection and query operations.
- `TableStruture`: Java model classes that map to database tables.

Typical chat flow:

1. `MainPanel` manages the current application state.
2. `ChatSelectPanel` chooses a friend conversation.
3. `ChatPanel` renders messages and sends new messages.
4. `Insert` writes message, text/image subtype, and communication event rows.
5. `Read` loads conversation history and enriches it with sender and timestamp data.
6. `MessageReceiver` refreshes the active conversation periodically.

## Local Setup

Prerequisites:

- JDK 8 or newer
- Maven
- MySQL 8.x

Create a MySQL database named `letstalk`, recreate the schema from `document/LetsTalk Tables Script.pdf`, then load the seed data from `document/LetsTalk Data Script.sql` if you want the demo records.

Configure the database connection with environment variables:

```powershell
$env:LETSTALK_DB_URL="jdbc:mysql://localhost:3306/letstalk"
$env:LETSTALK_DB_USER="letstalk"
$env:LETSTALK_DB_PASSWORD="your-password"
```

Build and run:

```powershell
mvn clean package
java -jar target/LetsTalk-jar-with-dependencies.jar
```

## Future Improvements

- Add password hashing instead of storing raw passwords.
- Replace polling refresh with WebSocket or server-push messaging.
- Add automated tests around JDBC operations with a local test database.
- Move image resizing and file conversion into a reusable media service.
- Add schema SQL as a plain `.sql` file instead of relying on PDF artifacts.
- Introduce a clearer MVC or service-layer boundary between Swing panels and persistence code.

## Authors

Built by Kerla Zhou, Gray Keng, and Lester Li.
