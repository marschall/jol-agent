JOL Agent  [![Build Status](https://github.com/marschall/jol-agent/actions/workflows/build.yml/badge.svg?branch=master)](https://github.com/marschall/jol-agent/actions?query=branch%3Amaster) [![Maven Central](https://img.shields.io/maven-central/v/com.github.marschall/jol-agent?color=31c653&label=maven%20central)](https://central.sonatype.com/artifact/com.github.marschall/jol-agent)
=========

A repackaging of [JOL](https://github.com/openjdk/jol) with the `Premain-Class` and `Launcher-Agent` headers set to get around the self-attach warnings.

Usage
-----

```xml

<!-- the actual agent itself, only used in tests -->
<dependency>
  <groupId>com.github.marschall</groupId>
  <artifactId>jol-agent</artifactId>
  <version>0.17.1</version>
  <scope>test</scope>
</dependency>

<!--so that se get the path of the agent artifact as a property -->
<plugin>
  <artifactId>maven-dependency-plugin</artifactId>
  <executions>
    <execution>
      <goals>
        <goal>properties</goal>
      </goals>
    </execution>
  </executions>
</plugin>

<plugin>
  <artifactId>maven-surefire-plugin</artifactId>
  <configuration>
    <!--
        Use agent argument for surefire, requires forking.
        Latter line is for JEP 471 in JDK 23+
    -->
    <argLine>
      -javaagent:@{com.github.marschall:jol-agent:jar}
      --sun-misc-unsafe-memory-access=allow
    </argLine>
    <!-- JOL classes are to be used from the agent, not the classpath -->
    <classpathDependencyExcludes>
      <classpathDependencyExclude>com.github.marschall:jol-agent</classpathDependencyExclude>
    </classpathDependencyExcludes>
  </configuration>
</plugin>

```

