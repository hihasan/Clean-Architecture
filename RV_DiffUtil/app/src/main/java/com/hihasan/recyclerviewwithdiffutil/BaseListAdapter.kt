package com.hihasan.recyclerviewwithdiffutil

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class BaseListAdapter<T>(
    private val mContext: Context?,
    private var list: List<T>?,//generic list can take any Object
    private val layoutId: Int,// the item_layout
    private val listener: BaseListAdapter.BaseAdapterListener? = null// call back inside your view
) : ListAdapter<T, BaseListAdapter.DataBindingViewHolder>(CompareAble()) {


    class DataBindingViewHolder( val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        return DataBindingViewHolder(DataBindingUtil.inflate(layoutInflater, layoutId, parent, false))
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        listener?.onBind(holder, position, list?.get(position), layoutId)
    }

    fun getLayoutId(): Int {
        return layoutId
    }

    fun deleteItem(position: Int,holder: DataBindingViewHolder) {
        /*val mutableList = list?.toMutableList()
        mutableList!!.removeAt(position)
        setItem(mutableList)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, list?.size!!)
        holder.itemView.visibility = View.GONE*/


        val arrayList = list as ArrayList<T>

        arrayList.removeAt(position)
        setItem(arrayList)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, list?.size!!)
        //   holder.itemView.visibility = View.GONE
    }

    fun setItem(newList: List<T>){
/*        if (list.isNullOrEmpty()){
            list = newList
            notifyDataSetChanged()
        }
        list = newList
        notifyDataSetChanged()*/

        val itemListDiffUtil = ItemListDiffUtil(list!!, newList)
        val diffUtilResult = DiffUtil.calculateDiff(itemListDiffUtil)
        list = newList
        diffUtilResult.dispatchUpdatesTo(this)
    }

    override fun getItem(position: Int):T {
        list?.let {
            if (position < it.size)
                return it[position]
        }
        return list?.get(position) ?: null as T
    }

    fun getItemList(): List<T>? {
        return list
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    interface BaseAdapterListener {
        fun onBind(holder: DataBindingViewHolder, position: Any?, item: Any?, layoutId: Int)
    }

    class CompareAble<T>  : DiffUtil.ItemCallback<T>() {

        override fun areItemsTheSame(oldItemPosition: T, newItemPosition: T): Boolean {
            return oldItemPosition === newItemPosition
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItemPosition: T, newItemPosition: T): Boolean {
            return oldItemPosition == newItemPosition
        }

    }
}