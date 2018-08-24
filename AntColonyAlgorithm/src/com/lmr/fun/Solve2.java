package com.lmr.fun;

import java.util.Arrays;

import com.lmr.chart.FLineChart;

/**
 * �㷨
 */
public class Solve2 {

	/**
	 * ��������
	 */
	public Ant2[] m_antAry = new Ant2[Common2.N_ANT_COUNT];
	/**
	 * ����һ������,��������ÿһ�������н��Ž��,����������
	 */
	public Ant2[] m_betterAnts = new Ant2[Common2.N_IT_COUNT];
	/**
	 * ����һ������,��������ÿһ�������������������Ž��,����������
	 */
	public Ant2[] m_bestAnts = new Ant2[Common2.N_IT_COUNT];

	/**
	 * �㷨��ʼ
	 */
	public void Start() {
		initParameter();
		initTrial();
		Iterator();
		ShowResult();
	}

	/**
	 * ��ʼ������
	 */
	public void initParameter() {

		Common2.ALPHA = 1.0;
		Common2.BETA = 1.0;
		Common2.ROU = 0.3;
		Common2.DBQ = 1.0;
		Common2.DBS = 1.0;

		m_antAry = new Ant2[Common2.N_ANT_COUNT];
		m_betterAnts = new Ant2[Common2.N_IT_COUNT];
		m_bestAnts = new Ant2[Common2.N_IT_COUNT];
		
		Common2.g_Area=new double[Common2.VAR_NUM][Common2.N_SPLIT_COUNT];

	}

	/**
	 * ��ʼ������
	 */
	public void initTrial() {

		for (int i = 0; i < Common2.N_ANT_COUNT; i++) {
			m_antAry[i] = new Ant2();
		}
		for (int i = 0; i < Common2.N_IT_COUNT; i++) {
			m_betterAnts[i] = new Ant2();
			if(Common2.Type==1){
				m_betterAnts[i].m_nCurFunVal = Common2.DB_MIN;// �ѽ������ϵ�·����������Ϊһ����Сֵ
			}
			else{
				m_betterAnts[i].m_nCurFunVal = Common2.DB_MAX;// �ѽ������ϵ�·����������Ϊһ���ܴ�ֵ
			}
		}
		for (int i = 0; i < Common2.N_IT_COUNT; i++) {
			m_bestAnts[i] = new Ant2();
			if(Common2.Type==1){
				m_bestAnts[i].m_nCurFunVal = Common2.DB_MIN;// ���������ϵ�·����������Ϊһ����Сֵ
			}
			else{
				m_bestAnts[i].m_nCurFunVal = Common2.DB_MAX;// ���������ϵ�·����������Ϊһ���ܴ�ֵ
			}
			
		}

	}

	/**
	 * ����
	 */
	public void Iterator() {
		// ���������ڽ���ѭ��
		for (int i = 0; i < Common2.N_IT_COUNT; i++) {

			// ��������ǰ,���г�ʼ��
			for (int k = 0; k < Common2.N_ANT_COUNT; k++) {
				m_antAry[k].Init();
			}
			// ��ʼ����Ϣ��
			for (int q = 0; q < Common2.VAR_NUM; q++) {
				for (int k = 0; k < Common2.N_SPLIT_COUNT; k++) {
					Common2.g_Area[q][k] = Common2.DBS;
				}
			}

			// ѭ��
			for (int j = 0; j < Common2.N_IT_COUNT; j++) {
				// ѭ��kֻ����
				for (int k = 0; k < Common2.N_ANT_COUNT; k++) {
					// ÿ��ѭ���ƶ�һ�Σ�ת�Ƶ���һ����
					for (int q = 0; q < Common2.VAR_NUM; q++) {
						m_antAry[k].Move(q,ChooseNextCity(m_antAry[k],q));
					}
					
					//���㺯��ֵ
					m_antAry[k].UpdateVal();
					
				}
				
//				//���ÿֻ���ϵ�����ֵ
//				CheckAntVal();
				
				// ������Ϣ��
				UpdateArea();

			}
			
			//����ȫ�������Լ��ֲ����Ž��
			SaveBetterAndBest(i);

			//���±�����ȡֵ��Χ
			UpdateVar();

		}

	}

