package com.example.intentaug19

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import com.example.intentaug19.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding:ActivityLoginBinding
    lateinit var preference:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preference=getSharedPreferences("_login", MODE_PRIVATE)
        binding.loginBtn.setOnClickListener(this)
        binding.loginMailId.editText?.addTextChangedListener {
            binding.loginMailId.error=null
        }
        binding.loginPassword.editText?.addTextChangedListener {
            binding.loginPassword.error=null
        }
        binding.signupText.setOnClickListener {
            var intent= Intent(this@LoginActivity,SignUp::class.java)
            startActivity(intent)
            finish()
        }
        binding.skipText.setOnClickListener {
            var intent= Intent(this@LoginActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onClick(p0: View?) {

        var editor=preference.edit()
        editor.putString(KeysIntent.login_mail,binding.loginMailId.editText?.text.toString())
        editor.putString(KeysIntent.login_password,binding.loginPassword.editText?.text.toString())
        editor.commit()
        if(preference.getString(KeysIntent.signup_mail,"sign")==preference.getString(KeysIntent.login_mail,"login") &&
            preference.getString(KeysIntent.signup_password,"sign")==preference.getString(KeysIntent.login_password,"login")){
            editor.putBoolean(KeysIntent.flag,true)
            editor.commit()
        }

        var loginid=preference.getBoolean(KeysIntent.flag,false)
        if(loginid==true){
            var intent= Intent(this@LoginActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        else if( preference.getBoolean(KeysIntent.checkflagsignUp,false)==true && preference.getString(KeysIntent.signup_mail,"sign")==preference.getString(KeysIntent.login_mail,"login")){
            binding.loginPassword.error="Enter valid password"
        }
        else if( preference.getBoolean(KeysIntent.checkflagsignUp,false)==true && preference.getString(KeysIntent.signup_password,"sign")==preference.getString(KeysIntent.login_password,"login")){
            binding.loginMailId.error="Enter valid mailid"
        }
        else if( preference.getBoolean(KeysIntent.checkflagsignUp,false)==true ){
            binding.loginMailId.error="Enter valid mailid"
            binding.loginPassword.error="Enter valid password"
        }
        else{
            var intent= Intent(this@LoginActivity,SignUp::class.java)
            startActivity(intent)
        }


    }

//    override fun onBackPressed() {
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("Exit")
//        builder.setMessage("Are you sure you want to exit?")
//        builder.setPositiveButton("Yes") { _, _ ->
//            finish() // Close the activity and exit the app
//        }
//        builder.setNegativeButton("No", null) // Do nothing if "No" is clicked
//        builder.show()
//    }

}