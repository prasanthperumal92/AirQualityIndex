package com.proximity.airqualitymonitoring.views.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.proximity.airqualitymonitoring.domain.model.CityAQ
import com.proximity.airqualitymonitoring.views.viewholders.AQLViewHolder

class AQLAdapter(list:List<CityAQ>,interactionListener: AQLViewHolder.InteractionListener?=null):RecyclerView.Adapter<AQLViewHolder>() {
    var mList = list
    var mInteractionListener:AQLViewHolder.InteractionListener? = interactionListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AQLViewHolder {
        return AQLViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: AQLViewHolder, position: Int) {
        holder.bind(mList.get(position),mInteractionListener)
    }


    fun updateData(list:List<CityAQ>)
    {
        mList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}