package com.rezkalla.core.data.repository

import com.rezkalla.core.data.api.PostApiService
import com.rezkalla.core.domain.entity.PostEntity
import com.rezkalla.core.domain.repository.PostsRepository

class PostsRepositoryImpl(private val service: PostApiService):PostsRepository  {
    override suspend fun getPosts(): List<PostEntity> {
        return service.getPosts()
    }
}