package model;




public class Book {
	
	private int idBook;
	private  String title;
	private  String author;

	public void setTitle(String title) {
		this.title = title;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public Book(int idbook,String title, String author) {
		this.title =title;
		this.author =author;
	    this.idBook=idbook;
	}
	
	

	public int getIdBook() {
		return idBook;
	}



	public String getAuthor() {
		return author;
	}


	public String getTitle() {
		return title;
	}
	
	





	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}



	@Override
	public String toString() {
		return "Book [idBook=" + idBook + ", title=" + title + ", author=" + author + "]";
	}
	

	

}
