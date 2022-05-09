package binar.ganda.datastorepreferences.Latihan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import binar.ganda.datastorepreferences.R
import binar.ganda.datastorepreferences.UserManager
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Register : AppCompatActivity() {
    lateinit var userManager : Manager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        userManager = Manager(this)

        register_btn.setOnClickListener {
            val username = et_username.text.toString()
            val password = et_password.text.toString()

            if (username.isEmpty() && password.isEmpty()) {
                Toast.makeText(this, "Harus Diisi", Toast.LENGTH_LONG).show()
            } else {
                GlobalScope.launch {
                    userManager.saveData(username, password)
                    startActivity(Intent(this@Register, Login::class.java))
                }
            }
        }
    }
}