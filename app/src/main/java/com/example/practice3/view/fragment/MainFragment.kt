package com.example.practice3.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.practice3.databinding.FragmentMainBinding
import com.example.practice3.view.MainApplication
import com.example.practice3.view.contract.IUserView
import com.example.practice3.view.model.User
import com.example.practice3.view.presenter.UserPresenter
import javax.inject.Inject

class MainFragment : IUserView, Fragment() {

    private lateinit var inputMethodManager: InputMethodManager
    private lateinit var binding: FragmentMainBinding

    @Inject
    lateinit var presenter: UserPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity?.application as? MainApplication)?.component?.inject(this)
        presenter.injectView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)

        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLoad.setOnClickListener { loadUserInfo() }
    }

    private fun loadUserInfo() {
        presenter.getUserInfo(binding.edtUsername.text.toString())
    }

    override fun hideKeyboard() {
        hideKeyboard(binding.edtUsername)
    }

    override fun loading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun showUserInfo(userInfo: User) {
        binding.tvUsername.text = userInfo.username
        binding.tvRepo.apply {
            isClickable = true
            text = userInfo.repoUrl
        }

        showAvatarImage(userInfo.imageUrl)
    }

    private fun showAvatarImage(avatarUrl: String) {
      //  Glide.with(context).load(avatarUrl).diskCacheStrategy(DiskCacheStrategy.RESULT).into(binding.ivProfile)
    }

    override fun showUserInfoError(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun dismissLoading() {
        binding.progressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private fun hideKeyboard(view: View) {
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}
