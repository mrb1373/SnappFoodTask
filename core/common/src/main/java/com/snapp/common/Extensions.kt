package com.snapp.common


fun String.retrieveId(): String {
    val regex = """.*/(\d+)/""".toRegex()
    val match = regex.find(this)
    val id = match?.groupValues?.get(1)
    return id ?: "0"
}

fun Int.cmToInches(): Int {
    return (this * 0.393701).toInt()
}

fun Int.cmToFeet(): Int {
    return (this * 0.0328084).toInt()
}
