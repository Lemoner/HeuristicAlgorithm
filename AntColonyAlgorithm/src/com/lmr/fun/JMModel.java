package com.lmr.fun;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * JM�ɿ���ģ��
 */
public class JMModel {

	/**
	 * ģ�͹��д�����a
	 */
	public static double a;
	/**
	 * ģ�ͱ���������
	 */
	public static double b;
	
	/**
	 * �ɿ���
	 */
	public static double Reliable;
	/**
	 * ���ɿ���
	 */
	public static double aoReliable;
	/**
	 * ʧЧ��
	 */
	public static double FailureRate;
	/**
	 * ʣ�������
	 */
	public static double ResidualFaults;
	/**
	 * ƽ��ʧЧ�ȴ�ʱ��
	 */
	public static double MTTF;
	
	/**
	 * Ԥ���ʧЧʱ��
	 */
	public static List<Double> PredictFailureTimeList;
	/**
	 * Ԥ���ʧЧʱ����
	 */
	public static List<Double> PredictFailureTimeFragList;
	/**
	 * Ԥ��Ĳ��ɿ���F(t)
	 */
	public static List<Double> PredictNoReliableList;
	/**
	 * Ԥ��ĸ��ʷֲ��ܶ�f(t)
	 */
	public static List<Double> PredictDistributionDensityList;
	
	/**
	 * Uͼ������
	 */
	public static List<Double> UList;
	/**
	 * Yͼ������
	 */
	public static List<Double> YList;
	
	/**
	 * ģ����϶�
	 */
	public static double FD;
	/**
	 * ģ��׼ȷ�ԣ�������Ȼ��
	 */
	public static double PL;
	/**
	 * ģ��ƫ�Uͼ��KS����
	 */
	public static double KS_U;
	/**
	 * ģ��ƫ�����ƣ�Yͼ��KS����
	 */
	public static double KS_Y;
	/**
	 * ģ������
	 */
	public static double MN;
	
	
	/**
	 * ��ȡԤ������
	 */
	public static void GetData(){
		
		SetFailureRate();
		SetResidualFaults();
		SetMTTF();
		SetReliable();
		
		SetPredictFailureTimeAndFragList();
		SetPredictNoReliableList();
		SetPredictDistributionDensityList();
		
		setFD();
		setPL();
		setKS_U();
		setKS_Y();
		setMN();
	}
	
	/**
	 * չʾ���
	 */
	public static void ShowData(){
//		Main.frame.TA_JM.setText("");
		Main.frame.TA_JM.append("----------JMģ��----------\n");
		Main.frame.TA_JM.append("��ʼ������ n: "+Model.n+"\n");
		Main.frame.TA_JM.append("���д����� N: "+a+"\n");
		Main.frame.TA_JM.append("�������� ��: "+b+"\n");
		Main.frame.TA_JM.append("\n");
		Main.frame.TA_JM.append("\n");
		Main.frame.TA_JM.append("�ɿ��ȣ� "+Reliable+"\n");
		Main.frame.TA_JM.append("ʧЧ�ʣ� "+FailureRate+"\n");
		Main.frame.TA_JM.append("ʣ��������� "+ResidualFaults+"\n");
		Main.frame.TA_JM.append("MTTF�� "+MTTF+"\n");
		Main.frame.TA_JM.append("\n");
		Main.frame.TA_JM.append("ģ����϶ȣ� "+FD+"\n");
		Main.frame.TA_JM.append("ģ��׼ȷ�ԣ� "+PL+"\n");
		Main.frame.TA_JM.append("ģ��ƫ� "+KS_U+"\n");
		Main.frame.TA_JM.append("ģ��ƫ�����ƣ� "+KS_Y+"\n");
		Main.frame.TA_JM.append("ģ�������� "+MN+"\n");
		
	}

	
	/**
	 * ������д��������󾫶�
	 * @param x	δ֪�������д�����
	 * @return	
	 */
	static double GetA(double x) {
		
		int t,p;
		double f;
		
		t=0;
		p=0;
		for(int i=1;i<=Model.n;i++){
			t+=Model.fragTime[i];
			p+=(i-1)*Model.fragTime[i];
		}
		
		f=0.0;
		for(int i=1;i<=Model.n;i++){
			if(x-i+1==0){
				continue;
			}
			f+=1.0/(x-i+1);
		}
		f-=(Model.n*1.0)/(x-1.0/t*p);
		
		if(f<0.0){
			f=-f;
		}
		
		return f;
	}
	
	/**
	 * �����������
	 */
	static void GetB() {
		
		int t,p;
		
		t=0;
		p=0;
		for(int i=1;i<=Model.n;i++){
			t+=Model.fragTime[i];
			p+=(i-1)*Model.fragTime[i];
		}
		
		b=Model.n*1.0/(a*t-p);
		
	}
	
	/**
	 * ����ʧЧ��
	 */
	static void SetFailureRate(){
		FailureRate=b*(a-Model.n+1);
	}
	
	/**
	 * ����ʣ�������
	 */
	static void SetResidualFaults(){
		ResidualFaults=a-Model.n;
	}
	
	/**
	 * ����ƽ��ʧЧ�ȴ�ʱ��
	 */
	static void SetMTTF(){
		MTTF=1.0/(b*(a-Model.n+1));
	}
	
	/**
	 * ����ɿ���
	 */
	static void SetReliable(){
//		Reliable=Model.PowE(-b*(a-Model.n+1)*Model.fragTime[Model.n]);
		Reliable=Model.PowE(-b*(a-Model.n+1));
	}
	
