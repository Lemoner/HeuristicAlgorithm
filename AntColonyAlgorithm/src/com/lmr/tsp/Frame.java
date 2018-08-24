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
 * ����ʵ��
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
	 * �����ʼ������
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
		setTitle("��Ⱥ�㷨���TSP������ʾ����");
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

		betterAntLabel = new JLabel("ÿһ�ε����ľֲ����ź�ȫ�����ŵ�·������");
		bestAntLabel = new JLabel("���·������");
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
				+ "����1��10�����У����Ž��293<br>"
				+ "����2��30�����У����Ž��423.741<br>"
				+ "����3��50�����У����Ž��427.96<br>"
				+ "����4��75�����У����Ž��542.31<br>"
				+ "</body></html>");
		antLabel = new JLabel("��������������:");
		itLabel = new JLabel("�������������:");
		cityLabel = new JLabel("����������������:");
		methodLabel=new JLabel("��ѡ���㷨:");

		antTF = new JTextField(Common.N_ANT_COUNT + "");
		itTF = new JTextField(Common.N_IT_COUNT + "");
		cityTF = new JTextField(Common.N_CITY_ID + "");
		
		checkBox1=new JCheckBox("ACO");
		checkBox2=new JCheckBox("ACS");
		checkBox3=new JCheckBox("MMAS");

		beginBtn = new JButton("��ʼ����");

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
	 * ��ʼ����
	 */
	public void Begin() throws CloneNotSupportedException {
		betterAntTA.setText("");
		bestAntTA.setText("");
		String antCountStr = antTF.getText().replaceAll("[^\\d]", "");// ȥ�����з������ַ�
		String itCountStr = itTF.getText().replaceAll("[^\\d]", "");
		String cityIdStr = cityTF.getText().replaceAll("[^\\d]", "");

		if (!antCountStr.equals("") && !itCountStr.equals("") && !cityIdStr.equals(""))// ��Ϊ"";
		{
			Common.N_ANT_COUNT = Integer.parseInt(antCountStr);// ת��Ϊ����
			Common.N_IT_COUNT = Integer.parseInt(itCountStr);
			Common.N_CITY_ID = Integer.parseInt(cityIdStr);
		}

		// ��ʼִ��
		Tsp tsp = new Tsp();
		tsp.InitData();

	}

}
