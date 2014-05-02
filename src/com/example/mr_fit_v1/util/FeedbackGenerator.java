package com.example.mr_fit_v1.util;

public class FeedbackGenerator {
	public static final String EXTREMELY_BAD = "Do you really do any exercise?";
	public static final String VERY_BAD = "Apparently, you are far from your goal...";
	public static final String JUST_SO_SO = "Not to bad, but you should do more exericse...";
	public static final String GOOD = "Very good, keep doing exerise, you will be very healthy...";
	public static final String EXCELLENT = "Excellent achievement! You will keep fitness well, trust me:)";
	
	public static String getFeedback(int targetBurnedCalorie, int actualBurnedCalorie) {
		float goalFulfilledRequirement = (float)actualBurnedCalorie / targetBurnedCalorie;
		if (goalFulfilledRequirement <= 10.0f) {
			return EXTREMELY_BAD;
		} else if (goalFulfilledRequirement > 10.0f && goalFulfilledRequirement <= 40.0f) {
			return VERY_BAD;
		} else if (goalFulfilledRequirement > 40.0f && goalFulfilledRequirement <= 70.0f) {
			return JUST_SO_SO;
		} else if (goalFulfilledRequirement > 70.0f && goalFulfilledRequirement <= 90.0f) {
			return GOOD;
		} else {
			return EXCELLENT;
		}
	}
}
