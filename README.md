# senderAPi
Spring Web, H2 Database and Spring JPA Data Dependency is used. 

The data is posted in JSON format to sender API. The API maps it to Transaction POJO and then encrypts the data in Base64 format. The encrypted data is posted to receiver api using REST Template. The receiver api link is configurable from application.properties. 
