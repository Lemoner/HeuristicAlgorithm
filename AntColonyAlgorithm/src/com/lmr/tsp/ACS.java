package com.lmr.tsp;

import com.lmr.chart.LineChart;

/**
 * ��Ⱥϵͳ�㷨
 */
public class ACS {

	/**
	 * ��������
	 */
	public Ant[] m_antAry = new Ant[Common.N_ANT_COUNT];
	/**
	 * ����һ������,��������ÿһ�������н��Ž��,����������
	 */
	public Ant[] m_betterAnts = new Ant[Common.N_IT_COUNT];
	/**
	 * ����һ������,��������ÿһ�������������������Ž��,����������
	 */
	public Ant[] m_bestAnts = new Ant[Common.N_IT_COUNT];

	/**
	 * �㷨��ʼ
	 */
	public void Start() {
		initParameter();
		initTrial();
		Iterator();
//		ShowResult();
	}
	
	/**
	 * ��ʼ������
	 */
	private void initParameter() {
		
		Common.ALPHA=1.0;
		Common.BETA=5.0;
		Common.ROU=0.3;
		Common.DBQ=1.0;
		Common.ACSQ=0.1;
		
		double greedLen=GreedPathLength();
		
		Common.DBS=1.0/(greedLen*Common.N_CITY_COUNT);
		
	}
	
	/**
	 * ʹ��̰�ļ���·�����ȣ�ÿ��ѡ�����ڵĵ�һ��δ���ʵĳ���
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
	 * ��ʼ�����Ϻ���Ϣ��
	 */
	public void initTrial() {

		for (int i = 0; i < Common.N_ANT_COUNT; i++) {
			m_antAry[i] = new Ant();
		}
		for (int i = 0; i < Common.N_IT_COUNT; i++) {
			m_betterAnts[i] = new Ant();
			m_betterAnts[i].m_dbPathLength = Common.DB_MAX;// �ѽ������ϵ�·����������Ϊһ���ܴ�ֵ
		}
		for (int i = 0; i < Common.N_IT_COUNT; i++) {
			m_bestAnts[i] = new Ant();
			m_bestAnts[i].m_dbPathLength = Common.DB_MAX;// ���������ϵ�·����������Ϊһ���ܴ�ֵ
		}

		for (int i = 0; i < Common.N_CITY_COUNT; i++) {
			for (int j = 0; j < Common.N_CITY_COUNT; j++) {
				Common.g_Trial[i][j] = Common.DBS;
			}
		}
	}

