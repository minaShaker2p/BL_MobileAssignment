package com.rezkalla.core.domain.repository

import com.rezkalla.core.domain.entity.PostEntity


interface PostsRepository {

    suspend fun getPosts(): List<PostEntity>
}