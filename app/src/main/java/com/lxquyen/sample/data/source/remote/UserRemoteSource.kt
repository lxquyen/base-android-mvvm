package com.lxquyen.sample.data.source.remote

import com.lxquyen.sample.data.source.remote.model.UserDto
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.*

interface UserRemoteSource {
    @POST("users")
    fun createUser(@Body userDto: UserDto): Observable<UserDto>

    @GET("users")
    fun readUsers(@Query("page") page: Int = 1, @Query("limit") limit : Int = 10): Observable<List<UserDto>>

    @PUT("users/{id}")
    fun updateUser(@Path("id") id: String?, @Body userDto: UserDto): Observable<UserDto>

    @DELETE("users/{id}")
    fun deleteUser(@Path("id") id: String?): Completable
}