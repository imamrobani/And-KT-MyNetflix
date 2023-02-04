package id.imrob.mynetflix.core.domain.model

import id.imrob.mynetflix.core.data.local.room.entity.UserEntity

data class User(
  val name: String,
  val email: String,
  val password: String
)

fun User.toUserEntity() = UserEntity(
  this.name,
  this.email,
  this.password
)