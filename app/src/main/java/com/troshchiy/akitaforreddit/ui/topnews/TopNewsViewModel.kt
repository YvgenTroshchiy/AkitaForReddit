package com.troshchiy.akitaforreddit.ui.topnews

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.troshchiy.akitaforreddit.network.RedditService
import com.troshchiy.akitaforreddit.network.data.RedditPost
import com.troshchiy.akitaforreddit.network.data.mapToDomainModel
import javax.inject.Inject
import kotlinx.coroutines.launch

class TopNewsViewModel @Inject constructor(private val service: RedditService) : ViewModel() {

    val posts: MutableState<List<RedditPost>> = mutableStateOf(ArrayList())

    init {
        loadTopNews()
    }

    private fun loadTopNews() {
        viewModelScope.launch {
            val result = service.topNews(10, null)

            if (result.isSuccessful && result.body() != null) {
                val right = result.body()!!.mapToDomainModel()
                posts.value = right
                Log.d("TopNewsActivity", "result: $right")
            } else {
                Log.w("TopNewsActivity", "Error by loading: ${result.message()}")
//                requireContext().toast("Error by loading")
            }
        }
    }
}
