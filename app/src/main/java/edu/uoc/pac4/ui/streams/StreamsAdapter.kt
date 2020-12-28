package edu.uoc.pac4.ui.streams

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.uoc.pac4.R
import edu.uoc.pac4.data.streams.Stream
import kotlinx.android.synthetic.main.item_stream.view.*
import java.text.NumberFormat

/**
 * Created by alex on 07/09/2020.
 */

class StreamsAdapter : ListAdapter<Stream, StreamsAdapter.StreamViewHolder>(streamsDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamViewHolder {
        return StreamViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_stream, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StreamViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class StreamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindTo(stream: Stream) {

            // Set Stream Info
            itemView.title.text = stream.userName
            itemView.description.text = stream.title

            // Set Stream Image
            stream.thumbnailUrl
                ?.replace("{width}", "1014")
                ?.replace("{height}", "396")
                ?.let {
                    Glide.with(itemView)
                        .load(it)
                        .centerCrop()
                        .into(itemView.imageView)
                }
            // Set Stream Views
            val formattedViews = NumberFormat.getInstance().format(stream.viewerCount)
            itemView.viewsText.text = itemView.context.resources
                .getQuantityString(R.plurals.viewers_text, stream.viewerCount, formattedViews)
        }
    }

    companion object {
        private val streamsDiffCallback = object : DiffUtil.ItemCallback<Stream>() {

            override fun areItemsTheSame(oldItem: Stream, newItem: Stream): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Stream, newItem: Stream): Boolean {
                return oldItem == newItem
            }
        }
    }
}