package com.proximity.airqualitymonitoring.views.fragments

import android.R
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.components.*
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.Utils
import com.google.gson.Gson
import com.proximity.airqualitymonitoring.databinding.FragmentAqgraphBinding
import com.proximity.airqualitymonitoring.domain.model.AQHistorical
import com.proximity.airqualitymonitoring.domain.model.AQIColorCode
import com.proximity.airqualitymonitoring.domain.model.CityAQ
import com.proximity.airqualitymonitoring.utils.Logger
import com.proximity.airqualitymonitoring.viewmodel.AQViewModel
import com.proximity.airqualitymonitoring.views.adapters.AQLAdapter


class AQGraphFragment : Fragment() {

    lateinit var binding: FragmentAqgraphBinding
    lateinit var viewModel: AQViewModel
    var adapter: AQLAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAqgraphBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(AQViewModel::class.java)
        bindChart()
        val string = arguments?.getString("data")
        var cityId:String? = null
        if (string!=null)
        {
            val cityAQ = Gson().fromJson(string,CityAQ::class.java)
            cityId = cityAQ._id
        }

        viewModel.getAQDataHistory(cityId).observe(viewLifecycleOwner,{
            Logger.e("${it.size} is size of data points for graph")
            setData(it)

        })
        return binding.root
    }


    fun bindChart() {
        binding.lineChart.setDragEnabled(true);
        binding.lineChart.setScaleEnabled(true);
        binding.lineChart.setPinchZoom(true);
        val xAxis: XAxis
        xAxis = binding.lineChart.getXAxis()
        xAxis.setDrawGridLines(false)
        val yAxis: YAxis
        yAxis = binding.lineChart.getAxisLeft()
        yAxis.setDrawGridLines(false)
        xAxis.setAvoidFirstLastClipping(true)
        binding.lineChart.getAxisRight().setEnabled(false)
        yAxis.axisMaximum = 400f
        yAxis.axisMinimum = 0f
        setData(ArrayList());
        binding.lineChart.animateX(1500)
        val l: Legend = binding.lineChart.getLegend()
        l.setForm(LegendForm.LINE)
    }


    private fun setData(list:List<AQHistorical>) {
        val values: ArrayList<Entry> = ArrayList()
        for (i in 0 until list.size) {
            val value = list.get(i).aqi?.times(100)?.let { Math.round(it).toFloat().div(100) }
            if (value!=null) {
                values.add(
                    Entry(
                        i.toFloat().times(30),
                        value,
                        null
                    )
                )
            }
            Logger.e("${list.get(i).timeStamp}")
        }
            val set1: ILineDataSet
            var title = if(list.size>0) list.get(0).cityName else "api"
            var lineColor = if(list.size>0&&list.get(0).aqi!=null) AQIColorCode.getColorCode(list.get(0).aqi!!)  else Color.BLACK
            set1 = LineDataSet(values, title)
            set1.color = lineColor
            set1.setCircleColor(lineColor)
            set1.lineWidth = 1f
            set1.setDrawCircleHole(false)
            set1.setDrawValues(false)
            set1.setDrawFilled(false)
            set1.formLineWidth = 1f
            set1.enableDashedHighlightLine(10f, 5f, 0f)
            val description = Description()
            description.text = "The graph is updated every 30 secs"
            binding.lineChart.description= description
            val dataSets: ArrayList<ILineDataSet> = ArrayList()
            dataSets.add(set1) // add the data sets
            val data = LineData(dataSets)
            binding.lineChart.setData(data)
            binding.lineChart.invalidate()
//        }
    }
}