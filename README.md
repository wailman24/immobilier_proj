Before executing the code, ensure you have the following installed:
-Java 17 or higher
-Oracle Database (Oracle Database 11g Express Edition Release 11.2.0.2.0 - 64bit Production)
Add the ojdbc11.jar and rs2xml.jar to the building path of the project (To connect with the dataBase):
-go to the project -> Build path -> configure Build path -> Modulepath -> Add External JARs -> add your ojdbc11.jar
-go to the project -> Build path -> configure Build path -> Classpath -> Add External JARs -> add your rs2xml.jar
one last thing is to add your Oracle database (Username and password) to the classes that need these informations.
like this : connection =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","Username","password");
And create The data base table in your dataBase using the script project.sql 
