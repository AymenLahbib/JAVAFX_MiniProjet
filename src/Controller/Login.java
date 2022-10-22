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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login {

    @FXML
    private TextField password;

    @FXML
    private Button login;

    @FXML
    private TextField username;

    @FXML
    private Button register;

    @FXML
    void Register(ActionEvent event) {
    	try {
			 
			Parent root=FXMLLoader.load(getClass().getResource("/view/register.fxml"));
			Stage stage= new Stage();
			stage.setTitle("Page d'inscription");
			stage.setScene(new Scene(root,1150,800));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void Login(ActionEvent event)throws SQLException {
    	
			Connection con=Connexion.getConnection();
			PreparedStatement stat ;
			ResultSet rs ;
			String sql = "select * from admin where name=? and password =?";
			try
			{
				stat=con.prepareStatement(sql);
				stat.setString(1,username.getText().toString());
				stat.setString(2,password.getText().toString());
				rs=stat.executeQuery();
				if(rs.next()) {
					
					System.out.println("Connecter");
					
					try {
						 
						Parent root=FXMLLoader.load(getClass().getResource("/view/listebookk.fxml"));
						Stage stage= new Stage();
						stage.setTitle("Page d'acceuill");
						stage.setScene(new Scene(root,1150,800));
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}}
			catch(SQLException e)
			{
				e.printStackTrace();
				System.out.println("erreur "+ e.getMessage());
			}
			///////////////: login admin//////////////////////////////////////////:
	//	PreparedStatement stata ;
			//	ResultSet rsa ;

    PreparedStatement statA ;
	ResultSet rsA ;
	String sqlA = "select * from users where username=? and password =?";
	try
	{
		statA=con.prepareStatement(sqlA);
		statA.setString(1,username.getText().toString());
		statA.setString(2,password.getText().toString());
		rsA=statA.executeQuery();
		if(rsA.next()) {
			
			System.out.println("Connecter");
			
			try {
				 
				Parent root=FXMLLoader.load(getClass().getResource("/view/user.fxml"));
				Stage stage= new Stage();
				stage.setTitle("Page d'acceuill");
				stage.setScene(new Scene(root,1150,800));
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	catch(SQLException e)
	{
		e.printStackTrace();
		System.out.println("erreur "+ e.getMessage());
	}}
    
    

   

}