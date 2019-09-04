package com.realpage.jj_kotlin_mars_rover.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
import com.realpage.jj_kotlin_mars_rover.R
import com.realpage.jj_kotlin_mars_rover.data.Post
import java.util.*

class postsListAdapter(postList: List<Post> ) : RecyclerView.Adapter<postsListAdapter.PostViewHolder>(){

    private var posts = postList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_post, parent, false)

        return PostViewHolder(view)
    }

    override fun onBindViewHolder(view:PostViewHolder, position: Int) {
        var post = posts.get(position)
        view.titleView.text = post.title
        view.commentView.text = post.body

    }

    override fun getItemCount(): Int = posts.size


    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var titleView: TextView
        var commentView: TextView

        init {
            titleView = itemView.findViewById(R.id.titleTextView) as TextView
            commentView = itemView.findViewById(R.id.commentTextView) as TextView
        }
    }

}