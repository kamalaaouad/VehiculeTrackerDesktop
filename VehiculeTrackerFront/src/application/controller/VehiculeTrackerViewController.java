package application.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Date;
import java.util.Hashtable;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.DesAffecter;
import entities.GPSTracker;
import entities.Vehicule;
import entities.VehiculeGPSTracker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.IDesAffect;
import service.IGPSTrackerRemote;
import service.IVehiculeGPSTrackerRemote;
import service.IVehiculeRemote;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;

public class VehiculeTrackerViewController implements Initializable {
	@FXML 
	private TableView<Vehicule> tableVehicules;
	@FXML
	private TableColumn<Vehicule, Long> colIdV;
	@FXML
	private TableColumn<Vehicule, String> colMatV;
	@FXML
	private TableColumn<Vehicule, String> colMarV;
	@FXML
	private TableColumn<Vehicule, String> colTypeV;
	@FXML
	private TableView<GPSTracker> tableTrackers;
	@FXML
	private TableColumn<GPSTracker, Long> colIdT;
	@FXML
	private TableColumn<GPSTracker, String> colSimT;
	@FXML
	private TableColumn<GPSTracker, String> colIMieT;
	@FXML
	private TableView<VehiculeGPSTracker> tableVehiculeTracker;
	@FXML
	private TableColumn<VehiculeGPSTracker, Long> colId_v;
	@FXML
	private TableColumn<VehiculeGPSTracker, Long> colId_t;
	@FXML
	private TableColumn<VehiculeGPSTracker, Date> colDateD;
	@FXML
	private TableColumn<VehiculeGPSTracker, Date> colDateF;

	private ObservableList<GPSTracker> observableListGps = FXCollections.observableArrayList();
	private ObservableList<Vehicule> observableListVe = FXCollections.observableArrayList();
	private ObservableList<VehiculeGPSTracker> observableListVG = FXCollections.observableArrayList();
	private GPSTracker gpsTracker;
	private Vehicule vehicule;
	private VehiculeGPSTracker desAffect;
	public VehiculeTrackerViewController(){
		System.out.println("constructor");
//		gpsTracker= new GPSTracker();
//		vehicule=new Vehicule();
	}

	// Event Listener on Button.onAction
//	@FXML
//	public void onDesAffect(ActionEvent event) throws NamingException {
//		
//	}
	
	@FXML
	public void onDesAffect(ActionEvent event) throws NamingException {
		// TODO Autogenerated
		if(desAffect!=null) {
			IDesAffect proxy=lookUpDesAffectRemote();
			IVehiculeGPSTrackerRemote proxy1=lookUpVGTRemote();
			proxy.desAffecter(new DesAffecter(desAffect.getIdV(), desAffect.getIdT(),desAffect.getDateDebut(), new Date()));
			proxy1.delete(new VehiculeGPSTracker(desAffect.getVehicule(), desAffect.getGpsTracker(), desAffect.getDateDebut(), desAffect.getDateFin()));
		}
		System.out.println("not done");
	}
	@FXML
	public void onAffect(ActionEvent event) throws NamingException {
		
		if(vehicule!=null && gpsTracker!=null) {
			//System.out.println("vehicule1 "+vehicule.getId()+" gps "+gpsTracker.getId());
			IVehiculeGPSTrackerRemote proxy=lookUpVGTRemote();
			IVehiculeRemote proxyVehicule=lookUpVehiculeRemote();
			IGPSTrackerRemote proxyGps=lookUpGpsRemote();
			if(proxy.create(new VehiculeGPSTracker(proxyVehicule.findById(vehicule.getId()),proxyGps.findById(gpsTracker.getId()), new Date(), null))) {
				System.out.println("yes");
				loadDate();
				loadDateT();
				loadDateV();
				vehicule=null;
				gpsTracker=null;
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setContentText("Bien cr�er");
				alert.showAndWait();
			}else {
				Alert alert = new Alert(AlertType.NONE,"non pas affecter sont deja affecter",ButtonType.APPLY);
				//alert.setContentText("non pas affecter sont deja affecter");
				alert.showAndWait();
			}
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("selectionner la vehicule souhait� avec tracker");
			alert.showAndWait();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			System.out.println("hello");
			loadDate();
			loadDateT();
			loadDateV();
			//setCellValueFromTableToTextField();
			setCellValueFromTableToTextFieldV();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void loadDateV() throws NamingException {
		tableVehicules.getItems().clear();
		IVehiculeRemote proxy = lookUpVehiculeRemote();
		for (Vehicule v : proxy.findAll())
			observableListVe.add(v);
		colIdV.setCellValueFactory(new PropertyValueFactory<Vehicule, Long>("id"));
		colMatV.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("matricule"));
		colMarV.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("marque"));
		colTypeV.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("type"));
		tableVehicules.setItems(observableListVe);
		//System.out.println(proxy.findAll());
	}
