package application.controller;

import java.io.Serializable;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.IDesAffect;
import service.IVehiculeGPSTrackerRemote;
import service.IVehiculeRemote;
import javafx.scene.control.TableColumn;

public class DesAffectViewController implements Initializable {
	
	@FXML
	private TableView<DesAffecter> tableVehiculeTracker;
	@FXML
	private TableColumn<DesAffecter, Long> colId_v;
	@FXML
	private TableColumn<DesAffecter, Long> colId_t;
	@FXML
	private TableColumn<DesAffecter, Date> colDateD;
	@FXML
	private TableColumn<DesAffecter, Date> colDateF;
	@FXML
	private TableColumn<DesAffecter, Long> colId;
	
	private ObservableList<DesAffecter> observableListVG = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			loadDate();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadDate() throws NamingException {
		tableVehiculeTracker.getItems().clear();
		Vehicule v = new Vehicule();
		GPSTracker gps=new GPSTracker();
		
		IDesAffect proxy = lookUpDesAffectRemote();
		for (DesAffecter vg : proxy.getAll())
			observableListVG.add(vg);
		colId_v.setCellValueFactory(new PropertyValueFactory<DesAffecter, Long>("idV"));
		colId_t.setCellValueFactory(new PropertyValueFactory<DesAffecter, Long>("idT"));
		colId.setCellValueFactory(new PropertyValueFactory<DesAffecter, Long>("id"));
		colDateD.setCellValueFactory(new PropertyValueFactory<DesAffecter, Date>("dateDebut"));
		colDateF.setCellValueFactory(new PropertyValueFactory<DesAffecter, Date>("dateFin"));
		tableVehiculeTracker.setItems(observableListVG);
		//System.out.println(proxy.findAll());
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

}
