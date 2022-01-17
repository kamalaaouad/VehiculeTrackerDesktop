package implServices;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.GPSTracker;
import service.IGPSTrackerRemote;

@Stateless(name = "SGT")
public class GPSTrackerService implements IGPSTrackerRemote {

	@PersistenceContext(name="vehiculeTrackerEjb")
	private EntityManager em;
	@Override
	public boolean create(GPSTracker o) {
		try {
			em.persist(o);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean update(GPSTracker o) {
		try {
			em.merge(o);
			return true;
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
		
	}

	@Override
	public String delete(GPSTracker o) {
		try {
			System.out.println("yes "+o);
			em.remove(em.contains(o)? o:em.merge(o));
			System.out.println("good");
			return "deleted with successful";
		} catch (Exception e) {
			return "not deleted object";
		}
	}

	@Override
	public GPSTracker findById(long id) {
		GPSTracker gpsTracker = em.find(GPSTracker.class, id);
		if(gpsTracker ==null) throw new RuntimeException("n'existe pas le tracker correspondant a cette id :"+id);
		return gpsTracker;
	}

	@Override
	public List<GPSTracker> findAll() {
		try {
			Query query=em.createQuery("select t from GPSTracker t");
			return query.getResultList();
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

}
