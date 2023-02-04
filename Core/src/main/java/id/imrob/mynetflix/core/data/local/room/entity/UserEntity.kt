package id.imrob.mynetflix.core.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.imrob.mynetflix.core.domain.model.User

@Entity(
    tableName = "user"
)
data class UserEntity(
    @ColumnInfo(name = "full_name")
    val fullName: String,

    @ColumnInfo(name = "email")
    @PrimaryKey
    val email: String,

    @ColumnInfo(name = "password")
    val password: String
)

fun UserEntity.toUser() = User(
    this.fullName,
    this.email,
    this.password
)
