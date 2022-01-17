package implServices;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.VehiculeGPSTracker;
import service.IVehiculeGPSTrackerRemote;

@Stateless(name = "SVGT")
public class VehiculeGPSTrackerService implements IVehiculeGPSTrackerRemote {

	@PersistenceContext(name="vehiculeTrackerEjb")
	private EntityManager em;
	
	@Override
	public boolean create(VehiculeGPSTracker o) {
		try {
			System.out.println(o);
			em.merge(o);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean update(VehiculeGPSTracker o) {
		try {
			em.merge(o);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	@Override
	public String delete(VehiculeGPSTracker o) {
		try {
			em.remove((em.contains(o))? o:em.merge(o));
			return "deleted with successful";
			
		} catch (Exception e) {
			// TODO: handle exception
			return "not deleted";
		}
	}

	@Override
	public VehiculeGPSTracker findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehiculeGPSTracker> findAll() {
		try {
			Query query = em.createQuery("select vt from VehiculeGPSTracker vt");
			return query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			return null;
		}
	}

}
