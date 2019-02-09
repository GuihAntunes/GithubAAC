package com.example.logonrmlocal.githubaac.di.modules

import com.example.logonrmlocal.githubaac.ui.userprofile.UserProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeUserProfileFragment() : UserProfileFragment
}