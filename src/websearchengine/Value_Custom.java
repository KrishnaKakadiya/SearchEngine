package websearchengine;

import java.util.ArrayList;

public class Value_Custom {

	
	private ArrayList<PageNameCounter> pnamecount;
	private ArrayList<String> pname;
	public ArrayList<String> getPageName() {
		return pname;
	}
	public void setPageName(ArrayList<String> pagename) {
		pname = pagename;
	}
	private int no_oftimes;
	
	public int getNumberOfTimes() {
		return no_oftimes;
	}
	public void setNumberOfTimes(int numberOfTimes) {
		no_oftimes = numberOfTimes;
	}
	public ArrayList<PageNameCounter> getPageNameCounters() {
		return pnamecount;
	}
	public void setPageNameCounters(ArrayList<PageNameCounter> pageNameCounters) {
		this.pnamecount = pageNameCounters;
	}
	private String occur;
	
	private int EditDistance=0;
	public int getEditDistance() {
		return EditDistance;
	}
	public void setEditDistance(int editDistance) {
		EditDistance = editDistance;
	}
	
	public String getOccurence() {
		return occur;
	}
	public void setOccurence(String occurence) {
		this.occur = occurence;
	}
	

	
	
}
