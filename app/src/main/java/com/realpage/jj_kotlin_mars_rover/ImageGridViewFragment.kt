package com.realpage.jj_kotlin_mars_rover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.realpage.jj_kotlin_mars_rover.adapters.imageListAdapter
import com.realpage.jj_kotlin_mars_rover.data.Photo
import com.realpage.jj_kotlin_mars_rover.viewmodels.CuriosityViewModel

class ImageGridViewFragment : Fragment(){

    private  var curiosityViewModel: CuriosityViewModel = CuriosityViewModel()
    private var photoList: MutableList<Photo> = arrayListOf()
    private lateinit var photoRecyclerView: RecyclerView
    private lateinit var imageListAdapter: imageListAdapter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.gridview_main, container, false)

        photoRecyclerView = rootView.findViewById(R.id.photosRecyclerView)
        photoRecyclerView.layoutManager = GridLayoutManager(context, 2)

        curiosityViewModel = ViewModelProviders.of(this).get(curiosityViewModel::class.java)
        curiosityViewModel.fetchCuriosityPhotos()
        curiosityViewModel.curiosityPhotosLiveData.observe(this, Observer { it ->

            if(!it.isNullOrEmpty()){
                photoList.addAll(it)

                val sizeWidth = (this.resources.displayMetrics.widthPixels / 2) - 16
                imageListAdapter = imageListAdapter(photoList, sizeWidth)
                photoRecyclerView.adapter = imageListAdapter

            }else{
                //NO DATA RETURNED
                curiosityViewModel.cancelAllRequests()
            }

        })

        return rootView
    }

}