package com.lmr.fun;

import com.lmr.chart.FLineChart;
import com.lmr.chart.ModelDataChart;

/**
 * �㷨
 */
public class Solve_Ant_SA {
	
	public String name;

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

	public Solve_Ant_SA() {
	}
	
	public Solve_Ant_SA(String name) {
		this.name=name;
	}

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

		Common.ALPHA = 1.0;
		Common.BETA = 1.0;
		Common.ROU = 0.3;
		Common.DBQ = 1.0;
		Common.DBS = 1.0;

		m_antAry = new Ant[Common.N_ANT_COUNT];
		m_betterAnts = new Ant[Common.N_IT_COUNT];
		m_bestAnts = new Ant[Common.N_IT_COUNT];
		
		Common.g_Area=new double[Common.N_SPLIT_COUNT];

	}

	/**
	 * ��ʼ������
	 */
	public void initTrial() {

		for (int i = 0; i < Common.N_ANT_COUNT; i++) {
			m_antAry[i] = new Ant();
		}
		for (int i = 0; i < Common.N_IT_COUNT; i++) {
			m_betterAnts[i] = new Ant();
			if(Common.Type==1){
				m_betterAnts[i].m_nCurFunVal = Common.DB_MIN;// �ѽ������ϵ�·����������Ϊһ����Сֵ
			}
			else{
				m_betterAnts[i].m_nCurFunVal = Common.DB_MAX;// �ѽ������ϵ�·����������Ϊһ���ܴ�ֵ
			}
		}
		for (int i = 0; i < Common.N_IT_COUNT; i++) {
			m_bestAnts[i] = new Ant();
			if(Common.Type==1){
				m_bestAnts[i].m_nCurFunVal = Common.DB_MIN;// ���������ϵ�·����������Ϊһ����Сֵ
			}
			else{
				m_bestAnts[i].m_nCurFunVal = Common.DB_MAX;// ���������ϵ�·����������Ϊһ���ܴ�ֵ
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
			// ��ʼ����Ϣ��
			for (int k = 0; k < Common.N_SPLIT_COUNT; k++) {
				Common.g_Area[k] = Common.DBS;
			}

//			// ѭ��
//			for (int j = 0; j < Common.N_IT_COUNT; j++) {
//				// ѭ��kֻ����
//				for (int k = 0; k < Common.N_ANT_COUNT; k++) {
//					// ÿ��ѭ���ƶ�һ�Σ�ת�Ƶ���һ����
//					m_antAry[k].Move(ChooseNextCity(m_antAry[k]));
//				}
//
//				// ������Ϣ��
//				UpdateArea();
//
//			}
			
			Common.T_It = 100;

			Common.T_Start = 100;
			Common.T_End = 1;
			Common.T_Speed = 0.999;

			Common.T_Cur = Common.T_Start;
			
			// ѭ��
			while (Common.T_Cur > Common.T_End) {
				// ѭ��kֻ����
				for (int k = 0; k < Common.N_ANT_COUNT; k++) {
					// ÿ��ѭ���ƶ�һ�Σ�ת�Ƶ���һ����
					m_antAry[k].Move(ChooseNextCity(m_antAry[k]));
				}
				
				// ѭ��kֻ����
				for (int k = 0; k < Common.N_ANT_COUNT; k++) {
					// ÿ��ѭ���ƶ�һ�Σ�ת�Ƶ���һ����
					SA(m_antAry[k]);
				}

				// ������Ϣ��
				UpdateArea();
				
				Common.T_Cur *= Common.T_Speed;
				
//				System.out.println(Common.T_Cur);

			}
			
			//����ȫ�������Լ��ֲ����Ž��
			SaveBetterAndBest(i);
			
			//���±�����ȡֵ��Χ
			UpdateVar();
			
		}

	}

	private void SA(Ant ant) {
		
		Ant m_ant,new_ant,better_ant;
		
		m_ant=new Ant();
		m_ant.m_nCurXVal=ant.m_nCurXVal;
		m_ant.m_nCurFunVal=ant.m_nCurFunVal;
		
		better_ant = m_ant;
		
		for(int i=0;i<Common.T_It;i++){
			new_ant=new Ant();
			new_ant.m_nCurXVal = FindNewSolution(m_ant.m_nCurXVal);

			if (new_ant.m_nCurXVal < Common.VAR_LOW || new_ant.m_nCurXVal > Common.VAR_UPPER) {
				continue;
			}
			
			new_ant.m_nCurFunVal = Fun.getFunResult(new_ant.m_nCurXVal);
			
			if (Common.Type == 1) {

				if (better_ant.m_nCurFunVal < new_ant.m_nCurFunVal) {
					better_ant = new_ant;
				}

				if (m_ant.m_nCurFunVal < new_ant.m_nCurFunVal) {
					m_ant = new_ant;
				} else {
					double rnd = Common.rnd();
					double p = Math.pow(Math.E, (m_ant.m_nCurFunVal - new_ant.m_nCurFunVal) / Common.T_Cur);
					if (p > rnd) {
						m_ant = new_ant;
					}
				}
			} else {

				if (better_ant.m_nCurFunVal > new_ant.m_nCurFunVal) {
					better_ant = new_ant;
				}

				if (m_ant.m_nCurFunVal > new_ant.m_nCurFunVal) {
					m_ant = new_ant;
				} else {
					double rnd = Common.rnd();
					double p = Math.pow(Math.E, (new_ant.m_nCurFunVal - m_ant.m_nCurFunVal) / Common.T_Cur);
					if (p > rnd) {
						m_ant = new_ant;
					}
				}
			}
			
		}
		
		double cur,left,right;
		left=Common.VAR_LOW;
		cur=(Common.VAR_UPPER-Common.VAR_LOW)*1.0/Common.N_SPLIT_COUNT;
		for(int i=1;i<=Common.N_SPLIT_COUNT;i++){
			right=left+cur;
			if(better_ant.m_nCurXVal>=left&&better_ant.m_nCurXVal<=right){
				better_ant.m_nCurAreaNo=i-1;
				break;
			}
			left=right;
		}
		
		if (Common.Type == 1) {
			ant.m_dbDiffVal+=better_ant.m_nCurFunVal-ant.m_nCurFunVal;
		}
		else{
			ant.m_dbDiffVal+=ant.m_nCurFunVal-better_ant.m_nCurFunVal;
		}
		
		ant.m_nCurXVal=better_ant.m_nCurXVal;
		ant.m_nCurAreaNo=better_ant.m_nCurAreaNo;
		ant.m_nCurFunVal=better_ant.m_nCurFunVal;
		
	}

	private double FindNewSolution(double val) {
		double r = Common.rnd();
		double cur=(Common.VAR_UPPER-Common.VAR_LOW)*1.0/Common.N_SPLIT_COUNT;
		double x,y;
		
		x=Common.VAR_Scale_UPPER - val;
		if(x>cur){
			x=cur;
		}
		y=val - Common.VAR_Scale_LOW;
		if(y>cur){
			y=cur;
		}
		
		int t = (int) (Common.rnd() * 100) / 10;
		if ((t & 1) == 0) {
			val += x * r;
		} else {
			val -= y * r;
		}

		return val;
	}

	/**
	 * ����ȫ�������Լ��ֲ����Ž��
	 * @param i 
	 */
	private void SaveBetterAndBest(int i) {
		
		if(Common.Type==1){
			// ����ֲ����Ž��
			for (int j = 0; j < Common.N_ANT_COUNT; j++) {
				if (m_antAry[j].m_nCurFunVal > m_betterAnts[i].m_nCurFunVal) {
					// ����ֲ����Ž��
					try {
						m_betterAnts[i] = (Ant) m_antAry[j].clone();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
			}

			// ����ȫ�����Ž��
			if (i == 0 || m_betterAnts[i].m_nCurFunVal > m_bestAnts[i - 1].m_nCurFunVal) {
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
		}
		else{
			// ����ֲ����Ž��
			for (int j = 0; j < Common.N_ANT_COUNT; j++) {
				if (m_antAry[j].m_nCurFunVal < m_betterAnts[i].m_nCurFunVal) {
					// ����ֲ����Ž��
					try {
						m_betterAnts[i] = (Ant) m_antAry[j].clone();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
			}

			// ����ȫ�����Ž��
			if (i == 0 || m_betterAnts[i].m_nCurFunVal < m_bestAnts[i - 1].m_nCurFunVal) {
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
		}
		
	}

	/**
	 * ����ѡ����һ������ ����ֵΪ���б��
	 */
	public int ChooseNextCity(Ant ant) {

		int nSelectedCity = ant.m_nCurAreaNo;// ���ؽ��,��ʼ��Ϊ-1

		double dbTotal = 0.0;// ���㵱ǰ���к�ûȥ�����е���Ϣ�ص��ܺ�
		double[] prob = new double[Common.N_SPLIT_COUNT];// ��������������б�ѡ�еĸ���
		
		//���ɸ���������㣬�������Ӧ�ĺ���ֵ
		for (int i = 0; i < Common.N_SPLIT_COUNT; i++) {
			ant.m_nAreaXVal[i]=Common.rndAreaXVal(i);
			ant.m_nAreaFunVal[i]=Fun.getFunResult(ant.m_nAreaXVal[i]);
		}
		
//		if(Common.Type==1){
//			if(ant.m_nCurFunVal<ant.m_nAreaXVal[ant.m_nCurAreaNo]){
//				ant.m_nCurFunVal=ant.m_nAreaXVal[ant.m_nCurAreaNo];
//				ant.m_nCurXVal=ant.m_nAreaXVal[ant.m_nCurAreaNo];
//			}
//		}
//		else{
//			if(ant.m_nCurFunVal>ant.m_nAreaXVal[ant.m_nCurAreaNo]){
//				ant.m_nCurFunVal=ant.m_nAreaXVal[ant.m_nCurAreaNo];
//				ant.m_nCurXVal=ant.m_nAreaXVal[ant.m_nCurAreaNo];
//			}
//		}

		for (int i = 0; i < Common.N_SPLIT_COUNT; i++) {

			if (ant.m_nCurAreaNo != i) {

				double fc, fi;
				// ��������i�͵�ǰ�����������Ŀ�꺯��ֵ
//				fc=Math.max(ant.m_nCurFunVal, ant.m_nAreaXVal[ant.m_nCurAreaNo]);
				fc=ant.m_nCurFunVal;
				fi=ant.m_nAreaFunVal[i];
				
				if(Common.Type==1){
					//���
					if (fc - fi < 0) {
						// ������i�͵�ǰ�������Ϣ��
						prob[i] = Math.pow(Common.g_Area[ant.m_nCurAreaNo], Common.ALPHA) * Math.pow(fi - fc, Common.BETA);
						dbTotal = dbTotal + prob[i];// �ۼ���Ϣ��
					} else {
						// ��ֵ����0����ת�ƣ�ѡ�и���Ϊ0
						prob[i] = 0.0;
					}
				}
				else{
					//��С
					if (fi - fc < 0) {
						// ������i�͵�ǰ�������Ϣ��
						prob[i] = Math.pow(Common.g_Area[ant.m_nCurAreaNo], Common.ALPHA) * Math.pow(fc - fi, Common.BETA);
						dbTotal = dbTotal + prob[i];// �ۼ���Ϣ��
					} else {
						// ��ֵ����0����ת�ƣ�ѡ�и���Ϊ0
						prob[i] = 0.0;
					}
				}

			}

		}

		// �������
		for (int i = 0; i < Common.N_SPLIT_COUNT; i++) {
			prob[i] = prob[i] / dbTotal;
		}
		
//		System.out.println(Arrays.toString(prob));
//		System.out.println(dbTotal);

		// ��������ѡ��
		double dbTemp = 0.0;
		if (dbTotal > 0.0)// ����ܵ���Ϣ�ش���0
		{
			// dbTemp=PublicFun.rnd(0.0, dbTotal);//ȡһ�������
			dbTemp = Common.rnd();

			for (int i = 0; i < Common.N_SPLIT_COUNT; i++) {
				if (ant.m_nCurAreaNo != i && prob[i]>0.0) {// ������û��ȥ��

					dbTemp = dbTemp - prob[i];// �൱������

					if (dbTemp < 0.0) {
						// ����ֹͣת��,���³��б��,����ѭ��
						nSelectedCity = i;
						break;
					}
				}

			}
		}

		return nSelectedCity;
	}

	/**
	 * ���»�����Ϣ��
	 */
	public void UpdateArea() {
		// ��ʱ����,�����ֻ���������������µ���Ϣ��
		double[] dbTempAry = new double[Common.N_SPLIT_COUNT];

		// ȫ������Ϊ0;
		for (int i = 0; i < Common.N_SPLIT_COUNT; i++) {
			dbTempAry[i] = 0.0;
		}

		// ���������ӵ���Ϣ��,���浽��ʱ����
		for (int i = 0; i < Common.N_ANT_COUNT; i++) {
			if(m_antAry[i].m_dbDiffVal>0.0){
				dbTempAry[m_antAry[i].m_nCurAreaNo] = Common.DBQ / m_antAry[i].m_dbDiffVal;
			}
		}

		// ���»�����Ϣ��
		for (int i = 0; i < Common.N_SPLIT_COUNT; i++) {
			// ���µĻ�����Ϣ�� = �������Ϣ�� + �����µ���Ϣ��
			Common.g_Area[i] = (1 - Common.ROU) * Common.g_Area[i] + dbTempAry[i];
		}
		
		
	}
	
	/**
	 * ���±���ȡֵ��Χ
	 */
	public void UpdateVar() {
		
		double dbTemp=-1.0;
		int dbIndex=0;
		
		for (int i = 0; i < Common.N_SPLIT_COUNT; i++) {
			if(Common.g_Area[i]>dbTemp){
				dbTemp=Common.g_Area[i];
				dbIndex=i;
			}
		}
		
		dbTemp=Common.VAR_LOW+(Common.VAR_UPPER-Common.VAR_LOW)*0.5/Common.N_SPLIT_COUNT*(2*dbIndex+1);
		Common.VAR_LOW=dbTemp-(Common.VAR_UPPER-Common.VAR_LOW)*0.5*Common.VAR_DELTA;
		Common.VAR_UPPER=dbTemp+(Common.VAR_UPPER-Common.VAR_LOW)*0.5*Common.VAR_DELTA;
		
		Common.VAR_LOW=Math.max(Common.VAR_Scale_LOW, Common.VAR_LOW);
		Common.VAR_UPPER=Math.min(Common.VAR_Scale_UPPER, Common.VAR_UPPER);
		
	}

	/**
	 * չʾ���
	 */
	public void ShowResult() {
		System.out.println(m_bestAnts[m_bestAnts.length-1].m_nCurXVal+" - "+m_bestAnts[m_bestAnts.length-1].m_nCurFunVal);
		
//		FLineChart.AddSeriesCollection(m_betterAnts, "Better");
		FLineChart.AddSeriesCollection(m_bestAnts, "Ant_SA");
		
		JMModel.a=m_bestAnts[m_bestAnts.length-1].m_nCurXVal;
		JMModel.GetB();
		JMModel.GetData();
		JMModel.ShowData();
		ModelDataChart.AddSeriesCollection("Ant_SA");
		
//		GOModel.b=m_bestAnts[m_bestAnts.length-1].m_nCurXVal;
//		GOModel.GetA();
//		GOModel.GetData();
//		GOModel.ShowData();
//		ModelDataChart.AddSeriesCollection("Ant_SA");
		
	}

}
