package com.kodeleku.mvvm_damd.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class User(
    @PrimaryKey val uid: String,
    val email: String,
)
