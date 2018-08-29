package com.lmr.chart;

import java.awt.Color;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleEdge;

import com.lmr.fun.Ant;
import com.lmr.fun.Ant2;
import com.lmr.fun.Ant3;

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
public class FLineChart {
	
	public static XYSeriesCollection seriescollection=new XYSeriesCollection();
	
	public FLineChart() {
	}

	public static void AddSeriesCollection(List<Ant> betterAntList, String str) {
		XYSeries xyseries = new XYSeries(str);
		for(int i=0;i<betterAntList.size();i++){
			if(i>100){
				break;
			}
			if(i%3==0){
				xyseries.add((i+1),betterAntList.get(i).m_nCurFunVal);
//				xyseries.add((i+1),betterAntList.get(i).m_nCurXVal);				
			}
		}
		seriescollection.addSeries(xyseries);
	}
	
	public static void AddSeriesCollection(Ant[] ants,String str){
		XYSeries xyseries = new XYSeries(str);
		for(int i=0;i<ants.length;i++){
			if(i>100){
				break;
			}
			if(i%3==0){
				xyseries.add((i+1),ants[i].m_nCurFunVal);
//				xyseries.add((i+1),ants[i].m_nCurXVal);				
			}
		}
		seriescollection.addSeries(xyseries);
	}
	
	public static void AddSeriesCollection2(Ant2[] ants,String str){
//		XYSeries xyseries = new XYSeries(str);
//		for(int i=0;i<ants.length;i++){
//			xyseries.add((i+1),ants[i].m_nCurFunVal);
//		}
//		seriescollection.addSeries(xyseries);
		
		XYSeries xyseries0 = new XYSeries("��0");
		XYSeries xyseries1 = new XYSeries("��1");
		for(int i=0;i<ants.length;i++){
			xyseries0.add((i+1),ants[i].m_nCurVarVal[0]);
			xyseries1.add((i+1),ants[i].m_nCurVarVal[1]);
		}
		seriescollection.addSeries(xyseries0);
		seriescollection.addSeries(xyseries1);
		
	}
	
	public static void AddSeriesCollection3(Ant3[] ants,String str){
//		XYSeries xyseries = new XYSeries(str);
//		for(int i=0;i<ants.length;i++){
//			xyseries.add((i+1),ants[i].m_nCurFunVal);
//		}
//		seriescollection.addSeries(xyseries);
		
		XYSeries xyseries0 = new XYSeries("��0");
		XYSeries xyseries1 = new XYSeries("��1");
		for(int i=0;i<ants.length;i++){
			xyseries0.add((i+1),ants[i].m_nCurVarVal[0]);
			xyseries1.add((i+1),ants[i].m_nCurVarVal[1]);
		}
		seriescollection.addSeries(xyseries0);
		seriescollection.addSeries(xyseries1);
		
	}
	
	
//	public DefaultCategoryDataset createDataset() {
//		// ��ע���
//		String[] categories = { "V1.0", "V1.1", "V1.2", "V1.3", "V1.4" };
//		Vector<Serie> series = new Vector<Serie>();
//		// �������ƣ��������е�ֵ����
//		series.add(new Serie("���η�����", new Integer[] { 30, 23, 37, 25, 19 }));
//		series.add(new Serie("���ν��ȱ����", new Integer[] { 15, 20, 23, 21, 15 }));
////		series.add(new Serie("London", new Double[] { 48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2 }));
////		series.add(new Serie("Berlin", new Double[] { 42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1 }));
//		// 1���������ݼ���
//		DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
//		return dataset;
//	}

	public ChartPanel createChart() {
		// 2������Chart[������ͬͼ��]
		JFreeChart chart = ChartFactory.createXYLineChart("", "", "", seriescollection);
		// 3:���ÿ���ݣ���ֹ������ʾ�����
		ChartUtils.setAntiAlias(chart);// �����
		// 4:�����ӽ�����Ⱦ[[���ò�ͬ��Ⱦ]]
//		ChartUtils.setLineRender(chart.getCategoryPlot(), false,true);//
//		// 5:���������ֽ�����Ⱦ
//		ChartUtils.setXAixs(chart.getCategoryPlot());// X��������Ⱦ
//		ChartUtils.setYAixs(chart.getCategoryPlot());// Y��������Ⱦ
		
		XYPlot xyplot = (XYPlot)chart.getPlot();
		XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)xyplot.getRenderer();
        xylineandshaperenderer.setBaseShapesVisible(true);
        xylineandshaperenderer.setBaseShapesFilled(true);
		
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

}
