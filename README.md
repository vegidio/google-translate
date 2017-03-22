# Google Translate

[![JitPack](https://jitpack.io/v/vegidio/google-translate.svg)](https://jitpack.io/#vegidio/google-translate)
[![Apache 2.0](https://img.shields.io/badge/license-Apache_License_2.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)

This library lets you translate texts using the Google Translate API without an API key.

## Installation

### Gradle

Add in your file `build.gradle` at the end of repositories:

```
allprojects {
	repositories {
		// ...
		maven { url "https://jitpack.io" }
	}
}
```

Then add the dependency:

```
dependencies {
	compile 'com.github.vegidio:google-translate:1.0.1'
}
```

### Maven

Add in your file `pom.xml` at the end of repositories:

```xml
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
```

Then add the dependency:

```xml
<dependency>
	<groupId>com.github.vegidio</groupId>
	<artifactId>google-translate</artifactId>
	<version>1.0.1</version>
</dependency>
```

## Usage

```java
Translate translate = new Translate();

// Translating from English to Portuguese
String text = translate.execute("I love cookies", Language.ENGLISH, Language.PORTUGUESE);

// Printing the source and the translated texts
System.out.println("Original text..: " + translate.getSourceText());
System.out.println("Translated text: " + text);
```

More examples can be found in the folder `src/test/java/com/hryun/gtranslate/`.

## License

**google-translate** is released under the Apache License. See [LICENSE](LICENSE.txt) for details.

## Author

Vinicius Egidio ([vinicius.io](http://vinicius.io))
