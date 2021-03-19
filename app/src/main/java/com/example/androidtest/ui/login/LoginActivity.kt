package com.example.androidtest.ui.login

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*

import com.example.androidtest.R
import com.example.androidtest.ui.newsList.NewsListActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val login = findViewById<Button>(R.id.login)
        val user = findViewById<EditText>(R.id.username)
        val pass = findViewById<EditText>(R.id.password)
        val loading = findViewById<ProgressBar>(R.id.loading_login)

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            login.isEnabled = loginState.isDataValid
            user.isEnabled = true
            pass.isEnabled = true

            if (loginState.usernameError != null) {
                user.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                pass.error = getString(loginState.passwordError)
            }
            if (loginState.isLoading) {
                user.isEnabled = false
                pass.isEnabled = false
                login.isEnabled = false
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                login.isEnabled = true
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success == true) {
                updateUiWithUserToken(loginResult.token)
            }
            setResult(Activity.RESULT_OK)
        })

        findViewById<EditText>(R.id.username).afterTextChanged {
            loginViewModel.loginDataChanged(
                user.text.toString(),
                pass.text.toString()
            )
        }

        findViewById<EditText>(R.id.password).apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    user.text.toString(),
                    pass.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            user.text.toString(),
                            pass.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(
                    user.text.toString(),
                    pass.text.toString()
                )
            }
        }
    }

    private fun updateUiWithUserToken(token: String) {
        //TODO Update UI When Logging is Successfull
        startActivity(Intent(this, NewsListActivity::class.java).putExtra("Token", token))
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}