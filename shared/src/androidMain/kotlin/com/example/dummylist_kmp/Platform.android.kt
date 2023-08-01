package com.example.dummylist_kmp

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()
// actual is the way of telling the compiler that you have provided a platform specific implementation
// and shared module is ready to go.
