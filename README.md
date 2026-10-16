Service: Catalog  
Port: 8081  
Endpoints:
- /products
    - GET: Get all product in catalog
    - POST: Add one product to catalog
- /products/min-price?price=1000
  - GET: Retrieve products with a price greater than or equal to the specified price.
- /products/max-price?price=3000
  - GET: Retrieve products with a price less than or equal to the specified price.
- /products/by-category?name=Laptop
  - GET: Retrieve products belonging to a specific category.



```
CICD-1 Lab 1
Browser / Swagger
|
+--> Catalog Service :8081 --> temporary Product List<>
|
+--> Order Service :8082 --> temporary Order List<>
Separate GitHub repositories
Separate open pull requests
Week 2: add JPA + H2
Week 3: add Order -> Catalog communication with OpenFeign
```

