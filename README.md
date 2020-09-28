# BOOKING VEHICLE INSPECTION STATION
##**PROVINCE**

Province Get: http://localhost:8080/province/list

Province Post: http://localhost:8080/province/province
{
  "id": 0,
  "name":"İstanbul"
}

Province Delete: http://localhost:8080/province/{id}

Province Put: http://localhost:8080/province/province

##**DISTRICT**
District Get: http://localhost:8080/district/list

District Post: http://localhost:8080/district/district
{
  "id": 0,
  "name":"Kadıköy",
  "provinceId":1
}

District Delete: http://localhost:8080/district/{id}

District Put: http://localhost:8080/district/district

##**INSPECTION STATION**
Inspection Station Get: http://localhost:8080/inspection/list

Inspection Station Post: http://localhost:8080/inspection/inspection
{
  "id": 0,
  "name":"Çetinkaya A.Ş",
  "districtId":1,
  "vehicleCapacity":10
}

Inspection Station Delete: http://localhost:8080/inspection/{id}

Inspection Station Put: http://localhost:8080/inspection/inspection

##**CUSTOMER**
Customer Get: http://localhost:8080/customer/list

Customer Post: http://localhost:8080/customer/customer
{
  "id": 0,
  "firstName":"Ahmet",
  "lastName":"Yakup",
  "districtId":1
}

Customer Delete: http://localhost:8080/customer/{id}

Customer Put: http://localhost:8080/customer/customer

##**REGISTER**
Register Get: http://localhost:8080/register/list

Register Post: http://localhost:8080/register/register
{
  "id": 0,
  "createDate":"2020-06-17T12:57:54",
  "customerId":1
}

Register Delete: http://localhost:8080/register/{id}

Register Put: http://localhost:8080/register/register

##**RESERVATION**
Reservation Get: http://localhost:8080/reservation/list

Reservation Post: http://localhost:8080/reservation/reservation
{
  "id": 0,
  "licenseOwner":"Sefa",
  "licenseNumber":123,
  "dateTime":"2020-06-17T09:00:00",
  "inspectionStationId":1,
  "customerId":1
}

Reservation Delete: http://localhost:8080/reservation/{id}

Reservation Put: http://localhost:8080/reservation/reservation
