**To get started**:

**NOTE:** to modify database connection properties change *application.properties* file

***Server:***

spring-boot application, to run use *mvn spring-boot:run* from server root

by default application accessible at port 8080 (can be change at application.properties file)
```
To see all available endpoints or test them go to *http://localhost:8080/swagger-ui.html*
```

or 

to get cab information (how many trips each medallion has made (cached by default)):

```
curl -X GET \
  'http://localhost:8080/cabtrip?pickupDatetime=2013-12-01&medallion=D7D598CD99978BD012A87A76A7C891B7&medallion=5455D5FF2BD94D10B304A15D4B7F2735' \
  -H 'content-type: application/json'
```  

to ignore the cache use ignoreCache flag:

```
curl -X GET \
  'http://localhost:8080/cabtrip?pickupDatetime=2013-12-01&medallion=D7D598CD99978BD012A87A76A7C891B7&medallion=5455D5FF2BD94D10B304A15D4B7F2735&ignoreCache=true' \
  -H 'content-type: application/json' 
```  

to flush the cache:

```
curl -X DELETE \
  http://localhost:8080/cabtrip \
  -H 'content-type: application/json' 
```  

***Client:***

Doesn't have any maven dependencies, so just build it via maven *mvn clean install* from client root,
find client-1.0-SNAPSHOT.jar at /target. and run:

java -jar client-1.0-SNAPSHOT.jar

**List of available options**:

**NOTE:** only one option should be used at the same time, see examples below

**-info**  prints how many trips each medalion has made, parameters *date=* for pickup_datetime (in yyyy-MM-dd format) for
  *medallion=* coma separated list of medallion identifications, *ignoreCache=* it is **optional** parameter, *true* to ignore the cache,
   *false* to use cached values (that is default if not specified)
example:
```
-info date=2013-12-01 medallion=D7D598CD99978BD012A87A76A7C891B7,5455D5FF2BD94D10B304A15D4B7F2735
```

**-flush** used to clear the cache;

**-init** used to specify URL, it is **optional** parameter if not specified  *http://localhost:8080/cabtrip* is used,
example:
```
-init http://localhost:8080/cabtrip 
```