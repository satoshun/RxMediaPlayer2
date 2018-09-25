object Vers {
  val compile_sdk = 28
  val min_sdk = 21
  val target_sdk = 28
  val agp = "3.2.0"

  val kotlin = "1.2.71"
}

object Libs {
  val android_plugin = "com.android.tools.build:gradle:${Vers.agp}"
  val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Vers.kotlin}"
  val ktlint_plugin = "gradle.plugin.org.jlleitschuh.gradle:ktlint-gradle:4.1.0"
  val dokka_plugin = "org.jetbrains.dokka:dokka-android-gradle-plugin:0.9.17"

  val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Vers.kotlin}"

  val appcompat = "androidx.appcompat:appcompat:1.0.0"
  val constraint_layout = "androidx.constraintlayout:constraintlayout:2.0.0-alpha2"
  val material = "com.google.android.material:material:1.0.0"
  val media2 = "androidx.media2:media2:1.0.0-alpha03"

  val rxjava = "io.reactivex.rxjava2:rxjava:2.2.0"
  val rxandroid = "io.reactivex.rxjava2:rxandroid:2.0.2"

  val junit = "junit:junit:4.12"
  val test_runner = "androidx.test:runner:1.1.0-alpha4"
  val test_rules = "androidx.test:rules:1.1.0-alpha4"
  val espresso = "com.android.support.test.espresso:espresso-core:3.0.1"

  val robolectric = "org.robolectric:robolectric:4.0-alpha-3"
  val truth = "com.google.truth:truth:0.42"
  val mockito_kotlin = "com.nhaarman:mockito-kotlin-kt1.1:1.5.0"
}
