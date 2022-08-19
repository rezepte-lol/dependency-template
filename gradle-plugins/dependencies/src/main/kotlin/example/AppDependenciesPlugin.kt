@file:Suppress("Unused")

package example

import org.gradle.api.Plugin
import org.gradle.api.Project

class AppDependenciesPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        // noop
    }

    object Dependencies {
        const val MinimumSdkVersion = 29
        const val CompileSdkVersion = 32
        const val TargetSdkVersion = 31

        object KotlinX {
            fun coroutines(target: String) = "org.jetbrains.kotlinx:kotlinx-coroutines-$target:1.6.1"
            object Test {
                val coroutinesTest = coroutines("test")
            }
        }

        object Android {
            const val desugaring = "com.android.tools:desugar_jdk_libs:1.1.5"
            const val appcompat = "androidx.appcompat:appcompat:1.4.1"
            const val legacySupport = "androidx.legacy:legacy-support-v4:1.0.0"
            const val coreKtx = "androidx.core:core-ktx:1.7.0"

            fun lifecycle(module: String) = "androidx.lifecycle:lifecycle-$module:2.4.0"

            const val composeActivity = "androidx.activity:activity-compose:1.4.0"

            object Test {
                const val runner = "androidx.test:runner:1.4.1-alpha03"
                const val orchestrator = "androidx.test:orchestrator:1.4.1-beta01"
                const val archCore = "androidx.arch.core:core-testing:2.1.0"
                const val core = "androidx.test:core:1.4.1-alpha03"
                const val junitExt = "androidx.test.ext:junit:1.1.3"
            }
        }

        const val composeVersion = "1.2.0"

        object Compose {
            const val animation = "androidx.compose.animation:animation:$composeVersion"
            const val foundation = "androidx.compose.foundation:foundation:$composeVersion"
            const val material = "androidx.compose.material:material:$composeVersion"
            const val runtime = "androidx.compose.runtime:runtime:$composeVersion"
            const val ui = "androidx.compose.ui:ui:$composeVersion"
            const val uiTooling = "androidx.compose.ui:ui-tooling:$composeVersion"
            const val preview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"

            fun accompanist(module: String) = "com.google.accompanist:accompanist-$module:0.24.13-rc"

            object Test {
                const val ui = "androidx.compose.ui:ui-test:$composeVersion"
                const val junit4 = "androidx.compose.ui:ui-test-junit4:$composeVersion"
            }
        }

        object Test {
            const val junit4 = "junit:junit:4.13.2"
        }
    }
}

object App {
    fun kotlinX(init: AppDependenciesPlugin.Dependencies.KotlinX.() -> Unit) =
        AppDependenciesPlugin.Dependencies.KotlinX.init()

    fun kotlinXTest(init: AppDependenciesPlugin.Dependencies.KotlinX.Test.() -> Unit) =
        AppDependenciesPlugin.Dependencies.KotlinX.Test.init()

    fun android(init: AppDependenciesPlugin.Dependencies.Android.() -> Unit) =
        AppDependenciesPlugin.Dependencies.Android.init()

    fun androidTest(init: AppDependenciesPlugin.Dependencies.Android.Test.() -> Unit) =
        AppDependenciesPlugin.Dependencies.Android.Test.init()

    fun compose(init: AppDependenciesPlugin.Dependencies.Compose.() -> Unit) =
        AppDependenciesPlugin.Dependencies.Compose.init()

    fun composeTest(init: AppDependenciesPlugin.Dependencies.Compose.Test.() -> Unit) =
        AppDependenciesPlugin.Dependencies.Compose.Test.init()

    fun test(init: AppDependenciesPlugin.Dependencies.Test.() -> Unit) =
        AppDependenciesPlugin.Dependencies.Test.init()
}

fun app(init: App.() -> Unit) = App.init()
val app = AppDependenciesPlugin.Dependencies