	/**
	 * ����ȫ�������Լ��ֲ����Ž��
	 * @param i 
	 */
	private void SaveBetterAndBest(int i) {
		
		if(Common2.Type==1){
			// ����ֲ����Ž��
			for (int j = 0; j < Common2.N_ANT_COUNT; j++) {
				if (m_antAry[j].m_nCurFunVal > m_betterAnts[i].m_nCurFunVal) {
					// ����ֲ����Ž��
					m_betterAnts[i] = m_antAry[j].clone();
				}
			}

			// ����ȫ�����Ž��
			if (i == 0 || m_betterAnts[i].m_nCurFunVal > m_bestAnts[i - 1].m_nCurFunVal) {
				m_bestAnts[i] =m_betterAnts[i].clone();
			} else {
				m_bestAnts[i] = m_bestAnts[i - 1].clone();
			}
		}
		else{
			// ����ֲ����Ž��
			for (int j = 0; j < Common2.N_ANT_COUNT; j++) {
				if (m_antAry[j].m_nCurFunVal < m_betterAnts[i].m_nCurFunVal) {
					// ����ֲ����Ž��
					m_betterAnts[i] = m_antAry[j].clone();
				}
			}

			// ����ȫ�����Ž��
			if (i == 0 || m_betterAnts[i].m_nCurFunVal < m_bestAnts[i - 1].m_nCurFunVal) {
				m_bestAnts[i] = m_betterAnts[i].clone();
			} else {
				m_bestAnts[i] = m_bestAnts[i - 1].clone();
			}
		}
		
	}

	/**
	 * ����ѡ����һ������ ����ֵΪ���б��
	 */
	public int ChooseNextCity(Ant2 ant,int varIndex) {

		int nSelectedCity = ant.m_nCurAreaNo[varIndex];// ���ؽ��,��ʼ��Ϊ-1
//		int nSelectedCity = -1;

		double dbTotal = 0.0;// ���㵱ǰ���к�ûȥ�����е���Ϣ�ص��ܺ�
		double[] prob = new double[Common2.N_SPLIT_COUNT];// ��������������б�ѡ�еĸ���
		
		for (int i = 0; i < Common2.N_SPLIT_COUNT; i++) {
			prob[i]=Common2.g_Area[varIndex][i];
			dbTotal=dbTotal+ Common2.g_Area[varIndex][i];
		}
		
		// �������
		for (int i = 0; i < Common.N_SPLIT_COUNT; i++) {
			prob[i] = prob[i] / dbTotal;
		}

		// ��������ѡ��
		double dbTemp = 0.0;
		if (dbTotal > 0.0)// ����ܵ���Ϣ�ش���0
		{
//			dbTemp=Common2.rnd(0.0, dbTotal);//ȡһ�������
			dbTemp = Common2.rnd();

			for (int i = 0; i < Common2.N_SPLIT_COUNT; i++) {
//				if (ant.m_nCurAreaNo[varIndex] != i && prob[i]>0.0) {// ������û��ȥ��

					dbTemp = dbTemp - prob[i];// �൱������

					if (dbTemp < 0.0) {
						// ����ֹͣת��,���³��б��,����ѭ��
						nSelectedCity = i;
						break;
					}
//				}
			}
		}

		return nSelectedCity;
	}

