package com.bangkit.stuntcare.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.stuntcare.ui.theme.StuntCareTheme
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.component.shape.shader.fromBrush
import com.patrykandpatrick.vico.compose.style.ChartStyle
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.DefaultAlpha
import com.patrykandpatrick.vico.core.DefaultDimens
import com.patrykandpatrick.vico.core.chart.line.LineChart.LineSpec
import com.patrykandpatrick.vico.core.component.marker.MarkerComponent
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.entry.entriesOf
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.patrykandpatrick.vico.core.legend.LegendItem
import com.patrykandpatrick.vico.core.marker.Marker

@Composable
fun QuadLineChartComponent(
    modifier: Modifier = Modifier
) {
    val xValue: Array<Float> = dummyLineChart.map {
        it.xValue
    }.toTypedArray()

    val yValue: Array<Float> = dummyLineChart.map {
        it.yValue
    }.toTypedArray()

    val color: List<Color> = listOf(Color.Green, Color.Blue)
    val localColor = LocalContentColor.current

    val mLineComponent: MutableList<LineComponent> = mutableListOf()
    val lineComponent: List<LineComponent> = mLineComponent

    val mLineChart: MutableList<LineSpec> = mutableListOf()
    val lineChart: List<LineSpec> = mLineChart

    color.map {
        val lineComponentData = LineComponent(
            color = it.toArgb(),
            thicknessDp = 2f
        )
        val lineChartData = LineSpec(
            lineColor = it.toArgb(),
        )
        mLineComponent.add(lineComponentData)
        mLineChart.add(lineChartData)
    }

    val chartEntryModel = entryModelOf(entriesOf(*xValue), entriesOf(*yValue))
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        ProvideChartStyle(
            remember {
                ChartStyle(
                    axis = ChartStyle.Axis(
                        axisLabelColor = localColor,
                        axisGuidelineColor = Color.Transparent,
                        axisLineColor = localColor,
                    ),
                    columnChart = ChartStyle.ColumnChart(
                        columns = lineComponent
                    ),
                    lineChart = ChartStyle.LineChart(
                        lines = lineChart
                    ),
                    marker = ChartStyle.Marker(),
                    elevationOverlayColor = localColor
                )
            }
        ) {
            Chart(
                chart = lineChart(),
                model = chartEntryModel,
                startAxis = rememberStartAxis(),
                bottomAxis = rememberBottomAxis()
            )
        }
    }
}

data class ChartDataImpl(
    val chartString: String, val xValue: Float, val yValue: Float
)

val dummyLineChart = listOf(
    ChartDataImpl("0.1 Tahun", 11F, 48F),
    ChartDataImpl("0.2 Tahun", 12F, 50F),
    ChartDataImpl("0.3 Tahun", 13F, 51F),
    ChartDataImpl("0.4 Tahun", 14F, 55F),
    ChartDataImpl("0.5 Tahun", 15F, 57F)
)

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun LineChartComponenetPreview() {
    StuntCareTheme {
        QuadLineChartComponent(
        )
    }
}
