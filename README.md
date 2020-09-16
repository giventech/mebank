# Technical challenge 

## Installation

* The example is implemented in Java 8. 
* See    https://www.java.com/en/download/help/download_options.xml. 
*  The  examples need to be compiled so you need to install a JDK (Java Development Kit). 
*  A JRE (Java Runtime Environment) is not   sufficient. 
*  After the installation you should be able to execute   `java` and `javac` on the command line.
*  It also need maven install to be able to run the build phase
   
## Build

Change to the directory `mebank` and run `mvn clean install` 

## Run the solution
-------------------
### Run the command below and provide the data input:

java -cp target/me-bank-1.0-SNAPSHOT.jar com.giventech.finances.Main

### Provide the input as comma separated list

accountid,start date,end date
    
## Example of input and outputs
---------

![image](https://user-images.githubusercontent.com/17228294/92208668-0c496900-eecf-11ea-9ee8-cda61b0595e0.png)
   
    
## Design 
---------

The design consider the belwo 
3) Feature base implementation with validation of acceptance criteria (TDD supporting this)
1) Single responsibility based on S.O.L.I.D.
2) Test Driven development allowing built-in quality and code refactoring



Design is based on TDD and refactoring the code keeping acceptance criteria satisfied.


### Test Driven Development (Supporting Feature Based developements)

* Basic Unit tests:  Implemented file loading and transaction seggregation function
* Refactor  2 Refactor code including data model transaction transaction type as well as calcultations for 
* Refactor  3 Considering output formats, code, maintenances, and the possibility of multiple implemtations getRelativeAccountBalance
* Refactor  4 Considered risk of code consumers modifying critical source data (transaction loaded are copy and read-only of the original)


### SOLID.

*  Single Responsibility :   TransactionAnalyser is the sole  interface to business logic for the analyser, Utils provide helpers to manipulate transactions
*  Open for extension Close for Modification: Proding the interface give flexibility for overriding the implementation of the analyser methods in case requirement change. 
or e.g. transaction cam be sourced from different datasource
* Liskov Principle: TransactionAnalyser expose two method signatures currently, this could be only one based on the need to API client.
* Dependency: Injection (TBC)

Note: In a production environment performance improvement can beconsidered/re-considered based platorm capacity e.g. does this list of transaction need to be cached, synchronised with other processes 

## Backlog of possible improvements Feature Driven Development  VS  Non Functional Requirements
-------------------------------------------------------------
* Application could be re-factored based on the assumption below

* As a bank tellers we want to be able to consults concurrently data from all transaction so that we can respond to clietns
- Caching requests, synchronisation or access to the collection of all transaction could be considered.
- Parallel stream
