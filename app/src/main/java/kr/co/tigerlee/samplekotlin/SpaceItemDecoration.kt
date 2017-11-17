package kr.co.tigerlee.samplekotlin

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by tigerlee on 2017. 11. 17..
 */
class SpaceItemDecoration(val top: Int, var bottom: Int, var left: Int, var right: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect?.let {
            outRect.top = top
            outRect.bottom = bottom
            outRect.left = left
            outRect.right = right
        }
    }
}