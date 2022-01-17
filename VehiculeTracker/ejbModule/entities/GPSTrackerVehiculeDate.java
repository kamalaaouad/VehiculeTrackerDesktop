package entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GPSTrackerVehiculeDate implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8419162889416780543L;

	@Column(name = "vehicule_id")
	private Long vehiculeid;
	
	@Column(name = "gpsTracker_id")
	private Long gpsTrackerid;
	
	
	public GPSTrackerVehiculeDate() {
		super();
	}
	@Override
	public int hashCode() {
		return Objects.hash(gpsTrackerid, vehiculeid);
	}
	
	public GPSTrackerVehiculeDate(Long vehiculeId, Long gpsTrackerId) {
		super();
		this.vehiculeid = vehiculeId;
		this.gpsTrackerid = gpsTrackerId;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GPSTrackerVehiculeDate other = (GPSTrackerVehiculeDate) obj;
		return gpsTrackerid == other.gpsTrackerid && vehiculeid == other.vehiculeid;
	}
	public long getVehiculeId() {
		return vehiculeid;
	}
	public void setVehiculeId(long vehiculeId) {
		this.vehiculeid = vehiculeId;
	}
	public long getGpsTrackerId() {
		return gpsTrackerid;
	}
	public void setGpsTrackerId(long gpsTrackerId) {
		this.gpsTrackerid = gpsTrackerId;
	}
	

}
