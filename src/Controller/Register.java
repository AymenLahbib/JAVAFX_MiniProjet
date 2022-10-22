package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connexion.Connexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Register {

    @FXML
    private TextField pass;

    @FXML
    private TextField user;

    @FXML
    private Button register;

 

    @FXML
    void register(ActionEvent event) {
    	
    	try{
			if(user.getText().isEmpty() || pass.getText().isEmpty())
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Error de validation de formulaire");
			alert.setContentText("Vos champs sont vides ");
			alert.showAndWait();
		}
		
		}
    	catch(Exception e)
    	
		{
    		System.out.println("Remplir vos champs");
		}
		
		
			
	
		Connection con=Connexion.getConnection();
		PreparedStatement stat ;
		ResultSet rs ;
		String sql = "insert into users (username,password)values (?,?)";
		try
		{
			stat=con.prepareStatement(sql);
			stat.setString(1,user.getText().toString());
		
			stat.setString(2,pass.getText().toString());
			

			
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
			stage.setTitle("Register");
			stage.setScene(new Scene(root,1150,800));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		}

    }


