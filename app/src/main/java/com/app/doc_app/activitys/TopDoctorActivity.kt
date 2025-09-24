package com.app.doc_app.activitys

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.doc_app.R
import com.app.doc_app.adaptor.CategoryAdaptor
import com.app.doc_app.adaptor.DoctorAdaptor2
import com.app.doc_app.databinding.ActivityTopDoctorBinding
import com.app.doc_app.viewModel.MainViewModel

class TopDoctorActivity : AppCompatActivity() {
    lateinit var binding : ActivityTopDoctorBinding
    lateinit var viewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        intitView()

    }

    fun intitView(){
        binding.progressBarTopDoctors.visibility = View.VISIBLE
        viewModel.doctors.observe(this@TopDoctorActivity , Observer {
            binding.rvtopdoctors.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL , false)
            binding.rvtopdoctors.adapter = DoctorAdaptor2(context = this , it)
            binding.progressBarTopDoctors.visibility = View.GONE
        })
        viewModel.fetchDoctors()
        binding.backbutton.setOnClickListener {
            finish()
        }



    }
}