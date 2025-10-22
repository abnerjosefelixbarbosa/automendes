# Automendes

[![build-backend](https://github.com/abnerjosefelixbarbosa/automendes/actions/workflows/build-backend.yml/badge.svg?branch=development)](https://github.com/abnerjosefelixbarbosa/automendes/actions/workflows/build-backend.yml)

# Models

## About

Automendes backend application.

## Class Diagram

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

# Project Resources

## Features

- Register Brand
- Update Brand by Id
- List Brands
- Register Model
- Update Model By Id
- List Models
- Register Vehicle
- Update Vehicle By Id
- List Vehicles
- Register Employee
- Update Employee By Id
- List Employees
- Register Customer
- Update Customer By Id
- Search Customer By Document

## Backend

- Java
- Spring Boot
- Hibernate/JPA
- Lombok
- PostgreeSQL
- H2
- MVC
- SOLID
- API Rest

# Project Execution

- Clone and run in an IDE

```bash
# clonar repositório
git clone https://github.com/abnerjosefelixbarbosa/automendes.git
```

# Author

Abner José Felix Barbosa

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/abner-jose-feliz-barbosa/)
