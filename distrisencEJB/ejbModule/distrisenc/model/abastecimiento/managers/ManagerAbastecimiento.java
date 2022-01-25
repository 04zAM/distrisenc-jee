package distrisenc.model.abastecimiento.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import distrisenc.model.core.entities.AbsMaterialPrima;
import distrisenc.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerAbastecimiento
 */
@Stateless
@LocalBean
public class ManagerAbastecimiento {

	@EJB
	private ManagerDAO mDAO;
	
    /**
     * Default constructor. 
     */
    public ManagerAbastecimiento() {
        // TODO Auto-generated constructor stub
    }
    
    public List<AbsMaterialPrima> findAllAbsMateriaPrima(){
    	return mDAO.findAll(AbsMaterialPrima.class);
    }
    
    public void createAbsMateriaPrima(AbsMaterialPrima Abs) throws Exception {
    	mDAO.insertar(Abs);
    }
    
}
