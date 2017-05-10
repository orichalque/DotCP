# DotCP
Scans .classpath eclipse files in order to find the jar files

## Example

Using mvn eclipse:eclipse on a maven project generates the following .classpath file: 

```
<?xml version="1.0" encoding="UTF-8"?>
<classpath>
  <classpathentry kind="src" path="src/test/java" output="target/test-classes" including="**/*.java"/>
  <classpathentry kind="src" path="src/test/resources" output="target/test-classes" excluding="**/*.java"/>
  <classpathentry kind="src" path="src/main/java" including="**/*.java"/>
  <classpathentry kind="output" path="target/classes"/>
  <classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER"/>
  <classpathentry kind="var" path="M2_REPO/junit/junit/4.12/junit-4.12.jar" sourcepath="M2_REPO/junit/junit/4.12/junit-4.12-sources.jar"/>
  <classpathentry kind="var" path="M2_REPO/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar"/>
</classpath>
```

DotCP will scan this file, in order to find the jars files declared in this .classpath, and will get their absolute paths:

### Standard Maven Configuration
```
File file = new File(".classpath");
List<File> jars = new DotCPParserBuilder().create().parse(file);
```

The maven jars will be found in : 
* `C:/Users/${username}/.m2/repository` if Windows
* `~/.m2/repository` if Linux

### Specific Maven Configuration

```
File file = new File(".classpath");
List<File> jars = new DotCPParserBuilder().withM2Directory(new File("/.m2/repository").create().parse(file);
```

If the Maven configuration is specific

