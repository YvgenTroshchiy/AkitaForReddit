package com.troshchiy.akitaforreddit.ui.topnews

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.troshchiy.akitaforreddit.appComponent
import com.troshchiy.akitaforreddit.extensions.toast
import com.troshchiy.akitaforreddit.network.RedditService
import com.troshchiy.akitaforreddit.ui.theme.AkitaForRedditTheme
import com.troshchiy.akitaforreddit.ui.topnews.data.toTopNews
import javax.inject.Inject
import kotlinx.coroutines.launch

class TopNewsActivity : ComponentActivity() {
    @Inject lateinit var service: RedditService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        lifecycleScope.launch {
            loadTopNews()
        }

        setContent {
            AkitaForRedditTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }

    private suspend fun loadTopNews() {
        val result = service.topNews(10, null)

        if (result.isSuccessful && result.body() != null) {
            val right = result.body()!!.toTopNews()
            Log.d("TopNewsActivity", "result: $right")
        } else {
            Log.w("TopNewsActivity", "Error by loading: ${result.message()}")
            toast("Error by loading")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AkitaForRedditTheme {
        Greeting("Android")
    }
}
