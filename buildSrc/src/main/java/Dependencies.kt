object Vers {
  const val compile_sdk = 28
  const val min_sdk = 21
  const val target_sdk = 28

  const val kotlin = "1.3.11"
}

object Libs {
  const val android_plugin = "com.android.tools.build:gradle:3.4.0-alpha09"
  const val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Vers.kotlin}"
  const val ktlint_plugin = "gradle.plugin.org.jlleitschuh.gradle:ktlint-gradle:4.1.0"
  const val dokka_plugin = "org.jetbrains.dokka:dokka-android-gradle-plugin:0.9.17"

  const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Vers.kotlin}"

  const val appcompat = "androidx.appcompat:appcompat:1.0.0"
  const val constraint_layout = "androidx.constraintlayout:constraintlayout:2.0.0-alpha2"
  const val material = "com.google.android.material:material:1.0.0"

  const val media2 = "androidx.media2:media2:1.0.0-alpha04"
  const val media2_exoplayer = "androidx.media2:media2-exoplayer:1.0.0-alpha01"

  const val rxjava = "io.reactivex.rxjava2:rxjava:2.2.0"
  const val rxandroid = "io.reactivex.rxjava2:rxandroid:2.0.2"

  const val junit = "junit:junit:4.12"
  const val test_runner = "androidx.test:runner:1.1.1"
  const val test_rules = "androidx.test:rules:1.1.1"
  const val test_junit_runner = "androidx.test.ext:junit:1.1.0"
  const val espresso = "com.android.support.test.espresso:espresso-core:3.0.1"

  const val robolectric = "org.robolectric:robolectric:4.1"
  const val truth = "com.google.truth:truth:0.42"
}
