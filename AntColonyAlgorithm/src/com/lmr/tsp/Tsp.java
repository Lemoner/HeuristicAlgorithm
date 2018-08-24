package com.lmr.tsp;

import com.lmr.chart.LineChart;

/**
 * Tsp问题类
 */
public class Tsp {

	/**
	 * 初始化数据
	 */
	public void InitData() throws CloneNotSupportedException {
		// 根据样本编号选择不同的城市样本
		if (Common.N_CITY_ID == 1) {
			// 最优结果293
			// [0, 3, 8, 2, 1, 5, 9, 4, 6, 7, 0]
			// 初始化城市
			Common.N_CITY_COUNT = 10;
			Common.g_Distance = new double[Common.N_CITY_COUNT][Common.N_CITY_COUNT];
			Common.g_Trial = new double[Common.N_CITY_COUNT][Common.N_CITY_COUNT];

			// 生成两城市之间的距离
			int[][] Distance = { { 0, 98, 99, 27, 89, 175, 96, 26, 64, 49 }, { 98, 0, 54, 98, 73, 18, 77, 89, 63, 64 },
					{ 99, 54, 0, 61, 36, 69, 46, 53, 13, 81 }, { 27, 98, 61, 0, 86, 75, 15, 87, 21, 87 },
					{ 89, 73, 36, 86, 0, 46, 19, 87, 65, 46 }, { 175, 18, 69, 75, 46, 0, 26, 53, 21, 23 },
					{ 96, 77, 46, 15, 19, 26, 0, 46, 36, 54 }, { 26, 89, 53, 87, 87, 53, 46, 0, 75, 65 },
					{ 64, 63, 13, 21, 65, 21, 36, 75, 0, 54 }, { 49, 64, 81, 87, 46, 23, 54, 65, 54, 0 }, };

			for (int i = 0; i < Common.N_CITY_COUNT; i++) {
				for (int j = 0; j < Common.N_CITY_COUNT; j++) {
					Common.g_Distance[i][j] = Distance[i][j];
				}
			}
		} else if (Common.N_CITY_ID == 2) {
			// 最优结果423.741
			// 初始化城市
			Common.N_CITY_COUNT = 30;
			Common.g_Distance = new double[Common.N_CITY_COUNT][Common.N_CITY_COUNT];
			Common.g_Trial = new double[Common.N_CITY_COUNT][Common.N_CITY_COUNT];

			// 城市坐标点(x,y)
			int[] x = { 41, 37, 54, 25, 7, 2, 68, 71, 54, 83, 64, 18, 22, 83, 91, 25, 24, 58, 71, 74, 87, 18, 13, 82,
					62, 58, 45, 41, 44, 4 };
			int[] y = { 94, 84, 67, 62, 64, 99, 58, 44, 62, 69, 60, 54, 60, 46, 38, 38, 42, 69, 71, 78, 76, 40, 40, 7,
					32, 35, 21, 26, 35, 50 };

			// 生成两城市之间的距离
			for (int i = 0; i < Common.N_CITY_COUNT; i++) {
				for (int j = 0; j < Common.N_CITY_COUNT; j++) {
					if (i == j) {
						Common.g_Distance[i][j] = 0.0;// 同一个城市为0
					} else {
						double rij = Math.sqrt(((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j])));
						Common.g_Distance[i][j] = rij;
						Common.g_Distance[j][i] = Common.g_Distance[i][j];
					}
				}
			}
		} else if (Common.N_CITY_ID == 3) {
			// 最优结果427.96
			// 初始化城市
			Common.N_CITY_COUNT = 50;
			Common.g_Distance = new double[Common.N_CITY_COUNT][Common.N_CITY_COUNT];
			Common.g_Trial = new double[Common.N_CITY_COUNT][Common.N_CITY_COUNT];

			// 城市坐标点(x,y)
			int[] x = { 5, 5, 5, 7, 8, 10, 12, 13, 16, 17, 17, 20, 21, 21, 25, 25, 27, 27, 30, 30, 31, 31, 32, 32, 36,
					37, 37, 38, 39, 40, 42, 42, 43, 45, 46, 48, 49, 51, 52, 52, 52, 56, 57, 58, 58, 59, 61, 62, 62,
					63 };
			int[] y = { 64, 25, 6, 38, 52, 17, 42, 13, 57, 33, 63, 26, 47, 10, 32, 55, 68, 23, 48, 15, 62, 32, 22, 39,
					16, 69, 52, 46, 10, 30, 57, 41, 67, 35, 10, 28, 49, 21, 33, 41, 64, 37, 58, 27, 48, 15, 33, 42, 63,
					69 };

			// 生成两城市之间的距离
			for (int i = 0; i < Common.N_CITY_COUNT; i++) {
				for (int j = 0; j < Common.N_CITY_COUNT; j++) {
					if (i == j) {
						Common.g_Distance[i][j] = 0.0;// 同一个城市为0
					} else {
						double rij = Math.sqrt(((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j])));
						Common.g_Distance[i][j] = rij;
						Common.g_Distance[j][i] = Common.g_Distance[i][j];
					}
				}
			}
		} else if (Common.N_CITY_ID == 4) {
			// 最优结果542.31
			// 初始化城市
			Common.N_CITY_COUNT = 75;
			Common.g_Distance = new double[Common.N_CITY_COUNT][Common.N_CITY_COUNT];
			Common.g_Trial = new double[Common.N_CITY_COUNT][Common.N_CITY_COUNT];

			// 城市坐标点(x,y)
			int[] x = { 6, 7, 9, 10, 11, 12, 12, 15, 15, 15, 16, 17, 20, 21, 21, 21, 22, 22, 26, 26, 26, 27, 28, 30, 30,
					30, 31, 33, 33, 35, 35, 35, 36, 36, 38, 40, 40, 40, 40, 41, 43, 44, 45, 45, 47, 48, 50, 50, 50, 50,
					50, 50, 51, 52, 54, 54, 55, 55, 55, 55, 55, 55, 57, 59, 60, 62, 62, 62, 62, 64, 65, 66, 66, 67,
					70 };
			int[] y = { 25, 43, 56, 70, 28, 17, 38, 5, 14, 56, 19, 64, 30, 48, 45, 36, 53, 22, 29, 13, 59, 24, 39, 50,
					20, 60, 76, 34, 44, 51, 16, 60, 6, 26, 33, 37, 66, 60, 20, 46, 26, 13, 42, 35, 66, 21, 30, 40, 50,
					70, 4, 15, 42, 26, 38, 10, 34, 45, 50, 65, 57, 20, 72, 5, 15, 57, 48, 35, 24, 4, 27, 14, 8, 41,
					64 };

			// 生成两城市之间的距离
			for (int i = 0; i < Common.N_CITY_COUNT; i++) {
				for (int j = 0; j < Common.N_CITY_COUNT; j++) {
					if (i == j) {
						Common.g_Distance[i][j] = 0.0;// 同一个城市为0
					} else {
						double rij = Math.sqrt(((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j])));
						Common.g_Distance[i][j] = rij;
						Common.g_Distance[j][i] = Common.g_Distance[i][j];
					}
				}
			}
		} else if (Common.N_CITY_ID == 5) {
			// 最优结果10628
			// 初始化城市
			Common.N_CITY_COUNT = 48;
			Common.g_Distance = new double[Common.N_CITY_COUNT][Common.N_CITY_COUNT];
			Common.g_Trial = new double[Common.N_CITY_COUNT][Common.N_CITY_COUNT];

			// 城市坐标点(x,y)
			int x[] = { 6734, 2233, 5530, 401, 3082, 7608, 7573, 7265, 6898, 1112, 5468, 5989, 4706, 4612, 6347, 6107,
					7611, 7462, 7732, 5900, 4483, 6101, 5199, 1633, 4307, 675, 7555, 7541, 3177, 7352, 7545, 3245, 6426,
					4608, 23, 7248, 7762, 7392, 3484, 6271, 4985, 1916, 7280, 7509, 10, 6807, 5185, 3023 };
			int y[] = { 1453, 10, 1424, 841, 1644, 4458, 3716, 1268, 1885, 2049, 2606, 2873, 2674, 2035, 2683, 669,
					5184, 3590, 4723, 3561, 3369, 1110, 2182, 2809, 2322, 1006, 4819, 3981, 756, 4506, 2801, 3305, 3173,
					1198, 2216, 3779, 4595, 2244, 2829, 2135, 140, 1569, 4899, 3239, 2676, 2993, 3258, 1942 };

			// 生成两城市之间的距离
			for (int i = 0; i < Common.N_CITY_COUNT; i++) {
				for (int j = 0; j < Common.N_CITY_COUNT; j++) {
					if (i == j) {
						Common.g_Distance[i][j] = 0.0;// 同一个城市为0
					} else {
						// 计算距离矩阵 ，它有48个城市，距离计算方法为伪欧氏距离
						double rij = Math.sqrt(((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j])) / 10.0);
						// 四舍五入，取整
						int tij = (int) Math.round(rij);
						if (tij < rij) {
							Common.g_Distance[i][j] = tij + 1;
							Common.g_Distance[j][i] = Common.g_Distance[i][j];
						} else {
							Common.g_Distance[i][j] = tij;
							Common.g_Distance[j][i] = Common.g_Distance[i][j];
						}
					}
				}
			}
		}
		
		LineChart.initSeriesCollection();

		long start,end;
		double acot,acst,mmast;
		
		if(Main.frame.checkBox1.isSelected()){
			
////			double[] p=new double[]{0.1,0.3,0.5,0.7,0.9};
////			double[] p=new double[]{0.5,1.0,2.0,3.0,4.0,5.0,7.0,9.0};
//			int[] p=new int[]{5,10,15,20,25,30};
//			
//			for(int i=0;i<p.length;i++){
//				start=System.currentTimeMillis();
//				// 调用ACO算法
//				ACO aco=new ACO();
//				PublicFun.N_ANT_COUNT=p[i];
//				aco.initParameter();
//				aco.Start();
//				end=System.currentTimeMillis();
//				acot=(end-start)*1.00/1000;
//				
//				LineChart.AddSeriesCollection(aco.m_bestAnts, p[i]+"");
//				Main.frame.bestAntTA.append("蚂蚁数量:"+p[i]+" ACO长度:" + aco.m_bestAnts[aco.m_bestAnts.length - 1].getAntLength()+" 耗时:"+acot+" s\n");
//			}
			
			start=System.currentTimeMillis();
			// 调用ACO算法
			ACO aco=new ACO();
			aco.Start();
			end=System.currentTimeMillis();
			acot=(end-start)*1.00/1000;
			
			LineChart.AddSeriesCollection(aco.m_bestAnts, "ACO");
			Main.frame.bestAntTA.append("ACO长度:" + aco.m_bestAnts[aco.m_bestAnts.length - 1].getAntLength()+" 耗时:"+acot+" s\n");
		}
		
		if(Main.frame.checkBox2.isSelected()){
			start=System.currentTimeMillis();
			// 调用ACS算法
			ACS acs = new ACS();
			acs.Start();
			end=System.currentTimeMillis();
			acst=(end-start)*1.00/1000;
			
			LineChart.AddSeriesCollection(acs.m_bestAnts, "ACS");
			Main.frame.bestAntTA.append("ACS长度:" + acs.m_bestAnts[acs.m_bestAnts.length - 1].getAntLength()+" 耗时:"+acst+" s\n");
		}
		
		if(Main.frame.checkBox3.isSelected()){
			start=System.currentTimeMillis();
			//调用MMAS算法
			MMAS mmas=new MMAS();
			mmas.Start();
			end=System.currentTimeMillis();
			mmast=(end-start)*1.00/1000;
			
			LineChart.AddSeriesCollection(mmas.m_bestAnts, "MMAS");
			Main.frame.bestAntTA.append("MMAS长度:" + mmas.m_bestAnts[mmas.m_bestAnts.length - 1].getAntLength()+" 耗时:"+mmast+" s\n");
		}
		
		Main.frame.acoChartPanel.removeAll();
		Main.frame.acoChartPanel.add(new LineChart().createChart());

		Main.frame.betterAntTA.setVisible(false);
		Main.frame.bestAntTA.setCaretPosition(0);

		Main.frame.rootPanel.setVisible(false);
		Main.frame.rootPanel.repaint();
		Main.frame.rootPanel.setVisible(true);

	}

}
