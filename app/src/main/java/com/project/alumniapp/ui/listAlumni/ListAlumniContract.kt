package com.project.alumniapp.ui.listAlumni

import com.project.alumniapp.model.DataItem

interface ListAlumniContract {


    interface View{
        fun getdata(data: List<DataItem>)
        fun showFailureMessage(error: String)
    }

    interface Presenter{
        fun showData(token: String)
    }

}