	/**
	 * ����Ԥ���ʧЧʱ���ʧЧʱ����
	 */
	static void SetPredictFailureTimeAndFragList(){
		
		PredictFailureTimeList=new ArrayList<>();
		PredictFailureTimeList.add(0.0);
		
		PredictFailureTimeFragList=new ArrayList<>();
		PredictFailureTimeFragList.add(0.0);
		
		double sum=0.0;
		for(int i=1;i<=a;i++){
			double f=1.0/(b*(a-i+1));
			sum+=f;
			PredictFailureTimeFragList.add(f);
			PredictFailureTimeList.add(sum);
		}
	}
	
	/**
	 * ����Ԥ��Ĳ��ɿ���F(t)
	 */
	static void SetPredictNoReliableList(){
		
		PredictNoReliableList=new ArrayList<>();
		PredictNoReliableList.add(0.0);
		
		for(int i=1;i<Model.fragTime.length;i++){
			double f=1.0-Math.pow(Math.E, -b*(a-i+1)*Model.fragTime[i]);
			PredictNoReliableList.add(f);
		}
		
//		System.out.println(PredictNoReliableList.toString());
		
	}
	
	/**
	 * ����Ԥ��ĸ��ʷֲ��ܶ�f(t)
	 */
	static void SetPredictDistributionDensityList() {
		
		PredictDistributionDensityList=new ArrayList<>();
		PredictDistributionDensityList.add(0.0);
		
		for(int i=1;i<Model.fragTime.length;i++){
			double f=b*(a-i+1)*Math.pow(Math.E, -b*(a-i+1)*Model.fragTime[i]);
			PredictDistributionDensityList.add(f);
		}
		
		System.out.println(PredictDistributionDensityList.toString());
		
	}
	
	/**
	 * ����ģ����϶�
	 */
	static void setFD(){

		double t1,t2,t;
		
		t=0.0;
		for(int i=1;i<PredictNoReliableList.size();i++){
			t1=PredictNoReliableList.get(i)-(i-1)*1.0/PredictNoReliableList.size();
			t2=i*1.0/PredictNoReliableList.size()-PredictNoReliableList.get(i);
			
			if(t1<0){
				t1=-t1;
			}
			if(t2<0){
				t2=-t2;
			}
			
			t=Math.max(t, Math.max(t1, t2));
			
		}
		
		FD=t;
		
	}
	
	/**
	 * ����ģ��׼ȷ�ԣ�������Ȼ��
	 */
	static void setPL(){
		
		double f;
		
		f=1.0;
		
		for(int i=Model.n;i<PredictDistributionDensityList.size();i++){
			f*=PredictDistributionDensityList.get(i);
		}
		
		PL=f;
	
	}
	
	/**
	 * ����ģ��ƫ�Uͼ��KS����
	 */
	static void setKS_U(){
		
		UList=new ArrayList<>();
		
		for(int i=1;i<PredictNoReliableList.size();i++){
			UList.add(PredictNoReliableList.get(i));
		}
			
		UList.sort(new Comparator<Double>() {
			@Override
			public int compare(Double o1, Double o2) {
				if(o1.doubleValue()>o2.doubleValue()){
					return 1;
				}
				else if(o1.doubleValue()==o2.doubleValue()){
					return 0;
				}
				else{
					return -1;
				}
			}
		});
		
		double max=0.0;
		double t;
		
		for(int i=0;i<UList.size();i++){
//			if(UList.get(i)==0.0){
//				continue;
//			}
			t=Math.abs(UList.get(i)-i*1.0/(UList.size()+1));
			max=Math.max(max, t);
			t=Math.abs(UList.get(i)-(i+1)*1.0/(UList.size()+1));
			max=Math.max(max, t);
		}
//		max=Math.max(max, Math.abs(1-UList.size()*1.0/(UList.size()+1)));
		
		KS_U=max;
		
	}
	
	/**
	 * ģ��ƫ�Yͼ��KS����
	 */
	static void setKS_Y(){
		
		YList=new ArrayList<>();
		List<Double> XList=new ArrayList<>();
		double x,s,p;
		
		s=0.0;
		for(int i=1;i<PredictNoReliableList.size();i++){
			x=-Math.log(1-PredictNoReliableList.get(i));
			XList.add(x);
			s+=x;
		}
		
		p=0.0;
		for(int i=0;i<XList.size()-1;i++){
			p+=XList.get(i);
			YList.add(p/s);
		}
			
		YList.sort(new Comparator<Double>() {
			@Override
			public int compare(Double o1, Double o2) {
				if(o1.doubleValue()>o2.doubleValue()){
					return 1;
				}
				else if(o1.doubleValue()==o2.doubleValue()){
					return 0;
				}
				else{
					return -1;
				}
			}
		});
		
		double max=0.0;
		double t;
		
//		max=Math.max(max, 1.0/(YList.size()+1));
		for(int i=0;i<YList.size();i++){
//			if(YList.get(i)==0.0){
//				continue;
//			}
			t=Math.abs((i+1)*1.0/(YList.size()+1)-YList.get(i));
			max=Math.max(max, t);
			t=Math.abs((i+2)*1.0/(YList.size()+1)-YList.get(i));
			max=Math.max(max, t);
		}
		
		KS_Y=max;
		
//		System.out.println(KS_Y);
		
	}
	
	/**
	 * ����ģ������
	 */
	static void setMN(){
		
		double f,t;
		
		t=0.0;
		for(int i=2;i<=Model.n;i++){
			f=(PredictFailureTimeFragList.get(i)-PredictFailureTimeFragList.get(i-1))/PredictFailureTimeFragList.get(i-1);
			if(f<0){
				f=-f;
			}
			t+=f;
		}
		
		MN=t;
		
	}
	
}
