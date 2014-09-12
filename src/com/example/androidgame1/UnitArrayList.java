package com.example.androidgame1;

import java.util.ArrayList;
import java.util.Iterator;

import com.example.tenth.system.SystemNode;

public class UnitArrayList extends ArrayList<SystemNode> {
	
	public UnitArrayList(int maxUnits) {
		super(maxUnits);
	}

	public Iterator iterator(final Filterable filter) {
		final UnitArrayList self = this;
		
		return new Iterator<SystemNode>() {

				private int counter = -1;
				
				/** The next item to return */
				private int nextHit = -1;
				
				/** The next item to filter for */
				private int queryCounter = 0;
				
				private boolean tested = false;
				
				private boolean cachedHasNext = false;
				
				/**
				 * Not idempotent! 
				 */
				@Override
				public boolean hasNext() {
					if (tested) {
						return cachedHasNext;
					}
					while (queryCounter < self.size()) {
						if (filter.filter(self.get(queryCounter)) != null) {
							nextHit = queryCounter;
							queryCounter++;
							tested = true;
							cachedHasNext = true;
							return true;
						}
						queryCounter++;
					}
					cachedHasNext = false;
					return false;
				}

				/**
				 * Call hasNext first to filter!
				 */
				@Override
				public SystemNode next() {
					tested = false;
					if (cachedHasNext) {
						return self.get(nextHit);
					}
					return null;  
				}

				/**
				 * Call hasNext first!
				 */
				@Override
				public void remove() {
					if (tested) {
						if (cachedHasNext) {
							self.remove(nextHit);
							queryCounter -= 1;
							tested = false;
						}	
					}
				}
		};
	}
	
	public interface Filterable {
		public SystemNode filter(SystemNode toFilter);
	}
}
