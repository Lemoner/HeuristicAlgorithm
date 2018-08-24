package com.lmr.tsp;

/** 
 * 蚂蚁类 
 * 使用对象的复制,必须实现Cloneable接口,然后重写Object中clone()方法 
 */  
public class Ant implements Cloneable  
{  
	/**
	 * 蚂蚁走过的路径  
	 */
    public int[] m_nPath=new int[Common.N_CITY_COUNT];
    /**
     * 蚂蚁走过的路径长度
     */
    public double m_dbPathLength;  
    
    /**
     * 蚂蚁没有去过的城市  
     */
    public int[] m_nAllowedCity=new int[Common.N_CITY_COUNT];
    /**
     * 当前所在城市的编号  
     */
    public int m_nCurCityNo;
    /**
     * 已经去过的城市数量  
     */
    public int m_nMovedCityCount;
      
      
    /** 
     * 初始化函数,蚂蚁每次搜索前调用该方法 
     */  
    public void Init()  
    {  
        for (int i = 0; i < Common.N_CITY_COUNT; i++)  
        {  
            m_nAllowedCity[i]=1;//设置全部城市没有去过  
            m_nPath[i]=0;//蚂蚁走过的路径全部设置为0  
        }  
        m_dbPathLength=0.0; //蚂蚁走过的路径长度设置为0  
          
        m_nCurCityNo=Common.rnd(0,Common.N_CITY_COUNT);//随机选择一个出发城市  
          
        m_nPath[0]=m_nCurCityNo;//把出发城市保存的路径数组中  
          
        m_nAllowedCity[m_nCurCityNo]=0;//标识出发城市已经去过了  
          
        m_nMovedCityCount=1;//已经去过的城市设置为1;  
    }  
      
    /** 
     * 覆盖Object中的clone()方法 
     * 实现对象的复制 
     */  
    protected Object clone() throws CloneNotSupportedException  
    {  
        return super.clone();  
    }  
      
    /** 
     * 蚂蚁移动到下一城市
     * 
     */  
    public void Move(int nCityNo)  
    {  
        m_nPath[m_nMovedCityCount]=nCityNo;//保存蚂蚁走过的路径  
        m_nAllowedCity[nCityNo]=0;//把这个城市设置为已经去过了  
        m_nCurCityNo=nCityNo;//改变当前城市为选择的城市  
        m_nMovedCityCount++;//已经去过的城市加1  
    } 
      
    /** 
     * 计算蚂蚁走过的路径长度 
     */  
    public void CalPathLength()  
    {  
        m_dbPathLength=0.0;//先把路径长度置0  
        int m=0;  
        int n=0;  
          
        for(int i=1;i<Common.N_CITY_COUNT;i++)  
        {  
            m=m_nPath[i];  
            n=m_nPath[i-1];  
            m_dbPathLength=m_dbPathLength+Common.g_Distance[m][n];  
        }  
        //加上从最后城市返回出发城市的距离  
        n=m_nPath[0];  
        m_dbPathLength=m_dbPathLength+Common.g_Distance[m][n];  
//        m_dbPathLength=(Math.round(m_dbPathLength*100))/100.0;  
    } 
    
    /** 
     * 返回蚂蚁经过的路径 
     */  
    public String getAntPath()  
    {  
        String s="";  
        for(int i=0;i<this.m_nPath.length;i++)  
        {  
            s+=this.m_nPath[i]+"-";  
        }  
        s+=this.m_nPath[0];  //蚂蚁最后要回到开始城市  
        return s;  
    }  
    /** 
     * 返回蚂蚁经过的长度 
     */  
    public double getAntLength()  
    {  
        return this.m_dbPathLength;  
    } 
}  
