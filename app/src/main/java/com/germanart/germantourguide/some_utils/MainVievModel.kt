package com.germanart.germantourguide.some_utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainVievModel: ViewModel() {

//

    private var _isListEmpty = MutableLiveData<Boolean>()
    val isListEmpty: LiveData<Boolean>
        get() = _isListEmpty


    init {


    }




//


}