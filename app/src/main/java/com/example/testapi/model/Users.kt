package com.example.testapi.model

import java.util.*

data class Users (
    val id: Int, // database entity
    val name: String,
    val birthdate: Date,
    val results: List<Result>
)

