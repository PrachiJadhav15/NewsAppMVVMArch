package com.prachi.newsappmvvmarch.data.model

import com.google.gson.annotations.SerializedName

data class TopHeadlineSource(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String = "",
)
