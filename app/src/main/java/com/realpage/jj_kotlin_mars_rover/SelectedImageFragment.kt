package com.realpage.jj_kotlin_mars_rover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.chrisbanes.photoview.PhotoView
import com.realpage.jj_kotlin_mars_rover.data.Photo
import com.realpage.jj_kotlin_mars_rover.viewmodels.CuriosityViewModel
import com.squareup.picasso.Picasso

class SelectedImageFragment(photo: Photo) : Fragment(){

    private lateinit var mainImageView: PhotoView
    private lateinit var roverNameTextView: TextView
    private lateinit var cameraNameTextView: TextView
    private lateinit var earthDateTextView: TextView
    private lateinit var solTextView: TextView
    private val photo = photo



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.selected_image_fragment, container, false)

        mainImageView = rootView.findViewById(R.id.imageView) as PhotoView
        roverNameTextView = rootView.findViewById(R.id.roverTextView)
        cameraNameTextView = rootView.findViewById(R.id.cameraTextView)
        earthDateTextView = rootView.findViewById(R.id.earthDateTextView)
        solTextView = rootView.findViewById(R.id.solTextView)
        loadImage(photo)

        return rootView
    }

    private fun loadImage(mPhoto: Photo){
              roverNameTextView.text = mPhoto.rover.name
              cameraNameTextView.text = mPhoto.camera.full_name
              earthDateTextView.text = mPhoto.earth_date
              solTextView.text = mPhoto.sol.toString()
              Picasso.get().load(mPhoto.img_src).into(mainImageView)
          }



}