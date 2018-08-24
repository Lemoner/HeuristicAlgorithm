package com.lmr.chart;

import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleEdge;

import com.lmr.tsp.Ant;

/**
 * 
 * @author ccw
 * @date 2014-6-11
 *       <p>
 *       创建图表步骤：<br/>
 *       1：创建数据集合<br/>
 *       2：创建Chart：<br/>
 *       3:设置抗锯齿，防止字体显示不清楚<br/>
 *       4:对柱子进行渲染，<br/>
 *       5:对其他部分进行渲染<br/>
 *       6:使用chartPanel接收<br/>
 * 
 *       </p>
 */
public class LineChart {
	
	public static XYSeriesCollection seriescollection=new XYSeriesCollection();
	
	public LineChart() {
	}

	private XYSeriesCollection createDataset(Ant[] bestAnts) {
		// TODO Auto-generated method stub
		
		XYSeriesCollection xyseriescollection=new XYSeriesCollection();
		
		XYSeries bestxyseries = new XYSeries("最优路径");
		
		for(int i=0;i<bestAnts.length;i++){
			bestxyseries.add((i+1),bestAnts[i].getAntLength());
		}
		
		xyseriescollection.addSeries(bestxyseries);
		
		return xyseriescollection;
	}
	
	private XYSeriesCollection createDataset(Ant[] betterAnts,Ant[] bestAnts) {
		// TODO Auto-generated method stub
		
		XYSeriesCollection xyseriescollection=new XYSeriesCollection();
		
		XYSeries betterxyseries = new XYSeries("局部较优");
		XYSeries bestxyseries = new XYSeries("全局最优");
		
		
		for(int i=0;i<betterAnts.length;i++){
			betterxyseries.add((i+1),betterAnts[i].getAntLength());
		}
		
		for(int i=0;i<bestAnts.length;i++){
			bestxyseries.add((i+1),bestAnts[i].getAntLength());
		}
		
		xyseriescollection.addSeries(betterxyseries);
		xyseriescollection.addSeries(bestxyseries);
		
		return xyseriescollection;
	}
	
	private XYSeriesCollection createDataset(Ant[] acoAnts,Ant[] acsAnts,Ant[] mmasAnts) {
		// TODO Auto-generated method stub
		
		XYSeriesCollection xyseriescollection=new XYSeriesCollection();
		
		XYSeries acoxyseries = new XYSeries("ACO");
		XYSeries acsxyseries = new XYSeries("ACS");
		XYSeries mmasxyseries = new XYSeries("MMAS");
		
		
		for(int i=0;i<acoAnts.length;i++){
			acoxyseries.add((i+1),acoAnts[i].getAntLength());
		}
		for(int i=0;i<acsAnts.length;i++){
			acsxyseries.add((i+1),acsAnts[i].getAntLength());
		}
		for(int i=0;i<mmasAnts.length;i++){
			mmasxyseries.add((i+1),mmasAnts[i].getAntLength());
		}
		
		xyseriescollection.addSeries(acoxyseries);
		xyseriescollection.addSeries(acsxyseries);
		xyseriescollection.addSeries(mmasxyseries);
		
		return xyseriescollection;
	}
	
	public static void AddSeriesCollection(Ant[] ants,String str){
		XYSeries xyseries = new XYSeries(str);
		for(int i=0;i<ants.length;i++){
			xyseries.add((i+1),ants[i].getAntLength());
		}
		seriescollection.addSeries(xyseries);
	}
	
	
//	public DefaultCategoryDataset createDataset() {
//		// 标注类别
//		String[] categories = { "V1.0", "V1.1", "V1.2", "V1.3", "V1.4" };
//		Vector<Serie> series = new Vector<Serie>();
//		// 柱子名称：柱子所有的值集合
//		series.add(new Serie("本次发现数", new Integer[] { 30, 23, 37, 25, 19 }));
//		series.add(new Serie("本次解决缺陷数", new Integer[] { 15, 20, 23, 21, 15 }));
////		series.add(new Serie("London", new Double[] { 48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2 }));
////		series.add(new Serie("Berlin", new Double[] { 42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1 }));
//		// 1：创建数据集合
//		DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
//		return dataset;
//	}

	
	public ChartPanel createChart(Ant[] bestAnts) {
		// 2：创建Chart[创建不同图形]
		JFreeChart chart = ChartFactory.createXYLineChart("", "", "", createDataset(bestAnts));
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染[[采用不同渲染]]
//		ChartUtils.setLineRender(chart.getCategoryPlot(), false,true);//
//		// 5:对其他部分进行渲染
//		ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
//		ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		// 标注位于右侧
		chart.getLegend().setPosition(RectangleEdge.RIGHT);
		
		XYPlot plot=(XYPlot) chart.getPlot();
		plot.setDomainGridlinesVisible(false);
		
		// 6:使用chartPanel接收
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}
	
	public ChartPanel createChart(Ant[] betterAnts,Ant[] bestAnts) {
		// 2：创建Chart[创建不同图形]
		JFreeChart chart = ChartFactory.createXYLineChart("", "", "", createDataset(betterAnts,bestAnts));
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染[[采用不同渲染]]
//		ChartUtils.setLineRender(chart.getCategoryPlot(), false,true);//
//		// 5:对其他部分进行渲染
//		ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
//		ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		// 标注位于右侧
		chart.getLegend().setPosition(RectangleEdge.RIGHT);
		
		XYPlot plot=(XYPlot) chart.getPlot();
		plot.setDomainGridlinesVisible(false);
		
		// 6:使用chartPanel接收
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}
	
	public ChartPanel createChart(Ant[] acoAnts,Ant[] acsAnts,Ant[] mmasAnts) {
		// 2：创建Chart[创建不同图形]
		JFreeChart chart = ChartFactory.createXYLineChart("", "", "", createDataset(acoAnts,acsAnts,mmasAnts));
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染[[采用不同渲染]]
//		ChartUtils.setLineRender(chart.getCategoryPlot(), false,true);//
//		// 5:对其他部分进行渲染
//		ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
//		ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		// 标注位于右侧
		chart.getLegend().setPosition(RectangleEdge.RIGHT);
		
		XYPlot plot=(XYPlot) chart.getPlot();
		plot.setDomainGridlinesVisible(false);
		
		// 6:使用chartPanel接收
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}
	
	public ChartPanel createChart() {
		// 2：创建Chart[创建不同图形]
		JFreeChart chart = ChartFactory.createXYLineChart("", "", "", seriescollection);
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染[[采用不同渲染]]
//		ChartUtils.setLineRender(chart.getCategoryPlot(), false,true);//
//		// 5:对其他部分进行渲染
//		ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
//		ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		// 标注位于右侧
		chart.getLegend().setPosition(RectangleEdge.RIGHT);
		
		XYPlot plot=(XYPlot) chart.getPlot();
		plot.setDomainGridlinesVisible(false);
		
		// 6:使用chartPanel接收
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}

	/**
	 * 初始化数据组
	 */
	public static void initSeriesCollection() {
		seriescollection=new XYSeriesCollection();
	}

}
