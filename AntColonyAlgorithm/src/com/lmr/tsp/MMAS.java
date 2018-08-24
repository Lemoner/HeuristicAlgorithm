package com.lmr.tsp;

import com.lmr.chart.LineChart;

/**
 * 最大最小蚁群系统算法
 */
public class MMAS {

	/**
	 * 蚂蚁数组
	 */
	public Ant[] m_antAry = new Ant[Common.N_ANT_COUNT];
	/**
	 * 定义一组蚂蚁,用来保存每一次搜索中较优结果,不参与搜索
	 */
	public Ant[] m_betterAnts = new Ant[Common.N_IT_COUNT];
	/**
	 * 定义一组蚂蚁,用来保存每一次搜索结束的最终最优结果,不参与搜索
	 */
	public Ant[] m_bestAnts = new Ant[Common.N_IT_COUNT];

	/**
	 * 算法开始
	 */
	public void Start() {
		initParameter();
		initTrial();
		Iterator();
//		ShowResult();
	}

	/**
	 * 初始化参数
	 */
	private void initParameter() {
		
		Common.ALPHA=1.0;
		Common.BETA=2.0;
		Common.ROU=0.02;
		Common.DBQ=1.0;
		
		double greedLen=GreedPathLength();
		
		Common.DBS=1.0/(greedLen*Common.ROU);
		
		double temp=Math.exp(Math.log(Common.PB_Best)/((double)Common.N_CITY_COUNT));
		Common.DBS_RATE=(2.0*(1.0-temp))/((((double)Common.N_CITY_COUNT)-2.0)*temp);		
		
	}

	/**
	 * 使用贪心计算路径长度，每次选择相邻的第一个未访问的城市
	 * @return
	 */
	private double GreedPathLength() {
		
		int index=Common.rnd(0, Common.N_CITY_COUNT);
		int pre;
		boolean[] visited=new boolean[Common.N_CITY_COUNT];
		double len=0.0;
		
		visited[index]=true;
		pre=index;
		for (int i = 1; i < Common.N_CITY_COUNT; i++) {
			for (int j = 0; j < Common.N_CITY_COUNT; j++) {
				if(!visited[j]){
					len+=Common.g_Distance[pre][j];
					pre=j;
					visited[j]=true;
					break;
				}
			}
		}
		len+=Common.g_Distance[pre][index];
		
		return len;
	}

	/**
	 * 初始化蚂蚁和信息素
	 */
	public void initTrial() {

		for (int i = 0; i < Common.N_ANT_COUNT; i++) {
			m_antAry[i] = new Ant();
		}
		for (int i = 0; i < Common.N_IT_COUNT; i++) {
			m_betterAnts[i] = new Ant();
			m_betterAnts[i].m_dbPathLength = Common.DB_MAX;// 把较优蚂蚁的路径长度设置为一个很大值
		}
		for (int i = 0; i < Common.N_IT_COUNT; i++) {
			m_bestAnts[i] = new Ant();
			m_bestAnts[i].m_dbPathLength = Common.DB_MAX;// 把最优蚂蚁的路径长度设置为一个很大值
		}
		
		// 设置各条路径上的初始信息素为信息素的最大值
		for (int i = 0; i < Common.N_CITY_COUNT; i++) {
			for (int j = 0; j < Common.N_CITY_COUNT; j++) {
				Common.g_Trial[i][j] = Common.DBS;
			}
		}
	}

