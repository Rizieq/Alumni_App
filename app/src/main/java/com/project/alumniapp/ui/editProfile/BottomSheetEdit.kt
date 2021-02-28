package com.project.alumniapp.ui.editProfile

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.orhanobut.hawk.Hawk
import com.project.alumniapp.R
import com.project.alumniapp.model.ResponseUserEdit
import com.project.alumniapp.ui.profile.ProfileActivity
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.fieldGraduationYear
import kotlinx.android.synthetic.main.activity_profile.view.*
import kotlinx.android.synthetic.main.botom_sheet_edit.*
import kotlinx.android.synthetic.main.botom_sheet_edit.view.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.IOException


class BottomSheetEdit: BottomSheetDialogFragment(), EditProfileContract.View {


    private val editProfilePresenter = EditProfilePresenter(this)
    private val IMG_REQUEST = 777
    private var bitmap: Bitmap? = null
    private var path: Uri? = null
    val STORAGE_PERMISSION_CODE = 1



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v : View = inflater.inflate(R.layout.botom_sheet_edit,container,false)

        v.fbAddImgCv.setOnClickListener {
            selectImage()
        }
        v.fbAddImgProfile.setOnClickListener {
            selectImage()
        }
        v.saveButton.setOnClickListener {
            uploadImage()
        }




        PermissionGalery()
        return v
    }



    private fun uploadImage() {
        val bioProfile: String = fieldBio.getText().toString()
        val graduationYear: String = fieldGraduationYear.getText().toString()
        val token: String = Hawk.get("token")



        val mBioProfile: RequestBody =
            RequestBody.create(MediaType.parse("multipart/form-data"), bioProfile)
        val mGraduationYear: RequestBody =
            RequestBody.create(MediaType.parse("multipart/form-data"), graduationYear)

        // Mengambil alamat file image


        // Mengambil alamat file image
        val myFile = File(path!!.path)
        val selectedImage: Uri? = context?.let { getImageContentUri(it, myFile, path!!) }
        val partImage: String = selectedImage?.let { context?.let { it1 -> getPath(it1, it).toString() } }!!
        val imageFile = File(partImage)

        val requestBody =
            RequestBody.create(MediaType.parse("multipart/form-data"), imageFile)
        val mPartImage =
            MultipartBody.Part.createFormData("image", imageFile.name, requestBody)
        val mPartImageCV =
            MultipartBody.Part.createFormData("cv", imageFile.name, requestBody)

        editProfilePresenter.editProfile(mPartImage,mPartImageCV,mBioProfile,mGraduationYear,"Bearer $token")
    }

    private fun selectImage() {
        // ini untuk select gambar
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, IMG_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null) {
            path = data.data
            try {
                bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, path)
                view?.imgProfileAlumni?.setImageBitmap(bitmap)
                view?.imgProfileAlumni?.setVisibility(View.VISIBLE)
                view?.fbAddImgProfile?.setEnabled(false)
                view?.saveButton?.setEnabled(true)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun getPath(
        context: Context,
        filePath: Uri
    ): String? {
        var cursor =
            context.contentResolver.query(filePath, null, null, null, null)
        cursor!!.moveToFirst()
        var document_id = cursor.getString(0)
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1)
        cursor.close()
        cursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null, MediaStore.Images.Media._ID + " = ? ", arrayOf(document_id), null
        )
        cursor!!.moveToFirst()
        val path =
            cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
        cursor.close()
        return path
    }

    private fun PermissionGalery() {
        // Mencek apakah user sudah memberikan permission untuk mengakses external storage
        if (context?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            } === PackageManager.PERMISSION_GRANTED
        ) return
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                context as Activity,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            STORAGE_PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE) {
            //If permission is granted
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                showMessage("Permission granted now you can read the storage")
                Log.i(
                    "Permission on",
                    "onRequestPermissionsResult: $grantResults"
                )
            } else {
                //Displaying another toast if permission is not granted
                showMessage("Oops you just denied the permission")
                Log.i(
                    "Permission off",
                    "onRequestPermissionsResult: $grantResults"
                )
            }
        }
    }

    private fun showMessage(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }

    private fun getImageContentUri(
        context: Context,
        imageFile: File,
        filePath: Uri
    ): Uri {
        val fileAbsolutePath = imageFile.absolutePath
        val cursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            arrayOf(MediaStore.Images.Media._ID),
            MediaStore.Images.Media.DATA + "=? ",
            arrayOf(fileAbsolutePath),
            null
        )
        return if (cursor != null && cursor.moveToFirst()) {
            // Apabila file gambar sudah pernah diapakai namun ada kondisi lain yang belum diketahui
            // Apabila file gambar sudah pernah dipakai pengambilan bukan di galery
            Log.i("Isi Selected if", "Masuk cursor ada")
            filePath
        } else {
            Log.i("Isi Selected else", "cursor tidak ada")
            if (imageFile.exists()) {
                // Apabila file gambar baru belum pernah di pakai
                Log.i("Isi Selected else", "imagefile exists")
                val values = ContentValues()
                values.put(MediaStore.Images.Media.DATA, fileAbsolutePath)
                context.contentResolver.insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values
                )!!
            } else {
                // Apabila file gambar sudah pernah dipakai
                // Apabila file gambar sudah pernah dipakai di galery
                Log.i("Isi Selected else", "imagefile tidak exists")
                filePath
            }
        }
    }
    override fun showError(msg: String) {
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }

    override fun showEditProfileSuccess(data: ResponseUserEdit) {
        Toast.makeText(context,"Edit Sucessfully!",Toast.LENGTH_SHORT).show()
        startActivity(Intent(context,ProfileActivity::class.java))
//        fieldName.setText(data.name)
//        fieldEmail.setText(data.email)
//        fieldNoHandphone.setText(data.noHandphone)
//        fieldPassword.setText(data.password)
//        fieldGraduationYear.setText(data.tahunAlumni)
    }


}