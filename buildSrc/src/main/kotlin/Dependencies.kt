/**
 * Plugins
 */
object BuildPlugins {
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
}

/**
 * Dependencies
 */
object Libs {
    const val android = "androidx.core:core-ktx:${Versions.androidCore}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompact}"
    const val kotlin ="org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val materialDesign = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val espressoTest = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val androidJunitTest = "androidx.test.ext:junit:${Versions.androidTestJunit}"
    const val junit = "junit:junit:${Versions.jUnit}"

}