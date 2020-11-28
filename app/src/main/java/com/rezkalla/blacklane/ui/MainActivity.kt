package com.rezkalla.blacklane.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rezkalla.blacklane.R
import com.rezkalla.blacklane.presentation.PostsViewModel
import com.rezkalla.blacklane.utils.VerticalSpaceItemDecoration
import com.rezkalla.blacklane.utils.ViewModelFactory
import com.rezkalla.blacklane.utils.afterTextChanged
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    private val postsAdapter = PostsAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<PostsViewModel>

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(PostsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel.postsLiveData.observe(this, Observer {
            postsAdapter.update(it)
        })
        edtFilter.afterTextChanged {
            viewModel.filterPosts(it)
        }
    }

    private fun setupRecyclerView() {
        rclPosts.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            addItemDecoration(VerticalSpaceItemDecoration(24))
            adapter = postsAdapter
        }
        swipePosts.setOnRefreshListener {
            viewModel.refresh()
            swipePosts.isRefreshing=false
        }
    }
}