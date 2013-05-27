package main.evaluator;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;

public class TradeGraphPlotter extends JFrame {

	private static final long serialVersionUID = 1L;

	public TradeGraphPlotter(String appTitle, String chartTitle) {
		XYDataset xyDataSet = createDataSet();
		JFreeChart chart = createChart(xyDataSet, chartTitle);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(chartPanel);
	}

	private XYDataset createDataSet() {
		XYSeries tradeData = new XYSeries("Trades");
		
		return null;
	}

	private JFreeChart createChart(XYDataset xyDataSet, String chartTitle) {
		JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, 
				"Time", 
				"Price", 
				xyDataSet, 
				PlotOrientation.HORIZONTAL, 
				true, 
				true, 
				false);
		
		XYPlot plot = (XYPlot) chart.getPlot();
		
		return chart;
	}
}
