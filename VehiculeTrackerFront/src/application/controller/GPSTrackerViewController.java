package application.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.IGPSTrackerRemote;
import service.IVehiculeRemote;

import java.net.URL;
import java.util.Hashtable;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.GPSTracker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;

public class GPSTrackerViewController implements Initializable {
	@FXML
	private TextField tfSimT;
	@FXML
	private TextField tfMieT;
	@FXML
	private TextField tfIdT;
	@FXML
	private TableView<GPSTracker> tableTracker;
	@FXML
	private TableColumn<GPSTracker, Long> colIdT;
	@FXML
	private TableColumn<GPSTracker, String> colIdSim;
	@FXML
	private TableColumn<GPSTracker, String> colIme;
	
	private ObservableList<GPSTracker> observableListT= FXCollections.observableArrayList();
	
	public static IGPSTrackerRemote lookUpGpsRemote() throws NamingException {
		Hashtable<Object, Object> jndiProperties=new Hashtable<Object, Object>();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(jndiProperties);
		String appName="";
		String moduleName="vehiculeTrackerEjb";
		String beanName="SGT";
		String remoteInterface=IGPSTrackerRemote.class.getName();
		String name ="ejb:"+appName+"/"+moduleName+"/"+beanName+"!"+remoteInterface;
		return (IGPSTrackerRemote) context.lookup("ejb:/VehiculeTracker/SGT!service.IGPSTrackerRemote");
	}

	// Event Listener on Button.onAction
	@FXML
	public void onAddTracker(ActionEvent event) throws NamingException {
		if(tfIdT.getText().equals("") && tfSimT.getText() !="" && tfMieT.getText() != "") {
			IGPSTrackerRemote proxy = lookUpGpsRemote();
			proxy.create(new GPSTracker(tfSimT.getText(),tfMieT.getText()));
			tfSimT.setText("");
			tfMieT.setText("");
			loadDate();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.showAndWait();
		}else {
			System.out.println("no data saisie");
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void onUpdateTracker(ActionEvent event) throws NamingException {
		if(tfIdT.getText() !="" && tfMieT.getText() !="" && tfSimT.getText()!="") {
			IGPSTrackerRemote proxy = lookUpGpsRemote();
			proxy.update(new GPSTracker(Long.parseLong(tfIdT.getText()),tfSimT.getText(),tfMieT.getText()));
			tfSimT.setText("");
			tfMieT.setText("");
			tfIdT.setText("");
			loadDate();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.showAndWait();
		}else {
			System.out.println("data no saisie");
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void onDeleteTracker(ActionEvent event) throws NamingException {
		if(tfIdT.getText() !="" && tfMieT.getText() !="" && tfSimT.getText()!="") {
			IGPSTrackerRemote proxy = lookUpGpsRemote();
			proxy.delete(new GPSTracker(Long.parseLong(tfIdT.getText()),tfSimT.getText(),tfMieT.getText()));
			System.out.println("after delete");
			tfSimT.setText("");
			tfMieT.setText("");
			tfIdT.setText("");
			loadDate();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.showAndWait();
		}else {
			System.out.println("data no saisie");
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			loadDate();
			setCellValueFromTableToTextField();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void loadDate() throws NamingException {
		tableTracker.getItems().clear();
		IGPSTrackerRemote proxy = lookUpGpsRemote();
		for(GPSTracker v:proxy.findAll())
			observableListT.add(v);
		colIdT.setCellValueFactory(new PropertyValueFactory<GPSTracker, Long>("id"));
		colIdSim.setCellValueFactory(new PropertyValueFactory<GPSTracker, String>("simNumber"));
		colIme.setCellValueFactory(new PropertyValueFactory<GPSTracker, String>("ime"));
		tableTracker.setItems(observableListT);
	}
	private void setCellValueFromTableToTextField() {
		tableTracker.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				GPSTracker gpsTracker = tableTracker.getItems().get(tableTracker.getSelectionModel().getSelectedIndex());
				tfIdT.setText(String.valueOf(gpsTracker.getId()));
				tfSimT.setText(gpsTracker.getSimNumber());
				tfMieT.setText(gpsTracker.getIme());
			}
		});
	}
}
