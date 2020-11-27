package com.rezkalla.core.domain.usecase

import com.rezkalla.core.domain.repository.PostsRepository
import com.rezkalla.core.utils.TestCoroutineRule
import com.rezkalla.core.utils.TestDataGenerator
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class GetPostsUseCaseTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var repository: PostsRepository

    private lateinit var useCase: GetPostsUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        useCase = GetPostsUseCase(repository)
    }

    @Test
    fun `test get post data from repository return list of posts successfully`() = runBlockingTest {
        //when
        val posts = TestDataGenerator.get()
        Mockito.`when`(repository.getPosts())
            .thenReturn(posts)

        // action
        val result = useCase()

        // verify
        assertTrue(result.isNotEmpty())
        assertEquals(posts, result)
    }

    @Test
    fun `test get post data from repository return empty  list of posts successfully`() = runBlockingTest {
        //when
        Mockito.`when`(repository.getPosts())
            .thenReturn(emptyList())

        // action
        val result = useCase()

        // verify
        assertTrue(result.isEmpty())
    }

}