	/**
	 * ����
	 */
	public void Iterator() {
		// ���������ڽ���ѭ��
		for (int i = 0; i < Common.N_IT_COUNT; i++) {

			// ��������ǰ,���г�ʼ��
			for (int k = 0; k < Common.N_ANT_COUNT; k++) {
				m_antAry[k].Init();
			}

			// ѭ��j������
			for (int j = 1; j < Common.N_CITY_COUNT; j++) {
				// ѭ��kֻ����
				for (int k = 0; k < Common.N_ANT_COUNT; k++) {
					// �ƶ�
					m_antAry[k].Move(ChooseNextCity(m_antAry[k]));
				}

				// ���¾ֲ���Ϣ��
				for (int k = 0; k < Common.N_ANT_COUNT; k++) {
					UpdateLocalTrial(m_antAry[k]);
				}

			}

			// �������������߹���·������
			for (int k = 0; k < Common.N_ANT_COUNT; k++) {
				m_antAry[k].CalPathLength();
			}

			// ����ֲ����Ž��
			for (int j = 0; j < Common.N_ANT_COUNT; j++) {
				if (m_antAry[j].m_dbPathLength < m_betterAnts[i].m_dbPathLength) {
					// ����ֲ����Ž��
					try {
						m_betterAnts[i] = (Ant) m_antAry[j].clone();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
			}

			// ����ȫ�����Ž��
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

			// ����ȫ����Ϣ��
			UpdateGlobalTrial(m_bestAnts[i]);
		}

	}

	/**
	 * ����ѡ����һ������ ����ֵΪ���б��
	 */
	public int ChooseNextCity(Ant ant) {

		int nSelectedCity = -1;// ���ؽ��,��ʼ��Ϊ-1

		double dbTotal = 0.0;// ���㵱ǰ���к�ûȥ�����е���Ϣ�ص��ܺ�
		double maxProb = 0.0;// ������Ϣ��
		int maxProbCity = -1;// ������Ϣ�����ڵĳ���
		double[] prob = new double[Common.N_CITY_COUNT];// ��������������б�ѡ�еĸ���

		for (int i = 0; i < Common.N_CITY_COUNT; i++) {
			if (ant.m_nAllowedCity[i] == 1)// ����ûȥ��
			{
				// �ó��к͵�ǰ���е���Ϣ��
				prob[i] = Math.pow(Common.g_Trial[ant.m_nCurCityNo][i], Common.ALPHA)
						* Math.pow(1.0 / Common.g_Distance[ant.m_nCurCityNo][i], Common.BETA);
				dbTotal = dbTotal + prob[i];// �ۼ���Ϣ��

				// ����������Ϣ�غ������ڵĳ���
				if (maxProb < prob[i]) {
					maxProb = prob[i];
					maxProbCity = i;
				}

			} else// �������ȥ���� ��ѡ�еĸ���Ϊ0;
			{
				prob[i] = 0.0;
			}
		}

		// ����α�������ѡ��
		double dbQ = Common.rnd();
		if (dbQ <= Common.ACSQ) {
			nSelectedCity=maxProbCity;
		}
		else{
			// �������
			for (int i = 0; i < Common.N_CITY_COUNT; i++) {
				prob[i] = prob[i] / dbTotal;
			}

			// ��������ѡ��
			double dbTemp = 0.0;
			if (dbTotal > 0.0)// ����ܵ���Ϣ�ش���0
			{
				dbTemp = Common.rnd();

				for (int i = 0; i < Common.N_CITY_COUNT; i++) {
					if (ant.m_nAllowedCity[i] == 1)// ����û��ȥ��
					{
						dbTemp = dbTemp - prob[i];// �൱������

						if (dbTemp < 0.0)// ����ֹͣת��,���³��б��,����ѭ��
						{
							nSelectedCity = i;
							break;
						}
					}

				}
			}
		}

		/*
		 * ������м����Ϣ�طǳ�С ( С����double�ܹ���ʾ����С�����ֻ�ҪС ) ��ô���ڸ�����������ԭ���������ĸ����ܺͿ���Ϊ0
		 * ����־�������������û�г��б�ѡ����� ��������������Ͱѵ�һ��ûȥ���ĳ�����Ϊ���ؽ��
		 */
		if (nSelectedCity == -1) {
			for (int i = 0; i < Common.N_CITY_COUNT; i++) {
				if (ant.m_nAllowedCity[i] == 1)// ����û��ȥ��
				{
					nSelectedCity = i;
					break;
				}
			}
		}
		return nSelectedCity;
	}

	/**
	 * ���¾ֲ���Ϣ�أ�ֻ���µ�ǰ������ת�Ƶ�·����i��j���ϵ���Ϣ��
	 * 
	 * @param ant
	 *            ��ǰת�Ƶ�����
	 */
	public void UpdateLocalTrial(Ant ant) {

		int m = ant.m_nPath[ant.m_nMovedCityCount - 1];
		int n = ant.m_nPath[ant.m_nMovedCityCount - 2];

		// ���µĻ�����Ϣ�� = �������Ϣ�� + �����µ���Ϣ��
		Common.g_Trial[n][m] = (1 - Common.ROU) * Common.g_Trial[n][m] + Common.ROU * Common.DBS;
		Common.g_Trial[m][n] = Common.g_Trial[n][m];

		// �����������뿪ʼ����֮�����Ϣ��
		n = ant.m_nPath[0];
		if (ant.m_nMovedCityCount == Common.N_CITY_COUNT) {
			Common.g_Trial[n][m] = (1 - Common.ROU) * Common.g_Trial[n][m] + Common.ROU * Common.DBS;
			Common.g_Trial[m][n] = Common.g_Trial[n][m];
		}

	}

	/**
	 * ����ȫ����Ϣ�أ�ֻ����ȫ������·���ϵ���Ϣ��
	 * 
	 * @param ant
	 *            ��ǰȫ����������
	 */
	public void UpdateGlobalTrial(Ant ant) {

//		int m = 0;
//		int n = 0;
//		for (int i = 1; i < PublicFun.N_CITY_COUNT; i++) {
//			m = ant.m_nPath[i];
//			n = ant.m_nPath[i - 1];
//			// ���µĻ�����Ϣ�� = �������Ϣ�� + �����µ���Ϣ��
//			PublicFun.g_Trial[n][m] = (1 - PublicFun.ROU) * PublicFun.g_Trial[n][m]
//					+ PublicFun.ROU * PublicFun.DBQ / ant.m_dbPathLength;
//			PublicFun.g_Trial[m][n] = PublicFun.g_Trial[n][m];
//		}
//
//		// �����������뿪ʼ����֮�����Ϣ��
//		n = ant.m_nPath[0];
//		PublicFun.g_Trial[n][m] = (1 - PublicFun.ROU) * PublicFun.g_Trial[n][m]
//				+ PublicFun.ROU * PublicFun.DBQ / ant.m_dbPathLength;
//		PublicFun.g_Trial[m][n] = PublicFun.g_Trial[n][m];

		
		// ��ʱ����,�����ֻ�������������м������µ���Ϣ��
		double[][] dbTempAry = new double[Common.N_CITY_COUNT][Common.N_CITY_COUNT];

		// ȫ������Ϊ0;
		for (int i = 0; i < Common.N_CITY_COUNT; i++) {
			for (int j = 0; j < Common.N_CITY_COUNT; j++) {
				dbTempAry[i][j] = 0.0;
			}
		}

		// ���������ӵ���Ϣ��,���浽��ʱ����
		int m = 0;
		int n = 0;
		for (int i = 1; i < Common.N_CITY_COUNT; i++) {
			m = ant.m_nPath[i];
			n = ant.m_nPath[i - 1];
			dbTempAry[n][m] = dbTempAry[n][m] + Common.ROU * Common.DBQ / ant.m_dbPathLength;
			dbTempAry[m][n] = dbTempAry[n][m];
		}

		// �������뿪ʼ����֮�����Ϣ��
		n = ant.m_nPath[0];
		dbTempAry[n][m] = dbTempAry[n][m] + Common.ROU * Common.DBQ / ant.m_dbPathLength;
		dbTempAry[m][n] = dbTempAry[n][m];

		// ���»�����Ϣ��
		for (int i = 0; i < Common.N_CITY_COUNT; i++) {
			for (int j = 0; j < Common.N_CITY_COUNT; j++) {
				// ���µĻ�����Ϣ�� = �������Ϣ�� + �����µ���Ϣ��
				Common.g_Trial[i][j] = (1 - Common.ROU) * Common.g_Trial[i][j] + dbTempAry[i][j];
			}
		}
		
	}

	/**
	 * չʾ���
	 */
	public void ShowResult() {

		Main.frame.acoChartPanel.removeAll();
		Main.frame.acoChartPanel.add(new LineChart().createChart(m_betterAnts, m_bestAnts));

		// �ѽ����ʾ�ڴ�����
		for (int i = 0; i < m_betterAnts.length; i++) {
			Main.frame.betterAntTA.append("(" + (i + 1) + ") ����:" + m_betterAnts[i].getAntLength() + " - "
					+ m_bestAnts[i].getAntLength() + "\n");
		}
		Main.frame.betterAntTA.setCaretPosition(0);
		Main.frame.bestAntTA.append("����:" + m_bestAnts[m_bestAnts.length - 1].getAntLength());
		Main.frame.bestAntTA.setCaretPosition(0);

		Main.frame.rootPanel.setVisible(false);
		Main.frame.rootPanel.repaint();
		Main.frame.rootPanel.setVisible(true);

	}

}
