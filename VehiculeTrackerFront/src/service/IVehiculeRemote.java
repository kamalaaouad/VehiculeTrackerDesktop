package service;

import java.util.List;

import javax.ejb.Remote;

import entities.Vehicule;

@Remote
public interface IVehiculeRemote {
	boolean create(Vehicule o);

	boolean update(Vehicule o);

	String delete(Vehicule o);

	Vehicule findById(long id);

	List<Vehicule> findAll();
}
