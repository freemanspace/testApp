package com.freeman.samplekotlin.activity.fragment.home.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.freeman.samplekotlin.R
import kotlinx.android.synthetic.main.adapter_pagelist.view.*
import kotlinx.android.synthetic.main.page_list.*

class ListPage :Fragment() {

    var fragmentView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.page_list, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var datas = ArrayList<ListData>()
        for (i in 0..15){
            if(i%5==0) {
                datas.add(ListData(1, i * 3456, "容幣${i}", 60 + 5*i, i * 1.25, i * 30.55, i * 258.25))
            } else{
                datas.add(ListData(0, i * 3456, "容幣${i}", 60 + 5*i, i * 1.25, i * 30.55, i * 258.25))
            }
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = PageListAdapter(datas)
        }
    }

    private inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    private inner class PageListAdapter(val datas:ArrayList<ListData>) : RecyclerView.Adapter<ViewHolder>() {

        override fun getItemViewType(position: Int): Int {
            return datas.get(position).type
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            lateinit var contactView: View
            if(viewType==0) {
                 contactView = LayoutInflater.from(requireContext())
                        .inflate(R.layout.adapter_pagelist, parent, false)
            } else if(viewType==1){
                contactView = LayoutInflater.from(requireContext())
                        .inflate(R.layout.adapter_pagelist_ad, parent, false)
            }
            return ViewHolder(contactView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            var data = datas.get(position)
            if(data.type==0) {
                holder.itemView.apply {
                    tvTitle.setText(data.tittle)
                    tv_money.setText("%,d".format(data.coin))
                    progressBar.setProgress(data.progress)
                    tv_interaction.setText("+%,.2f".format(data.interaction))
                    tv_social.setText("+%,.2f".format(data.social))
                    tv_revenue.setText("+%,.2f".format(data.revenue))
                }
            } else if(data.type==1){

            }
        }

        override fun getItemCount(): Int {
            return datas.size
        }
    }

    private inner class ListData(var type:Int, var coin:Int,var tittle:String,var progress:Int,var interaction:Double,var social:Double,var revenue:Double){
    }

}