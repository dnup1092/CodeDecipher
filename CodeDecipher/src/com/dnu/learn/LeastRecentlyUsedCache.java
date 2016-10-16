package com.dnu.learn;

import java.util.LinkedList;

public class LeastRecentlyUsedCache {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] pages = {1, 2, 3, 1, 4, 5};
		//pages = 
		System.out.println("Answer:  " + checkInCache(4, pages));
	}
	
	public static int checkInCache(int maxSize,int[] pages) {
		LinkedList<Integer> cache = new LinkedList<>();
		int numofCacheMiss = 0;
		for(int walker=0;walker<pages.length;walker++) {
			if(!cache.contains(pages[walker])) {
				if(cache.size() < maxSize) {
					cache.offer(pages[walker]);
					numofCacheMiss++;
				} else {
					cache.poll();
					numofCacheMiss++;
					cache.offer(pages[walker]);
				}
			} else {
				cache.removeFirstOccurrence(pages[walker]);
				cache.offer(pages[walker]);
			}
		}
		return numofCacheMiss;
	}

}
