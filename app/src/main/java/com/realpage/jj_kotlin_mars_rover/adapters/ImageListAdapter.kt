package com.realpage.jj_kotlin_mars_rover.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.realpage.jj_kotlin_mars_rover.R
import com.realpage.jj_kotlin_mars_rover.SelectedImageFragment
import com.realpage.jj_kotlin_mars_rover.data.Photo
import com.squareup.picasso.Picasso

class ImageListAdapter(imageList: List<Photo>, context: Context?, width: Int ) : RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>(){

    private var images = imageList
    private val width = width
    private val context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_image, parent, false)

        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(view:ImageViewHolder, position: Int) {
        var image = images[position]

        Picasso.get().load(image.img_src).resize( width, width).centerInside().placeholder(R.mipmap.ic_launcher_round).into(view.imageViewIcon)
        view.imageViewIcon.setOnClickListener{v ->
            val selectedImageFragment = SelectedImageFragment(image)

            val fragActivity = context as FragmentActivity
            val fragmentTransaction = fragActivity.supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.fragment_container,selectedImageFragment)
            fragmentTransaction.addToBackStack("SelectedImageFragment")
            fragmentTransaction.commit()
        }
    }

    override fun getItemCount(): Int = images.size




    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var imageViewIcon = itemView.findViewById(R.id.imageView2) as ImageView

    }

}