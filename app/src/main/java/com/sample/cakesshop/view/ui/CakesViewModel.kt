package com.sample.cakesshop.view.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.cakesshop.domain.model.ApiResponse
import com.sample.cakesshop.domain.usecase.GetCakesUseCase
import com.sample.cakesshop.view.mapper.toItemPresentationList
import com.sample.cakesshop.view.model.CakePresentationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by AnkitSingh on 12/26/22.
 */
@HiltViewModel
class CakesViewModel @Inject constructor(
    private val getCakesUseCase: GetCakesUseCase
) : ViewModel() {

    private val _cakeItems = MutableLiveData<List<CakePresentationModel>>()
    val cakeItems: LiveData<List<CakePresentationModel>> = _cakeItems

    private var _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    fun getToastItemsData() {
        viewModelScope.launch {
            getCakesUseCase().collectLatest { response ->
                when (response) {
                    is ApiResponse.Success -> _cakeItems.value =
                        response.data.toItemPresentationList()
                    is ApiResponse.Error -> _error.value = true
                }
            }
        }
    }
}