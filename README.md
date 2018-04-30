# print-tweets-using-java 1.8

There are many approaches i took for the solution with regard to how you run it:

1.First solution ProcessTextFileUsingSpring uses camel,spring and java as solution.

With this approach i faced challenge of configuring camel route to pick up two files as input to my program simultaneously and because of time constraints had to leave it


2.Second solution uses ProcessTextUsingBasicJava class with main method in it and with this approach a user simply enters absolute path of the two files separated by comma.

3.Third solution uses Rest endpoint http://localhost:5656/printUserAndTweets/input?userFileAbsolutePath=&tweetFileAbsolutePath=
To use this approach simply:

3i)mvn clean install and make sure your Java Compile version is set to 1.8

3ii)Run the TextToJavaApplication springboot app to start the undertow container

3iii)Call the Get http://localhost:5656/printUserAndTweets/input?userFileAbsolutePath=&tweetFileAbsolutePath=


#How to run second solution

I would have wanted my solution to be run via the command line wherein you will execute javac,java ProcessTextUsingBasicJava but this meant stripping out all the non java libraries i am referencing in this project including those from spring as well.
For a simplicity i decided to stick to IDE based test and here is how to run the program

1.Clone the repo : https://github.com/admirechifura/print-tweets-using-java.git

2.From the IDE simply run the main method of ProcessTextUsingBasicJava class


#How to run third solution

#Limitations
