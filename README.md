# about

abbyy-to-alto is a tiny Java library to convert <a href="https://abbyy.technology/en:features:ocr:xml">abbyy.xml</a> 
to <a href="https://www.loc.gov/standards/alto/">alto.xml</a>.
It supports <a href="http://www.abbyy.com/FineReader_xml/FineReader10-schema-v1.xml">abbyy v10</a>
and <a href="http://www.loc.gov/standards/alto/v2/alto-2-0.xsd">alto v2</a>.

# include with maven
```xml
<repositories>
  <repository>
    <id>MyCoRe HQ</id>
    <url>http://artifactory.mycore.de/mycore-releases</url>
    <snapshots>
      <enabled>false</enabled>
    </snapshots>
  </repository>
  <repository>
    <id>MyCoRe HQ Snapshots</id>
    <url>http://artifactory.mycore.de/mycore-snapshots</url>
    <releases>
      <enabled>false</enabled>
    </releases>
    <snapshots>
      <enabled>true</enabled>
    </snapshots>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>org.mycore</groupId>
    <artifactId>abbyy-to-alto</artifactId>
    <version>0.1-SNAPSHOT</version>
  </dependency>
</dependencies>
```

# use the cli
abbyy-to-alto contains a very simple command line interface

* checkout the project with git
* do a "mvn clean install" in the checked out directory
* use the cli
  * java -jar target/abbyy-to-alto-0.1-SNAPSHOT-jar-with-dependencies.jar convert {source abbyy xml} {target directory}
  * java -jar target/abbyy-to-alto-0.1-SNAPSHOT-jar-with-dependencies.jar directory {source directory} {target directory}
