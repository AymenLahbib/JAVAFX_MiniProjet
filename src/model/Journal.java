package model;

public class Journal {

      private int idjour;
      private String date;
      private String title;
      
      
      
      
	public Journal(int idjour, String date, String title) {
		super();
		this.idjour = idjour;
		this.date = date;
		this.title = title;
	}
	public int getIdjour() {
		return idjour;
	}
	public void setIdjour(int idjour) {
		this.idjour = idjour;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Journal [idjour=" + idjour + ", date=" + date + ", title=" + title + "]";
	}
	
	
      
      
}
