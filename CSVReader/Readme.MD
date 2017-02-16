*Language*
- Java

*Execution Manually*
- Usage		: javac Orders.java [To compile]
- Execute 	: java Orders
- Output 	: redemptions.csv file generated under output folder

*Automated Tests*
- I have used pom.xml to compile the java file and create it as a single JAR using Apache Maven plugin
- Please use the command 'maven clean install'
- The "orders.csv" file should be present while running the jar file
- java -jar stride.jar [command line]


*OutPut*
- No print statements used.
- redemptions.csv will be generated in the output folder

*Note*
- An empty line in the first row of the redemptions.csv was done intentionally