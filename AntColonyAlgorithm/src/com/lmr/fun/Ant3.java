package com.lmr.fun;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/** 
 * ������ 
 * ʹ�ö���ĸ���,����ʵ��Cloneable�ӿ�,Ȼ����дObject��clone()���� 
 */  
public class Ant3 implements Serializable 
{  

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * ����ת������Ĳ�ֵ
     */
    public double m_dbDiffVal;
    /**
     * ѡȡ���ı���
     */
    public int m_nMovedCount;
    
    /**
     * ��ǰ��������ı��  
     */
    public int[] m_nCurAreaNo;
    /**
     * ��ǰ���������xֵ  
     */
    public double[] m_nCurVarVal;
    /**
     * ��ǰ��������ĺ���ֵ  
     */
    public double m_nCurFunVal;
    
    /**
     * ��ǰ·����������ı��  
     */
    public int[] m_nTempAreaNo;
    /**
     * ��ǰ·�����������xֵ  
     */
    public double[] m_nTempVarVal;
    /**
     * ��ǰ·����������ĺ���ֵ  
     */
    public double m_nTempFunVal;
    
    /**
     * �ô�ѭ����������������x��
     */
    public double[][] m_nAreaVarVal;
    /**
     * �ô�ѭ����������������x���Ӧ�ĺ���ֵ
     */
    public double[] m_nAreaFunVal;
      
      
    /** 
     * ��ʼ������,����ÿ������ǰ���ø÷��� 
     */  
    public void Init()  
    {  
    	
    	m_nMovedCount=0;
    	
    	m_nCurAreaNo=new int[Common3.VAR_NUM];
    	m_nCurVarVal=new double[Common3.VAR_NUM];
    	m_nCurFunVal=Integer.MAX_VALUE;
    	
    	m_nTempAreaNo=new int[Common3.VAR_NUM];
    	m_nTempVarVal=new double[Common3.VAR_NUM];
    	
//    	//���ѡ��һ����������  
//    	for(int i=0;i<Common3.VAR_NUM;i++){
//    		m_nCurAreaNo[i]=Common3.rnd(0, Common3.N_SPLIT_COUNT);
//    		m_nCurVarVal[i]=Common3.rndAreaVarVal(i,m_nCurAreaNo[i]);
//    	}
//    	m_nCurFunVal=getAntVal();
//        
//        m_nAreaVarVal=new double[Common3.VAR_NUM][Common3.N_SPLIT_COUNT];
//        m_nAreaFunVal=new double[Common3.N_SPLIT_COUNT];
          
    }  
      
    @Override
	public Ant3 clone(){
    	Ant3 ant = null;

		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			ObjectOutputStream outputStream = new ObjectOutputStream(output);
			outputStream.writeObject(this);

			ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
			ObjectInputStream inputStream = new ObjectInputStream(input);
			ant = (Ant3) inputStream.readObject();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ant;
	}
    
      
    /** 
     * �����ƶ�����һ����
     * 
     */  
    public void Move(int varIndex, int nAreaNo)  
    {  
//    	m_nCurAreaNo[varIndex]=nAreaNo;
//    	m_nCurVarVal[varIndex]=Common3.rndAreaVarVal(varIndex, nAreaNo);
    	
    	m_nTempAreaNo[varIndex]=nAreaNo;
    	m_nTempVarVal[varIndex]=Common3.rndAreaVarVal(varIndex, nAreaNo);
    	
    	m_nMovedCount++;
    } 
    
    /**
     * ���º���ֵ
     */
    public void UpdateVal() {
    	
//    	m_nCurFunVal=getAntVal();
    	
    	m_nTempFunVal=getAntVal(m_nTempVarVal);
    	
    	if(m_nTempFunVal<=m_nCurFunVal){
    		for(int i=0;i<m_nCurAreaNo.length;i++){
    			m_nCurAreaNo[i]=m_nTempAreaNo[i];
    		}
    		for(int i=0;i<m_nCurAreaNo.length;i++){
    			m_nCurVarVal[i]=m_nTempVarVal[i];
    		}
    		m_nCurFunVal=m_nTempFunVal;
    	}
    	else{
    		//�ֲ�����
    		for(int i=0;i<m_nCurAreaNo.length;i++){
    			int nAreaNo=m_nCurAreaNo[i];
    			m_nTempAreaNo[i]=nAreaNo;
    	    	m_nTempVarVal[i]=Common3.rndAreaVarVal(i, nAreaNo);
    		}
    		m_nTempFunVal=getAntVal(m_nTempVarVal);
    		
    		if(m_nTempFunVal<=m_nCurFunVal){
        		for(int i=0;i<m_nCurAreaNo.length;i++){
        			m_nCurAreaNo[i]=m_nTempAreaNo[i];
        		}
        		for(int i=0;i<m_nCurAreaNo.length;i++){
        			m_nCurVarVal[i]=m_nTempVarVal[i];
        		}
        		m_nCurFunVal=m_nTempFunVal;
        	}
    	}
    	
	}
      
    /** 
     * �������Ͼ����ĳ��� 
     */  
//    public void setAntVal()  
//    {  
//    	double r=0.0;
////    	for(int i=0;i<2;i++){
////    		r+=m_nCurVarVal[i]*m_nCurVarVal[i];
////    	}
//    	
//    	double[] a=m_nCurVarVal;
//    	
//		r = 3 * (1 - a[0]) * (1 - a[0])
//				* Math.pow(Math.E,
//						-a[0] * a[0] - (a[1] + 1) * (a[1] + 1))
//								- 10 * (a[0] / 5 - a[0] * a[0] * a[0] - a[1] * a[1] * a[1] * a[1] * a[1])
//										* Math.pow(Math.E, -a[0] * a[0] - a[1] * a[1])
//								- 1 / 3 * Math.pow(Math.E, -(a[0] + 1) * (a[0] + 1) - a[1] * a[1]);
//
//    	m_nCurFunVal= r;
//    	
//    } 
    
    public double getAntVal()  
    {  
    	double r=0.0;
//    	for(int i=0;i<2;i++){
//    		r+=m_nCurVarVal[i]*m_nCurVarVal[i];
//    	}
    	
    	double[] a=m_nCurVarVal;
    	
//		r = 3 * (1 - a[0]) * (1 - a[0])
//				* Math.pow(Math.E,
//						-a[0] * a[0] - (a[1] + 1) * (a[1] + 1))
//								- 10 * (a[0] / 5 - a[0] * a[0] * a[0] - a[1] * a[1] * a[1] * a[1] * a[1])
//										* Math.pow(Math.E, -a[0] * a[0] - a[1] * a[1])
//								- 1 / 3 * Math.pow(Math.E, -(a[0] + 1) * (a[0] + 1) - a[1] * a[1]);

//    	r=0.8/(a[0]*a[0]+a[1]*a[1]+1)+0.2*(a[0]*a[0]+3*a[1]*a[1]+1);
    	
//    	r=100*(a[0]*a[0]-a[1])*(a[0]*a[0]-a[1])+(1-a[0])*(1-a[0]);
    	
    	
//    	r=a[0]*Math.sin(10.0*Math.PI*a[0])+2;
    	
    	r=LVModel.GetB0B1(a[0], a[1]);
    	
    	return r;
    	
    }
    
    public double getAntVal(double[] a){
   
    	double r=0.0;
    	r=LVModel.GetB0B1(a[0], a[1]);
    	
    	return r;
    }
    

}  
