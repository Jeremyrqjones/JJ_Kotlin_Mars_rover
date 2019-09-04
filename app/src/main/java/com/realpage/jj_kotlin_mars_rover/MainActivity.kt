package com.realpage.jj_kotlin_mars_rover

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.realpage.jj_kotlin_mars_rover.data.Photo
import com.realpage.jj_kotlin_mars_rover.viewmodels.CuriosityViewModel
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private  var curiosityViewModel: CuriosityViewModel = CuriosityViewModel()
    private var arrayIndex: Int = 0
    private var photoList: MutableList<Photo> = arrayListOf()
    private lateinit var mainImageView: ImageView
    private lateinit var roverNameTextView: TextView
    private lateinit var cameraNameTextView: TextView
    private lateinit var earthDateTextView: TextView
    private lateinit var solTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        mainImageView = findViewById(R.id.imageView)
        roverNameTextView = findViewById(R.id.roverTextView)
        cameraNameTextView = findViewById(R.id.cameraTextView)
        earthDateTextView = findViewById(R.id.earthDateTextView)
        solTextView = findViewById(R.id.solTextView)

        curiosityViewModel = ViewModelProviders.of(this).get(curiosityViewModel::class.java)

        curiosityViewModel.fetchCuriosityPhotos()

        curiosityViewModel.curiosityPhotosLiveData.observe(this, Observer { it ->

            if(!it.isNullOrEmpty()){
                photoList.addAll(it)
                loadImage(arrayIndex)
            }else{
                //NO DATA RETURNED
                curiosityViewModel.cancelAllRequests()
            }

        })

        val mButton = findViewById<Button>(R.id.button)
        mButton.setOnClickListener(mOnClickListener)

    }

    private val mOnClickListener = View.OnClickListener { v ->
        arrayIndex++
        loadImage(arrayIndex)
    }

    private fun loadImage(index: Int){
        roverNameTextView.text = photoList[index].rover.name
        cameraNameTextView.text = photoList[index].camera.full_name
        earthDateTextView.text = photoList[index].earth_date
        solTextView.text = photoList[index].sol.toString()
        Picasso.get().load(photoList[index].img_src).into(mainImageView)
    }




}
