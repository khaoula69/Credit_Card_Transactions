
# Credit_Card_Transactions_Assessement

## Description
This project represents a Spring boot API managing credit card transactions. It is created to permit the user to list, filter, sort, and paginate credit card transactions.

## Installation Requirements
  * Java 11 or higher
  * Maven
  * Git
## Installation Steps
1) Cloning the project from git
```
git clone https://github.com/khaoula69/Credit_Card_Transactions.git

```
2) Building the project
```
mvn clean install

```
## Running the application 
In order to run the application, execute this command:
```
mvn spring-boot:run

```
## Testing APIs
  To be able to test the apis developed, you can use POSTMAN
  1) Get transactions list
  ```
  GET  http://localhost:8090/creditCardTransaction/transactions

  ```
  2) Get filtered transactions list (filtering parameters are optional)
  ```
  GET  http://localhost:8090/creditCardTransaction/filtredTransactions?[parameters]

  ```
Arguments:
* amount: filter by amount
* merchant: filter by merchant
* status: filter by status

 3) Get filtered, sorted and paginated transactions list (all parameters are optional)
  ```
  GET  http://localhost:8090/creditCardTransaction/filtredSortedTransactions?[parameters]

  ```
Arguments:
* amount: filter by amount
* merchant: filter by merchant
* status: filter by status
* sortField: sorting transaction by field
* page: page number (default value = 1)
* perPage: items per page (default value = 5)
		   
		 




 
	 
