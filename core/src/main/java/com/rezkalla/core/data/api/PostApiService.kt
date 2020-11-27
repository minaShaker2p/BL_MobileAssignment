package com.rezkalla.core.data.api

import com.rezkalla.core.domain.entity.PostEntity
import retrofit2.http.GET

interface PostApiService {

    @GET("posts")
    suspend fun getPosts(): List<PostEntity>
}