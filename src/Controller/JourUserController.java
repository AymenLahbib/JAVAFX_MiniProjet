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
import model.Journal;

public class JourUserController implements  Initializable {

	@FXML
    private TextField recherche;
	
    @FXML
    private Button add;

    @FXML
    private TableColumn<Journal, String> Date_parution;

    @FXML
    private TableColumn<Journal, String> idJour;

    @FXML
    private TableColumn<Journal, String> title;

    @FXML
    private Button deleteId;

    @FXML
    private TableView<Journal> table;

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
			 
			Parent root=FXMLLoader.load(getClass().getResource("/view/user.fxml"));
			Stage stage= new Stage();
			stage.setTitle("Page de livre");
			stage.setScene(new Scene(root,1150,800));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

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
    void Add(ActionEvent event) {
    	
    	try {
			 
			Parent root=FXMLLoader.load(getClass().getResource("/view/AddJour.fxml"));
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
		Journal index = table.getSelectionModel().getSelectedItem();
		String sql ="delete from journal where idj ="+index.getIdjour();
		Connection con=Connexion.getConnection();
		PreparedStatement stat ;
		ResultSet rs;
		try {
			stat=con.prepareStatement(sql);
			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			data.clear();
			view(event);
		}
	
	}
	
		};
		deleteId.setOnAction(eventHandler);;
        table.refresh();

    }
    
    

    ObservableList<Journal> data =FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Connection con=Connexion.getConnection();
		PreparedStatement stat ;
		ResultSet rs;
		String sql ="SELECT * FROM journal";
		try {
			stat=con.prepareStatement(sql);
			rs=stat.executeQuery();
        while(rs.next()) {
        	data.add(new Journal(rs.getInt("idj"),rs.getString("dateparution"),rs.getString("titre")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		table.setEditable(true);
		idJour.setCellValueFactory(new PropertyValueFactory<Journal,String>("idjour"));
    	title.setCellValueFactory(new PropertyValueFactory<Journal,String>("Title"));
    	Date_parution.setCellValueFactory(new PropertyValueFactory<Journal,String>("date"));
    	table.setItems(data);
    	

		title.setCellFactory(TextFieldTableCell.forTableColumn());
		title.setOnEditCommit(new EventHandler<CellEditEvent<Journal,String>>()
    	{ 
    		public void handle(CellEditEvent<Journal,String> b) 
    		{ 
    			((Journal) b.getTableView().getItems().get(b.getTablePosition().getRow())).setTitle(b.getNewValue());
    			int code=((Journal) b.getTableView().getItems().get(b.getTablePosition().getRow())).getIdjour();
    			String sql="update journal set titre =? where idj = ?";
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
		
		
		
		Date_parution.setCellFactory(TextFieldTableCell.forTableColumn());
		Date_parution.setOnEditCommit(new EventHandler<CellEditEvent<Journal,String>>()
    	{ 
    		public void handle(CellEditEvent<Journal,String> b) 
    		{ 
    			((Journal) b.getTableView().getItems().get(b.getTablePosition().getRow())).setDate(b.getNewValue());
    			int code=((Journal) b.getTableView().getItems().get(b.getTablePosition().getRow())).getIdjour();
    			String sql="update journal set dateparution =? where idj = ?";
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
    		String sql="SELECT * FROM journal" ;
    		stat=con.prepareStatement(sql);
    		rs=stat.executeQuery();
    		while(rs.next())
    		{
    		
    			data.add(new Journal(rs.getInt("idj"),rs.getString("dateparution"),rs.getString("titre")));
    		}
    		
    		
    	}
    		catch(SQLException e)
    	{
    			e.printStackTrace();
    	}
    	
    	table.setEditable(true);
		idJour.setCellValueFactory(new PropertyValueFactory<Journal,String>("idjour"));
    	title.setCellValueFactory(new PropertyValueFactory<Journal,String>("Title"));
    	Date_parution.setCellValueFactory(new PropertyValueFactory<Journal,String>("date"));

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
    		String sql="select * from journal where titre=?" ;
    		stat=con.prepareStatement(sql);
    		stat.setString(1,recherche.getText());
   	    	System.out.println(" recherche 1:"+ recherche.getText());

    		rs=stat.executeQuery();
       		while(rs.next())
    		{
       	    	System.out.println(" recherche 2 :"+ recherche.getText());

    		
    			this.data.add(new Journal(rs.getInt("idj"),rs.getString("dateparution"),rs.getString("titre")));
    		}
    		
    		
    	}
    		catch(SQLException e)
    	{
    		  	System.out.println(" non  recherche ");
    			e.printStackTrace();
    	}
    	
    	table.setEditable(true);
		idJour.setCellValueFactory(new PropertyValueFactory<Journal,String>("idjour"));
    	title.setCellValueFactory(new PropertyValueFactory<Journal,String>("Title"));
    	Date_parution.setCellValueFactory(new PropertyValueFactory<Journal,String>("date"));
    	table.setItems(data);

    }
	
}
