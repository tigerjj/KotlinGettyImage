package kr.co.tigerlee.samplekotlin

import android.support.v7.widget.RecyclerView

/**
 * Created by tigerlee on 2017. 11. 17..
 */
abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = ArrayList<T>()

    fun add(item: T){
        items.add(item)
    }
    fun addAll(allItems: List<T>){
        items.addAll(allItems)
    }

    fun getItem(index: Int): T{
        return items.get(index)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    fun isEmpty() : Boolean{
        return items.isEmpty()
    }
    fun clear(){
       items.clear()
    }
}