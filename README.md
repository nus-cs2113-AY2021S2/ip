# Hi, I am Friday, your personal assistant.
## (A task management applictaion based on the Duke project)

This is a project based on the template for a greenfield Java project. It's named after the Java mascot _Duke_. 
Given below are instructions on how to use it.

## Run the application using the ip.jar file

Prerequisites: Java 11.  
1. Open a terminal at the folder where the `ip.jar` file is located.  
1. Run command `java -jar ip.jar`

## Setting up in Intellij

Prerequisites: JDK 11, update IntelliJ to the most recent version.

### Open the project using IntelliJ version 2020.3.1 (latest) and beyond

1. Open IntelliJ (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Import the project into IntelliJ as follows:
   1. Click `Open`
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
1. After that, locate the `src/main/java/duke/Duke.java` file, right-click it, and choose `Run Duke.main()`. If the setup is correct, you should see something like the output below.

   ```
   _____________________________________________________________
    Welcome.
    I am your assistant Friday. ✧ʕ̢̣̣̣̣̩̩̩̩·͡˔·ོɁ̡̣̣̣̣̩̩̩̩✧
    Just FYI, I am developed by Song Yu.
    May I know what I can help you?
   _____________________________________________________________
   ```
