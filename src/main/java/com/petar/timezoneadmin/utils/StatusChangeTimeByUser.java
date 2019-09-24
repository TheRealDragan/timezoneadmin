package com.petar.timezoneadmin.utils;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
/**Contains the userId->status change time in milis for users. This is required in order
 * to invalidate JWT tokens that modified users had prior to their status change 
 * @author Petar
 *
 */
public class StatusChangeTimeByUser {
	
	private ConcurrentHashMap<Long, Long> timesOfUserStatusChanges = new ConcurrentHashMap<Long, Long>();
	
	public void addToMap(Long key, Long value) {
		timesOfUserStatusChanges.put(key, value);
	}
	
	public Long fetch(Long key) {
		return timesOfUserStatusChanges.get(key);
	}

}
