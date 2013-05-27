package main.gui.view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;

import main.implementations.order.AlgorithmicTrade;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class TradeGraphPlotter extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private Map<AlgorithmicTrade, AlgorithmicTrade> tradePair;

	public TradeGraphPlotter(String appTitle, String chartTitle, Map<AlgorithmicTrade, AlgorithmicTrade> tradePair) {
		this.tradePair = tradePair;
		XYSeriesCollection xySeries = createDataSet();
		JFreeChart chart = createChart(xySeries, chartTitle);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(1024, 720));
		setContentPane(chartPanel);
	}

	private XYSeriesCollection createDataSet() {
		XYSeries bidData = new XYSeries("Bids");
		XYSeries askData = new XYSeries("Asks");
		XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
		
		List<AlgorithmicTrade> keys = new ArrayList<AlgorithmicTrade>();
		keys.addAll(tradePair.keySet());
		
		for (AlgorithmicTrade bid : keys) {
			AlgorithmicTrade ask = tradePair.get(bid);
			bidData.add(bid.getBidOrder().getDateTime().getTime(), bid.getBidOrder().getPrice());
			askData.add(ask.getAskOrder().getDateTime().getTime(), ask.getAskOrder().getPrice());
		}
		
		xySeriesCollection.addSeries(bidData);
		xySeriesCollection.addSeries(askData);
		
		return xySeriesCollection;
	}

	private JFreeChart createChart(XYSeriesCollection xySeriesCollection, String chartTitle) {
		JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, 
				"Time", 
				"Price", 
				xySeriesCollection, 
				PlotOrientation.HORIZONTAL, 
				true, 
				true, 
				false);
		
		XYPlot plot = (XYPlot) chart.getPlot();
		NumberAxis domain = (NumberAxis) plot.getDomainAxis();
		domain.setRange(0, 30);
		domain.setTickUnit(new NumberTickUnit(1));
		DateAxis range = (DateAxis) plot.getRangeAxis();
		range.setTickUnit(new DateTickUnit(DateTickUnit.SECOND, 60));
		
		return chart;
	}
}
