package implServices;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import entities.Vehicule;
import service.IVehiculeRemote;

@Stateless(name = "SV")
public class VehiculeService implements IVehiculeRemote {
	
	@PersistenceContext(name="vehiculeTrackerEjb")
	private EntityManager em;
	
	@Override
	public boolean create(Vehicule o) {
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
	public boolean update(Vehicule o) {
		try {
			em.merge(o);
			return true;
			
		}catch (Exception e) {
			e.getLocalizedMessage();
			e.getMessage();
			e.getStackTrace();
			return false;
		}
	}

	@Override
	public String delete(Vehicule o) {
		try {
			em.remove(em.contains(o)? o:em.merge(o));
			return "deleted with successful";
		} catch (Exception e) {
			return "not delete object";
		}
	}

	@Override
	public Vehicule findById(long id) {
		Vehicule vehicule = em.find(Vehicule.class, id);
		if(vehicule == null) throw new RuntimeException("not found this vehicule correspond this id: "+id);
		
		return vehicule;
	}

	@Override
	public List<Vehicule> findAll() {
		Query query = em.createQuery("select v from Vehicule v");
		return query.getResultList();
	}

}
