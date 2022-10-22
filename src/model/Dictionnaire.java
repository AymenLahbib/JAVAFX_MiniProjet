package model;

public class Dictionnaire {
	
	private int idDic;
	private  String title;
	private  String author;

	public void setTitle(String title) {
		this.title = title;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public Dictionnaire(int idDic,String title, String author) {
		this.title =title;
		this.author =author;
	    this.idDic=idDic;
	}
	
	

	public int getIdDic() {
		return idDic;
	}



	public String getAuthor() {
		return author;
	}


	public String getTitle() {
		return title;
	}
	
	

	public void setIdidDic(int idDic) {
		this.idDic = idDic;
	}



	@Override
	public String toString() {
		return "Dictionnaire [idDic=" + idDic + ", title=" + title + ", author=" + author + "]";
	}

}
