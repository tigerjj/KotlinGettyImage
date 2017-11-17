package kr.co.tigerlee.samplekotlin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kr.co.tigerlee.samplekotlin.model.GettyImage


/**
 * Created by tigerlee on 2017. 11. 17..
 */
class GettyImageRecyclerAdapter : BaseRecyclerAdapter<GettyImage>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_gettyimage, parent, false)
        return GettyImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        var gettyImage = getItem(position)
        var viewHolder = holder as GettyImageViewHolder

        var task = ImageLoaderTask(viewHolder.imageView)
        task.execute(gettyImage)

        viewHolder.titleTextView?.text = gettyImage.title
        viewHolder.captionTextView?.text = gettyImage.caption
    }

    class GettyImageViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView? = null
        var titleTextView: TextView? = null
        var captionTextView: TextView? = null

        init {
            imageView = itemView?.findViewById(R.id.imageView) as ImageView?
            titleTextView = itemView?.findViewById(R.id.titleTextView) as TextView?
            captionTextView = itemView?.findViewById(R.id.captionTextView) as TextView?
        }
    }
}