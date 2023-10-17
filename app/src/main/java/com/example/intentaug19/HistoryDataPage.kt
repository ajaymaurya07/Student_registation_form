package com.example.intentaug19

import MyDb
import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.intentaug19.databinding.ActivityHistoryDataPageBinding

class HistoryDataPage : AppCompatActivity() {
    lateinit var binding: ActivityHistoryDataPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHistoryDataPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        var myDb=MyDb(this@HistoryDataPage)
//        var list=myDb.getData()
//
//        var adapter=HistoryAdapter(list)
//        binding.listviewHistory.adapter=adapter


//        val mlist= arrayListOf<String>()
//        mlist.add("aaju")
//        mlist.add("anshul")
// Retrieve data from the database
        var myDb=MyDb(this)
        val mlist = myDb.getData()
//        val mlist = arrayListOf<StudentData>()
//        mlist.add(StudentData(1,"aaju","46242452","gsha@gmail.com","09/098.","male","android"))

// Create an adapter for your ListView and set it
        val adapter = HistoryAdapter(mlist)
        binding.listviewHistory.adapter = adapter





    }
}