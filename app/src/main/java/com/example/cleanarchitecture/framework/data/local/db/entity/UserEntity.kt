package com.example.cleanarchitecture.framework.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @ColumnInfo(name = "avatar") val avatar: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "first_name") val firstName: String,
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "last_name") val lastName: String
)