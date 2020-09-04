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
    java -cp target/me-bank-1.0-SNAPSHOT.jar com.giventech.finances.Main
    
## Output 
---------
    
    
    
## DESIGN 
---------


1) Separation of concerns based on S.O.L.I.D.
2) Test Driven development allowing built-in quality and code refactoring
3) Feature driven development based on requirements

Also below listed the assumption for Non-functional requirement which will depends

Design is based on TDD and refactoring the code keeping acceptance criteria satisfied.


### Test Driven Development

Firt tests1 Implmented file loading functions for validation of file loading 
Refactor  2 Refactor code including data model transaction transaction type as well as calcultations for 
Refactor  3 Considering output formats, code, maintenances, and the possibility of multiple implemtations getRelativeAccountBalance
Refactor  4 Considered risk of code consumers modifying critical source data (transaction loaded are copy and read-only of the original)


### SOLID.

Separation of concern: Transaction, Balance and Transaction type are are domain model,  TransactionAnalyser is an interface to business logic for the analyser
Open for extensiont Close for Modification: Proding the interface give flexibility for overriding the implementation of the analyser methods in case requirement change
or e.g. transaction cam be sourced from different datasource
Liskov Principle: TransactionAnalyser expose two interfaces currently, this could be only one based on the need to API client.


Note: In a production environment this would need to be considered/re-considered based platorm capacity 
e.g. does this list of transaction need to be cached, synchronised with other processes 

## FEATURE DRIVEN DEVELOPMENT VS  NON-FUNCTIONAL REQUIREMENTS
-------------------------------------------------------------

Currently a back of NFR/Functional feature could be considered base on the assumptions below
While these are not specified in the problement statement the current solution design will enable the addition of these features in interative fashion
Thanks to S.O.L.I.D. Design and how the application is build.

As a bank tellers we want to be able to consults concurrently data from all transaction so that we can respond to clietns
-> Caching requests, synchronisation or access to the collection of all transaction could be considered.
-> Parallel stream
