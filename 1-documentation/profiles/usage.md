## Why do we need profiles?
To change configuration easily by using different `.properties` files for different environments.

## What profiles are present in application?
At the moment there are two profiles if our application:
1. `cloud` - for production environment with appropriate configuration in `application-cloud.properties`. 
2. `debug` - for test environment with appropriate configuration in `application-debug.properties`.

These profiles are situated in each service.

## How can we run application with specific profile?
1. In menu bar chose `Run` -> chose `Edit Configurations...` in dropdown menu. A `Run/Debug Configurations` window will be opened. 
2. In `Run/Debug Configurations` window add new VM option (if `VM options` field is not visible, click 
`Modify options` -> chose `Add VM options` in dropdown menu)

`-Dspring.profiles.active={$name_profile}`, 

e.g. `-Dspring.profiles.active=debug` and then run an application.
As a result a row `The following 1 profile is active:  "debug"` will be printed in console logs.