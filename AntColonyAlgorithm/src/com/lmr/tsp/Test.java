package com.lmr.tsp;

import com.lmr.fun.Common2;
import com.lmr.fun.Solve2;

public class Test {

	public static void main(String[] args) {
		
//		Common2.Type=-1;
//		Common2.VAR_NUM=2;
//		Common2.VAR_LOW=new double[]{-3,-3};
//		Common2.VAR_UPPER=new double[]{3,3};
//		Common2.VAR_Scale_LOW=new double[]{-3,-3};
//		Common2.VAR_Scale_UPPER=new double[]{3,3};
		
		Common2.VAR_NUM=1;
		Common2.VAR_LOW=new double[]{-1};
		Common2.VAR_UPPER=new double[]{2};
		Common2.VAR_Scale_LOW=new double[]{-1};
		Common2.VAR_Scale_UPPER=new double[]{2};
		
		Solve2 solve=new Solve2();
		
		solve.Start();
		
	}
	
}
