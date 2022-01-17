package service;

import java.util.List;

import javax.ejb.Remote;

import entities.VehiculeGPSTracker;
@Remote
public interface IVehiculeGPSTrackerRemote {
	boolean create(VehiculeGPSTracker o);

	boolean update(VehiculeGPSTracker o);

	String delete(VehiculeGPSTracker o);

	VehiculeGPSTracker findById(long id);

	List<VehiculeGPSTracker> findAll();
}
