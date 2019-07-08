package br.com.ledszeppelin.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.ledszeppelin.R
import br.com.ledszeppelin.data.ServiceGenerator
import br.com.ledszeppelin.databinding.LoginFragmentBinding
import br.com.ledszeppelin.login.service.LoginRepository
import br.com.ledszeppelin.login.service.LoginService
import br.com.ledszeppelin.login.service.remote.LoginRemoteDataSource
import br.com.ledszeppelin.login.viewmodel.LoginViewModel
import br.com.ledszeppelin.login.viewmodel.factories.LoginViewModelFactory


class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var binding: LoginFragmentBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.login.observe(this, Observer { loginResponseModel ->
            loginResponseModel?.let { login ->
                Toast.makeText(requireContext(), login.access, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            LoginViewModelFactory(
                LoginRepository.getInstance(
                    LoginRemoteDataSource.getInstance(
                        ServiceGenerator.createService(
                            LoginService::class.java
                        )
                    )
                )
            )
        ).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.login_fragment,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }
}
