package com.dnu.learn;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BiasedChef {
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String numOfCust = null;
		String value[] = null;
		int[] arrival = new int[100];
		List<JobSchedular> CustQueue = new ArrayList<JobSchedular>();
		try {
			numOfCust = br.readLine();
			Integer cust = Integer.parseInt(numOfCust);
			for(int walker = 0; walker < cust ; walker++) {
				value = br.readLine().split(" ");
				arrival[walker] = Integer.parseInt(value[0]);
				JobSchedular job = new JobSchedular();
				job.setArrivalTime(Integer.parseInt(value[0]));
				job.setOrderProcessingTime(Integer.parseInt(value[1]));
				job.setWaitTime(Integer.parseInt(value[2]));
				job.setPreferredCustomer(MyBoolean.parseBoolean(Integer.parseInt(value[3])));
				
				CustQueue.add(job);
			}
			Collections.sort(CustQueue);
			int walker = 0;
			int clockTick = 0;
			int preferredCust=0,notPreferredCust=0;
			while(walker < CustQueue.size()) {
				if(CustQueue.get(walker).getWaitTime() >= (clockTick + CustQueue.get(walker).getOrderProcessingTime())) {
					if(CustQueue.get(walker).getArrivalTime() <= clockTick) {
						clockTick+=CustQueue.get(walker).getOrderProcessingTime();
						if(CustQueue.get(walker).isPreferredCustomer()) {
							preferredCust++;
						} else {
							notPreferredCust++;
						}
						walker++;
						continue;
					}
				} else {
					walker++;
				}
			}
			System.out.println("Preferred Cutomer Servered: " + preferredCust + "\nNon-Preferred Cutomer Servered: " + notPreferredCust);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

class JobSchedular implements Comparator<JobSchedular>, Comparable<JobSchedular>{
	private Integer arrivalTime;
	private Integer orderProcessingTime;
	private Integer waitTime;
	private boolean preferredCustomer;
	/**
	 * @return the arrivalTime
	 */
	public Integer getArrivalTime() {
		return arrivalTime;
	}
	/**
	 * @param arrivalTime the arrivalTime to set
	 */
	public void setArrivalTime(Integer arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	/**
	 * @return the orderProcessingTime
	 */
	public Integer getOrderProcessingTime() {
		return orderProcessingTime;
	}
	/**
	 * @param orderProcessingTime the orderProcessingTime to set
	 */
	public void setOrderProcessingTime(Integer orderProcessingTime) {
		this.orderProcessingTime = orderProcessingTime;
	}
	/**
	 * @return the waitTime
	 */
	public Integer getWaitTime() {
		return waitTime;
	}
	/**
	 * @param waitTime the waitTime to set
	 */
	public void setWaitTime(Integer waitTime) {
		this.waitTime = waitTime;
	}
	/**
	 * @return the preferredCustomer
	 */
	public boolean isPreferredCustomer() {
		return preferredCustomer;
	}
	/**
	 * @param preferredCustomer the preferredCustomer to set
	 */
	public void setPreferredCustomer(boolean preferredCustomer) {
		this.preferredCustomer = preferredCustomer;
	}
	
	@Override
	public int compare(JobSchedular o1, JobSchedular o2) {
		if(o1.getArrivalTime() < o2.getArrivalTime()) {
			return -1;
		} else if(o1.getArrivalTime() > o2.getArrivalTime()) {
			return 1;
		}
		return 0;
	}
	@Override
	public int compareTo(JobSchedular o) {
		int result = this.getArrivalTime().compareTo(o.getArrivalTime());
		if(result == 0) {
			if(this.isPreferredCustomer() && o.isPreferredCustomer()) {
				return 0;
			} else if(this.isPreferredCustomer()) {
				return -1;
			} else {
				return 1;
			}
		}
		return result;
	}
	
}

class MyBoolean {
	public static boolean parseBoolean(int i) {
		if(i==1){
			return true;
		} 
		return false;
	}
}