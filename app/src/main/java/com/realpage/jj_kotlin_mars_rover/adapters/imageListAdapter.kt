package com.realpage.jj_kotlin_mars_rover.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.realpage.jj_kotlin_mars_rover.R
import com.realpage.jj_kotlin_mars_rover.data.Photo
import com.squareup.picasso.Picasso

class imageListAdapter(imageList: List<Photo>, width: Int ) : RecyclerView.Adapter<imageListAdapter.ImageViewHolder>(){

    private var images = imageList
    private val width = width

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_image, parent, false)

        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(view:ImageViewHolder, position: Int) {
        var image = images.get(position)

        Picasso.get().load(image.img_src).resize( width, width).centerInside().placeholder(R.mipmap.ic_launcher_round).into(view.imageViewIcon)
    }

    override fun getItemCount(): Int = images.size


    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var imageViewIcon = itemView.findViewById(R.id.imageView2) as ImageView

    }

}