	/**
	 * 迭代
	 */
	public void Iterator() {
		// 迭代次数内进行循环
		for (int i = 0; i < Common.N_IT_COUNT; i++) {

			// 蚂蚁搜索前,进行初始化
			for (int k = 0; k < Common.N_ANT_COUNT; k++) {
				m_antAry[k].Init();
			}

			// 循环j个城市
			for (int j = 1; j < Common.N_CITY_COUNT; j++) {
				// 循环k只蚂蚁
				for (int k = 0; k < Common.N_ANT_COUNT; k++) {
					// 移动
					m_antAry[k].Move(ChooseNextCity(m_antAry[k]));
				}
			}

			// 完成搜索后计算走过的路径长度
			for (int k = 0; k < Common.N_ANT_COUNT; k++) {
				m_antAry[k].CalPathLength();
			}

			// 保存局部较优结果
			for (int j = 0; j < Common.N_ANT_COUNT; j++) {
				if (m_antAry[j].m_dbPathLength < m_betterAnts[i].m_dbPathLength) {
					// 保存局部较优结果
					try {
						m_betterAnts[i] = (Ant) m_antAry[j].clone();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
			}

			// 保存全局最优结果
			if (i == 0 || m_betterAnts[i].m_dbPathLength < m_bestAnts[i - 1].m_dbPathLength) {
				try {
					m_bestAnts[i] = (Ant) m_betterAnts[i].clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			} else {
				try {
					m_bestAnts[i] = (Ant) m_bestAnts[i - 1].clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}

			if(i%2==0){
				// 更新全局信息素
				UpdateTrial(m_betterAnts[i]);
				// 限定信息素
				LimitTrial(m_betterAnts[i]);
			}
			else{
				// 更新全局信息素
				UpdateTrial(m_bestAnts[i]);
				// 限定信息素
				LimitTrial(m_bestAnts[i]);
			}
			
		}

	}

	/**
	 * 蚂蚁选择下一个城市 返回值为城市编号
	 */
	public int ChooseNextCity(Ant ant) {

		int nSelectedCity = -1;// 返回结果,初始化为-1

		double dbTotal = 0.0;// 计算当前城市和没去过城市的信息素的总和
		double[] prob = new double[Common.N_CITY_COUNT];// 用来保存各个城市被选中的概率

		for (int i = 0; i < Common.N_CITY_COUNT; i++) {
			if (ant.m_nAllowedCity[i] == 1)// 城市没去过
			{
				// 该城市和当前城市的信息素
				prob[i] = Math.pow(Common.g_Trial[ant.m_nCurCityNo][i], Common.ALPHA)
						* Math.pow(1.0 / Common.g_Distance[ant.m_nCurCityNo][i], Common.BETA);
				dbTotal = dbTotal + prob[i];// 累加信息素
			} else// 如果城市去过了 则被选中的概率为0;
			{
				prob[i] = 0.0;
			}
		}

		// 计算概率
		for (int i = 0; i < Common.N_CITY_COUNT; i++) {
			prob[i] = prob[i] / dbTotal;
		}

		// 进行轮盘选择
		double dbTemp = 0.0;
		if (dbTotal > 0.0)// 如果总的信息素大于0
		{
			// dbTemp=PublicFun.rnd(0.0, dbTotal);//取一个随机数
			dbTemp = Common.rnd();

			for (int i = 0; i < Common.N_CITY_COUNT; i++) {
				if (ant.m_nAllowedCity[i] == 1)// 城市没有去过
				{
					dbTemp = dbTemp - prob[i];// 相当于轮盘

					if (dbTemp < 0.0)// 轮盘停止转动,记下城市编号,跳出循环
					{
						nSelectedCity = i;
						break;
					}
				}

			}
		}

		/*
		 * 如果城市间的信息素非常小 ( 小到比double能够表示的最小的数字还要小 ) 那么由于浮点运算的误差原因，上面计算的概率总和可能为0
		 * 会出现经过上述操作，没有城市被选择出来 出现这种情况，就把第一个没去过的城市作为返回结果
		 */
		if (nSelectedCity == -1) {
			for (int i = 0; i < Common.N_CITY_COUNT; i++) {
				if (ant.m_nAllowedCity[i] == 1)// 城市没有去过
				{
					nSelectedCity = i;
					break;
				}
			}
		}
		return nSelectedCity;
	}

	/**
	 * 更新信息素，只更新局部最优路径上的信息素
	 * 
	 * @param ant
	 *            当前局部最优蚂蚁
	 */
	public void UpdateTrial(Ant ant) {

		// 临时数组,保存各只蚂蚁在两两城市间新留下的信息素
		double[][] dbTempAry = new double[Common.N_CITY_COUNT][Common.N_CITY_COUNT];

		// 全部设置为0;
		for (int i = 0; i < Common.N_CITY_COUNT; i++) {
			for (int j = 0; j < Common.N_CITY_COUNT; j++) {
				dbTempAry[i][j] = 0.0;
			}
		}

		// 计算新增加的信息素,保存到临时变量
		int m = 0;
		int n = 0;
		for (int i = 1; i < Common.N_CITY_COUNT; i++) {
			m = ant.m_nPath[i];
			n = ant.m_nPath[i - 1];
			dbTempAry[n][m] = dbTempAry[n][m] + Common.DBQ / ant.m_dbPathLength;
			dbTempAry[m][n] = dbTempAry[n][m];
		}

		// 最后城市与开始城市之间的信息素
		n = ant.m_nPath[0];
		dbTempAry[n][m] = dbTempAry[n][m] + Common.DBQ / ant.m_dbPathLength;
		dbTempAry[m][n] = dbTempAry[n][m];

		// 更新环境信息素
		for (int i = 0; i < Common.N_CITY_COUNT; i++) {
			for (int j = 0; j < Common.N_CITY_COUNT; j++) {
				// 最新的环境信息素 = 留存的信息素 + 新留下的信息素
				Common.g_Trial[i][j] = (1 - Common.ROU) * Common.g_Trial[i][j] + dbTempAry[i][j];
			}
		}

	}

	/**
	 * 限定信息素在最大值和最小值之间
	 * @param ant	当前局部最优蚂蚁
	 */
	public void LimitTrial(Ant ant) {
		
		//重新计算信息素的范围
		Common.DBS_MAX=1.0/(ant.m_dbPathLength*Common.ROU);
		Common.DBS_MIN=Common.DBS_MAX*Common.DBS_RATE;
		
		for (int i = 0; i < Common.N_CITY_COUNT; i++) {
			for (int j = 0; j < Common.N_CITY_COUNT; j++) {
				if (Common.g_Trial[i][j] > Common.DBS_MAX) {
					Common.g_Trial[i][j] = Common.DBS_MAX;
				} else if (Common.g_Trial[i][j] < Common.DBS_MIN) {
					Common.g_Trial[i][j] = Common.DBS_MIN;
				}
			}
		}
	}

	/**
	 * 展示结果
	 */
	public void ShowResult() {

		Main.frame.acoChartPanel.removeAll();
		Main.frame.acoChartPanel.add(new LineChart().createChart(m_betterAnts, m_bestAnts));

		// 把结果显示在窗口中
		for (int i = 0; i < m_betterAnts.length; i++) {
			// Main.frame.betterAntTA.append("("+(i+1)+")
			// 路径:"+m_betterAnts[i].getAntPath()+"长度:"+m_betterAnts[i].getAntLength()+"\n");
			Main.frame.betterAntTA.append("(" + (i + 1) + ") 长度:" + m_betterAnts[i].getAntLength() + " - "
					+ m_bestAnts[i].getAntLength() + "\n");
		}
		Main.frame.betterAntTA.setCaretPosition(0);
		// Main.frame.bestAntTA.append("路径:"+m_bestAnt.getAntPath()+"长度:"+m_bestAnt.getAntLength());
		Main.frame.bestAntTA.append("长度:" + m_bestAnts[m_bestAnts.length - 1].getAntLength());
		Main.frame.bestAntTA.setCaretPosition(0);

		Main.frame.rootPanel.setVisible(false);
		Main.frame.rootPanel.repaint();
		Main.frame.rootPanel.setVisible(true);

	}

}
