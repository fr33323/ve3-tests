
# saucedemo-tests

Automated data-driven login and checkout/order testing for saucedemo.com using Selenium, TestNG, and Allure. This project validates multiple credential sets (valid/invalid) and generates detailed test reports.

### Prerequisites
- Java JDK 23
- Maven 3.9.6+
- IntelliJ IDEA/Eclipse
- Google Chrome(133)

### Installation
- Install dependencies:
```
mvn clean install
```
- Run tests:
```
mvn clean test
```
- Generate Allure report:
```
allure serve target/allure-results
```
