package com.lmr.fun;

import java.util.ArrayList;
import java.util.List;

import com.lmr.chart.FLineChart;
import com.lmr.chart.ModelDataChart;

/**
 * 退火算法
 */
public class Solve_SA {

	public String name;

	/**
	 * 蚂蚁数组
	 */
	public Ant m_ant = new Ant();

	public Ant new_ant = new Ant();

	public Ant better_ant = new Ant();

	public List<Ant> betterAntList = new ArrayList<>();

	/**
	 * 定义一组蚂蚁,用来保存每一次搜索中较优结果,不参与搜索
	 */
	public Ant[] m_betterAnts = new Ant[Common.N_IT_COUNT];
	/**
	 * 定义一组蚂蚁,用来保存每一次搜索结束的最终最优结果,不参与搜索
	 */
	public Ant[] m_bestAnts = new Ant[Common.N_IT_COUNT];

	public Solve_SA() {
	}

	public Solve_SA(String name) {
		this.name = name;
	}

	/**
	 * 算法开始
	 */
	public void Start() {
		initParameter();
		initTrial();
		Iterator();
		ShowResult();
	}

	/**
	 * 初始化参数
	 */
	public void initParameter() {

		m_ant = new Ant();
		m_betterAnts = new Ant[Common.N_IT_COUNT];
		m_bestAnts = new Ant[Common.N_IT_COUNT];

		Common.T_It = 1000;

		Common.T_Start = 500;
		Common.T_End = 1;
		Common.T_Speed = 0.9999;

		Common.T_Cur = Common.T_Start;

	}

	/**
	 * 初始化蚂蚁
	 */
	public void initTrial() {

		m_ant.m_nCurXVal = Common.rnd(Common.VAR_Scale_LOW, Common.VAR_Scale_UPPER);
		m_ant.m_nCurFunVal = Fun.getFunResult(m_ant.m_nCurXVal);

	}

	/**
	 * 迭代
	 */
	public void Iterator() {
		// 迭代次数内进行循环
//		for (int k = 0; k < Common.N_IT_COUNT; k++) {

			while (Common.T_Cur > Common.T_End) {

				better_ant = m_ant;

				for (int i = 0; i < Common.T_It; i++) {

					new_ant = new Ant();
					new_ant.m_nCurXVal = FindNewSolution(m_ant.m_nCurXVal);

					if (new_ant.m_nCurXVal < Common.VAR_Scale_LOW || new_ant.m_nCurXVal > Common.VAR_Scale_UPPER) {
						continue;
					}

					new_ant.m_nCurFunVal = Fun.getFunResult(new_ant.m_nCurXVal);

					// System.out.println(new_ant.m_nCurXVal+"
					// "+new_ant.m_nCurFunVal);

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
							// System.out.println(m_ant.m_nCurXVal+"
							// "+m_ant.m_nCurFunVal);
						} else {
							double rnd = Common.rnd();
							double p = Math.pow(Math.E, (new_ant.m_nCurFunVal - m_ant.m_nCurFunVal) / Common.T_Cur);
							if (p > rnd) {
								m_ant = new_ant;
								// System.out.println(m_ant.m_nCurXVal+"
								// "+m_ant.m_nCurFunVal);
							}
						}
					}

				}

				m_ant = better_ant;

				Ant ant = new Ant();
				ant.m_nCurXVal = better_ant.m_nCurXVal;
				ant.m_nCurFunVal = better_ant.m_nCurFunVal;
				betterAntList.add(ant);

				Common.T_Cur *= Common.T_Speed;

			}
			
//			Ant ant = new Ant();
//			ant.m_nCurXVal = m_ant.m_nCurXVal;
//			ant.m_nCurFunVal = m_ant.m_nCurFunVal;
//			betterAntList.add(ant);
//
//		}

	}

	private double FindNewSolution(double val) {

		double x = Common.rnd();
		int t = (int) (Common.rnd() * 100) / 10;
		if ((t & 1) == 0) {
			val += (Common.VAR_Scale_UPPER - val) * x;
		} else {
			val -= (val - Common.VAR_Scale_LOW) * x;
		}

		return val;
	}

	/**
	 * 展示结果
	 */
	public void ShowResult() {
		System.out.println("+++++++++  " + m_ant.m_nCurXVal + " " + m_ant.m_nCurFunVal);
		// System.out.println(m_bestAnts[m_bestAnts.length-1].m_nCurXVal+" -
		// "+m_bestAnts[m_bestAnts.length-1].m_nCurFunVal);

		FLineChart.AddSeriesCollection(betterAntList, "SA");
		// FLineChart.AddSeriesCollection(m_bestAnts, name);
		
		JMModel.a=betterAntList.get(betterAntList.size()-1).m_nCurXVal;
		JMModel.GetB();
		JMModel.GetData();
		JMModel.ShowData();
		ModelDataChart.AddSeriesCollection("SA");
		
//		GOModel.b=betterAntList.get(betterAntList.size()-1).m_nCurXVal;
//		GOModel.GetA();
//		GOModel.GetData();
//		GOModel.ShowData();
//		ModelDataChart.AddSeriesCollection("SA");

	}

}
