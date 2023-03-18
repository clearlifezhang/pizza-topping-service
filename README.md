# pizza-topping-service

service to generate metrics of pizza toppings (Kotlin REST and Rsocket service)

## Background

build a backend app that can help solve the following problem
The business has decided that it wants to expand into other industries and to open a
pizzeria. After collaborating with multiple food supplier services in the area, we realized that we
need to figure out which toppings customers would want so that we know what to order. To
facilitate this, another team has built an API which allows users to submit their
email and the list of toppings they are interested in. We must retrieve the data and perform
some basic analysis as to what toppings to order.

### Requirements:

* 1. Create a pipeline that will do the following:
   * a. retrieve data from an API endpoint which returns a Json object containing a list of
tuples (string email, string[] toppings).
   * b. Create and persist the following metrics
       * i. Total Count per topping
       * ii. Unique user count per topping
       
* 2. Create an endpoint which allows users to retrieve:
   * a. Total count per topping
   * b. Unique count per topping
   * c. Most popular topping(s)
   * d. Least popular topping(s)
   
* 3. Implementation of the pipeline can use any appropriate framework that could run in a
kubernetes container.

* 4. Endpoint implementation must happen using Kotlin & Spring Boot.
