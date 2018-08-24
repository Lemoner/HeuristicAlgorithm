package com.lmr.tsp;

import java.util.Arrays;

public class Tsp_DFS {

	int CityCount;
	double[][] CityDistance;
	
	int[] x;
	int[] y;
	
	int count;
	double bestDistance;
	double distance;
	int[] city;
	
	int[] trip;
	int[] bestTrip;
	
	public void initData(int id){
		
		if(id==1){
			CityCount=10;
			CityDistance =new double[][] { 
					{ 0, 98, 99, 27, 89, 175, 96, 26, 64, 49 }, 
					{ 98, 0, 54, 98, 73, 18, 77, 89, 63, 64 },
					{ 99, 54, 0, 61, 36, 69, 46, 53, 13, 81 }, 
					{ 27, 98, 61, 0, 86, 75, 15, 87, 21, 87 },
					{ 89, 73, 36, 86, 0, 46, 19, 87, 65, 46 }, 
					{ 175, 18, 69, 75, 46, 0, 26, 53, 21, 23 },
					{ 96, 77, 46, 15, 19, 26, 0, 46, 36, 54 }, 
					{ 26, 89, 53, 87, 87, 53, 46, 0, 75, 65 },
					{ 64, 63, 13, 21, 65, 21, 36, 75, 0, 54 }, 
					{ 49, 64, 81, 87, 46, 23, 54, 65, 54, 0 },
				};
		}
		else if(id==2){
			CityCount=30;
			CityDistance=new double[CityCount][CityCount];  
			int[] x = { 41, 37, 54, 25, 7, 2, 68, 71, 54, 83, 64, 18, 22, 83, 91, 25, 24, 58, 71, 74, 87, 18, 13, 82, 62, 58, 45, 41, 44, 4 };
			int[] y = { 94, 84, 67, 62, 64, 99, 58, 44, 62, 69, 60, 54, 60, 46, 38, 38, 42, 69, 71, 78, 76, 40, 40, 7, 32, 35, 21, 26, 35, 50 };

            for(int i=0;i<CityCount;i++){
            	for(int j=0;j<CityCount;j++){
            		if(i==j){  
            			CityDistance[i][j]=0.0;//同一个城市为0
            		}
            		else{
            			double rij = Math.sqrt(((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j])* (y[i] - y[j])));  
            			CityDistance[i][j] = rij;  
            			CityDistance[j][i] = CityDistance[i][j];  
            		}
            	}
            }
            
		}
		
		
		count=1;
		bestDistance=99999999;
		distance=0;
		city=new int[CityCount];
		city[0]=1;
		
		trip=new int[CityCount];
		bestTrip=new int[CityCount];
		
	}
	
	public void DFS(int cityIndex){
		System.out.println(Arrays.toString(trip));
		if(count>=CityCount){
			if(distance+CityDistance[trip[CityCount-1]][trip[0]]<bestDistance){
				bestDistance=distance+CityDistance[trip[CityCount-1]][trip[0]];
				for(int i=0;i<CityCount;i++){
					bestTrip[i]=trip[i];
				}
			}
		}
		else{
			for(int i=0;i<CityCount;i++){
				if(i!=cityIndex&&city[i]!=1){
					city[i]=1;
					trip[count]=i;
					count++;
					distance+=CityDistance[cityIndex][i];
					DFS(i);
					distance-=CityDistance[cityIndex][i];
					count--;
					city[i]=0;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		Tsp_DFS tsp=new Tsp_DFS();
		
		tsp.initData(2);
		tsp.DFS(0);
		
		System.out.println(tsp.bestDistance);
		System.out.println(Arrays.toString(tsp.bestTrip));
		
	}
	
}
