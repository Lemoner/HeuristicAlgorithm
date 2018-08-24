package com.lmr.chart;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleEdge;

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
public class DataLineChart {
	
	public static XYSeriesCollection seriescollection=new XYSeriesCollection();
	
	public DataLineChart() {
	}

	public void createDataset() {
		
		int n=26;
		int[] sumTime,fragTime;
		fragTime=new int[n+1];
//		sumTime=new int[]{0,9,21,32,36,43,45,50,58,63,70,71,77,78,87,91,92,95,98,104,105,116,149,156,247,249,250};
		sumTime=new int[]{0,1,1,3,3,3,3,3,3,3,3,3,3,6,8,8,18,18,18,18,26,26,26,27,30,36,38,40,40,42,44,47,47,47,48,50,54,54,55,56};
		
		for(int i=1;i<=n;i++){
			fragTime[i]=sumTime[i]-sumTime[i-1];
		}
		
//		XYSeries xyseries = new XYSeries("");
//		
//		for(int i=0;i<=n;i++){
//			xyseries.add(sumTime[i],i);
//		}
//		
//		seriescollection.addSeries(xyseries);
		
		int t,p;
		double f;
		XYSeries xyseries = new XYSeries("");
		
		t=0;
		p=0;
		for(int i=1;i<=n;i++){
			t+=fragTime[i];
			p+=(i-1)*fragTime[i];
		}
		
		for(double x=60.0;x<80;x+=0.00001){
			f=0.0;
			for(int i=1;i<=n;i++){
				if(x-i+1==0){
					continue;
				}
				f+=1.0/(x-i+1);
			}
			f-=(n*1.0)/(x-p*1.0/t);
			if(f<0.0){
				f=-f;
			}
			xyseries.add(x,f);
		}
		
		seriescollection.addSeries(xyseries);
		
	}
	
	public void createDataset1() {
		
		XYSeries xyseries = new XYSeries("");
		
		double f;
		for(double x=-1;x<=2;x+=0.00001){
			f=x*Math.sin(10.0*Math.PI*x)+2;
			xyseries.add(x,f);
		}
		
		seriescollection.addSeries(xyseries);
		
	}

	
	public ChartPanel createChart() {
		
		createDataset();
		
		// 2：创建Chart[创建不同图形]
		JFreeChart chart = ChartFactory.createXYLineChart("xsin(10πx)+2", "", "", seriescollection);
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
	
	public static void main(String[] args) {
		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024, 420);
		frame.setLocationRelativeTo(null);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// 创建图形
				new DataLineChart().createChart();
				initSeriesCollection();
				ChartPanel chartPanel = new DataLineChart().createChart();
				frame.getContentPane().add(chartPanel);
				frame.setVisible(true);
			}
		});

	}

}
