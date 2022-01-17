package entities;

import java.io.Serializable;
import java.util.Date;



public class VehiculeGPSTracker implements Serializable {

	
	

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4491139769787986147L;


	private GPSTrackerVehiculeDate id = new GPSTrackerVehiculeDate();


	private Vehicule vehicule;

	private Long idV;
	private Long idT;

	private GPSTracker gpsTracker;

	private Date dateDebut;
	private Date dateFin;

	public VehiculeGPSTracker() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public VehiculeGPSTracker(GPSTrackerVehiculeDate id, Vehicule vehicule, GPSTracker gpsTracker, Date dateDebut,
			Date dateFin) {
		super();
		this.id = id;
		this.vehicule = vehicule;
		this.gpsTracker = gpsTracker;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}


	public VehiculeGPSTracker(Vehicule vehicule, GPSTracker gpsTracker, Date dateDebut, Date dateFin) {
		super();
		this.vehicule = vehicule;
		this.gpsTracker = gpsTracker;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	
	public Long getIdV() {
		return getVehicule().getId();
	}


	public void setIdV(Long idV) {
		this.idV = idV;
	}


	public Long getIdT() {
		return getGpsTracker().getId();
	}


	public void setIdT(Long idT) {
		this.idT = idT;
	}


	public GPSTrackerVehiculeDate getId() {
		return id;
	}

	public void setId(GPSTrackerVehiculeDate id) {
		this.id = id;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	public GPSTracker getGpsTracker() {
		return gpsTracker;
	}

	public void setGpsTracker(GPSTracker gpsTracker) {
		this.gpsTracker = gpsTracker;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}


	@Override
	public String toString() {
		return "VehiculeGPSTracker [id=" + id + ", vehicule=" + vehicule.getId() + ", gpsTracker=" + gpsTracker.getId() + ", dateDebut="
				+ dateDebut + ", dateFin=" + dateFin + "]";
	}
	
}
