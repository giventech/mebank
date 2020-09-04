# mebank


# How to Run

This is a step-by-step guide how to run the example:

## Installation

* The example is implemented in Java 8. 
* See    https://www.java.com/en/download/help/download_options.xml. 
*  The  examples need to be compiled so you need to install a JDK (Java Development Kit). 
*  A JRE (Java Runtime Environment) is not   sufficient. 
*  After the installation you should be able to execute   `java` and `javac` on the command line.
*  It also need maven install to be able to run the build phase
   
## Build

Change to the directory `mebank` and run `mvn clean install` 

## Run Exercise 1 
------------------
    java -cp target/me-bank-1.0-SNAPSHOT.jar mebank.Main
    
## Output 
---------
    
    
    
## DESIGN 
---------
Design is based on TDD and refactoring the code keeping acceptance criteria satisfied.

Stage1 created a static class
Stage2 refactored code considering an instance variable holding the list of transaction collection
Note: In a production environment this would need to be considered/re-considered based platorm capacity 
e.g. does this list of transaction need to be cached, synchronised with other processes 

## FURTHER IMPROVEMENT
----------------------

1) Caching requests
2) Synchronize access to all transactions
3) Consolidate code to ensure immutability of critical data (this would be in discussion with BA)
