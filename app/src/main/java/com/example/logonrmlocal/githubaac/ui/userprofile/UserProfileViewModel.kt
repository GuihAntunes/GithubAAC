package com.example.logonrmlocal.githubaac.ui.userprofile

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.logonrmlocal.githubaac.data.local.entity.User
import com.example.logonrmlocal.githubaac.data.repositories.UserRepository
import javax.inject.Inject

class UserProfileViewModel @Inject constructor(var userRepository: UserRepository): ViewModel() {

    var user: LiveData<User> = MutableLiveData<User>()

    fun getUser(login: String) {
        user = userRepository.getUser(login)
    }

}