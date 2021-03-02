package com.project.alumniapp.ui.listAlumni

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orhanobut.hawk.Hawk
import com.project.alumniapp.R
import com.project.alumniapp.adapter.AdapterListAlumni
import com.project.alumniapp.model.DataItem
import kotlinx.android.synthetic.main.fragment_list_alumni.*
import kotlinx.android.synthetic.main.fragment_list_alumni.view.*


class ListAlumniFragment : Fragment(), ListAlumniContract.View {

    private var tampilPresenter = ListAlumniPresenter(this)
    lateinit var rvAlumni : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_list_alumni, container, false)
        val token: String = Hawk.get("token")
        rvAlumni = v?.rv_alumni!!
        tampilPresenter.showData("Bearer $token")
        return v
    }


    override fun getdata(data: List<DataItem>) {
        rvAlumni.hasFixedSize()
        rvAlumni.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        rvAlumni.adapter = activity?.let { AdapterListAlumni(it, data) }
    }

    override fun showFailureMessage(error: String) {
        Toast.makeText(context,error, Toast.LENGTH_SHORT).show()
    }
}