	/**
	 * ���»�����Ϣ��
	 */
	public void UpdateArea() {
		
//		for (int q = 0; q < Common2.VAR_NUM; q++) {
//			// ��ʱ����,�����ֻ���������������µ���Ϣ��
//			double[] dbTempAry = new double[Common2.N_SPLIT_COUNT];
//
//			// ȫ������Ϊ0;
//			for (int i = 0; i < Common2.N_SPLIT_COUNT; i++) {
//				dbTempAry[i] = 0.0;
//			}
//
//			// ���������ӵ���Ϣ��,���浽��ʱ����
//			for (int i = 0; i < Common2.N_ANT_COUNT; i++) {
//				if(m_antAry[i].m_dbDiffVal>0.0){
//					dbTempAry[m_antAry[i].m_nCurAreaNo[q]] = Common2.DBQ / m_antAry[i].m_dbDiffVal;
//				}
//			}
//
//			// ���»�����Ϣ��
//			for (int i = 0; i < Common2.N_SPLIT_COUNT; i++) {
//				// ���µĻ�����Ϣ�� = �������Ϣ�� + �����µ���Ϣ��
//				Common2.g_Area[q][i] = (1 - Common2.ROU) * Common2.g_Area[q][i] + dbTempAry[i];
//			}
//		}
		
		//�����Ͻ�������
		Ant2 antTemp;
		for (int i=0;i<Common2.N_ANT_COUNT;i++)
		{
			for (int j=i+1;j<Common2.N_ANT_COUNT;j++)
			{
				if(Common2.Type==1){
					if (m_antAry[i].m_nCurFunVal < m_antAry[j].m_nCurFunVal)
					{
						antTemp=m_antAry[i];
						m_antAry[i]=m_antAry[j];
						m_antAry[j]=antTemp;
					}
				}
				else{
					if (m_antAry[i].m_nCurFunVal > m_antAry[j].m_nCurFunVal)
					{
						antTemp=m_antAry[i];
						m_antAry[i]=m_antAry[j];
						m_antAry[j]=antTemp;
					}
				}
			}
		}
		
		// ��ʱ����,�����ֻ���������������µ���Ϣ��
		double[][] dbTempAry = new double[Common2.VAR_NUM][Common2.N_SPLIT_COUNT];

		for (int q = 0; q < Common2.VAR_NUM; q++) {
			// ȫ������Ϊ0;
			for (int i = 0; i < Common2.N_SPLIT_COUNT; i++) {
				dbTempAry[q][i] = 0.0;
			}
		}

		for (int q = 0; q < Common2.VAR_NUM; q++) {
			// ���������ӵ���Ϣ��,���浽��ʱ����
			for (int i = 0; i < Common2.N_ANT_COUNT; i++) {
				dbTempAry[q][m_antAry[i].m_nCurAreaNo[q]] += (Common2.DBQ / m_antAry[i].m_nCurFunVal)*Math.sqrt(Common2.N_ANT_COUNT-i);
			}
		}

		for (int q = 0; q < Common2.VAR_NUM; q++) {
			// ���»�����Ϣ��
			for (int i = 0; i < Common2.N_SPLIT_COUNT; i++) {
				// ���µĻ�����Ϣ�� = �������Ϣ�� + �����µ���Ϣ��
				Common2.g_Area[q][i] = (1 - Common2.ROU) * Common2.g_Area[q][i] + dbTempAry[q][i];
			}
		}
		
	}
	
	/**
	 * ���±���ȡֵ��Χ
	 */
	public void UpdateVar() {
		
		for (int q = 0; q < Common2.VAR_NUM; q++) {
			double dbTemp=-1.0;
			int dbIndex=0;
			
			for (int i = 0; i < Common2.N_SPLIT_COUNT; i++) {
				if(Common2.g_Area[q][i]>dbTemp){
					dbTemp=Common2.g_Area[q][i];
					dbIndex=i;
				}
			}
			
			dbTemp=Common2.VAR_LOW[q]+(Common2.VAR_UPPER[q]-Common2.VAR_LOW[q])*0.5/Common2.N_SPLIT_COUNT*(2*dbIndex+1);
			Common2.VAR_LOW[q]=dbTemp-(Common2.VAR_UPPER[q]-Common2.VAR_LOW[q])*0.5*Common2.VAR_DELTA;
			Common2.VAR_UPPER[q]=dbTemp+(Common2.VAR_UPPER[q]-Common2.VAR_LOW[q])*0.5*Common2.VAR_DELTA;
			
			Common2.VAR_LOW[q]=Math.max(Common2.VAR_Scale_LOW[q], Common2.VAR_LOW[q]);
			Common2.VAR_UPPER[q]=Math.min(Common2.VAR_Scale_UPPER[q], Common2.VAR_UPPER[q]);
			
		}
		
	}

	/**
	 * չʾ���
	 */
	public void ShowResult() {
//		System.out.println(m_bestAnts[m_bestAnts.length-1].m_nCurXVal+" - "+m_bestAnts[m_bestAnts.length-1].m_nCurFunVal);
		
		FLineChart.initSeriesCollection();
//		FLineChart.AddSeriesCollection2(m_betterAnts, "Better");
		FLineChart.AddSeriesCollection2(m_bestAnts, "Best");
		
		System.out.println(m_bestAnts[m_bestAnts.length-1].m_nCurFunVal);
		System.out.println(Arrays.toString(m_bestAnts[m_bestAnts.length-1].m_nCurVarVal));
		
	}

}
