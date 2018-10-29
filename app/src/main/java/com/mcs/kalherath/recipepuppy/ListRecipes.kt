package com.mcs.kalherath.recipepuppy

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import android.R.attr.orientation
import android.content.res.Configuration


class ListRecipes : AppCompatActivity(), ItemAdapter.Listener {



    private lateinit var mRecyclerView : RecyclerView
    private lateinit var mAdapter : ItemAdapter
    private var mList : MutableList<Item> = ArrayList()
    private  var ingredients : String = ""
    private  var dish : String = ""
    private val vm = ItemModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_recipes)

        mRecyclerView = findViewById(R.id.rv)
        initView()

        ingredients = "onions,garlic"
        dish="pizza"

        vm.getRecipes(ingredients,dish)

        val nameObserver = Observer<MutableList<Item>> { newList ->
            mList = newList!!
            mAdapter = ItemAdapter(mList, this)
            mRecyclerView.adapter = mAdapter
        }

        vm.mList.observe(this, nameObserver)




    }

    private fun initView(){
        val LM = LinearLayoutManager(this)
        val layoutManager : RecyclerView.LayoutManager = LM
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = layoutManager
    }

    override fun onItemClick(item : Item) {
        Toast.makeText(this,item.title, Toast.LENGTH_SHORT).show()
    }
}