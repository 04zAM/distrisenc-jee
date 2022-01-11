package distrisenc.model.facturas.managers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import distrisenc.model.core.entities.VenFactura;
import distrisenc.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerFacturas
 */
@Stateless
@LocalBean
public class ManagerFacturas { 
@EJB
private ManagerDAO mDAO;
    /**
     * Default constructor. 
     */
    public ManagerFacturas() {
    
    }
    
    public List<VenFactura> findAllFacturas(){
    	return mDAO.findAll(VenFactura.class);
    }
    
    public VenFactura inicializarFactura() {
    	VenFactura factura=new VenFactura();
    	factura.setIdSegUsuario(1);
    	factura.setFecha(new Date());
    	factura.setEstado("true");
    	factura.setObservaciones(" ");
    	factura.setTotal(new BigDecimal(0));
    	return factura;
    }
    
    public void insertarFactura(VenFactura nuevaFactura)  throws Exception {
		mDAO.insertar(nuevaFactura);
	}
    

}