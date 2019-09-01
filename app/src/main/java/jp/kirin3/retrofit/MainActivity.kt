package jp.kirin3.retrofit


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handler = Handler()

        // メインスレッドでネットワーク通信を行うことができない
        thread {
            try {
                val response = APIClient.fetchReposList()
                // とりあえず1つだけ表示させてみる
                val firstRepos = response.body()!![0]

                // 別スレッドからUI操作ができないのでhandlerを使用する
                handler.post(Runnable {
                    name_text.text = firstRepos.name
                    description_text.text = firstRepos.description
                    language_text.text = firstRepos.language
                    url_text.text = firstRepos.url
                })

                Log.d("DebugData", "リポジトリのID" + response.body())
            } catch (e: Exception) {
                Log.w("DebugData", "fetchReposList :" + e)
            }
        }

    }
}
