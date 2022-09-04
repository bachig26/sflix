package com.tanasi.sflix.models

import com.tanasi.sflix.adapters.SflixAdapter

class Episode(
    val id: String,
    val number: Int,
    val title: String = "",
    val poster: String = "",

    val servers: List<Server> = listOf(),
) : SflixAdapter.Item {


    override var itemType = SflixAdapter.Type.EPISODE
}