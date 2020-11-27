package com.rezkalla.blacklane.model

import com.rezkalla.core.domain.entity.PostEntity


data class PostUI(val title: String, val body: String)

fun PostEntity.map(): PostUI {
    return PostUI(this.title, this.body)
}
