package com.rezkalla.core.data.repository

import com.rezkalla.core.data.api.PostApiService
import com.rezkalla.core.utils.TestCoroutineRule
import com.rezkalla.core.utils.TestDataGenerator
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class PostsRepositoryImplTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var postApiService: PostApiService

    private lateinit var repository: PostsRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = PostsRepositoryImpl(postApiService)
    }

    @Test
    fun `test get posts from post repository and return list of posts successfully`() =
        runBlockingTest {

            // when
            val posts = TestDataGenerator.get()
            Mockito.`when`(postApiService.getPosts()).thenReturn(posts)

            //action
            val results = repository.getPosts()

            //verify
            assertTrue(results.isNotEmpty())
            assertEquals(posts, results)
        }

    @Test
    fun `test get posts from post repository and return empty list  successfully`() =
        runBlockingTest {

            // when
            Mockito.`when`(postApiService.getPosts()).thenReturn(emptyList())

            //action
            val results = repository.getPosts()

            //verify
            assertTrue(results.isEmpty())
        }
}