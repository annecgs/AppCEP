package com.example.appcep.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcep.model.ApiHelper
import com.example.appcep.model.Cep
import com.example.appcep.model.RetrofitBuilder
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    val errorType = MutableLiveData<Int>()
    val cep = MutableLiveData<Cep>()

    // Recebendo CEP
    fun setCep(cep: String?){
        if (cep != null) {
            fetchCep(cep)
        }
    }

    // Pegando CEP com coroutines
    private fun fetchCep(str: String) {
        loading.value = true
        // Corroutine
        viewModelScope.launch {
            try {
                val currentCep = ApiHelper(RetrofitBuilder(str).apiService).getCep()
                if(currentCep.erro != null){
                    error.value = true
                    errorType.value = 1
                    loading.value = false
                }else {
                    cep.value = currentCep
                    error.value = false
                    loading.value = false
                }
            }catch (e: Exception){
                error.value = true
                errorType.value = 0
                loading.value = false
            }
        }
    }
}