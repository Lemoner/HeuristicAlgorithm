package com.lmr.fun;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * LV�ɿ���ģ��
 */
public class LVModel {

	/**
	 * ģ���βΦ�
	 */
	public static double a;
	/**
	 * ģ��ʵ��Ϊ��(i)=��0+��1*i�ı���������0
	 */
	public static double b0;
	/**
	 * ģ��ʵ��Ϊ��(i)=��0+��1*i�ı���������1
	 */
	public static double b1;
	
	/**
	 * �ɿ���
	 */
	public static double Reliable;
	/**
	 * ���ɿ���
	 */
	public static double NoReliable;
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
//		SetResidualFaults();
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
		Main.frame.TA_LV.setText("");
		Main.frame.TA_LV.append("----------LVģ��----------\n");
		Main.frame.TA_LV.append("��ʼ������ n: "+Model.n+"\n");
		Main.frame.TA_LV.append("���д����� N: ? \n");
		Main.frame.TA_LV.append("�βΦ�: "+a+"\n");
		Main.frame.TA_LV.append("����������0: "+b0+"\n");
		Main.frame.TA_LV.append("����������1: "+b1+"\n");
		Main.frame.TA_LV.append("�ɿ��ȣ� "+Reliable+"\n");
		Main.frame.TA_LV.append("ʧЧ�ʣ� "+FailureRate+"\n");
		Main.frame.TA_LV.append("ʣ��������� ? \n");
		Main.frame.TA_LV.append("MTTF�� "+MTTF+"\n");
		Main.frame.TA_LV.append("\n");
		Main.frame.TA_LV.append("ģ����϶ȣ� "+FD+"\n");
		Main.frame.TA_LV.append("ģ��׼ȷ�ԣ� "+PL+"\n");
		Main.frame.TA_LV.append("ģ��ƫ� "+KS_U+"\n");
		Main.frame.TA_LV.append("ģ��ƫ�����ƣ� "+KS_Y+"\n");
		Main.frame.TA_LV.append("ģ�������� "+MN+"\n");
		
	}

	
	/**
	 * ������д��������󾫶�
	 * @param x	δ֪�������д�����
	 * @return	
	 */
	static double GetA() {
		
		double f,p,q,bi;
		
		p=0.0;
		q=0.0;
		for(int i=1;i<=Model.n;i++){
			bi=Bi(i);
			p+=Model.ln(bi);
			q+=Model.ln(Model.fragTime[i]+bi);
		}
		
		f=Model.n*1.0/(q-p);
		
		return f;
	}
	
	/**
	 * �����������
	 */
	static double GetB0B1(double x0,double x1) {
		
		b0=x0;
		b1=x1;
		
		double a,p,q,pi,qi,bi,f,f1,f2;
		
		a=GetA();
		
		p=0.0;
		q=0.0;
		pi=0.0;
		qi=0.0;
		for(int i=1;i<=Model.n;i++){
			bi=Bi(i);
			p+=1.0/bi;
			q+=1.0/(Model.fragTime[i]+bi);
			pi+=F(i)*1.0/bi;
			qi+=F(i)*1.0/(Model.fragTime[i]+bi);
		}
		
		f1=a*p-(a+1)*q;
		f2=a*pi-(a+1)*qi;
		f=f1-f2;
		
		if(f1<0){
			f1=-f1;
		}
		if(f2<0){
			f2=-f2;
		}
		if(f<0){
			f=-f;
		}
		
		return f1+f2;
		
	}
	
	/**
	 * ʵ�Φ�(i)
	 * @param i
	 * @return
	 */
	static double Bi(int i){
		return b0+b1*F(i);
	}
	
	/**
	 * ���ԣ�������
	 * @param i
	 * @return i	i*i
	 */
	static double F(int i){
		return i;
//		return i*i;
	}
	
	/**
	 * ����
	 * ʧЧ��
	 */
	static void SetFailureRate(){
		FailureRate=a/(Model.fragTime[Model.n]+Bi(Model.n));
	}
	
	/**
	 * ����ʣ�������
	 */
	static void SetResidualFaults(){
		ResidualFaults=0;
	}
	
	/**
	 * ����ƽ��ʧЧ�ȴ�ʱ��
	 */
	static void SetMTTF(){
		MTTF=Bi(Model.n)/(a-1);
	}
	
	/**
	 * ����ɿ���
	 */
	static void SetReliable(){
//		Reliable=Math.pow(Bi(Model.n)/(Model.fragTime[Model.n]+Bi(Model.n)), a);
		Reliable=Model.PowE(-a/(Model.fragTime[Model.n]+Bi(Model.n)));
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
		for(int i=1;i<=Model.n+10;i++){
			double f=Bi(i)/(a-1);
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
			double f=1.0-Math.pow(Bi(i)/(Model.fragTime[i]+Bi(i)), a);
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
		
		Math.pow(Bi(Model.n)/(Model.fragTime[Model.n]+Bi(Model.n)), a);
		
		for(int i=1;i<Model.fragTime.length;i++){
			double f=a*Math.pow(Bi(i)/(Model.fragTime[i]+Bi(i)), a)/(Model.fragTime[i]+Bi(i));
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

