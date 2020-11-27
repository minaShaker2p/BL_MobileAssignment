package com.rezkalla.core.domain.usecase

import com.rezkalla.core.domain.repository.PostsRepository
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class GetPostsUseCaseTest {

    @Mock
    private lateinit var repository: PostsRepository

    private lateinit var useCase: GetPostsUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        useCase=GetPostsUseCase(repository)
    }

}