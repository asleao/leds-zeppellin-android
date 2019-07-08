package br.com.ledszeppelin.login.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.ledszeppelin.login.service.LoginRepository
import br.com.ledszeppelin.login.viewmodel.LoginViewModel

class LoginViewModelFactory(private val loginRepository: LoginRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(loginRepository) as T
    }
}