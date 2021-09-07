
package com.elifersumer.myapplication.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elifersumer.myapplication.HisseActivity
import com.elifersumer.myapplication.OnRecyclerItemClickListner
import com.elifersumer.myapplication.R
import com.elifersumer.myapplication.RecyclerViewAdapter
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import kotlinx.android.synthetic.main.fragment_karsilama.*
import kotlinx.android.synthetic.main.rowlaout.*

class KarsilamaFragment : Fragment() , OnRecyclerItemClickListner {
    private lateinit var pieChart: PieChart
    private lateinit var expanderr: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_karsilama, container, false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pieChart = view.findViewById(R.id.pieChart)
        initPieChart()
        setDataToPieChart()

        val tourList = arrayListOf(
            hisseler("YAPI VE KREDİ BANKASI A.Ş", "YKBNK" ,R.drawable.header_logo, "2000₺"),
            hisseler("İHLAS HOLDİNG A.Ş", "IHLAS" ,R.drawable.header_logo, "3000₺"),
            hisseler("TÜRKİYE GARANTİ BANKASI A.Ş\n", "GARAN",R.drawable.header_logo, "2000₺")
        )


        recyclerview.layoutManager=LinearLayoutManager(context)
        recyclerview.adapter= RecyclerViewAdapter(tourList,this)

    }

    @SuppressLint("SetTextI18n")
    //Dönüs yapmıyor tek giriş ama ekran aynı zamanda acılmıyor sormayı unutma
    override fun onItemClick(item: hisseler, position: Int) {
        if(expandableView.visibility == View.GONE){
            println("aaaaaaaaaaaaa")
                TransitionManager.beginDelayedTransition(expandableView, AutoTransition())
                expandableView.visibility = View.VISIBLE
                expander.text = "Colapse"
            }
            else{
            println("bfsabfdbfdfdbbfd")
            TransitionManager.beginDelayedTransition(expandableView, AutoTransition())
                expandableView.visibility = View.GONE
                expander.text = "Expand"
            }
    }



    private fun initPieChart() {

        pieChart.setUsePercentValues(true)
        pieChart.description.text = "Portfoy"
        //hollow pie chart
        pieChart.isDrawHoleEnabled = true
        pieChart.setTouchEnabled(false)
        pieChart.setDrawEntryLabels(false)
        //adding padding
        pieChart.setExtraOffsets(20f, 0f, 20f, 20f)
        pieChart.setUsePercentValues(true)
        pieChart.isRotationEnabled = false
        pieChart.setDrawEntryLabels(false)
        pieChart.legend.orientation = Legend.LegendOrientation.VERTICAL
        pieChart.legend.isWordWrapEnabled = true

    }
    private fun setDataToPieChart() {
        pieChart.setUsePercentValues(true)
        val dataEntries = ArrayList<PieEntry>()
        dataEntries.add(PieEntry(55f, "THYAO"))
        dataEntries.add(PieEntry(25f, "TUPRAS"))
        dataEntries.add(PieEntry(20f, "SASA"))
        dataEntries.add(PieEntry(5f, "PEGASUS"))

        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.parseColor("#4DD0E1"))
        colors.add(Color.parseColor("#FFF176"))
        colors.add(Color.parseColor("#FF8A65"))
        colors.add(Color.parseColor("#E6E6FA"))
        val dataSet = PieDataSet(dataEntries, "Results")
        val data = PieData(dataSet)
        // In Percentage
        data.setValueFormatter(PercentFormatter())
        dataSet.sliceSpace = 3f
        dataSet.colors = colors
        pieChart.data = data
        pieChart.invalidate()
        data.setValueTextSize(15f)
        pieChart.setExtraOffsets(5f, 10f, 5f, 5f)
        pieChart.animateY(2000, Easing.EaseInOutQuad)
        //create hole in center
        pieChart.holeRadius = 58f
        pieChart.transparentCircleRadius = 61f
        pieChart.isDrawHoleEnabled = true
        pieChart.setHoleColor(Color.WHITE)
        //add text in center
        pieChart.setDrawCenterText(true);
        pieChart.centerText = "Hisselerim"

    }

}
