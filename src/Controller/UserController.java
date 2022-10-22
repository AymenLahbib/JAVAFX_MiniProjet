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
import model.User;

public class UserController implements  Initializable{
	
	
	@FXML
    private TextField recherche;
	    @FXML
	    private Button deleteId;
	    
	    @FXML
	    private TableColumn<User, String> idUser;

	    @FXML
	    private TableView<User> table;

	    @FXML
	    private TableColumn<User, String> username;

	    
	    
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
	    void Book(ActionEvent event) {
	    	
	    	try {
				 
				Parent root=FXMLLoader.load(getClass().getResource("/view/listebookk.fxml"));
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
				 
				Parent root=FXMLLoader.load(getClass().getResource("/view/listejour.fxml"));
				Stage stage= new Stage();
				stage.setTitle("Page de journal");
				stage.setScene(new Scene(root,1150,800));
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}

	    }

	    @FXML
	    void Add(ActionEvent event) {

	    	try {
				 
				Parent root=FXMLLoader.load(getClass().getResource("/view/adduser.fxml"));
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
			User index = table.getSelectionModel().getSelectedItem();
			String sql ="delete from users where iduser ="+index.getIdUser();
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
	    
	    ObservableList<User> data =FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		Connection con=Connexion.getConnection();
		PreparedStatement stat ;
		ResultSet rs;
		String sql ="SELECT * FROM users";
		try {
			stat=con.prepareStatement(sql);
			rs=stat.executeQuery();
        while(rs.next()) {
        	data.add(new User(rs.getInt("iduser"),rs.getString("username"),rs.getString("password")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		table.setEditable(true);
		idUser.setCellValueFactory(new PropertyValueFactory<User,String>("idUser"));
		username.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
    	table.setItems(data);
    	
    	username.setCellFactory(TextFieldTableCell.forTableColumn());
    	username.setOnEditCommit(new EventHandler<CellEditEvent<User,String>>()
    	{ 
    		public void handle(CellEditEvent<User,String> b) 
    		{ 
    			((User) b.getTableView().getItems().get(b.getTablePosition().getRow())).setUsername(b.getNewValue());
    			int code=((User) b.getTableView().getItems().get(b.getTablePosition().getRow())).getIdUser();
    			String sql="update users set username =? where iduser = ?";
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
    		String sql="SELECT * FROM users" ;
    		stat=con.prepareStatement(sql);
    		rs=stat.executeQuery();
    		while(rs.next())
    		{
    		
    			data.add(new User(rs.getInt("iduser"),rs.getString("username"),rs.getString("password")));
    		}
    		
    		
    	}
    		catch(SQLException e)
    	{
    			e.printStackTrace();
    	}
    	
    	table.setEditable(true);
		idUser.setCellValueFactory(new PropertyValueFactory<User,String>("idUser"));
		username.setCellValueFactory(new PropertyValueFactory<User,String>("username"));

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
    		String sql="select * from users where username=?" ;
    		stat=con.prepareStatement(sql);
    		stat.setString(1,recherche.getText());
   	    	System.out.println(" recherche 1:"+ recherche.getText());

    		rs=stat.executeQuery();
       		while(rs.next())
    		{
       	    	System.out.println(" recherche 2 :"+ recherche.getText());

    		
    			this.data.add(new User(rs.getInt("iduser"),rs.getString("username"),rs.getString("password")));
    		}
    		
    		
    	}
    		catch(SQLException e)
    	{
    		  	System.out.println(" non  recherche ");
    			e.printStackTrace();
    	}
    	
    	table.setEditable(true);
		idUser.setCellValueFactory(new PropertyValueFactory<User,String>("idUser"));
		username.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
    	table.setItems(data);

    }
	
	
	
	

}
