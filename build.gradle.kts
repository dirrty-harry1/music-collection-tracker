plugins {
    java
    application
    eclipse
    id("com.diffplug.spotless") version "5.17.0"    
}

repositories {
    mavenCentral()
}

spotless {
	java {
		googleJavaFormat()
	}
}

dependencies {
    implementation("com.itextpdf:itextpdf:5.5.13")
    
    testImplementation("junit:junit:4.12")
}

application {    
    mainClassName = "com.dirrtyharry.music.collection.tracker.App"
}
