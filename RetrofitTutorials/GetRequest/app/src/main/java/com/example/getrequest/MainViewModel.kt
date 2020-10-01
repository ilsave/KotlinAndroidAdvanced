package com.example.getrequest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getrequest.model.Post
import com.example.getrequest.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private var repository: Repository): ViewModel() {

    val myResponse1: MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponse2: MutableLiveData<Response<Post>> = MutableLiveData()
    val myCustomPost: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val myCustomPost2: MutableLiveData<Response<List<Post>>> = MutableLiveData()

    fun getPost(){
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse1.value = response
        }
    }

    fun getPost2(number: Int){
        viewModelScope.launch {
            val response = repository.getPost2(number)
            myResponse2.value = response
        }
    }

    fun getCustomPosts(userId: Int, sort: String, order: String){
        viewModelScope.launch {
            val response = repository.getCustomPosts(userId, sort, order)
            myCustomPost.value = response
        }
    }

    fun getCustomPosts2(userId: Int, options: Map<String, String>){
        viewModelScope.launch {
            val response = repository.getCustomPosts2(userId, options)
            myCustomPost2.value = response
        }
    }

    fun pushPost(post: Post){
        viewModelScope.launch {
            val response = repository.pushPost(post)
            myResponse1.value = response
        }
    }

    fun pushPost2(userId: Int, id: Int, title: String, body: String){
        viewModelScope.launch {
            val response = repository.pushPost2(userId, id, title, body)
            myResponse1.value = response
        }
    }
}