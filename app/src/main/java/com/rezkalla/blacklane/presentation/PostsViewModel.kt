package com.rezkalla.blacklane.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezkalla.blacklane.model.PostUI
import com.rezkalla.blacklane.model.map
import com.rezkalla.core.domain.entity.PostEntity
import com.rezkalla.core.domain.usecase.GetPostsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostsViewModel @Inject constructor(private val getPostsUseCase: GetPostsUseCase) :
    ViewModel() {

    /**
     *  can change the value privately with _postsLiveData.value = "value"
     *  and expose only an immutable LiveData (so Activity or Fragment can not change its value ).
     */
    private val _postsLiveData = MutableLiveData<List<PostUI>>()
    val postsLiveData: LiveData<List<PostUI>> get() = _postsLiveData


    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch {
            filter()
        }
    }

    private suspend fun filter() = withContext(Dispatchers.IO) {
        val posts = getPostsUseCase().map {
            it.map()
        }
        _postsLiveData.postValue(posts)
    }

    fun refresh() {
        getPosts()
    }

}