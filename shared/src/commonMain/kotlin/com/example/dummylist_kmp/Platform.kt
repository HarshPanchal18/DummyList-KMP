package com.example.dummylist_kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform