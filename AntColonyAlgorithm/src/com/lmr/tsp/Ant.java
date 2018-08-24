package com.lmr.tsp;

/** 
 * ������ 
 * ʹ�ö���ĸ���,����ʵ��Cloneable�ӿ�,Ȼ����дObject��clone()���� 
 */  
public class Ant implements Cloneable  
{  
	/**
	 * �����߹���·��  
	 */
    public int[] m_nPath=new int[Common.N_CITY_COUNT];
    /**
     * �����߹���·������
     */
    public double m_dbPathLength;  
    
    /**
     * ����û��ȥ���ĳ���  
     */
    public int[] m_nAllowedCity=new int[Common.N_CITY_COUNT];
    /**
     * ��ǰ���ڳ��еı��  
     */
    public int m_nCurCityNo;
    /**
     * �Ѿ�ȥ���ĳ�������  
     */
    public int m_nMovedCityCount;
      
      
    /** 
     * ��ʼ������,����ÿ������ǰ���ø÷��� 
     */  
    public void Init()  
    {  
        for (int i = 0; i < Common.N_CITY_COUNT; i++)  
        {  
            m_nAllowedCity[i]=1;//����ȫ������û��ȥ��  
            m_nPath[i]=0;//�����߹���·��ȫ������Ϊ0  
        }  
        m_dbPathLength=0.0; //�����߹���·����������Ϊ0  
          
        m_nCurCityNo=Common.rnd(0,Common.N_CITY_COUNT);//���ѡ��һ����������  
          
        m_nPath[0]=m_nCurCityNo;//�ѳ������б����·��������  
          
        m_nAllowedCity[m_nCurCityNo]=0;//��ʶ���������Ѿ�ȥ����  
          
        m_nMovedCityCount=1;//�Ѿ�ȥ���ĳ�������Ϊ1;  
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
    public void Move(int nCityNo)  
    {  
        m_nPath[m_nMovedCityCount]=nCityNo;//���������߹���·��  
        m_nAllowedCity[nCityNo]=0;//�������������Ϊ�Ѿ�ȥ����  
        m_nCurCityNo=nCityNo;//�ı䵱ǰ����Ϊѡ��ĳ���  
        m_nMovedCityCount++;//�Ѿ�ȥ���ĳ��м�1  
    } 
      
    /** 
     * ���������߹���·������ 
     */  
    public void CalPathLength()  
    {  
        m_dbPathLength=0.0;//�Ȱ�·��������0  
        int m=0;  
        int n=0;  
          
        for(int i=1;i<Common.N_CITY_COUNT;i++)  
        {  
            m=m_nPath[i];  
            n=m_nPath[i-1];  
            m_dbPathLength=m_dbPathLength+Common.g_Distance[m][n];  
        }  
        //���ϴ������з��س������еľ���  
        n=m_nPath[0];  
        m_dbPathLength=m_dbPathLength+Common.g_Distance[m][n];  
//        m_dbPathLength=(Math.round(m_dbPathLength*100))/100.0;  
    } 
    
    /** 
     * �������Ͼ�����·�� 
     */  
    public String getAntPath()  
    {  
        String s="";  
        for(int i=0;i<this.m_nPath.length;i++)  
        {  
            s+=this.m_nPath[i]+"-";  
        }  
        s+=this.m_nPath[0];  //�������Ҫ�ص���ʼ����  
        return s;  
    }  
    /** 
     * �������Ͼ����ĳ��� 
     */  
    public double getAntLength()  
    {  
        return this.m_dbPathLength;  
    } 
}  
