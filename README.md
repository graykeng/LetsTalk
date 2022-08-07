# LetsTalk

## Intro
This project is about the connection between the Java application and the Azure MySQL online database for the SFU CMPT 354 group project (https://docs.google.com/document/d/1pddCtoKGd53H544DBfPs6eyynL1b-JM5/edit). This is a Java Chat App with many features, and users can send messages, images, and files to each other. We used JSwing and Azure MySQL to implement the front-end and back-end.

## Intro
The domain that we are going to model is a chat application, and it is a chatting and photo-sharing social networking service application. We will focus on the message data that people use in their communications. The aspect we modelled for the app is the message, including user information, messages and photos, where messages are divided into text messages, voice messages and picture messages. Chat logs can be saved to the database and queried by userID and friendID. Users can share and check the photos shared by others or themselves by photo ID and userID.

## Database Specifications
The database provides a stable environment to store the messages, files, albums, and friends. Users can access their data within its accessibility. The database can provide the LetsTalk with a variety of benefits. For example, the database offers storage of pictures for the feature “Album” in LetsTalk. Therefore, we only need the primary key (UserID) and the partial ID (PhotoID) to access the photo that belongs to anybody in the app.
The database can provide searching and storing features to the application. Also, the database management system includes the security feature for accessibility of messages. Furthermore, the database can transform the data format for each different country. For example, China’s users have the date format “2022/05/12”. In contrast, the same date would be entered in the United States as “05/12/2022” or in the United Kingdom as “12/05/2022”.
The multi-user access control is another feature of our database. To avoid compromising the integrity of the database, our DBMS controls multi-user accessibility when they access it concurrently by algorithms. The backup and recovery can also execute at the same time. Therefore, the data stored in the database can be safe and consistent.

## Technical Details
1. Java
Use the Java language as the basis for coding these projects, including object orientation, exception handling, input and output, multithreading, and network connectivity.
2. JDBC
Use JDBC to connect to the cloud database.
3. MySQL
Use MySQL on the Azure MySQL cloud databases.

This project was built by Gray Keng, Kerla Zhou, and Lester Li.
