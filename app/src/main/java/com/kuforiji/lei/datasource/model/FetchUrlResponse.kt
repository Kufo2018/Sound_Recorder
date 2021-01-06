package com.kuforiji.lei.datasource.model

data class FetchUrlResponse(
    val fileName: String,
    val url: String,
    //val eachObject: List<FetchUrlResponseObject>
)

data class FetchUrlResponseObject(
    val fileName: String,
    val url: String
)