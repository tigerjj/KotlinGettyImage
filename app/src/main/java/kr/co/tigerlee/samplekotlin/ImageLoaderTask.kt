package kr.co.tigerlee.samplekotlin

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import kr.co.tigerlee.samplekotlin.model.GettyImage
import java.net.HttpURLConnection
import java.net.URL


/**
 * Created by tigerlee on 2017. 11. 17..
 */
class ImageLoaderTask (imageView: ImageView?): AsyncTask<GettyImage, Int, Bitmap>() {
    private var imageView: ImageView? = imageView

    override fun doInBackground(vararg gettyImages: GettyImage): Bitmap {
        gettyImages?.let {
            var gettyImage = gettyImages.get(0)
            var displaySize = gettyImage.display_sizes[0]
            val url = URL(displaySize.uri)
            val connection = url.openConnection() as HttpURLConnection

            val inputStream = connection.inputStream
            return BitmapFactory.decodeStream(inputStream)
        }
    }

    override fun onPostExecute(result: Bitmap) {
        super.onPostExecute(result)
        result?.let {
            this.imageView?.setImageBitmap(result)
        }
    }
}