package com.project.alumniapp.ui.listAlumni

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.orhanobut.hawk.Hawk
import com.project.alumniapp.R
import com.project.alumniapp.adapter.AdapterListAlumni
import com.project.alumniapp.model.DataItem
import kotlinx.android.synthetic.main.activity_list_alumni.*

class ListAlumniActivity : AppCompatActivity(), ListAlumniContract.View {

    private  var tampilPresenter = ListAlumniPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_alumni)

        val token: String = Hawk.get("token")
        tampilPresenter.showData("Bearer $token")

    }

    override fun getdata(data: List<DataItem>) {
        rv_alumni.hasFixedSize()
        rv_alumni.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        rv_alumni.adapter = AdapterListAlumni(this,data)

    }

    override fun showFailureMessage(error: String) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
    }
}