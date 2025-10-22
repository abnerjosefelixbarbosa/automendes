# Automendes

[![build-backend](https://github.com/abnerjosefelixbarbosa/automendes/actions/workflows/build-backend.yml/badge.svg?branch=development)](https://github.com/abnerjosefelixbarbosa/automendes/actions/workflows/build-backend.yml)

# Models

```mermaid
classDiagram

Employee -- EmployeeType
Customer -- CustomerType
Vehicle -- BoxgearType
Vehicle -- VehicleType
SaleVehicle -- SaleVehicleId
SaleVehicle -- PaymentType

Employee "1" --* "*" Sale
Customer "1" --* "*" Sale
Sale "1" -- "*" SaleVehicle
Vehicle "1" -- "*" SaleVehicle
Model "1" --* "*" Vehicle
Brand "1" --* "*" Model

class Customer {
    <<entity>>
    - id: String
    - document: String
    - name: String
    - email: String
    - phone: String 
    - customerType: CustomerType
    - sales: List~Sale~
}

class Employee {
    <<entity>>
    - id: String
    - name: String
    - email: String
    - matriculation: String
    - phone: String
    - birthDate: LocalDate
    - commission: BigDecimal
    - employeeType: EmployeeType
    - sales: List~Sale~
}

class Sale {
    <<entity>>
    - id: String
    - quantity: Integer
    - SaleDateTime: LocalDateTime
    - total: BigDecimal
    - paymentType: PaymentType
    - customer: Customer
    - employee: Employee
    - saleVehicles: List~SaleVehicle~ 
}

class SaleVehicle {
    <<entity>>
    - sale: Sale
    - vehicle: Vehicle
    - paymentType: PaymentType  
}

class SaleVehicleId {
    - saleId: String
    - vehicleId: String
}


class Vehicle {
    <<entity>>
    - id: String
    - plate: String
    - price: BigDecimal
    - model: Model
    - boxgearType: BoxgearType
    - vehicleType: VehicleType
    - saleVehicles: List~SaleVehicle~ 
}

class Model {
    <<entity>>
    - id: String
    - name: String
    - brand: Brand
    - vehicles: List~Vehicle~
}

class Brand {
    <<entity>>
    - id: String
    - name: String
    - models: List~Model~  
}

class PaymentType {
    <<enum>>
    CASH, CREDIT_CARD, DEBIT_CARD, BANK_TRANSFER;
}

class BoxgearType {
     <<enum>>
    AUTO, MANUAL;
}

class EmployeeType {
    <<enum>>
    MANAGER, DEPUTY_MANAGER, SELLER;
}

class CustomerType {
    <<enum>>
    PF, PJ;
}

class VehicleType {
    <<enum>>
    CAR, MOTOCYCLE;
}
```

# Backend

## technologies

- Java
- Spring Boot
- Hibernate/JPA
- Lombok
- PostgreeSQL
- H2
- MVC
- SOLID
- API Rest

# Frontend

# Requests

## Brand

Register Brand

```json
/brands/register-brand

{
  "name": ""
}
```

Update Brand by Id

```json
/brands/update-brand-by-id?id=1

{
  "name": ""
}
```

List Brands

```json
/brands/list-brands
```

## Vehicle

Register Vehicle

```json
/vehicles/register-vehicle

{
  "plate": "",
  "modelName": "",
  "price": 0,
  "boxgearType": "AUTO",
  "vehicleType": "CAR"
}
```

Update Vehicle By Id

```json
/vehicles/update-vehicle-by-id?id=1

{
  "plate": "",
  "modelName": "",
  "price": 0,
  "boxgearType": "AUTO",
  "vehicleType": "CAR"
}
```

List Vehicles

```json
/vehicles/list-vehicles
```

## Model

Register Model

```json
/models/register-model

{
  "name": "",
  "brandName": ""
}
```

Update Model By Id

```json
/models/update-model-by-id?id=1

{
  "name": "",
  "brandName": ""
}
```

List Models

```json
/models/list-models

{
  "name": "string",
  "brandName": "string"
}
```

## Employee

Register Enployee

```json
/employees/register-employee

{
  "name": "",
  "email": "",
  "matriculation": "",
  "phone": "",
  "birthDate": "2025-10-22",
  "commission": 0,
  "employeeType": "MANAGER"
}
```

Update Employee By Id

```json
/employees/update-employee-by-id?id=1

{
  "name": "",
  "email": "",
  "matriculation": "",
  "phone": "",
  "birthDate": "2025-10-22",
  "commission": 0,
  "employeeType": "MANAGER"
}
```

List Models

```json
/models/list-models
```

## Customer

Register Customer

```json
/customers/register-customer

{
  "name": "",
  "email": "",
  "matriculation": "",
  "phone": "",
  "birthDate": "2025-10-22",
  "commission": 0,
  "employeeType": "MANAGER"
}
```

Update Customer By Id

```json
/customers/update-customer-by-id?id=1

{
  "name": "",
  "email": "",
  "matriculation": "",
  "phone": "",
  "birthDate": "2025-10-22",
  "commission": 0,
  "employeeType": "MANAGER"
}
```

Search Customer By Document

```json
/customers/search-customer-by-document?document=1
```
