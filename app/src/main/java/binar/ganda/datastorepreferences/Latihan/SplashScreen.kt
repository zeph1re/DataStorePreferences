package binar.ganda.datastorepreferences.Latihan

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import binar.ganda.datastorepreferences.R


class SplashScreen : AppCompatActivity() {

    lateinit var userManager: Manager
    lateinit var usernameLogin : String
    lateinit var passwordLogin :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        userManager = Manager(this)

        userManager.userUsername.asLiveData().observe( this) {
            usernameLogin = it.toString()
        }

        userManager.userPassword.asLiveData().observe( this) {
            passwordLogin = it.toString()
        }

        Handler().postDelayed(Runnable {
            if (usernameLogin.isNotEmpty() && passwordLogin.isNotEmpty()){
                startActivity(Intent(this@SplashScreen, Home::class.java))
            } else {
                val intent = Intent(this@SplashScreen, Login::class.java)
                startActivity(intent)
            }
            finish()
        }, 3000)
    }
}