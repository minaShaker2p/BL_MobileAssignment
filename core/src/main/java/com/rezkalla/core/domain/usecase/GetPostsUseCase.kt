package com.rezkalla.core.domain.usecase

import com.rezkalla.core.domain.entity.PostEntity
import com.rezkalla.core.domain.repository.PostsRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val repository: PostsRepository) {

    suspend operator fun invoke(): List<PostEntity> {
        return repository.getPosts()
    }
}