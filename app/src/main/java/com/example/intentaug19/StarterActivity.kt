package com.example.intentaug19

import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AlertDialog

class StarterActivity : AppCompatActivity() {
    lateinit var preference:SharedPreferences
    lateinit var editor:Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starter)
        preference=getSharedPreferences("_login", MODE_PRIVATE)
//        editor=preference.edit()
//
//        editor.clear()
//        editor.apply()
//        editor.putBoolean(KeysIntent.flag,false)
//        editor.commit()

        Handler(Looper.getMainLooper()).postDelayed({
            var login=preference.getBoolean(KeysIntent.flag,false)
            var t=preference.getBoolean(KeysIntent.checkflagsignUp,false)
            if(login==true){
                var intent=Intent(this@StarterActivity,MainActivity::class.java)
                startActivity(intent)
            }
            else if(login==false && t==false){
                var intent=Intent(this@StarterActivity,SignUp::class.java)
                startActivity(intent)
            }
             else if(login==false){
                var intent=Intent(this@StarterActivity,LoginActivity::class.java)
                startActivity(intent)
            }
           finish()

        },1000)

    }

}