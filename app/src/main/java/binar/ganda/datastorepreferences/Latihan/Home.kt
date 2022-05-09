package binar.ganda.datastorepreferences.Latihan

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import binar.ganda.datastorepreferences.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Home : AppCompatActivity() {

    lateinit var userManager : Manager

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        userManager = Manager(this)

        userManager.userUsername.asLiveData().observe( this) {
            welcome_user.text = "Welcome, " + it.toString()
        }

        logout_btn.setOnClickListener {
            GlobalScope.launch {
                userManager.clearData()
                startActivity(Intent(this@Home, Login::class.java))
            }
        }
    }
}