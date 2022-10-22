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
import model.Dictionnaire;

public class DicUserController implements  Initializable{

    @FXML
    private Button add;

    @FXML
    private Button updateId;

    @FXML
    private TextField recherche;
    
    @FXML
    private Button deleteId;
    
    @FXML
    private TableColumn<Dictionnaire, String> idDic;

    @FXML
    private TableColumn<Dictionnaire, String> author;

    @FXML
    private TableColumn<Dictionnaire, String> title;
    
    @FXML
    private TableView<Dictionnaire> table;

    @FXML
    void dictionnary(ActionEvent event) {
    	
    	try {
			 
			Parent root=FXMLLoader.load(getClass().getResource("/view/listedic.fxml"));
			Stage stage= new Stage();
			stage.setTitle("Page de dictionnaire");
			stage.setScene(new Scene(root,1150,800));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}


    }

    
    @FXML
    void Add(ActionEvent event) {
    	
    	try {
			 
			Parent root=FXMLLoader.load(getClass().getResource("/view/addDic.fxml"));
			Stage stage= new Stage();
			stage.setTitle("Page d'ajout");
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
		Dictionnaire index = table.getSelectionModel().getSelectedItem();
		String sql ="delete from dictionnaire where idDic ="+index.getIdDic();
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
    void Logout(ActionEvent event) {
    	
    	Platform.exit();

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
    void Book(ActionEvent event) {
    	
    	try {
			 
			Parent root=FXMLLoader.load(getClass().getResource("/view/user.fxml"));
			Stage stage= new Stage();
			stage.setTitle("Page de livre");
			stage.setScene(new Scene(root,1150,800));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

    }

    ObservableList<Dictionnaire> data =FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Connection con=Connexion.getConnection();
		PreparedStatement stat ;
		ResultSet rs;
		String sql ="SELECT * FROM dictionnaire";
		try {
			stat=con.prepareStatement(sql);
			rs=stat.executeQuery();
        while(rs.next()) {
        	data.add(new Dictionnaire(rs.getInt("idDic"),rs.getString("titre"),rs.getString("auteur")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		table.setEditable(true);
		idDic.setCellValueFactory(new PropertyValueFactory<Dictionnaire,String>("idDic"));
    	title.setCellValueFactory(new PropertyValueFactory<Dictionnaire,String>("Title"));
    	author.setCellValueFactory(new PropertyValueFactory<Dictionnaire,String>("Author"));
    	table.setItems(data);
    	

		title.setCellFactory(TextFieldTableCell.forTableColumn());
		title.setOnEditCommit(new EventHandler<CellEditEvent<Dictionnaire,String>>()
    	{ 
    		public void handle(CellEditEvent<Dictionnaire,String> b) 
    		{ 
    			((Dictionnaire) b.getTableView().getItems().get(b.getTablePosition().getRow())).setTitle(b.getNewValue());
    			int code=((Dictionnaire) b.getTableView().getItems().get(b.getTablePosition().getRow())).getIdDic();
    			String sql="update dictionnaire set titre =? where idDic = ?";
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
		author.setOnEditCommit(new EventHandler<CellEditEvent<Dictionnaire,String>>()
    	{ 
    		public void handle(CellEditEvent<Dictionnaire,String> b) 
    		{ 
    			((Dictionnaire) b.getTableView().getItems().get(b.getTablePosition().getRow())).setAuthor(b.getNewValue());
    			int code=((Dictionnaire) b.getTableView().getItems().get(b.getTablePosition().getRow())).getIdDic();
    			String sql="update dictionnaire set auteur =? where idDic = ?";
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
    		String sql="SELECT * FROM dictionnaire" ;
    		stat=con.prepareStatement(sql);
    		rs=stat.executeQuery();
    		while(rs.next())
    		{
    		
            	data.add(new Dictionnaire(rs.getInt("idDic"),rs.getString("titre"),rs.getString("auteur")));
    		}
    		
    		
    	}
    		catch(SQLException e)
    	{
    			e.printStackTrace();
    	}
    	
    	table.setEditable(true);
		idDic.setCellValueFactory(new PropertyValueFactory<Dictionnaire,String>("idDic"));
    	title.setCellValueFactory(new PropertyValueFactory<Dictionnaire,String>("Title"));
    	author.setCellValueFactory(new PropertyValueFactory<Dictionnaire,String>("Author"));

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
    		String sql="select * from dictionnaire where auteur=?" ;
    		stat=con.prepareStatement(sql);
    		stat.setString(1,recherche.getText());
   	    	System.out.println(" recherche 1:"+ recherche.getText());

    		rs=stat.executeQuery();
       		while(rs.next())
    		{
       	    	System.out.println(" recherche 2 :"+ recherche.getText());

    		
            	data.add(new Dictionnaire(rs.getInt("idDic"),rs.getString("titre"),rs.getString("auteur")));
    		}
    		
    		
    	}
    		catch(SQLException e)
    	{
    		  	System.out.println(" non  recherche ");
    			e.printStackTrace();
    	}
    	
    	table.setEditable(true);
		idDic.setCellValueFactory(new PropertyValueFactory<Dictionnaire,String>("idDic"));
    	title.setCellValueFactory(new PropertyValueFactory<Dictionnaire,String>("Title"));
    	author.setCellValueFactory(new PropertyValueFactory<Dictionnaire,String>("Author"));
    	table.setItems(data);

    }
	
	
	
	
	

}
