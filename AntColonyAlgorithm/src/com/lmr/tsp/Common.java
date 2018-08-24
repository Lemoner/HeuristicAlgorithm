package com.lmr.tsp;

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
	public static int N_IT_COUNT = 1000;
	/**
	 * ��������
	 */
	public static int N_CITY_COUNT = 48;
	/**
	 * �����������
	 */
	public static int N_CITY_ID = 1;

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
	 * �������м����Ϣ��
	 */
	public static double[][] g_Trial;

	/**
	 * �������м�ľ���
	 */
	public static double[][] g_Distance;

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
		return (int) (Math.random() * (nUpper - nLow) + nLow);
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
		return Math.random() * (dbUpper - dbLow) + dbLow;
	}

	/**
	 * ����0��1֮������������
	 * 
	 * @return
	 */
	public static double rnd() {
		// return new Random(System.currentTieMillis()).nextDouble();
		return Math.random();
	}

}
