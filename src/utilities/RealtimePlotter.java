package utilities;

import javax.swing.JFrame;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.knowm.xchart.XYSeries;

public class RealtimePlotter {
    private double[] xData, yData;
    private String seriesName;
    private final XChartPanel<XYChart> chartPanel;

    public RealtimePlotter(String seriesName) {
        this.seriesName = seriesName;
        this.chartPanel = buildPanel();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("XChart");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(chartPanel);

                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public XChartPanel<XYChart> buildPanel() {
        return new XChartPanel<XYChart>(getXYChart());
    }

    public XYChart getXYChart() {
        xData = new double[1];
        yData = new double[1];

        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        // Customize Chart
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
        chart.getStyler().setChartTitleVisible(false);
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setMarkerSize(16);

        XYSeries series = chart.addSeries(seriesName, xData, yData);
        series.setMarker(SeriesMarkers.CIRCLE);
        
        chart.setXAxisTitle("f1");
        chart.setYAxisTitle("f2");

        return chart;
    }

    public void updateData(double[] xData, double yData[]) {
        this.xData = xData;
        this.yData = yData;
        this.chartPanel.getChart().updateXYSeries(this.seriesName, this.xData, this.yData, null);
        this.chartPanel.updateUI();
    }
}