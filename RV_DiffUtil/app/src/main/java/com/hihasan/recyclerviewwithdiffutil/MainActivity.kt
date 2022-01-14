package com.hihasan.recyclerviewwithdiffutil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.hihasan.recyclerviewwithdiffutil.databinding.ActivityMainBinding
import com.hihasan.recyclerviewwithdiffutil.databinding.SingleListBinding

class MainActivity : AppCompatActivity(), BaseAdapter.BaseAdapterListener {
    
    lateinit var binding : ActivityMainBinding
    lateinit var itemAdapter: BaseAdapter<ItemData>
    var listItem : ArrayList<ItemData>  = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initListeners()
    }

    private fun initListeners() {

        val p1 = ItemData("1", "Hasan", "1")
        val p2 = ItemData("2", "Al", "2")
        val p3 = ItemData("3", "Mamun", "3")
        val p4 = ItemData("4", "Tasmiah", "4")
        val p5 = ItemData("5", "Khan", "5")
        listItem.addAll(listOf(p1, p2, p3, p4, p5))
        rv(listItem)

//        Handler(Looper.getMainLooper()).postDelayed({
//
//            itemAdapter.setItem(listOf(p3,p4,p5))
//
//        }, 4000)

        searchQuery(binding!!.search, listItem)

        binding!!.search.setOnCloseListener {

            val params: RelativeLayout.LayoutParams =
                binding!!.search.layoutParams as RelativeLayout.LayoutParams
            params.width = RelativeLayout.LayoutParams.WRAP_CONTENT
            binding!!.search.layoutParams = params
            binding!!.search.requestLayout()

            false
        }

        binding!!.search.setOnSearchClickListener {

            val params: RelativeLayout.LayoutParams =
                binding!!.search.layoutParams as RelativeLayout.LayoutParams
            params.width = RelativeLayout.LayoutParams.MATCH_PARENT
            params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
            binding!!.search.layoutParams = params
            binding!!.search.requestLayout()

        }

        val layoutManager = LinearLayoutManager(this)
        itemAdapter = BaseAdapter(this, listItem, R.layout.single_list, this)
        binding.itemRv.adapter = itemAdapter
        binding.itemRv.layoutManager = layoutManager

    }

    private fun rv(submitList: ArrayList<ItemData>) {

    }

    override fun onBind(
        holder: BaseAdapter.DataBindingViewHolder,
        position: Int,
        item: Any?,
        layoutId: Int,
    ) {
        (holder.binding as SingleListBinding).apply {
            items = item as ItemData
            card.setOnClickListener{
                Toast.makeText(applicationContext, items!!.name, Toast.LENGTH_SHORT).show()
            }
        }
    }

//    override fun onBind(
//        holder: BaseListAdapter.DataBindingViewHolder,
//        position: Any?,
//        item: Any?,
//        layoutId: Int,
//    ) {
//        (holder.binding as SingleListBinding).apply {
//            items = item as ItemData
//            card.setOnClickListener{
//                Toast.makeText(applicationContext, items!!.name, Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    fun searchQuery(search : SearchView, categoryList: ArrayList<ItemData>){
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterCategoryList(newText!!.trim(), categoryList)
                return false
            }

        })
    }

    private fun filterCategoryList(
        query: String,
        categoryList: ArrayList<ItemData>,
    ) {
        listItem.clear()
        listItem.addAll(categoryList.filter {
            it.name.lowercase().contains(query.lowercase())
        })

        itemAdapter?.setItem(listItem)

    }
}