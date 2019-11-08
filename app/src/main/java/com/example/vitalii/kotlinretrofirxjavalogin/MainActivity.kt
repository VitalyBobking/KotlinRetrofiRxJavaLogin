package com.example.vitalii.kotlinretrofirxjavalogin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.vitalii.kotlinretrofirxjavalogin.database.UserViewModel
import com.example.vitalii.kotlinretrofirxjavalogin.databinding.MainActivityBinding
import com.example.vitalii.kotlinretrofirxjavalogin.model.LoginUser
import com.example.vitalii.kotlinretrofirxjavalogin.viewmodel.LoginViewModel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*
import com.andreacioccarelli.cryptoprefs.CryptoPrefs
import com.example.vitalii.kotlinretrofirxjavalogin.constants.Const.Companion.FILE_NAME
import com.example.vitalii.kotlinretrofirxjavalogin.constants.Const.Companion.KEY
import com.example.vitalii.kotlinretrofirxjavalogin.snackbar.Snack.Companion.onSnack


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: MainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        userViewModel  = ViewModelProviders.of(this).get(UserViewModel::class.java)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding.viewModel = viewModel

        userViewModel.allUsers.observe(this, Observer<List<LoginUser>> {if(it.isNotEmpty())setEmailPassword(it[0])})
        binding.btnRegister.setOnClickListener{sendEmailPassword()}
        binding.btnLogout.setOnClickListener(View.OnClickListener {
            userViewModel.delete() ; clearTextInput(); removeToken(); onSnack(it, getString(R.string.not_registered))})
    }

    private fun sendEmailPassword() {
        val email: String? = validateTextInput(etUserName)
        val password:String? = validateTextInput(etPassword)

        viewModel.loginUser(email!!, password!!)
            .observe(this, Observer<LoginUser> {setToken(it.token.toString())
                saveEmailPassword(email, password, it.token.toString())})
            val intent = Intent(this, ProductsActivity::class.java)
            startActivity(intent)
    }
    private fun setEmailPassword(loginUser: LoginUser) {
        etUserName.editText!!.text = Editable.Factory.getInstance().newEditable(loginUser.username)
        etPassword.editText!!.text = Editable.Factory.getInstance().newEditable(loginUser.password)
    }
    private fun saveEmailPassword(email: String, password: String, token: String){
        val user =  LoginUser()
        user.username = email
        user.password = password
        user.token = token
        userViewModel.insert(user)
    }

    private fun setToken(value: String) {
        val prefs = CryptoPrefs(applicationContext, FILE_NAME, KEY)
        prefs.put(KEY, value)
    }
    private fun removeToken() {
        val prefs = CryptoPrefs(applicationContext, FILE_NAME, KEY)
        prefs.erase()
    }
    companion object{
        fun  getToken(context: Context): String {
            val prefs = CryptoPrefs(context.applicationContext, FILE_NAME, KEY)
            return prefs.getString(KEY, 0)
        }
        fun validateTextInput(editText: TextInputLayout): String {
            val textInput = editText.editText!!.text.toString().trim()
            if (textInput.isEmpty()) {
                editText.error = "Field can't be empty"
            } else {
                editText.error = null
            }
            return textInput
        }
    }
    private fun clearTextInput() {
        etPassword.editText!!.text.clear()
        etUserName.editText!!.text.clear()
    }
}
