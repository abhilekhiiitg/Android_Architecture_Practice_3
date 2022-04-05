package com.example.practice3.userdetails.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.practice3.MainApplication
import com.example.practice3.databinding.FragmentMainBinding
import com.example.practice3.userdetails.contract.IUserView
import com.example.practice3.userdetails.model.User
import com.example.practice3.userdetails.presenter.UserPresenter
import javax.inject.Inject

class UserFragment : IUserView, Fragment() {
    private lateinit var binding: FragmentMainBinding

    @Inject
    lateinit var presenter: UserPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as? MainApplication)?.component?.inject(this)
        presenter.attachView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLoad.setOnClickListener { loadUserInfo() }
    }

    private fun loadUserInfo() {
        // https://gorest.co.in/public/v2/users/3249
        presenter.getUserInfo(3229)
    }

    override fun loading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun showUserInfo(userInfo: User?) {
        dismissLoading()
        binding.tvUsername.text = userInfo?.username + "\n" + userInfo?.email + "\n" + userInfo?.status + "\n" + userInfo?.gender
    }

    override fun dismissLoading() {
        binding.progressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        if (::presenter.isInitialized) {
            presenter.detachView()
            presenter.clearResources()
        }
        super.onDestroy()
    }
}
