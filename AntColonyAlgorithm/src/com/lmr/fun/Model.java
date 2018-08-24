package com.lmr.fun;

/**
 * �ɿ���ģ�Ͳ���
 */
public class Model {

	/**
	 * ʧЧ��
	 */
	public static int n;
	/**
	 * ���Ե���ʱ��
	 */
	public static int endTime;
	/**
	 * ʧЧʱ��
	 */
	public static int[] sumTime;
	/**
	 * ʧЧ���
	 */
	public static int[] fragTime;
	/**
	 * ʧЧ����
	 */
	public static int[] sumFaults;
	/**
	 * ʧЧ�������
	 */
	public static int[] fragFaults;
	/**
	 * ���ظ���ʧЧʱ��
	 */
	public static int[] sumTime_NoRepeat;
	/**
	 * ���ظ���ʧЧ���
	 */
	public static int[] fragTime_NoRepeat;
	
	/**
	 * ��ʼ��
	 */
	public static void initData(){
		
		//����ʧЧ���
		fragTime=new int[n+1];
		for(int i=1;i<=n;i++){
			fragTime[i]=sumTime[i]-sumTime[i-1];
		}
		
		//����ʧЧ����
		int[] temp=new int[n+1];
		int sum=0,index=1;
		for(int i=1;i<=n;i++){
			sum++;
			while(i+1<=n&&sumTime[i]==sumTime[i+1]){
				sum++;
				i++;
			}
			temp[index++]=sum;
		}
		sumFaults=new int[index];
		System.arraycopy(temp, 0, sumFaults, 0, index);
		
		//����ʧЧ�������
		fragFaults=new int[index];
		for(int i=1;i<index;i++){
			fragFaults[i]=sumFaults[i]-sumFaults[i-1];
		}
		
		//���㲻�ظ���ʧЧʱ��
		sumTime_NoRepeat=new int[index];
		index=1;
		for(int i=1;i<=n;i++){
			while(i+1<=n&&sumTime[i]==sumTime[i+1]){
				i++;
			}
			sumTime_NoRepeat[index++]=sumTime[i];
		}
		
		//���㲻�ظ���ʧЧ���
		fragTime_NoRepeat=new int[index];
		for(int i=1;i<index;i++){
			fragTime_NoRepeat[i]=sumTime_NoRepeat[i]-sumTime_NoRepeat[i-1];
		}
		
//		System.out.println(sumTime.length+" - "+Arrays.toString(sumTime));
//		System.out.println(fragTime.length+" - "+Arrays.toString(fragTime));
//		System.out.println(temp.length+" - "+Arrays.toString(temp));
//		System.out.println(sumFaults.length+" - "+Arrays.toString(sumFaults));
//		System.out.println(fragFaults.length+" - "+Arrays.toString(fragFaults));
//		System.out.println(sumTime_NoRepeat.length+" - "+Arrays.toString(sumTime_NoRepeat));
		
	}
	
	/**
	 * ����
	 * @param x
	 * @return
	 */
	public static double PowE(double x){
		return Math.pow(Math.E, x);
	}
	
	public static double ln(double x){
		return Math.log(x);
	}
	
}
