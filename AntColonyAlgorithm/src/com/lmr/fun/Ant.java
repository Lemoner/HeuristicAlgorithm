package com.lmr.fun;

/** 
 * ������ 
 * ʹ�ö���ĸ���,����ʵ��Cloneable�ӿ�,Ȼ����дObject��clone()���� 
 */  
public class Ant implements Cloneable  
{  

    /**
     * ����ת������Ĳ�ֵ
     */
    public double m_dbDiffVal;
    /**
     * ��ǰ��������ı��  
     */
    public int m_nCurAreaNo;
    /**
     * ��ǰ���������xֵ  
     */
    public double m_nCurXVal;
    /**
     * ��ǰ��������ĺ���ֵ  
     */
    public double m_nCurFunVal;
    
    /**
     * �ô�ѭ����������������x��
     */
    public double[] m_nAreaXVal;
    /**
     * �ô�ѭ����������������x���Ӧ�ĺ���ֵ
     */
    public double[] m_nAreaFunVal;
      
      
    /** 
     * ��ʼ������,����ÿ������ǰ���ø÷��� 
     */  
    public void Init()  
    {  
    	//���ѡ��һ����������  
        m_nCurAreaNo=Common.rnd(0,Common.N_SPLIT_COUNT);
        
        //���ѡ�������е�x��
        m_nCurXVal=Common.rndAreaXVal(m_nCurAreaNo);
        m_nCurFunVal=Fun.getFunResult(m_nCurXVal);
        
        m_nAreaXVal=new double[Common.N_SPLIT_COUNT];
        m_nAreaFunVal=new double[Common.N_SPLIT_COUNT];
          
    }  
      
    /** 
     * ����Object�е�clone()���� 
     * ʵ�ֶ���ĸ��� 
     */  
    protected Object clone() throws CloneNotSupportedException  
    {  
        return super.clone();  
    }  
    
      
    /** 
     * �����ƶ�����һ����
     * 
     */  
    public void Move(int nAreaNo)  
    {  
    	
    	double fi, fj;
		// ���㵱ǰ����i����һת������j��Ŀ�꺯��ֵ
		fi = m_nCurFunVal;
		fj = m_nAreaFunVal[nAreaNo];
		
		if(m_nCurAreaNo==nAreaNo){
			//�ֲ�����
			if(Common.Type==1){
				if(fj>fi){
					m_dbDiffVal=fj-fi;
				}
				else{
					m_dbDiffVal=0.0;
				}
			}
			else{
				if(fi>fj){
					m_dbDiffVal=fi-fj;
				}
				else{
					m_dbDiffVal=0.0;
				}
			}
			if(m_dbDiffVal>0.0){
				m_nCurXVal=m_nAreaXVal[nAreaNo];
		        m_nCurFunVal=m_nAreaFunVal[nAreaNo];
			}
		}
		else{
			if(Common.Type==1){
				m_dbDiffVal=fj-fi;
			}
			else{
				m_dbDiffVal=fi-fj;
			}
			m_nCurAreaNo=nAreaNo;//�ı䵱ǰ����Ϊѡ�������  
	        m_nCurXVal=m_nAreaXVal[nAreaNo];
	        m_nCurFunVal=m_nAreaFunVal[nAreaNo];
		}
		
    } 
      
    /** 
     * �������Ͼ����ĳ��� 
     */  
    public double getAntVal()  
    {  
        return this.m_nCurFunVal;  
    } 
}  
