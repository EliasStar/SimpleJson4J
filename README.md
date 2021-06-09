# SimpleJson4J
> Simple yet general JSON library for Java providing a toJson and fromJson method

## Downloads
### Jar
Releases can be downloaded [here](https://github.com/EliasStar/SimpleJson4J/releases).
The javadoc can be found in the "javadoc" suffix jar and the "sources" suffix jar includes the raw, uncompiled source code for reference.

### Maven
#### pom.xml
Add the following under `project/repositories` tag:
```xml
<repository>
    <id>github</id>
    <name>SimpleJson4J GitHub Maven Packages</name>
    <url>https://maven.pkg.github.com/EliasStar/SimpleJson4J</url>
</repository>
```
Furthermore add the following under `project/dependencies` tag:
```xml
<dependency>
    <groupId>eliasstar</groupId>
    <artifactId>simple-json</artifactId>
    <version>VERSION</version>
</dependency>
```
Replace `VERSION` with a version found under releases, preferably the latest.

#### settings.xml
Is located under `~/.m2/settings.xml`.
Add the following under `settings/servers` tag:
```xml
<server>
    <id>github</id>
    <username>YOUR_USERNAME</username>
    <password>YOUR_PERSONAL_ACCESS_TOKEN</password>
</server>
```
Be sure to replace `YOUR_USERNAME` and `YOUR_PERSONAL_ACCESS_TOKEN`.

## License
SimpleJson4J - Simple yet general JSON library for Java providing a toJson and fromJson method <br>
Copyright (C) 2021 Elias*

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with this program. If not, see <https://www.gnu.org/licenses/>.
