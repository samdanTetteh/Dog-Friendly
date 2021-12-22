/**
 * Plugins
 */
object BuildPlugins {
    const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerHilt}"
    const val application = "com.android.application:${Versions.android}"
    const val androidLibrary = "com.android.library:${Versions.android}"
    const val gradleBuildTools = "com.android.tools.build:gradle:${Versions.gradle}"
    const val androidKotlin = "org.jetbrains.kotlin.android:${Versions.kotlin}"
    const val kotlinJvm = "org.jetbrains.kotlin.jvm:${Versions.kotlin}"
    const val navSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}

/**
 * Dependencies
 */
object Libs {
    const val androidKtx = "androidx.core:core-ktx:${Versions.androidCore}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompact}"
    const val kotlin ="org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val materialDesign = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val okHttp = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val androidJunitTest = "androidx.test.ext:junit:${Versions.androidTestJunit}"

    const val lifeCycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifeCycle}"

    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomRxJava = "androidx.room:room-rxjava2:${Versions.room}"

    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val retrofitRxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val rxRelay = "com.jakewharton.rxrelay2:rxrelay:${Versions.rxAndroid}"
    const val rxBindings = "com.jakewharton.rxbinding3:rxbinding:${Versions.rxBindings}"

    const val androidHilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"

    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragmentX}"

    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUIKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    const val picasso ="com.squareup.picasso:picasso:${Versions.picasso}"

    const val androidJunitTesting = "androidx.test.ext:junit-ktx:${Versions.androidXTestExtKotlinRunnerVersion}"

    const val javaxInject = "javax.inject:javax.inject:1"

    const val mockitoKotlin = "com.nhaarman:mockito-kotlin:1.5.0"
    const val mockK = "io.mockk:mockk:1.9.3"

    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"


    // Instrumentation tests
    const val junit = "junit:junit:${Versions.jUnit}"
    const val googleTruth = "com.google.truth:truth:${Versions.googleTruth}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockito}"
    const val androidxTestCore = "androidx.test:core:${Versions.coreAndroidxTesting}"
    const val androidxTestRunner = "androidx.test:runner:${Versions.coreAndroidxTesting}"
    const val androidxTestRules = "androidx.test:rules:${Versions.coreAndroidxTesting}"
    const val fragmentTesting ="androidx.fragment:fragment-testing:${Versions.fragmentX}"
    const val navigationTesting = "androidx.navigation:navigation-testing:${Versions.navigation}"
    const val androidArchCoreTesting = "androidx.arch.core:core-testing:${Versions.archCoreTesting}"
    const val espressoTest = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val espressoTestContrib = "androidx.test.espresso:espresso-contrib:${Versions.espressoCore}"
    const val espressoIntents = "androidx.test.espresso:espresso-intents:${Versions.espressoCore}"
    const val hiltTesting  = "com.google.dagger:hilt-android-testing:${Versions.daggerHilt}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.mockServer}"
    const val okHttpIdleResource= "com.jakewharton.espresso:okhttp3-idling-resource:1.0.0"
    const val espressoIdleResource = "androidx.test.espresso:espresso-idling-resource:${Versions.espressoCore}"
    const val androidTestCore = "androidx.test:core-ktx:${Versions.androidxCoreTest}"
    const val roomTesting = "androidx.room:room-testing:${Versions.room}"




}