object Vers {
  val compile_sdk = 28
  val min_sdk = 27 // todo minsdk is Build.VERSION_CODES.KITKAT on new MediaPlayer2
  val target_sdk = 28
  val agp = "3.1.4"

  val kotlin = "1.2.60"
  val couroutine = "0.24.0"
}

object Libs {
  val android_plugin = "com.android.tools.build:gradle:${Vers.agp}"
  val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Vers.kotlin}"
  val ktlint_plugin = "gradle.plugin.org.jlleitschuh.gradle:ktlint-gradle:4.1.0"
  val dokka_plugin = "org.jetbrains.dokka:dokka-android-gradle-plugin:0.9.17"

  val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Vers.kotlin}"
  val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Vers.couroutine}"
  val ui_coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Vers.couroutine}"

  val recyclerview = "androidx.recyclerview:recyclerview:1.0.0-rc01"
  val appcompat = "androidx.appcompat:appcompat:1.0.0-rc01"
  val constraint_layout = "androidx.constraintlayout:constraintlayout:2.0.0-alpha2"
  val material = "com.google.android.material:material:1.0.0-rc01"
  val paging = "androidx.paging:paging-runtime:2.0.0-rc01"
  val media2 = "androidx.media2:media2:1.0.0-alpha02"

  val rxjava = "io.reactivex.rxjava2:rxjava:2.2.0"
  val rxandroid = "io.reactivex.rxjava2:rxandroid:2.0.2"

  val junit = "junit:junit:4.12"
  val support_test = "com.android.support.test:runner:1.0.1"
  val espresso = "com.android.support.test.espresso:espresso-core:3.0.1"
  val arch_test = "android.arch.core:core-testing:1.1.1"

  val truth = "com.google.truth:truth:0.40"
  val mockito_kotlin = "com.nhaarman:mockito-kotlin-kt1.1:1.5.0"
  val multidex = "com.android.support:multidex:1.0.3"
}
