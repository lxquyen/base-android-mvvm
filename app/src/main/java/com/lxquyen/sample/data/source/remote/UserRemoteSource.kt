package com.lxquyen.sample.data.source.remote

import com.lxquyen.sample.data.source.remote.model.UserDto
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.*

interface UserRemoteSource {
    @POST("users")
    fun createUser(@Body userDto: UserDto): Completable

    @GET("users")
    fun readUsers(): Observable<List<UserDto>>

    @PUT("users/{id}")
    fun updateUser(@Path("id") id: String?, @Body userDto: UserDto): Observable<UserDto>

    @DELETE("users/{id}")
    fun deleteUser(@Path("id") id: String?): Completable
}