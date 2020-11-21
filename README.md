# Home Budget Assistant

##Table of Contents:
* [Introduction](#introduction)
* [Users possible action](#users-possible-action)
* [Technologies](#technologies)
* [Setup](#setup)
* [Posible actions with examples](#Possible-action-with-example-endpoints:)
    * GET all registers status
    * PUT To recharge register
    * POST to transfer money between acounts
## Introduction
This demo application was designed to help manage home budget. 

##Users possible action:
 1. Recharge accounts called registers 
 2. Transfer money between them
 3. Check accounts statuses
 
## Technologies
Project is created with:
* Java 
* Groovy (tests + build.gradle)
* Gradle
* Spring Boot version 2.4.0
* Hibernate
* H2 relational database
* Spock as test framework (unit and integration tests)
* Lombok
	
## Setup
To run this application:
1) clone the repository
2) Checkout develop branch (main branch before code review)
3) Navigate to project directory
4) Here we have got few options:
### Run it from the command line:
   - run command to create executable JAR file:
    ```
    gradle bootJar ```
- to start app run below command:
     ```
     java -jar home-budget-assistant-0.0.1-SNAPSHOT.jar
   ```  
 - application is up and running
    
### Run it from IDE (for example IntelliJ IDEA)

- import project by selecting `gradle.build` file
- select `Run` (play sign) or use shortcut `^R` on MAC or `shift+F10` on Windows if you have default
- application is up and running
if above doest work due to your default configuration use below:
- navigate to `HomeBudgetAssistantApplication.class`
- if the project is imported correctly click `PLAY` button near the class name and select:
`Run HomeBudgetAssistantApplication`
- application should be up and running


### Possible action with example endpoints:
To call Rest controllers you can use client as Postman, or use curl.
If you didn't change default host or ports (by `application.yml`) below examples will be valid.

#####1) get status for all register with current balance:
```
POSTMAN
method: GET
url: http://localhost:8080/registers/
```    
or curl command from terminal:
```$xslt
curl --location --request GET 'http://localhost:8080/registers/' 
```
example result:
```
    {
        "id": 1,
        "name": "Wallet",
        "balance": 1800.000000000
    },
    {
        "id": 2,
        "name": "Savings",
        "balance": 5000.000000000
    },
    {
        "id": 3,
        "name": "Insurance policy",
        "balance": 100.000000000
    },
    {
        "id": 4,
        "name": "Food expenses",
        "balance": 200.000000000
    }
]
```

#####2) To recharge register:
```
POSTMAN
method: PUT
url: http://localhost:8080/registers/{registerId}
body - raw - json:

{
    "value": 1000
}
```
where `{registerId}` is specific register id to recharge. By default at application start up there are 4 registers:
```$xslt
'Wallet' -> with Id 1
'Savings'-> with Id 2
'Insurance policy'-> with Id 3
'Food expenses'-> with Id 4
```    
or curl command from terminal:
```$xslt
curl --location --request PUT 'http://localhost:8080/registers/1' \
--header 'Content-Type: application/json' \
--data-raw '{
    "value": 1000
}'
```
example result:
```$xslt
{
    "id": 1,
    "name": "Wallet",
    "balance": 3800.000000000
}
```
3. To transfer money from one register to another one:
```
POSTMAN
method: POST
url: http://localhost:8080/transfers
body - raw - json:

{
    "fromRegisterId": 1,
    "toRegisterId": 4,
    "transfer": {
        "value": 200
    }
}
```
or curl command from terminal:
```$xslt
curl --location --request POST 'http://localhost:8080/transfers' \
--header 'Content-Type: application/json' \
--data-raw '{
    "fromRegisterId": 1,
    "toRegisterId": 4,
    "transfer": {
        "value": 200
    }
}'
```
example Transfer transaction result:
```$xslt
{
    "id": 12,
    "fromRegister": {
        "id": 1,
        "name": "Wallet",
        "balance": 8200.000000000
    },
    "toRegister": {
        "id": 4,
        "name": "Food expenses",
        "balance": 800.000000000
    },
    "transferValue": {
        "value": 200
    },
    "transactionType": "TRANSFER",
    "transactionDateTime": "2020-11-21T17:47:37.55926"
}
```
