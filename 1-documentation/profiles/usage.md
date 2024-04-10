## Why do we need profiles?
To change configuration in `.properties` files easily for different environments.

## What profiles are present in application?
At the moment there are two profiles if our application:
1. `cloud` - for production environment with appropriate configuration in `application-cloud.properties`. 
2. `debug` - for test environment with appropriate configuration in `application-debug.properties`.

These profiles are situated in each service.

## How can we run application with specific profile?
Append parameter -P`profile-name` while packaging an application.
For example:

```mvn clean package -Pcloud```

## Default profile
`debug` profile will be used by default if we don't specify which one to use.