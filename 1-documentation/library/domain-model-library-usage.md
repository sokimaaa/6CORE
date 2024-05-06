# Domain Model 6Core Usage Documentation

To be able use generated immutable objects with mupstruct add folowing dependencies to annotationProcessorPaths.

```
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <configuration>
          <source>17</source>
          <target>17</target>
          <annotationProcessorPaths>
            ...
            <path>
              <groupId>org.mapstruct</groupId>
              <artifactId>mapstruct-processor</artifactId>
              <version>1.5.5.Final</version>
            </path>
            <path>
              <groupId>org.immutables</groupId>
              <artifactId>value</artifactId>
              <version>2.10.1</version>
            </path>
            ...
          </annotationProcessorPaths>
        </configuration>
      </plugin>
    </plugins>
  </build>
``` 