package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Connexion.Connexion;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import model.Book;

public class BookUserController implements  Initializable {

	@FXML
    private TextField recherche;
    @FXML
    private Button add;

    @FXML
    private TableColumn<Book, String> author;

    @FXML
    private TableColumn<Book, String> idbook;

    @FXML
    private TableColumn<Book, String> title;
    
    @FXML
    private TableView<Book> table;


    @FXML
    private Button deleteId;
    
    @FXML
    private Button updateId;
    

    @FXML
    void Book(ActionEvent event) {

    }

    @FXML
    void dictionnary(ActionEvent event) {
    	
    	try {
			 
			Parent root=FXMLLoader.load(getClass().getResource("/view/listerUserDic.fxml"));
			Stage stage= new Stage();
			stage.setTitle("Page de dictionnaire");
			stage.setScene(new Scene(root,1150,800));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}


    }

    @FXML
    void newspaper(ActionEvent event) {
    	
    	try {
			 
			Parent root=FXMLLoader.load(getClass().getResource("/view/listeUserjour.fxml"));
			Stage stage= new Stage();
			stage.setTitle("Page de journal");
			stage.setScene(new Scene(root,1150,800));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

    }

    
    
    @FXML
    void Delete(ActionEvent event) {
    	
    	EventHandler<ActionEvent> eventHandler = new EventHandler <ActionEvent> ()
		{
	@Override
	public void handle(ActionEvent eventHandler)
	{
		Book index = table.getSelectionModel().getSelectedItem();
		String sql ="delete from livre where idLiv ="+index.getIdBook();
		Connection con=Connexion.getConnection();
		PreparedStatement stat ;
		ResultSet rs;
		try {
			stat=con.prepareStatement(sql);
			stat.execute();
			data.clear();
			view(event);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
		};
		deleteId.setOnAction(eventHandler);;
        table.refresh();

    }
    
    
    

    @FXML
    void Users(ActionEvent event) {
    	
    	try { 
			Parent root=FXMLLoader.load(getClass().getResource("/view/listeuser.fxml"));
			Stage stage= new Stage();
			stage.setTitle("Page des utilisateur");
			stage.setScene(new Scene(root,1150,800));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

    }

    @FXML
    void Logout(ActionEvent event) {
    	
        Platform.exit();

    }
    
    @FXML
    void Add(ActionEvent event) {

    	try {
			 
			Parent root=FXMLLoader.load(getClass().getResource("/view/addbook.fxml"));
			Stage stage= new Stage();
			stage.setTitle("Page d'ajout");
			stage.setScene(new Scene(root,1150,800));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

    }
    
    
    
    ObservableList<Book> data =FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Connection con=Connexion.getConnection();
		PreparedStatement stat ;
		ResultSet rs;
		String sql ="SELECT * FROM livre";
		try {
			stat=con.prepareStatement(sql);
			rs=stat.executeQuery();
        while(rs.next()) {
        	data.add(new Book(rs.getInt("idLiv"),rs.getString("titre"),rs.getString("auteur")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		table.setEditable(true);
		idbook.setCellValueFactory(new PropertyValueFactory<Book,String>("idBook"));
    	title.setCellValueFactory(new PropertyValueFactory<Book,String>("Title"));
    	author.setCellValueFactory(new PropertyValueFactory<Book,String>("Author"));
    	table.setItems(data);
    	

		title.setCellFactory(TextFieldTableCell.forTableColumn());
		title.setOnEditCommit(new EventHandler<CellEditEvent<Book,String>>()
    	{ 
    		public void handle(CellEditEvent<Book,String> b) 
    		{ 
    			((Book) b.getTableView().getItems().get(b.getTablePosition().getRow())).setTitle(b.getNewValue());
    			int code=((Book) b.getTableView().getItems().get(b.getTablePosition().getRow())).getIdBook();
    			String sql="update livre set titre =? where idLiv = ?";
    			PreparedStatement stat ;
				try {
					stat=con.prepareStatement(sql);
					stat.setString(1, b.getNewValue());
					stat.setInt(2, code);
					int rs= stat.executeUpdate();
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Confirmation");
					alert.setContentText("Add successfully !");
					alert.showAndWait();
				} catch (SQLException e) {
					e.printStackTrace();
				}

    		}
    		
    	});
		
		
		
		author.setCellFactory(TextFieldTableCell.forTableColumn());
		author.setOnEditCommit(new EventHandler<CellEditEvent<Book,String>>()
    	{ 
    		public void handle(CellEditEvent<Book,String> b) 
    		{ 
    			((Book) b.getTableView().getItems().get(b.getTablePosition().getRow())).setAuthor(b.getNewValue());
    			int code=((Book) b.getTableView().getItems().get(b.getTablePosition().getRow())).getIdBook();
    			String sql="update livre set auteur =? where idLiv = ?";
    			PreparedStatement stat ;
				try {
					stat=con.prepareStatement(sql);
					stat.setString(1, b.getNewValue());
					stat.setInt(2, code);
					int rs= stat.executeUpdate();
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Confirmation");
					alert.setContentText("Add successfully !");
					alert.showAndWait();
				} catch (SQLException e) {
					e.printStackTrace();
				}

    		}
    		
    	});
    	
	}
	
	
	public void view(ActionEvent event)
    {
    	data.clear();
    	try {
    		Connection con=Connexion.getConnection();
    		
    		PreparedStatement stat ;
    		ResultSet rs ;
    		String sql="SELECT * FROM livre" ;
    		stat=con.prepareStatement(sql);
    		rs=stat.executeQuery();
    		while(rs.next())
    		{
    		
    			data.add(new Book(rs.getInt("idLiv"),rs.getString("titre"),rs.getString("auteur")));
    		}
    		
    		
    	}
    		catch(SQLException e)
    	{
    			e.printStackTrace();
    	}
    	
    	table.setEditable(true);
		idbook.setCellValueFactory(new PropertyValueFactory<Book,String>("idBook"));
    	title.setCellValueFactory(new PropertyValueFactory<Book,String>("Title"));
    	author.setCellValueFactory(new PropertyValueFactory<Book,String>("Author"));

    	table.setItems(data);
    	//System.out.println(data);

    }
	
	
	@FXML
    public void search(ActionEvent event)
    {
    	
    	data.clear();
    	try {
    		Connection con=Connexion.getConnection();
    		
    		PreparedStatement stat ;
    		ResultSet rs ;
    		String sql="select * from livre where auteur=?" ;
    		stat=con.prepareStatement(sql);
    		stat.setString(1,recherche.getText());
   	    	System.out.println(" recherche 1:"+ recherche.getText());

    		rs=stat.executeQuery();
       		while(rs.next())
    		{
       	    	System.out.println(" recherche 2 :"+ recherche.getText());

    		
       	    	data.add(new Book(rs.getInt("idLiv"),rs.getString("titre"),rs.getString("auteur")));
    		}
    		
    		
    	}
    		catch(SQLException e)
    	{
    		  	System.out.println(" non  recherche ");
    			e.printStackTrace();
    	}
    	
    	idbook.setCellValueFactory(new PropertyValueFactory<Book,String>("idBook"));
    	title.setCellValueFactory(new PropertyValueFactory<Book,String>("Title"));
    	author.setCellValueFactory(new PropertyValueFactory<Book,String>("Author"));
    	table.setItems(data);

    }
	
	
	
	
}
