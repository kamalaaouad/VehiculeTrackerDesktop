package service;

import java.util.List;

import entities.DesAffecter;

public interface IDesAffect {
	boolean desAffecter(DesAffecter d);
	List<DesAffecter> getAll();
}
