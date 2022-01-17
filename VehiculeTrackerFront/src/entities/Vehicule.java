package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;




public class Vehicule implements Serializable {

	private static final long serialVersionUID = 6085739631413232113L;

	private long id;
	private String matricule;
	private String marque;
	private String type;
	

	//private List<VehiculeGPSTracker> vehiculeGPSTrackers;

	public Vehicule() {
		super();
	}   
	
	public Vehicule(String matricule, String marque, String type) {
		super();
		this.matricule = matricule;
		this.marque = marque;
		this.type = type;
	}
	

	public Vehicule(long id, String matricule, String marque, String type) {
		super();
		this.id = id;
		this.matricule = matricule;
		this.marque = marque;
		this.type = type;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}   
	public String getMatricule() {
		return this.matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}   
	public String getMarque() {
		return this.marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}   
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Vehicule [id=" + id + ", matricule=" + matricule + ", marque=" + marque + ", type=" + type
				 + "]";
	}
   
}
