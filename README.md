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
*  Implement API as **_Reactive Streams_** API.

### implementation (services)

* [Data inject service](https://github.com/clearlifezhang/topping-injector)
* [Data process and Redis service](https://github.com/clearlifezhang/topping-tracker-redis-service)
* [Topping tracker service](https://github.com/clearlifezhang/pizza-topping-service)
* [Topping tracker service client](https://github.com/clearlifezhang/topping-service-clients)

### containerize services and deploy to a Kubernetes cluster (local one node cluster)
* pull redis-server image from Docker Hub
  * ```docker login ```
  * ```docker pull redis:latest```
* build data-process-redis-service image (under the directory where pom.xml is located)
  * ```docker build -t data-process-redis . ```
* build data-injector
  * ```docker build -t data-injector . ```
* build topping-service image
  * ```docker build -t topping-service .```
* deploy redis-server container
  * ```kubectl apply -f redis_redis_server_deployment.yaml```
* deploy data-process-redis
  * ```kubectl apply -f data_process_redis_deployment.yaml```
* deploy data-injector 
  * ```kubectl apply -f data_injector_deployment.yaml```
* deploy data-process-redis
  * ```kubectl apply -f data_process_redis_deployment.yaml```
* check if all services are deployed properly
  * ```kubectl get pods```
* check if the api service backend is working properly
  * check from browser
    * ```http://localhost:30080/metrics/onions```
  * check from client
    * run the two integration tests in toppingserviceclient
    
### deploy to GCP and secure this API service backend application with HTTPS
* revert redis-service service type from NodePort back to ClusterIP
* configure GKE (Google Kubernetes Engine) ingress controller
* Update the DNS settings for the domain to point to the static IP address created
* URL for the API endpoint
  * ```https://clearlife.com/metrics/onions```


