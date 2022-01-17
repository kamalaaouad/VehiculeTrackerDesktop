package application.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.IVehiculeRemote;

import java.net.URL;

import java.util.Hashtable;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


import entities.Vehicule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;

public class VehiculeViewController implements Initializable {
	@FXML
	private TableView<Vehicule> tableVehicule;
	@FXML
	private TableColumn<Vehicule, Long> colIdV;
	@FXML
	private TableColumn<Vehicule, String> colMatV;
	@FXML
	private TableColumn<Vehicule, String> colMarV;
	@FXML
	private TableColumn<Vehicule, String> colTypV;
	@FXML
	private TextField tfMatriculeV;
	@FXML
	private TextField tfMarqueV;
	@FXML
	private TextField tfTypeV;
	@FXML
	private TextField tfIdV;
	@FXML
	private ChoiceBox<String> choxTy;
	
	//private String[] typeVehicule={"Voiture","Moto","Car","Velo","Camion"};
	ObservableList<String> test = FXCollections.observableArrayList("Voiture","Moto","Car","Velo","Camion");
	
	private ObservableList<Vehicule> observableListV = FXCollections.observableArrayList();

	// Event Listener on Button.onAction
	@FXML
	public void onAddVehicule(ActionEvent event) throws NamingException {
		if(tfIdV.getText().equals("") && tfMarqueV.getText() !="" && tfMatriculeV.getText() !="" && choxTy.getValue()!="") {
			IVehiculeRemote vehicule = lookUpVehiculeRemote();
			vehicule.create(new Vehicule(tfMatriculeV.getText(),tfMarqueV.getText(),choxTy.getValue()));
			System.out.println(choxTy.getValue());
			loadDate();
			tfMatriculeV.setText("");
			tfMarqueV.setText("");
			tfTypeV.setText("");
			choxTy.getItems().addAll(test);
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.showAndWait();
		}else {
			System.out.println("saisie data");
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void onUpDateVehicule(ActionEvent event) throws NamingException {
		if(tfIdV.getText()!="" && tfMarqueV.getText()!="" && tfMatriculeV.getText()!="" && choxTy.getValue()!="") {
		IVehiculeRemote vehicule = lookUpVehiculeRemote();
		vehicule.update(new Vehicule(Long.parseLong(tfIdV.getText()),tfMatriculeV.getText(),tfMarqueV.getText(),choxTy.getValue()));
		loadDate();
		tfIdV.setText("");
		tfMatriculeV.setText("");
		tfMarqueV.setText("");
		tfTypeV.setText("");
		choxTy.getItems().addAll(test);
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.showAndWait();
		}else {
			System.out.println("saisie data");
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void onDeleteVehicule(ActionEvent event) throws NamingException {
		if(tfIdV.getText()!="" && tfMarqueV.getText()!="" && tfMatriculeV.getText()!="" && choxTy.getValue()!="") {
			IVehiculeRemote vehicule = lookUpVehiculeRemote();
			vehicule.delete(new Vehicule(Long.parseLong(tfIdV.getText()),tfMatriculeV.getText(),tfMarqueV.getText(),choxTy.getValue()));
			loadDate();
			tfIdV.setText("");
			tfMatriculeV.setText("");
			tfMarqueV.setText("");
			tfTypeV.setText("");
			choxTy.getItems().addAll(test);
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.showAndWait();
			}else {
				System.out.println("saisie data");
			}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			if(choxTy.getItems().isEmpty()) {
				choxTy.getItems().addAll(test);
		        //box.setOnAction(this::switchscene);
		    }
			//choxTy.getItems().addAll(typeVehicule);
			loadDate();
			setCellValueFromTableToTextField();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static IVehiculeRemote lookUpVehiculeRemote() throws NamingException {
		Hashtable<Object, Object> jndiProperties=new Hashtable<Object, Object>();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(jndiProperties);
		String appName="";
		String moduleName="vehiculeTrackerEjb";
		String beanName="SV";
		String remoteInterface=IVehiculeRemote.class.getName();
		String name ="ejb:"+appName+"/"+moduleName+"/"+beanName+"!"+remoteInterface;
		return (IVehiculeRemote) context.lookup("ejb:/VehiculeTracker/SV!service.IVehiculeRemote");
	}
	private void loadDate() throws NamingException {
		tableVehicule.getItems().clear();
		IVehiculeRemote proxy = lookUpVehiculeRemote();
		for(Vehicule v:proxy.findAll())
			observableListV.add(v);
		colIdV.setCellValueFactory(new PropertyValueFactory<Vehicule, Long>("id"));
		colMatV.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("matricule"));
		colMarV.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("marque"));
		colTypV.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("type"));
		tableVehicule.setItems(observableListV);
	}
	
	private void setCellValueFromTableToTextField() {
		tableVehicule.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Vehicule vehicule = tableVehicule.getItems().get(tableVehicule.getSelectionModel().getSelectedIndex());
				tfIdV.setText(String.valueOf(vehicule.getId()));
				tfMatriculeV.setText(vehicule.getMatricule());
				tfMarqueV.setText(vehicule.getMarque());
				tfTypeV.setText(vehicule.getType());
				choxTy.setValue(vehicule.getType());
			}
		});
	}
}
