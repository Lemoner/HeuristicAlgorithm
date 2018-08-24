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
 *       ����ͼ���裺<br/>
 *       1���������ݼ���<br/>
 *       2������Chart��<br/>
 *       3:���ÿ���ݣ���ֹ������ʾ�����<br/>
 *       4:�����ӽ�����Ⱦ��<br/>
 *       5:���������ֽ�����Ⱦ<br/>
 *       6:ʹ��chartPanel����<br/>
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
		
		// 2������Chart[������ͬͼ��]
		JFreeChart chart = ChartFactory.createXYLineChart("xsin(10��x)+2", "", "", seriescollection);
		// 3:���ÿ���ݣ���ֹ������ʾ�����
		ChartUtils.setAntiAlias(chart);// �����
		// 4:�����ӽ�����Ⱦ[[���ò�ͬ��Ⱦ]]
//		ChartUtils.setLineRender(chart.getCategoryPlot(), false,true);//
//		// 5:���������ֽ�����Ⱦ
//		ChartUtils.setXAixs(chart.getCategoryPlot());// X��������Ⱦ
//		ChartUtils.setYAixs(chart.getCategoryPlot());// Y��������Ⱦ
		// ���ñ�ע�ޱ߿�
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		// ��עλ���Ҳ�
		chart.getLegend().setPosition(RectangleEdge.RIGHT);
		
		XYPlot plot=(XYPlot) chart.getPlot();
		plot.setDomainGridlinesVisible(false);
		
		// 6:ʹ��chartPanel����
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}

	/**
	 * ��ʼ��������
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
				// ����ͼ��
				new DataLineChart().createChart();
				initSeriesCollection();
				ChartPanel chartPanel = new DataLineChart().createChart();
				frame.getContentPane().add(chartPanel);
				frame.setVisible(true);
			}
		});

	}

}
