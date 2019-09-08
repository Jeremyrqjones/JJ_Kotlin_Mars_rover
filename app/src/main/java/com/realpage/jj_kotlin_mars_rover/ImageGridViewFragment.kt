package com.realpage.jj_kotlin_mars_rover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.realpage.jj_kotlin_mars_rover.adapters.ImageListAdapter
import com.realpage.jj_kotlin_mars_rover.data.Photo
import com.realpage.jj_kotlin_mars_rover.viewmodels.CuriosityViewModel

class ImageGridViewFragment : Fragment(){

    private  var curiosityViewModel: CuriosityViewModel = CuriosityViewModel()
    private var photoList: MutableList<Photo> = arrayListOf()
    private lateinit var photoRecyclerView: RecyclerView
    private lateinit var imageListAdapter: ImageListAdapter
    private lateinit var messageText: TextView



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.gridview_main, container, false)

        messageText = rootView.findViewById(R.id.defaultMessage)
        curiosityViewModel = ViewModelProviders.of(this).get(curiosityViewModel::class.java)
        curiosityViewModel.fetchCuriosityPhotos()
        curiosityViewModel.curiosityPhotosLiveData.observe(this, Observer { it ->

            if(!it.isNullOrEmpty()){
                photoList.addAll(it)
                messageText.visibility = View.GONE
                val sizeWidth = (this.resources.displayMetrics.widthPixels / 2) - 16
                photoRecyclerView = rootView.findViewById(R.id.photosRecyclerView)
                photoRecyclerView.layoutManager = GridLayoutManager(context, 2)

                imageListAdapter = ImageListAdapter(photoList, context, sizeWidth)
                photoRecyclerView.adapter = imageListAdapter

            }else{
                //NO DATA RETURNED
                messageText.visibility = View.VISIBLE
                messageText.text = "No images found"
                curiosityViewModel.cancelAllRequests()
            }

        })

        return rootView
    }

}