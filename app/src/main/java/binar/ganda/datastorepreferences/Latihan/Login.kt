package binar.ganda.datastorepreferences.Latihan

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.asLiveData
import binar.ganda.datastorepreferences.R
import binar.ganda.datastorepreferences.UserManager
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Login : AppCompatActivity() {

    lateinit var userManager: Manager
    lateinit var usernameLogin : String
    lateinit var passwordLogin : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userManager = Manager(this)

        userManager.userUsername.asLiveData().observe( this) {
            usernameLogin = it.toString()
        }

        userManager.userPassword.asLiveData().observe( this) {
            passwordLogin = it.toString()
        }

        login_btn.setOnClickListener {

            val username = et_username.text.toString()
            val password = et_password.text.toString()

            if (username.isEmpty() && password.isEmpty()) {
                Toast.makeText(this, "Harus Diisi", Toast.LENGTH_LONG).show()
            } else {
                    if (username == usernameLogin && password == passwordLogin){
                        GlobalScope.launch {
                            startActivity(Intent(this@Login, Home::class.java))
                        }
                    } else {
                        Toast.makeText(it.context, "Username dan Password Salah", Toast.LENGTH_LONG).show()
                    }
            }


        }

        register_btn.setOnClickListener {
            startActivity(Intent(this@Login, Register::class.java))
        }
    }
}