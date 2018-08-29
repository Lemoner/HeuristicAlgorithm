package com.lmr.fun;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
	JLabel funIdLabel;
	JPanel antPanel;
	JPanel itPanel;
	JPanel funPanel;
	JLabel antLabel;
	JLabel itLabel;
	JLabel funLabel;
	JTextField antTF;
	JTextField itTF;
	JTextField funTF;
	JButton beginBtn;
	
	JButton Btn1;
	JButton Btn2;
	JButton Btn3;

	JPanel acoPanel;
	JPanel acoChartPanel;
	JLabel betterAntLabel;
	JLabel bestAntLabel;
	JTextArea betterAntTA;
	JTextArea bestAntTA;
	JScrollPane betterAntScroll;
	JScrollPane bestAntScroll;
	
	JTextArea TA_JM;
	JTextArea TA_GO;
	JTextArea TA_Musa;
	JTextArea TA_LV;
	
	JPanel funChartPanel;
	JPanel uChartPanel;
	JPanel yChartPanel;

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
		setTitle("��Ⱥ�㷨��������Ժ���������ʾ����");
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
		
		funChartPanel=new JPanel();
		funChartPanel.setLayout(new GridLayout(1, 1));
		
		uChartPanel=new JPanel();
		uChartPanel.setLayout(new GridLayout(1, 1));
		
		yChartPanel=new JPanel();
		yChartPanel.setLayout(new GridLayout(1, 1));
		
		betterAntLabel = new JLabel("ÿһ�ε����ľֲ����ź�ȫ������");
		bestAntLabel = new JLabel("���");
		betterAntTA = new JTextArea();
		bestAntTA = new JTextArea();
		betterAntScroll = new JScrollPane(betterAntTA);
//		bestAntScroll = new JScrollPane(bestAntTA);
		
		
		TA_JM=new JTextArea();
		TA_GO=new JTextArea();
		TA_Musa=new JTextArea();
		TA_LV=new JTextArea();
		
		JPanel TA_Panel=new JPanel();
		TA_Panel.setLayout(new GridLayout(1, 4));
		TA_Panel.add(TA_JM);
		TA_Panel.add(TA_GO);
		TA_Panel.add(TA_Musa);
		TA_Panel.add(TA_LV);
		
		bestAntScroll = new JScrollPane(TA_Panel);
		

		GridBagLayout layout = new GridBagLayout();
		layout.setConstraints(betterAntLabel, new GBC(0, 0, 3, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(acoChartPanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(funChartPanel, new GBC(1, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		layout.setConstraints(uChartPanel, new GBC(1, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		layout.setConstraints(yChartPanel, new GBC(2, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		layout.setConstraints(betterAntScroll, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(bestAntLabel, new GBC(0, 2, 3, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(bestAntScroll, new GBC(0, 3, 3, 1).setFill(GBC.BOTH).setWeight(1, 1));
		acoPanel.setLayout(layout);
//		acoPanel.add(betterAntLabel);
		acoPanel.add(acoChartPanel);
		acoPanel.add(funChartPanel);
//		acoPanel.add(uChartPanel);
//		acoPanel.add(yChartPanel);
//		acoPanel.add(betterAntScroll);
//		acoPanel.add(bestAntLabel);
		acoPanel.add(bestAntScroll);
		
//		acoChartPanel.setVisible(false);

	}

	private void initBeginPanel() {

		beginPanel = new JPanel();
		antPanel = new JPanel();
		itPanel = new JPanel();
		funPanel = new JPanel();

//		funIdLabel = new JLabel(
//				"<html><body>"
//				+ "����1��10�����У����Ž��293<br>"
//				+ "����2��30�����У����Ž��423.741<br>"
//				+ "����3��50�����У����Ž��427.96<br>"
//				+ "����4��75�����У����Ž��542.31<br>"
//				+ "</body></html>");
		funIdLabel = new JLabel();
		antLabel = new JLabel("��������������:");
		itLabel = new JLabel("�������������:");
		funLabel = new JLabel("�����뺯���������:");

		antTF = new JTextField(Common.N_ANT_COUNT + "");
		itTF = new JTextField(Common.N_IT_COUNT + "");
		funTF = new JTextField(Common.N_FUN_ID + "");
		
		beginBtn = new JButton("��ʼ����");
		
		Btn1 = new JButton("1");
		Btn2 = new JButton("2");
		Btn3 = new JButton("3");
		
		Btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(acoChartPanel.isVisible()){
					acoChartPanel.setVisible(false);
				}
				else{
					acoChartPanel.setVisible(true);
				}
			}
		});
		Btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(funChartPanel.isVisible()){
					funChartPanel.setVisible(false);
				}
				else{
					funChartPanel.setVisible(true);
				}
			}
		});
		Btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bestAntScroll.isVisible()){
					bestAntScroll.setVisible(false);
				}
				else{
					bestAntScroll.setVisible(true);
				}
			}
		});
		
		

		antPanel.setLayout(new GridLayout(2, 1));
		antPanel.add(antLabel);
		antPanel.add(antTF);

		itPanel.setLayout(new GridLayout(2, 1));
		itPanel.add(itLabel);
		itPanel.add(itTF);

		funPanel.setLayout(new GridLayout(2, 1));
		funPanel.add(funLabel);
		funPanel.add(funTF);

		GridBagLayout layout = new GridBagLayout();
		layout.setConstraints(funIdLabel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(antPanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(funPanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(itPanel, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(beginBtn, new GBC(0, 4, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(Btn1, new GBC(0, 5, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(Btn2, new GBC(0, 6, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(Btn3, new GBC(0, 7, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		beginPanel.setLayout(layout);
		beginPanel.add(funIdLabel);
		beginPanel.add(antPanel);
		beginPanel.add(funPanel);
		beginPanel.add(itPanel);
		beginPanel.add(beginBtn);
		beginPanel.add(Btn1);
		beginPanel.add(Btn2);
		beginPanel.add(Btn3);

	}

	/**
	 * ��ʼ����
	 */
	public void Begin() throws CloneNotSupportedException {
		betterAntTA.setText("");
		bestAntTA.setText("");
		String antCountStr = antTF.getText().replaceAll("[^\\d]", "");// ȥ�����з������ַ�
		String itCountStr = itTF.getText().replaceAll("[^\\d]", "");
		String funIdStr = funTF.getText().replaceAll("[^\\d]", "");

		if (!antCountStr.equals("") && !itCountStr.equals("") && !funIdStr.equals(""))// ��Ϊ"";
		{
			Common.N_FUN_ID=Integer.parseInt(funIdStr);
		}

		// ��ʼִ��
		Fun fun = new Fun();
		fun.InitData();

	}

}
