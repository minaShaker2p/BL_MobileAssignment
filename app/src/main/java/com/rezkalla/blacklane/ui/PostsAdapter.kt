package com.rezkalla.blacklane.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rezkalla.blacklane.R
import com.rezkalla.blacklane.model.PostUI
import kotlinx.android.synthetic.main.item_post.view.*

class PostsAdapter(private val clickListener: OnPostClickListener) :
    RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    private val posts = mutableListOf<PostUI>()

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: PostUI) {
            itemView.tvTitle.text = post.title
            itemView.tvBody.text = post.body
        }
    }

    fun update(list: List<PostUI>) {
        posts.clear()
        posts.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(post = posts[position])
        holder.itemView.setOnClickListener {
            clickListener.onClick(postId = posts[position].postId)
        }
    }
}

interface OnPostClickListener {
    fun onClick(postId: Int)
}