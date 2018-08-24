package com.lmr.tsp;

/**
 * 公共函数以及变量
 */
public class Common {
	/**
	 * 信息启发式因子，信息素的重要程度
	 */
	public static double ALPHA = 1.0;
	/**
	 * 期望启发式因子, 城市间距离的重要程度
	 */
	public static double BETA = 5.0;
	/**
	 * 信息素挥发系数
	 */
	public static double ROU = 0.3;

	/**
	 * ACS的伪随机比例选择规则的算法参数，
	 */
	public static double ACSQ = 0.01;

	/**
	 * 蚂蚁数量
	 */
	public static int N_ANT_COUNT = 10;
	/**
	 * 迭代次数
	 */
	public static int N_IT_COUNT = 1000;
	/**
	 * 城市数量
	 */
	public static int N_CITY_COUNT = 48;
	/**
	 * 城市样本编号
	 */
	public static int N_CITY_ID = 1;

	/**
	 * 总信息素
	 */
	public static double DBQ = 1.0;
	/**
	 * 初始信息素
	 */
	public static double DBS = 0.1;

	/**
	 * 信息素的最大值
	 */
	public static double DBS_MAX = 1;
	/**
	 * 信息素的最小值
	 */
	public static double DBS_MIN = 0.1;
	/**
	 * 信息素的最大值与最小值的比例
	 */
	public static double DBS_RATE; 
	
	/**
	 * 蚂蚁一次搜索找到最优路径的概率
	 */
	public static double PB_Best=0.05;

	/**
	 * 一个标志数,用来初始化一个比较大的最优路径
	 */
	public static final double DB_MAX = Math.pow(10, 9);

	/**
	 * 两两城市间的信息量
	 */
	public static double[][] g_Trial;

	/**
	 * 两两城市间的距离
	 */
	public static double[][] g_Distance;

	/**
	 * 返回指定范围内的随机整数
	 * 
	 * @param nLow
	 *            上限
	 * @param nUpper
	 *            下限
	 * @return
	 */
	public static int rnd(int nLow, int nUpper) {
		return (int) (Math.random() * (nUpper - nLow) + nLow);
	}

	/**
	 * 返回指定范围内的随机浮点数
	 * 
	 * @param dbLow
	 *            上限
	 * @param dbUpper
	 *            下限
	 * @return
	 */
	public static double rnd(double dbLow, double dbUpper) {
		return Math.random() * (dbUpper - dbLow) + dbLow;
	}

	/**
	 * 返回0到1之间的随机浮点数
	 * 
	 * @return
	 */
	public static double rnd() {
		// return new Random(System.currentTieMillis()).nextDouble();
		return Math.random();
	}

}
