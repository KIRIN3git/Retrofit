package jp.kirin3.retrofit

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    private const val BASE_URL = "https://api.github.com/"
    private const val ACCOUNT_NAME = "KIRIN3git"

    private fun restClient() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun fetchReposList() : Response<List<GitHubData>> {
        val service = restClient().create(GitHubService::class.java)
        return service.fetchReposList(ACCOUNT_NAME, "desc").execute()
    }

    // 他のAPIから取得する場合にはここに追加をしていく
}