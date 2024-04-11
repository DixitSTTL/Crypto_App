package com.app.crypto.presentation

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.app.crypto.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.text.DecimalFormat

class CustomBindingAdapter {


    companion object {

        private val decimalFormat = DecimalFormat("#,###,###.###")

        @JvmStatic
        @BindingAdapter(value = ["setImagesFromData"], requireAll = false)
        fun setImagesFromData(imageView: ImageView, data: String?) {

            val imageLoader = ImageLoader.Builder(imageView.context)
                .components {
                    add(SvgDecoder.Factory())
                }
                .build()


            val request = ImageRequest.Builder(imageView.context)
                .data(data)
                .target(imageView)
                .crossfade(true)
                .build()

            imageLoader.enqueue(request)


        }

        @JvmStatic
        @BindingAdapter(value = ["setPriceChangeColor"], requireAll = false)
        fun setPriceChangeColor(textView: TextView?, data: String?) {

            data?.let {
                textView?.setText("($data%)")
                val change = data.toDouble()
                if (change > 0) {
                    textView?.setTextColor(textView.context.getColor(R.color.green))
                } else {
                    textView?.setTextColor(textView.context.getColor(R.color.red))
                }
            }


        }

        @JvmStatic
        @BindingAdapter(value = ["setPriceEnhance"], requireAll = false)
        fun setPriceEnhance(textView: TextView?, data: String?) {
            if (textView != null) {
                val size = data?.let { sizeConvertor(it.toDouble()) }
                textView.text = "$" +size
            }

        }

        @JvmStatic
        @BindingAdapter(value = ["setMarketCap"], requireAll = false)
        fun setMarketCap(textView: TextView?, data: String?) {
            if (textView != null) {
                data?.let {
                    val bNmN = it.toDouble() / 1000000000
                    textView.text =  sizeConvertor(bNmN)+" Bn$"
                }
            }

        }


        //Chart
        @JvmStatic
        @BindingAdapter(value = ["setMinChart", "setRedOrGreen"], requireAll = false)
        fun setMinChart(lineChart: LineChart, data: List<String>?, change: String?) {

            val changeDouble = change?.toDouble()
            data?.let {
                if (changeDouble != null) {
                    drawMinLineChart(lineChart, data, changeDouble < 0)
                }
            }
        }

        @JvmStatic
        @BindingAdapter(
            value = ["setMaxChart", "setRedOrGreen", "setLightDark"],
            requireAll = false
        )
        fun setMaxChart(
            lineChart: LineChart,
            data: List<String>?,
            change: String?,
            isDark: Boolean?
        ) {

            val changeDouble = change?.toDouble()
            data?.let {
                if (changeDouble != null) {
                    drawMaxLineChart(lineChart, data, changeDouble < 0, isDark)
                }
            }
        }


        private fun sizeConvertor(data: Double): String {
            return  decimalFormat.format(data)

        }

        private fun drawMinLineChart(lineChart: LineChart, data: List<String>, isRed: Boolean?) {
            val lineEntries = getDataSet(data)
            val color = if (isRed == true) R.color.red else R.color.green
            val lineDataSet = LineDataSet(lineEntries, "")
            lineDataSet.axisDependency = YAxis.AxisDependency.LEFT
            lineDataSet.isHighlightEnabled = false
            lineDataSet.setLineWidth(2f)
            lineDataSet.setColor(ContextCompat.getColor(lineChart.context, color))
            lineDataSet.setCircleColor(ContextCompat.getColor(lineChart.context, color))
            lineDataSet.circleRadius = 2f
            lineDataSet.setDrawCircleHole(false)
            lineDataSet.setDrawCircles(false)
            lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
            lineDataSet.setDrawFilled(false)
            lineDataSet.setFillColor(ContextCompat.getColor(lineChart.context, R.color.custom))
            lineDataSet.setDrawHighlightIndicators(false)
            lineDataSet.highLightColor = Color.RED
            lineDataSet.setValueTextColor(Color.DKGRAY)
            lineDataSet.valueTextSize = 0f
            val lineData = LineData(lineDataSet)


            lineChart.description.text = ""
            lineChart.description.isEnabled = false
            lineChart.setDrawMarkers(false)
//            lineChart.xAxis.position = XAxis.XAxisPosition.BOTH_SIDED
            lineChart.animateY(200)
            lineChart.animateX(200)
//            lineChart.xAxis.setDrawAxisLine(false)
//            lineChart.xAxis.setDrawGridLines(false)
//            lineChart.xAxis.textSize=10f
            lineChart.xAxis.isEnabled = false
//            lineChart.getAxis(AxisDependency.LEFT).setDrawAxisLine(false)
//            lineChart.getAxis(AxisDependency.LEFT).setDrawGridLines(false)
//            lineChart.getAxis(AxisDependency.LEFT).textSize=10f
            lineChart.getAxis(AxisDependency.LEFT).isEnabled = false
            lineChart.getAxis(AxisDependency.RIGHT).isEnabled = false
            lineChart.setPinchZoom(false)
            lineChart.isDoubleTapToZoomEnabled = false

            lineChart.setData(lineData)
            lineChart.legend.isEnabled = false

        }

        private fun drawMaxLineChart(
            lineChart: LineChart,
            data: List<String>,
            isRed: Boolean?,
            isDark: Boolean?
        ) {
            val lineEntries = getDataSet(data)
            val color = if (isRed == true) R.color.red else R.color.green
            val lightDark = if (isDark == true) Color.WHITE else Color.BLACK
            val lineDataSet = LineDataSet(lineEntries, "")
            lineDataSet.axisDependency = YAxis.AxisDependency.RIGHT
            lineDataSet.isHighlightEnabled = true
            lineDataSet.setLineWidth(2f)
            lineDataSet.setColor(ContextCompat.getColor(lineChart.context, color))
            lineDataSet.setCircleColor(ContextCompat.getColor(lineChart.context, color))
            lineDataSet.circleRadius = 2f
            lineDataSet.setDrawCircleHole(false)
            lineDataSet.setDrawCircles(true)
            lineDataSet.mode = LineDataSet.Mode.LINEAR
            lineDataSet.setDrawFilled(true)
            lineDataSet.setFillColor(ContextCompat.getColor(lineChart.context, color))
            lineDataSet.setDrawHighlightIndicators(true)
            lineDataSet.highLightColor = lightDark
            lineDataSet.setValueTextColor(Color.DKGRAY)
            lineDataSet.valueTextSize = 0f
            val lineData = LineData(lineDataSet)


            lineChart.description.text = ""
            lineChart.description.isEnabled = false
            lineChart.setDrawMarkers(true)
//            lineChart.xAxis.position = XAxis.XAxisPosition.BOTH_SIDED
            lineChart.animateY(1000)
            lineChart.animateX(1000)
//            lineChart.xAxis.setDrawAxisLine(false)
//            lineChart.xAxis.setDrawGridLines(false)
//            lineChart.xAxis.textSize=10f
            lineChart.xAxis.isEnabled = true
            lineChart.xAxis.textColor = lightDark
//            lineChart.getAxis(AxisDependency.LEFT).setDrawAxisLine(false)
//            lineChart.getAxis(AxisDependency.LEFT).setDrawGridLines(false)
//            lineChart.getAxis(AxisDependency.LEFT).textSize=10f
            lineChart.getAxis(AxisDependency.LEFT).textColor = lightDark
            lineChart.getAxis(AxisDependency.RIGHT).textColor = lightDark
            lineChart.getAxis(AxisDependency.LEFT).isEnabled = true
            lineChart.getAxis(AxisDependency.RIGHT).isEnabled = false
            lineChart.setPinchZoom(true)
            lineChart.isDoubleTapToZoomEnabled = true

            lineChart.setData(lineData)
            lineChart.legend.isEnabled = false

        }

        private fun getDataSet(data: List<String>): List<Entry> {
            val lineEntries: MutableList<Entry> = ArrayList()

            data.forEachIndexed { index, s ->

                lineEntries.add(Entry(index.toFloat(), s.toFloat()))

            }

            return lineEntries
        }

    }
}