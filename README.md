[![Build Status](https://travis-ci.org/islomar/url-shortener-islomar.svg)](https://travis-ci.org/islomar/url-shortener-islomar)
[![Coverage Status](https://coveralls.io/repos/islomar/url-shortener-islomar/badge.svg?branch=master&service=github)](https://coveralls.io/github/islomar/url-shortener-islomar?branch=master)

# Yet Another URL Shortener (YAUS)
URL shortener web application. 
Currently there is no user-friendly interface, you would need to use something like Postman or curl for sending POSTs requests.
 
# How to run it locally
* You will need **Java 8** and **Maven** installed.
* Execute `mvn clean package && java -jar target/url-shortener-1.0.0.jar`
* You can access the application in [http://localhost:5000](http://localhost:5000) 

## How to create a new shortened URL
1. Send a POST request to [http://url-shortener-islomar.herokuapp.com](http://url-shortener-islomar.herokuapp.com) (or [http://localhost:8080](http://localhost:8080) if you're running the web app locally), including in the body the URL that you want to shorten (e.g. `http://www.esailors.de`). You have several ways to do it:
 * E.g. using a curl command like: `curl -isbX POST -d "http://www.king.com" -H "Content-Type:text/plain" http://localhost:8080`
 * Another option is to use some kind of web browser addon like Postman.
2. You will receive a plain text answer with the shortened URL. You can use it directly to be redirected to your original full URL.

## How to use a shortened URL
You have two options to check:
* Either you introduce the shortened URL in a browser,
* or, from CLI, type something like `curl -v localhost:8080/50784f51` and verify the 302 redirection and the shown location.

## Current restrictions
* There is no database persistence, the shortened URLs are stored in-memory, so each time a new deployment/restart were done, everything gets lost.

## Continuous Deployment
The main frameworks, technologies and platforms used have been:
* **Spring Boot** (just to play around for the first time with it... using Spring for somthing like this is definitely overkilling...).
* **Heroku**: I have connected the GitHub account to Heroku, as well to TravisCI (for CI) and coveralls.io (code coverage):
 * Each time a push is done to the GitHub repository, it builds the application and executes all the tests. If the tests execution is successful, 
 then the deployment to Heroku is done... et voilà!!! :-)
 * **Papertrail** addon of Heroku to monitor logs.

## Management Services
* Health: [http://url-shortener-islomar.herokuapp.com/health](http://url-shortener-islomar.herokuapp.com/health)
* Metrics: [http://url-shortener-islomar.herokuapp.com/metrics](http://url-shortener-islomar.herokuapp.com/metrics)
* Info: [http://url-shortener-islomar.herokuapp.com/info](http://url-shortener-islomar.herokuapp.com/info)
* [http://docs.spring.io/spring-boot/docs/1.2.7.RELEASE/reference/htmlsingle/#production-ready-endpoints](http://docs.spring.io/spring-boot/docs/1.2.7.RELEASE/reference/htmlsingle/#production-ready-endpoints)
   
## API documentation
* (What is Swagger?)[http://swagger.io/getting-started-with-swagger-i-what-is-swagger/]
* The goal of (Swagger)[http://swagger.io]™ is to define a standard, language-agnostic interface to REST APIs which allows both humans and computers to discover and understand the capabilities of the service without access to source code, documentation, or through network traffic inspection.
* Technically speaking - Swagger is a formal specification surrounded by a large ecosystem of tools, which includes everything from front-end user interfaces, low-level code libraries and commercial API management solutions.

                   

## Next improvements
* Create a user-friendly interface so that a user can shorten a URL without the need of anything else (e.g. create a simple web form).
* Persist shortened URLs in a database (e.g. PostgreSQL, MongoDB): [http://www.javaadvent.com/2015/12/quick-web-app-prototyping-with-spring-boot-and-mongodb.html](http://www.javaadvent.com/2015/12/quick-web-app-prototyping-with-spring-boot-and-mongodb.html)
* Use integration tests.
* Use JSon to send and receive information (instead of plain text).
* Store Entities instead of a Map<Stirng, URL>, which includes the id and the absolute URL (probably more things in the future, like expiration time). 
* Buy a short domain to make a forward to Heroku (so that I could create shortened URLs like http://isi.co/f3065ee6).
* Check in the DB if the ID already exists before saving (I don't think that with the current algorithm, that's assured).
* Restrict data type accepted by the REST controller.
* Use something like Swagger for documenting the API.
* Create log files.
* Use Spock for testing.
* Use Spring integration tests for the end-to-end testing.
* Sanitize URL for malicious JavaScript.
* Create load tests to check the boundaries of the number of requests allowed (detect concurrency problems).
* New features:
 * Allow to set an expiration time for the shortened URL.
 * Track the use (how many times it was clicked, from which browsers, etc.)
 * Allow users to create ad-hoce shorthened URLs (e.g. iLoveYou for Saint-Valentine presents :-) )
 * Allow users to modify the URL where a shortened URL is pointing (N.B.: think about security issues).



## Heroku 

### Production URL
[http://url-shortener-islomar.herokuapp.com](http://url-shortener-islomar.herokuapp.com)

### Administration
[https://dashboard.heroku.com/apps/url-shortener-islomar/](https://dashboard.heroku.com/apps/url-shortener-islomar/)

### Logs
[https://papertrailapp.com/systems/url-shortener-islomar/events](https://papertrailapp.com/systems/url-shortener-islomar/events)

### Local execution
1. From a console, execute: `heroku local web`
2. From a web browser, access: [http://localhost:5000/](http://localhost:5000/)


### PostgreSQL Database (To Be Done)
* [https://postgres.heroku.com/databases](https://postgres.heroku.com/databases)

## Interesting links
### Heroku
* [https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku](https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku)
* [https://devcenter.heroku.com/articles/getting-started-with-java#deploy-the-app](https://devcenter.heroku.com/articles/getting-started-with-java#deploy-the-app)

### REST services
* [Building REST services](http://spring.io/guides/tutorials/bookmarks/)

#### Exception handling
* [https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc](https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc)
* [http://stackoverflow.com/questions/26134331/how-to-respond-with-http-status-code-in-a-spring-mvc-restcontroller-responsebo](http://stackoverflow.com/questions/26134331/how-to-respond-with-http-status-code-in-a-spring-mvc-restcontroller-responsebo)

### URL Shortening
* [https://en.wikipedia.org/wiki/URL_shortening](https://en.wikipedia.org/wiki/URL_shortening)


### Spring Boot
* [http://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-structuring-your-code.html](http://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-structuring-your-code.html)

#### Testing
* [http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html](http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html)
* [http://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-rest-api/](http://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-rest-api/)
* [http://www.petrikainulainen.net/programming/spring-framework/integration-testing-of-spring-mvc-applications-forms/](http://www.petrikainulainen.net/programming/spring-framework/integration-testing-of-spring-mvc-applications-forms/)
* [http://www.byteslounge.com/tutorials/spring-mvc-requestmapping-consumes-condition-example](http://www.byteslounge.com/tutorials/spring-mvc-requestmapping-consumes-condition-example)
* [https://fbflex.wordpress.com/2013/09/18/testing-spring-boot-applications-with-spock/](https://fbflex.wordpress.com/2013/09/18/testing-spring-boot-applications-with-spock/)
