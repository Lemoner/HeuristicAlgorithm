package com.lmr.fun;

import java.util.Random;

/**
 * ���������Լ�����
 */
public class Common {
	/**
	 * ��Ϣ����ʽ���ӣ���Ϣ�ص���Ҫ�̶�
	 */
	public static double ALPHA = 1.0;
	/**
	 * ��������ʽ����, ���м�������Ҫ�̶�
	 */
	public static double BETA = 5.0;
	/**
	 * ��Ϣ�ػӷ�ϵ��
	 */
	public static double ROU = 0.3;

	/**
	 * ACS��α�������ѡ�������㷨������
	 */
	public static double ACSQ = 0.01;

	/**
	 * ��������
	 */
	public static int N_ANT_COUNT = 10;
	/**
	 * ��������
	 */
	public static int N_IT_COUNT = 100;
	/**
	 * �ָ���Ŀ���ѱ�����ȡֵ��Χ�ָ�
	 */
	public static int N_SPLIT_COUNT = 50;
	/**
	 * �����������
	 */
	public static int N_FUN_ID = 3;
	
	/**
	 * ������ʵʱȡֵ����
	 */
	public static double VAR_LOW;
	/**
	 * ������ʵʱȡֵ����
	 */
	public static double VAR_UPPER;
	/**
	 * ������ȡֵ����
	 */
	public static double VAR_Scale_LOW;
	/**
	 * ������ʵʱȡֵ����
	 */
	public static double VAR_Scale_UPPER;
	/**
	 * �����ķ�Χ��С����
	 */
	public static double VAR_DELTA=0.99;
	
	/**
	 * ����Ϣ��
	 */
	public static double DBQ = 1.0;
	/**
	 * ��ʼ��Ϣ��
	 */
	public static double DBS = 0.1;

	/**
	 * ��Ϣ�ص����ֵ
	 */
	public static double DBS_MAX = 1;
	/**
	 * ��Ϣ�ص���Сֵ
	 */
	public static double DBS_MIN = 0.1;
	/**
	 * ��Ϣ�ص����ֵ����Сֵ�ı���
	 */
	public static double DBS_RATE; 
	
	/**
	 * ����һ�������ҵ�����·���ĸ���
	 */
	public static double PB_Best=0.05;

	/**
	 * һ����־��,������ʼ��һ���Ƚϴ������·��
	 */
	public static final double DB_MAX = Math.pow(10, 9);
	/**
	 * һ����־��,������ʼ��һ���Ƚ�С������·��
	 */
	public static final double DB_MIN = -Math.pow(10, 9);

	/**
	 * ÿ���������Ϣ��
	 */
	public static double[] g_Area;
	
	/**
	 * �����ֵ��1��������Сֵ��-1��
	 */
	public static int Type;
	
	/**
	 * ����
	 */
	public static final double E=0.00000001;
	
	
	/**
	 * ��ǰ�¶�
	 */
	public static int T_Cur;
	/**
	 * ��ʼ�¶�
	 */
	public static int T_Start;
	/**
	 * �����¶�
	 */
	public static int T_End;
	/**
	 * �¶��½�����
	 */
	public static double T_Speed;
	/**
	 * ͬһ�¶��µĵ�������
	 */
	public static int T_It;

	public static Random rand=new Random(12345678);
	
	/**
	 * ����ָ����Χ�ڵ��������
	 * 
	 * @param nLow
	 *            ����
	 * @param nUpper
	 *            ����
	 * @return
	 */
	public static int rnd(int nLow, int nUpper) {
		return (int) (rnd() * (nUpper - nLow) + nLow);
	}

	/**
	 * ����ָ����Χ�ڵ����������
	 * 
	 * @param dbLow
	 *            ����
	 * @param dbUpper
	 *            ����
	 * @return
	 */
	public static double rnd(double dbLow, double dbUpper) {
		return rnd() * (dbUpper - dbLow) + dbLow;
	}

	/**
	 * ����0��1֮������������
	 * 
	 * @return
	 */
	public static double rnd() {
//		return Math.random();
		return rand.nextDouble();
	}
	
	/**
	 * ���������ŷ��ظ���������ĵ�xֵ
	 * @param areaNo	������
	 * @return
	 */
	public static double getFunXVal(int areaNo){
		return VAR_LOW+(VAR_UPPER-VAR_LOW)*0.5/N_SPLIT_COUNT*(2*areaNo+1);
	}
	
	/**
	 * ���������ŷ��ظ�����������xֵ
	 * @param areaNo	������
	 * @return
	 */
	public static double rndAreaXVal(int areaNo){
		return VAR_LOW+(VAR_UPPER-VAR_LOW)*1.0/N_SPLIT_COUNT*(areaNo+rnd());
	}

}
