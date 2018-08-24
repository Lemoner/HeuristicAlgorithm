package com.lmr.tsp;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.lmr.chart.GBC;

/**
 * 界面实现
 */
@SuppressWarnings("serial")
public class Frame extends JFrame {
	JPanel rootPanel;

	JPanel beginPanel;
	JLabel cityIdLabel;
	JPanel antPanel;
	JPanel itPanel;
	JPanel cityPanel;
	JPanel methodPanel;
	JLabel antLabel;
	JLabel itLabel;
	JLabel cityLabel;
	JLabel methodLabel;
	JCheckBox checkBox1;
	JCheckBox checkBox2;
	JCheckBox checkBox3;
	JTextField antTF;
	JTextField itTF;
	JTextField cityTF;
	JButton beginBtn;

	JPanel acoPanel;
	JPanel acoChartPanel;
	JLabel betterAntLabel;
	JLabel bestAntLabel;
	JTextArea betterAntTA;
	JTextArea bestAntTA;
	JScrollPane betterAntScroll;
	JScrollPane bestAntScroll;

	JLabel descLabel;

	public Frame() {
		Init();
	}

	/**
	 * 界面初始化函数
	 */
	private void Init() {
		rootPanel = new JPanel();

		initBeginPanel();
		initAcoPanel();

		GridBagLayout layout = new GridBagLayout();
		layout.setConstraints(beginPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 1));
		layout.setConstraints(acoPanel, new GBC(1, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		rootPanel.setLayout(layout);
		rootPanel.add(beginPanel);
		rootPanel.add(acoPanel);

		setBounds(300, 150, 1200, 800);
		setTitle("蚁群算法解决TSP问题演示程序");
		setLayout(new GridLayout(1, 1));

		getContentPane().add(rootPanel);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		beginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Begin();
				} catch (CloneNotSupportedException e1) {
					e1.printStackTrace();
				}
			}
		});
		setVisible(true);
	}

	private void initAcoPanel() {

		acoPanel = new JPanel();

		acoChartPanel = new JPanel();
		acoChartPanel.setLayout(new GridLayout(1, 1));

		betterAntLabel = new JLabel("每一次迭代的局部较优和全局最优的路径长度");
		bestAntLabel = new JLabel("最佳路径长度");
		betterAntTA = new JTextArea();
		bestAntTA = new JTextArea();
		betterAntScroll = new JScrollPane(betterAntTA);
		bestAntScroll = new JScrollPane(bestAntTA);

		GridBagLayout layout = new GridBagLayout();
		layout.setConstraints(betterAntLabel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(acoChartPanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		layout.setConstraints(betterAntScroll, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(bestAntLabel, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(bestAntScroll, new GBC(0, 4, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		acoPanel.setLayout(layout);
		acoPanel.add(betterAntLabel);
		acoPanel.add(acoChartPanel);
//		acoPanel.add(betterAntScroll);
		acoPanel.add(bestAntLabel);
		acoPanel.add(bestAntScroll);

	}

	private void initBeginPanel() {

		beginPanel = new JPanel();
		antPanel = new JPanel();
		itPanel = new JPanel();
		cityPanel = new JPanel();
		methodPanel=new JPanel();

		cityIdLabel = new JLabel(
				"<html><body>"
				+ "样本1：10个城市，最优结果293<br>"
				+ "样本2：30个城市，最优结果423.741<br>"
				+ "样本3：50个城市，最优结果427.96<br>"
				+ "样本4：75个城市，最优结果542.31<br>"
				+ "</body></html>");
		antLabel = new JLabel("请输入蚂蚁数量:");
		itLabel = new JLabel("请输入迭代次数:");
		cityLabel = new JLabel("请输入城市样本编号:");
		methodLabel=new JLabel("请选择算法:");

		antTF = new JTextField(Common.N_ANT_COUNT + "");
		itTF = new JTextField(Common.N_IT_COUNT + "");
		cityTF = new JTextField(Common.N_CITY_ID + "");
		
		checkBox1=new JCheckBox("ACO");
		checkBox2=new JCheckBox("ACS");
		checkBox3=new JCheckBox("MMAS");

		beginBtn = new JButton("开始运行");

		antPanel.setLayout(new GridLayout(2, 1));
		antPanel.add(antLabel);
		antPanel.add(antTF);

		itPanel.setLayout(new GridLayout(2, 1));
		itPanel.add(itLabel);
		itPanel.add(itTF);

		cityPanel.setLayout(new GridLayout(2, 1));
		cityPanel.add(cityLabel);
		cityPanel.add(cityTF);
		
		methodPanel.setLayout(new GridLayout(4, 1));
		methodPanel.add(methodLabel);
		methodPanel.add(checkBox1);
		methodPanel.add(checkBox2);
		methodPanel.add(checkBox3);

		GridBagLayout layout = new GridBagLayout();
		layout.setConstraints(cityIdLabel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(antPanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(cityPanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(itPanel, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(methodPanel, new GBC(0, 4, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(beginBtn, new GBC(0, 5, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		beginPanel.setLayout(layout);
		beginPanel.add(cityIdLabel);
		beginPanel.add(antPanel);
		beginPanel.add(cityPanel);
		beginPanel.add(itPanel);
		beginPanel.add(methodPanel);
		beginPanel.add(beginBtn);

	}

	/**
	 * 开始运算
	 */
	public void Begin() throws CloneNotSupportedException {
		betterAntTA.setText("");
		bestAntTA.setText("");
		String antCountStr = antTF.getText().replaceAll("[^\\d]", "");// 去除所有非数字字符
		String itCountStr = itTF.getText().replaceAll("[^\\d]", "");
		String cityIdStr = cityTF.getText().replaceAll("[^\\d]", "");

		if (!antCountStr.equals("") && !itCountStr.equals("") && !cityIdStr.equals(""))// 不为"";
		{
			Common.N_ANT_COUNT = Integer.parseInt(antCountStr);// 转换为数字
			Common.N_IT_COUNT = Integer.parseInt(itCountStr);
			Common.N_CITY_ID = Integer.parseInt(cityIdStr);
		}

		// 开始执行
		Tsp tsp = new Tsp();
		tsp.InitData();

	}

}
