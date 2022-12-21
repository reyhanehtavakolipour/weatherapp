import Versions.v_android_arch_core
import Versions.v_android_test_coroutine
import Versions.v_androidx_activity
import Versions.v_androidx_arch_core
import Versions.v_androidx_compat
import Versions.v_androidx_constraintlayout
import Versions.v_androidx_core
import Versions.v_androidx_test_core
import Versions.v_androidx_test_espresso
import Versions.v_androidx_test_junit
import Versions.v_compose
import Versions.v_glide_compiler
import Versions.v_glied
import Versions.v_google_material
import Versions.v_hilt_android
import Versions.v_kapt_hilt_android
import Versions.v_navigation
import Versions.v_okhttp
import Versions.v_okhttp_interceptor
import Versions.v_retrofit
import Versions.v_retrofit_gson
import Versions.v_room
import Versions.v_swipe_refresh_layout
import Versions.v_test_coroutine
import Versions.v_test_junit

object Deps{
    const val androidx_core = "androidx.core:core-ktx:$v_androidx_core"
    const val androidx_compat = "androidx.appcompat:appcompat:$v_androidx_compat"
    const val google_material = "com.google.android.material:material:$v_google_material"
    const val androidx_constraintlayout = "androidx.constraintlayout:constraintlayout:$v_androidx_constraintlayout"
    const val test_junit = "junit:junit:$v_test_junit"
    const val androidx_test_junit = "androidx.test.ext:junit:$v_androidx_test_junit"
    const val androidx_test_espresso ="androidx.test.espresso:espresso-core:$v_androidx_test_espresso"
    const val androidx_test_core = "androidx.test:core:$v_androidx_test_core"
    const val androidx_arch_core = "androidx.arch.core:core-testing:$v_androidx_arch_core"
    const val android_arch_core_test = "android.arch.core:core-testing:$v_android_arch_core"
    const val jetbrain_coroutine_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$v_test_coroutine"
    const val android_jetbrain_coroutine_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$v_android_test_coroutine"
    const val androidx_activity = "androidx.activity:activity-ktx:$v_androidx_activity"
    const val hilt_android = "com.google.dagger:hilt-android:$v_hilt_android"
    const val kapt_hilt_android = "com.google.dagger:hilt-android-compiler:$v_kapt_hilt_android"
    const val retrofit = "com.squareup.retrofit2:retrofit:$v_retrofit"
    const val retrofit_gson = "com.squareup.retrofit2:converter-gson:$v_retrofit_gson"
    const val okhttp = "com.squareup.okhttp3:okhttp:$v_okhttp"
    const val okhttp_interceptor = "com.squareup.okhttp3:logging-interceptor:$v_okhttp_interceptor"
    const val glied = "com.github.bumptech.glide:glide:$v_glied"
    const val glide_compiler = "com.github.bumptech.glide:compiler:$v_glide_compiler"
    const val swipe_refresh_layout = "androidx.swiperefreshlayout:swiperefreshlayout:$v_swipe_refresh_layout"
    const val room_db = "androidx.room:room-ktx:$v_room"
    const val room_compiler = "androidx.room:room-compiler:$v_room"
    const val navigation = "androidx.navigation:navigation-fragment-ktx:$v_navigation"
    const val navigation_ui = "androidx.navigation:navigation-ui-ktx:$v_navigation"
    const val compose_material = "androidx.compose.material:material:$v_compose"
    const val navigation_arg = "androidx.navigation:navigation-safe-args-gradle-plugin:$v_navigation"
}