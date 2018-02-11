# event-service
## Installation and deployment
1. Install postgres 
2. Create user `dbuser` with password `pass` 
3. Create database event-service

    `createdb -U dbuser -O dbuser event-service`
4. Run app using command `gradlew bootRun`

## API
1. To register event

    `GET localhost:8080/event/register`

2. To get event amount for the last specified time

    `GET localhost:8080/event?minutes=x&hours=y`
    where `x` is amount of minutes and `y` is amount of hours