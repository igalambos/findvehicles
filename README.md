 # Car data API

The purpose of this project is to build a working API that will return consolidated data that has been provided by 
third parties.

## Understanding the task

The project can process csv files from third parties containing data of cars.  The Csv files should have header to 
identify the following properties: make, model, mileage, term and price. Any other headers will also be read as 
"other information". If two cars have the same make, model, mileage and term, it is assumed that it is the same car.

### Petclinic ER Model

![alt ermodel](er-diagram.png)

## Running petclinic locally

### With maven command line
```
git clone https://github.com/igalambos/findvehicles.git
cd findvehicles
./mvnw spring-boot:run
```

You can then access petclinic here: [http://localhost:8090/findvehicles](http://localhost:8090/findvehicles)

The API accepts 4 parameters:
 - make
 - model
 - mileage
 - term

## Configuration

In its default configuration the application can process the following header names:
 - make: "make", "name"
 - model: "model", "type"
 - mileage: "mileage"
 - term: "term"
 
 If you want to process a file with differently named headers, it can be configured in the application.properties file:
 
```
headers.makeHeaders[0]=make
headers.makeHeaders[1]=name
headers.modelHeaders[0]=model
headers.modelHeaders[1]=type
headers.termHeaders[0]=term
headers.mileageHeaders[0]=mileage
headers.priceHeaders[0]=price
```


  

