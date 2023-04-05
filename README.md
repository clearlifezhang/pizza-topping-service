# pizza-topping-service

service to generate metrics of pizza toppings (Kotlin REST and Rsocket service)

This is part of **_the Data Processing Pipeline + Web Application Back-end Project_**, as described below.

## Background

build a backend app that can help solve the following problem
The business has decided that it wants to expand into other industries and to open a
pizzeria. After collaborating with multiple food supplier services in the area, we realized that we
need to figure out which toppings customers would want so that we know what to order. To
facilitate this, another team has built an API which allows users to submit their
email and the list of toppings they are interested in. We must retrieve the data and perform
some basic analysis as to what toppings to order.

### Requirements:

*  Create a pipeline that will do the following:
   * retrieve data from an API endpoint which returns a Json object containing a list of
tuples (string email, string[] toppings).
   * Create and persist the following metrics
       * Total Count per topping
       * Unique user count per topping
       
*  Create an endpoint which allows users to retrieve:
   * Total count per topping
   * Unique user count per topping
   * Most popular topping(s)
   * Least popular topping(s)
   
*  Implementation of the pipeline can use any appropriate framework that could run in a
kubernetes container.

*  Endpoint implementation must happen using Kotlin & Spring Boot.

### implementation (services)

* [Data inject service](https://github.com/clearlifezhang/topping-injector)
* [Data process and Redis service](https://github.com/clearlifezhang/topping-tracker-redis-service)
* [Topping tracker service](https://github.com/clearlifezhang/pizza-topping-service)
* [Topping tracker service client](https://github.com/clearlifezhang/topping-service-clients)

### start the pipeline
* start Redis server from command line interface (default port: 6379)
  ```$ redis-server ```
* start topping-tracker-redis-service at port 6060
* start topping-injector at port 5050
* start pizza-topping-service at port 8080 (REST service) and 7070(RSocket service)
  * check REST service in browser
   ```http://localhost:8080/metrics/Bacon```
* Run end to end integration tests in toppingmetricsclient

