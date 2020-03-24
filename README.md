# Limit requests app
## Installation and deployment
1. Install postgres 
2. Create user `dbuser` with password `pass` 
3. Create database request-counter

    `createdb -U dbuser -O dbuser request-counter`
4. Run app using command `gradlew bootRun`

## API
Call test method

    GET localhost:8080/

If you exceed 100 requests per hour, you'll get HTTP 429.
Otherwise you'll get text "OK"