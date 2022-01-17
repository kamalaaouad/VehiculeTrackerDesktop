package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: GPSTracker
 *
 */
@Entity
public class GPSTracker implements Serializable {

	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4906664783988725650L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String simNumber;
	private String ime;
	
	public String getIme() {
		return ime;
	}


	public void setIme(String ime) {
		this.ime = ime;
	}

	@OneToMany(mappedBy = "gpsTracker",fetch = FetchType.EAGER)
	private List<VehiculeGPSTracker> vehiculeGPSTrackers;
	
	
	
	public GPSTracker(String simNumber,String ime) {
		super();
		this.simNumber = simNumber;
		this.ime=ime;
	}
	
	
	public GPSTracker(long id, String simNumber,String ime) {
		super();
		this.id = id;
		this.simNumber = simNumber;
		this.ime=ime;
	}


	public GPSTracker(long id, String simNumber, List<VehiculeGPSTracker> vehiculeGPSTrackers) {
		super();
		this.id = id;
		this.simNumber = simNumber;
		this.vehiculeGPSTrackers = vehiculeGPSTrackers;
	}


	public GPSTracker() {
		super();
	}   
	public long getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getSimNumber() {
		return this.simNumber;
	}

	public void setSimNumber(String simNumber) {
		this.simNumber = simNumber;
	}
	public List<VehiculeGPSTracker> getVehiculeGPSTrackers() {
		return vehiculeGPSTrackers;
	}
	public void setVehiculeGPSTrackers(List<VehiculeGPSTracker> vehiculeGPSTrackers) {
		this.vehiculeGPSTrackers = vehiculeGPSTrackers;
	}

	@Override
	public String toString() {
		return "GPSTracker [id=" + id + ", simNumber=" + simNumber + "]";
	}
	
   
}
