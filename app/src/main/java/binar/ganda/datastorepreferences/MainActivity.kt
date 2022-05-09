package binar.ganda.datastorepreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.lifecycle.asLiveData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var userManager : UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userManager = UserManager(this)

        save_btn.setOnClickListener {
            val name = et_name.text.toString()
            val age = et_age.text.toString().toInt()

            GlobalScope.launch {
                userManager.saveData(name,age)
            }
        }

        userManager.userNama.asLiveData().observe( this) {
            result_name.text = it.toString()
        }

        userManager.userAge.asLiveData().observe( this) {
            result_age.text = it.toString()
        }

        clear_btn.setOnClickListener {
            GlobalScope.launch {
                userManager.clearData()
            }
        }

    }
}