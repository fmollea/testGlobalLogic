package com.mosh.testgloballogic.domain

import java.io.Serializable

data class LatopItem(
    val description: String,
    val image: String,
    val title: String
) : Serializable