package com.proximity.airqualitymonitoring.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.proximity.airqualitymonitoring.databinding.FragmentAqlistBinding
import com.proximity.airqualitymonitoring.domain.model.CityAQ
import com.proximity.airqualitymonitoring.viewmodel.AQViewModel
import com.proximity.airqualitymonitoring.views.activity.replaceMainTopFragment
import com.proximity.airqualitymonitoring.views.adapters.AQLAdapter
import com.proximity.airqualitymonitoring.views.viewholders.AQLViewHolder

class AQIListFragment:Fragment() {
    lateinit var binding:FragmentAqlistBinding
    lateinit var viewModel: AQViewModel
     var adapter:AQLAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAqlistBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(AQViewModel::class.java)
        viewModel.getAQData().observe(viewLifecycleOwner,{
            if(it!=null)
            {
                if (adapter==null)
                {
                    binding.rvAQIList.layoutManager = LinearLayoutManager(requireContext())
                    adapter = AQLAdapter(it,object:AQLViewHolder.InteractionListener{
                        override fun onItemClick(cityAQ: CityAQ) {
                            activity?.replaceMainTopFragment(cityAQ)
                        }
                    })
                    binding.rvAQIList.adapter = adapter
                }else{
                    adapter!!.mList = it
                    adapter!!.notifyDataSetChanged()
                }
            }
        })


        return binding.root
    }


}