package net.mvvm.testApp.screen.home.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_adapter_view.view.*

import net.mvvm.testApp.R
import net.mvvm.testApp.data.model.album.Album
import net.mvvm.testApp.data.model.album.Image


class MainAdapter( val listener : LastItemListener ) : RecyclerView.Adapter<MainAdapter.LastItemViewHolder>() {

    private val albums : MutableList<Album> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): MainAdapter.LastItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_adapter_view , parent , false  )
        return LastItemViewHolder(view , listener)
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onBindViewHolder(viewHolder: MainAdapter.LastItemViewHolder, position: Int) {
        viewHolder.bind(albums[position])
    }

    fun setData(results: List<Album>) {
        Log.d("testTag" , "set data ")

        results?.let {
            albums.addAll( results)
            notifyDataSetChanged()
        }

    }

    fun clear() {
        albums.clear()
        notifyDataSetChanged()
    }


    class LastItemViewHolder(itemView : View , private val listener : LastItemListener) : RecyclerView.ViewHolder(itemView) {

        fun bind(album : Album){
            itemView.searchTitler.text = album.name
            itemView.searchTitlerSub.text = album.artist!!
            try {
                showImage(album.image!! , itemView.mainImage)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("testTag" , e.message)
            }
            itemView.setOnClickListener {
                listener.onLastItemClicked(album)
            }

        }

        private fun showImage(images: List<Image>, mainImage: ImageView?) {
            if (images == null )return
            for (image in images){
                if (image.size.equals("medium")){
                    image.text?.let {
                        Picasso.get().load(image.text).placeholder(R.drawable.ic_launcher_foreground).into(mainImage)

                    }
                }
            }
        }


    }


    interface LastItemListener{
        fun onLastItemClicked(album : Album)
    }

}