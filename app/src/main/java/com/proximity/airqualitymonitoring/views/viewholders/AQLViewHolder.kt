package com.proximity.airqualitymonitoring.views.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.proximity.airqualitymonitoring.databinding.ItemAqlListBinding
import com.proximity.airqualitymonitoring.domain.model.AQIColorCode
import com.proximity.airqualitymonitoring.domain.model.CityAQ
import com.proximity.airqualitymonitoring.utils.TimeUtils

class AQLViewHolder(binding: ItemAqlListBinding):RecyclerView.ViewHolder(binding.root) {
    val mBinding = binding
    interface InteractionListener{
        fun onItemClick(cityAQ: CityAQ)
    }
    var mInteractionListener:InteractionListener?=null
    companion object{
        fun from(parent: ViewGroup):AQLViewHolder
        {
           return AQLViewHolder(ItemAqlListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }
    }

    fun bind(cityAQ: CityAQ,interactionListener: InteractionListener?=null)
    {
        mInteractionListener = interactionListener
        mBinding.textCity.setText(cityAQ.cityName)
        if (cityAQ.aqi!=null) {
            val roundOff =  Math.round(cityAQ.aqi?.times(100)!!).toDouble().div(100)
            mBinding.textAQI.setText( "$roundOff")
            mBinding.textAQI.setTextColor(AQIColorCode.getColorCode(cityAQ.aqi!!))
            mBinding.textLastUpdated.setText(TimeUtils.getTimeDiff(cityAQ.lastUpdatedAt))

            mBinding.root.setOnClickListener {
                interactionListener?.onItemClick(cityAQ)
            }
        }

    }

}