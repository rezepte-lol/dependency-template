import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "17"
}

gradlePlugin {
    plugins.register("dependencies") {
        id = "example.dependencies"
        implementationClass = "example.AppDependenciesPlugin"
    }
}

dependencies {
    implementation("com.android.tools.build:gradle:7.2.0")
}
