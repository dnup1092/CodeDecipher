package com.dnu.learn.problems;

public class CadburyProblem {

	private static int getSolution(int m, int n, int p, int q) {
		int finalResult = 0;
		for(int i : new int[] {m,n}) {
			for(int j : new int[] {p , q}) {
				finalResult += getPartialSolution(i,j);
			}
		}
		return finalResult;
	}
	
	private static int getPartialSolution(int len, int bdh) {
		int sum =0;
		if(len == bdh) {
			return ++sum;
		}
		
		if(len > bdh) {
			sum += getPartialSolution(bdh,bdh);
			int newLen = len - bdh;
			sum+=getPartialSolution(newLen,bdh);
		} else {
			sum += getPartialSolution(len,len);
			int newBdh = bdh - len;
			sum+=getPartialSolution(len,newBdh);
		}
		
		return sum; // no of bars
	}
	
	public static void main(String [] args) {
		System.out.println(getSolution(5,6,3,4));
	}
	
}
