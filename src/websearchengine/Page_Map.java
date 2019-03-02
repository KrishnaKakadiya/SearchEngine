package websearchengine;

import java.util.Comparator;

public class Page_Map implements Comparator<Page_Map>, Comparable<Page_Map> {

	private int c;
	private String f;

	public int getCounter() {
		return c;
	}

	public void setCounter(int count_val) {
		this.c = count_val;
	}

	public String getFile() {
		return f;
	}

	public void setFile(String file) {
		this.f = file;
	}

	public int compareTo(Page_Map p) {
		return (this.f).compareTo(p.f);
	}

	public int compare(Page_Map p, Page_Map p1) {
		return (p.c - p1.c);

	}
}
