package com.example.logonrmlocal.githubaac.data.remote

import com.example.logonrmlocal.githubaac.data.local.entity.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    @GET("/users/{user}")
    fun getUser(@Path("user") userId: String): Call<User>
}