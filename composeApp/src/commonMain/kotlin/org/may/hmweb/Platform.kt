package org.may.hmweb

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun getScreenWidth(): Int