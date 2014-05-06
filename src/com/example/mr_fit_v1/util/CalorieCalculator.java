package com.example.mr_fit_v1.util;

public class CalorieCalculator {
	// Speed Scale (Mile/Hour)
	private static final float MAX_STATIC_SPEED = 0.5f;
	private static final float MAX_SLOW_WALK_SPEED = 3.0f;
	private static final float MAX_FAST_WALK_SPEED = 6.0f;
	private static final float MAX_SLOW_RUN_SPEED = 10.0f;
	
	// Calorie Consumption Scale (Calorie/Hour)
	private static final int STATIC_CONSUMPTION = 76;
	private static final int SLOW_WALK_CONSUMPTION = 255;
	private static final int FAST_WALK_CONSUMPTION = 555;
	private static final int SLOW_RUN_CONSUMPTION = 755;
	private static final int FAST_RUN_CONSUMPTION = 910;
	
	public static int calculateCalorie(float distance, int duration) {
		// Calculate speed for this time period
		float speed = distance / duration * 3600;
	
		int scale;
		if (speed < MAX_STATIC_SPEED) {
			scale = STATIC_CONSUMPTION;
		} else if (MAX_STATIC_SPEED <= speed && speed < MAX_SLOW_WALK_SPEED) {
			scale = SLOW_WALK_CONSUMPTION;
		} else if (MAX_SLOW_WALK_SPEED <= speed && speed < MAX_FAST_WALK_SPEED) {
			scale = FAST_WALK_CONSUMPTION;
		} else if (MAX_FAST_WALK_SPEED <= speed && speed < MAX_SLOW_RUN_SPEED) {
			scale = SLOW_RUN_CONSUMPTION;
		} else {
			scale = FAST_RUN_CONSUMPTION;
		}
		int calorie = (int)((float)duration / 3600 * scale);
		return calorie; 
	}
}
