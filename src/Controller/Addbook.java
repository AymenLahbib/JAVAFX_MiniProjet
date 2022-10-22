package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connexion.Connexion;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import javafx.stage.Stage;

public class Addbook {

    @FXML
    private Button add;

    @FXML
    private Button log;

    @FXML
    private TextField author;

    @FXML
    private TextField idbook;

    @FXML
    private TextField title;

    
    @FXML
    void Book(ActionEvent event) {
    	
    	try {
			 
			Parent root=FXMLLoader.load(getClass().getResource("/view/listebookk.fxml"));
			Stage stage= new Stage();
			stage.setTitle("Page de livre");
			stage.setScene(new Scene(root,1150,800));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

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
    	
    	EventHandler<ActionEvent> eventHandler = new EventHandler <ActionEvent> ()
		{
	@Override
	public void handle(ActionEvent eventHandler)
	{
		if(title.getText().equals("") )
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Form Error !");
			alert.setContentText("Remplir le titre");
			alert.showAndWait();
		}
		else if( author.getText().equals("") )
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Form Error !");
			alert.setContentText("Repmlir l'author");
			alert.showAndWait();
		}
		
		
		else
		{   
			Connection con=Connexion.getConnection();
			PreparedStatement stat ;
			ResultSet rs;
			String sql ="insert into livre (titre,auteur)values (?,?)";
			
			try
			{
				stat=con.prepareStatement(sql);
				stat.setString(1,author.getText().toString());
			
				stat.setString(2,title.getText().toString());
				

				
				int res=stat.executeUpdate();
					
			}
			
		catch(SQLException e)
			{
				e.printStackTrace();
				System.out.println("erreur "+ e.getMessage());
			}
			
			try {
				 
				Parent root=FXMLLoader.load(getClass().getResource("/view/listebookk.fxml"));
				Stage stage= new Stage();
				stage.setTitle("Page d'acceuill");
				stage.setScene(new Scene(root,1150,800));
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}
	}
	
		};
		add.setOnAction(eventHandler);;
		
		

    }

    @FXML
    void log_out(ActionEvent event) {
    	Platform.exit();

    }

}