//
	private void loadDateT() throws NamingException {
		tableTrackers.getItems().clear();
		IGPSTrackerRemote proxy = lookUpGpsRemote();
		for (GPSTracker v : proxy.findAll())
			observableListGps.add(v);
		colIdT.setCellValueFactory(new PropertyValueFactory<GPSTracker, Long>("id"));
		colSimT.setCellValueFactory(new PropertyValueFactory<GPSTracker, String>("simNumber"));
		colIMieT.setCellValueFactory(new PropertyValueFactory<GPSTracker, String>("ime"));
		tableTrackers.setItems(observableListGps);
	}
//
	private void loadDate() throws NamingException {
		tableVehiculeTracker.getItems().clear();
		Vehicule v = new Vehicule();
		GPSTracker gps=new GPSTracker();
		
		IVehiculeGPSTrackerRemote proxy = lookUpVGTRemote();
		for (VehiculeGPSTracker vg : proxy.findAll())
			observableListVG.add(vg);
		colId_v.setCellValueFactory(new PropertyValueFactory<VehiculeGPSTracker, Long>("idV"));
		colId_t.setCellValueFactory(new PropertyValueFactory<VehiculeGPSTracker, Long>("idT"));
		colDateD.setCellValueFactory(new PropertyValueFactory<VehiculeGPSTracker, Date>("dateDebut"));
		colDateF.setCellValueFactory(new PropertyValueFactory<VehiculeGPSTracker, Date>("dateFin"));
		tableVehiculeTracker.setItems(observableListVG);
		//System.out.println(proxy.findAll());
	}
//
	public static IGPSTrackerRemote lookUpGpsRemote() throws NamingException {
		Hashtable<Object, Object> jndiProperties = new Hashtable<Object, Object>();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(jndiProperties);
		String appName = "";
		String moduleName = "vehiculeTrackerEjb";
		String beanName = "SGT";
		String remoteInterface = IGPSTrackerRemote.class.getName();
		String name = "ejb:" + appName + "/" + moduleName + "/" + beanName + "!" + remoteInterface;
		return (IGPSTrackerRemote) context.lookup("ejb:/VehiculeTracker/SGT!service.IGPSTrackerRemote");
	}
//
	public static IVehiculeRemote lookUpVehiculeRemote() throws NamingException {
		Hashtable<Object, Object> jndiProperties = new Hashtable<Object, Object>();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(jndiProperties);
		String appName = "";
		String moduleName = "vehiculeTrackerEjb";
		String beanName = "SV";
		String remoteInterface = IVehiculeRemote.class.getName();
		String name = "ejb:" + appName + "/" + moduleName + "/" + beanName + "!" + remoteInterface;
		return (IVehiculeRemote) context.lookup("ejb:/VehiculeTracker/SV!service.IVehiculeRemote");
	}
	//
	public static IDesAffect lookUpDesAffectRemote() throws NamingException {
		Hashtable<Object, Object> jndiProperties = new Hashtable<Object, Object>();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(jndiProperties);
		String appName = "";
		String moduleName = "vehiculeTrackerEjb";
		String beanName = "SV";
		String remoteInterface = IVehiculeRemote.class.getName();
		String name = "ejb:" + appName + "/" + moduleName + "/" + beanName + "!" + remoteInterface;
		return (IDesAffect) context.lookup("ejb:/VehiculeTracker/desAffect!service.IDesAffect");
	}
//
	public static IVehiculeGPSTrackerRemote lookUpVGTRemote() throws NamingException {
		Hashtable<Object, Object> jndiProperties = new Hashtable<Object, Object>();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(jndiProperties);
		String appName = "";
		String moduleName = "vehiculeTrackerEjb";
		String beanName = "SVGT";
		String remoteInterface = IVehiculeGPSTrackerRemote.class.getName();
		String name = "ejb:" + appName + "/" + moduleName + "/" + beanName + "!" + remoteInterface;
		return (IVehiculeGPSTrackerRemote) context
				.lookup("ejb:/VehiculeTracker/SVGT!service.IVehiculeGPSTrackerRemote");
	}
	
	
	
	private void setCellValueFromTableToTextFieldV() {
		tableVehicules.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				 vehicule = tableVehicules.getItems().get(tableVehicules.getSelectionModel().getSelectedIndex());
				//System.out.println(vehicule);
			}
		});
		tableTrackers.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				 gpsTracker = tableTrackers.getItems().get(tableTrackers.getSelectionModel().getSelectedIndex());
				// System.out.println(gpsTracker);
				// System.out.println(vehicule);
			}
		});
		tableVehiculeTracker.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				 desAffect = tableVehiculeTracker.getItems().get(tableVehiculeTracker.getSelectionModel().getSelectedIndex());
				System.out.println(desAffect);
			}
		});


	}
}