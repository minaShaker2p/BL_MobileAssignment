package com.rezkalla.blacklane.model

import com.rezkalla.core.domain.entity.PostEntity


data class PostUI(val postId:Int,val title: String, val body: String)

fun PostEntity.map(): PostUI {
    return PostUI(this.id,this.title, this.body)
}
