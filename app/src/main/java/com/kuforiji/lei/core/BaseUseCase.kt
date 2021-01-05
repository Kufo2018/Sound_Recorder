package com.kuforiji.lei.core

interface BaseUseCase {

    interface GetUseCase<Param, Result> : BaseUseCase {
        suspend fun getData(param: Param): Result
    }

    interface PostUseCase<Param, Result> : BaseUseCase {
        suspend fun postData(param: Param): Result
    }
}