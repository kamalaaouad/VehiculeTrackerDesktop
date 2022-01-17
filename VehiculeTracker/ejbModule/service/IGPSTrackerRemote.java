package service;

import java.util.List;

import javax.ejb.Remote;

import entities.GPSTracker;

@Remote
public interface IGPSTrackerRemote {
	
	boolean create(GPSTracker o);

	boolean update(GPSTracker o);

	String delete(GPSTracker o);

	GPSTracker findById(long id);

	List<GPSTracker> findAll();
}
