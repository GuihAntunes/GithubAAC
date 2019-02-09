package com.example.logonrmlocal.githubaac.ui.userprofile


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.logonrmlocal.githubaac.R
import com.squareup.picasso.Picasso
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_user_profile.*
import javax.inject.Inject

class UserProfileFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: UserProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

        override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupDagger()
        setupViewModel()
        setupView()

    }

    fun setupDagger() {
        AndroidSupportInjection.inject(this)
    }

    fun setupView() {
        btPesquisar.setOnClickListener {
            viewModel.getUser(etUsuario.text.toString())
            viewModel.user.observe(this, Observer {
                tvUsuario.text = it?.name
                Picasso.get().load(it?.avatarURL).into(ivUsuario)
            })
        }
    }

    fun setupViewModel() {
        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(UserProfileViewModel::class.java)
    }

}
