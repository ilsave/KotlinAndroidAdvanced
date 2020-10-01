package ru.ilsave.homeworkotuscoroutinesmvvvmretrofit.network

data class InfoAPI(
    val accessibility: Double,
    val activity: String,
    val key: String,
    val link: String,
    val participants: Int,
    val price: Double,
    val type: String
)