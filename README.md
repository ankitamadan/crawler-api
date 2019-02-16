# Crawl Service
1. Crawl a sports orientated website for sporting events and save to database ie. crawl ESPN and save 3 ~ 5 games.

2.  Provide an API to access the crawled data stored in the database

* Database and API can be local or hosted.

The API should be written in Java, the crawler can be any technology they desire to use.

### Prerequisites

Java 1.8

### Database

Postgres

change application.properties for db configuration of postgres

## Getting started
```bash
git clone https://github.com/ankitamadan/crawler-api.git
cd crawler-api
```

### Build locally

In your terminal run `./gradlew clean test build`

If this runs successfully we will have verified that your command line is setup correctly.

### Run locally

In your terminal run `./gradlew bootRun`. 

1. navigate to http://localhost:8000/swagger-ui.html in your browser
to test the app is running.

2. navigate to http://localhost:8000/games/list to view list of games
