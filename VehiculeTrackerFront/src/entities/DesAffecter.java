package entities;

import java.util.Date;



public class DesAffecter {

	
	private long id;
	
	private long idV;
	private long idT;
	private Date dateDebut;
	private Date dateFin;
	public long getId() {
		return id;
	}
	
	public DesAffecter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DesAffecter(long id, long id_v, long id_g, Date dateDebut, Date dateFin) {
		super();
		this.id = id;
		this.idV = id_v;
		this.idT = id_g;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public DesAffecter(long id_v, long id_g, Date dateDebut, Date dateFin) {
		super();
		this.idV = id_v;
		this.idT = id_g;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getIdV() {
		return idV;
	}

	public void setIdV(long idV) {
		this.idV = idV;
	}

	public long getIdT() {
		return idT;
	}

	public void setIdT(long idT) {
		this.idT = idT;
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
	
}
