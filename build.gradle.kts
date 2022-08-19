plugins {
    kotlin("android") version "1.7.0" apply false
    id("com.android.application") version "7.2.0" apply false
}

val ktlintMain by configurations.creating
val ktlintRules by configurations.creating

dependencies {
    ktlintMain("com.pinterest:ktlint:0.46.1") {
        attributes {
            attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.SHADOWED))
        }
    }
}

val sourcesKt = listOf(
    "app/src/**/**/*.kt",
    "gradle-plugins/*/src/**/*.kt",

    "**/*.gradle.kts"
)

fun ktlintCreating(format: Boolean, sources: List<String>) =
    tasks.creating(JavaExec::class) {
        description = "Fix Kotlin code style deviations."
        classpath = ktlintMain + ktlintRules
        main = "com.pinterest.ktlint.Main"
        args = mutableListOf<String>().apply {
            if (format) add("-F")
            addAll(sources)
        }
    }

val ktlint by ktlintCreating(format = false, sources = sourcesKt)
val ktlintFormat by ktlintCreating(format = true, sources = sourcesKt)

tasks.register("clean", Delete::class) {
    rootProject.allprojects.forEach {
        delete(it.buildDir)
    }
    delete("gradle-plugins/dependencies/build")
}
