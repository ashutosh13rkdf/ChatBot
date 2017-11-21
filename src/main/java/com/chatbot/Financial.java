package com.chatbot;

public class Financial {
	private String revenue;
	private String pm;
	private String pm_percent;
	public String getRevenue() {
		return revenue;
	}
	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}
	public String getPm() {
		return pm;
	}
	public void setPm(String pm) {
		this.pm = pm;
	}
	public String getPm_percent() {
		return pm_percent;
	}
	public void setPm_percent(String pm_percent) {
		this.pm_percent = pm_percent;
	}
	@Override
	public String toString() {
		return "Financial [revenue=" + revenue + ", pm=" + pm + ", pm_percent="
				+ pm_percent + "]";
	}

}