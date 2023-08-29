package com.example.intentaug19

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.intentaug19.databinding.ActivityMain2Binding
import com.google.gson.Gson

class MainActivity2 : AppCompatActivity() {
//    lateinit var textView: TextView
//    lateinit var checkedtext:TextView
    lateinit var binding:ActivityMain2Binding
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

//        textView=findViewById(R.id.data_text)
//        checkedtext=findViewById(R.id.checked_data)


        var data=intent
//        var fname=data.getStringExtra(KeysIntent.fname)
//        var lneme=data.getStringExtra(KeysIntent.lname)
//        var sname=data.getStringExtra(KeysIntent.sname)

        var personstringdata=data.getStringExtra(KeysIntent.PERSON_DATA)!!
        var objectdata=Gson().fromJson(personstringdata,DataClass::class.java)

        var checkedData=""

        for (i in objectdata.checkdata){
            checkedData=checkedData +i+" , "
        }

        binding.resName.text="Name  : ${objectdata.fneme} ${objectdata.sname} ${objectdata.lname}"

        binding.resPhone.text="Phone : ${objectdata.phone}"
        binding.resMail.text="Gmail  : ${objectdata.mail}"
        binding.resGender.text="Gender: ${objectdata.gender}"
        binding.resSkill.text="Skills  : ${checkedData}"

//        var bundledata=data.getBundleExtra("temp")!!
//        var fname=bundledata.getString(KeysIntent.fname)!!
//        var lname=bundledata.getString(KeysIntent.lname)

//        textView.text="${fname} ${lname}"



    }
}