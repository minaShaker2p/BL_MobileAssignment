package com.rezkalla.blacklane.di

import com.rezkalla.core.data.api.PostApiService
import com.rezkalla.core.data.repository.PostsRepositoryImpl
import com.rezkalla.core.domain.repository.PostsRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providePostsRepository(postApiService: PostApiService): PostsRepository {
        return PostsRepositoryImpl(postApiService)
    }
}