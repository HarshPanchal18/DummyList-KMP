package com.example.dummylist_kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
// expect is used inside the commonMain to notify the compiler
// that shared part of your code requires some platform specific implementation
