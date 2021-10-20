FROM java:8
EXPOSE 8080
COPY /target/customer-0.0.1-SNAPSHOT.jar  customer.jar
COPY CustomersPhone.db CustomersPhone.db
ENTRYPOINT ["java","-jar","customer.jar"]
