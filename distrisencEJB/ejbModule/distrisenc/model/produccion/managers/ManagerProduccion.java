package distrisenc.model.produccion.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import distrisenc.model.auditoria.managers.ManagerAuditoria;
import distrisenc.model.core.entities.PrdProducto;
import distrisenc.model.core.managers.ManagerDAO;
import distrisenc.model.core.utils.ModelUtil;

/**
 * Implementa la logica de manejo de usuarios y autenticacion.
 * Funcionalidades principales:
 * <ul>
 * 	<li>Verificacion de credenciales de usuario.</li>
 *  <li>Asignacion de modulos a un usuario.</li>
 * </ul>
 * @author evasquez
 */
@Stateless
@LocalBean
public class ManagerProduccion {
	@EJB
	private ManagerDAO mDAO;
	@EJB
	private ManagerAuditoria mAuditoria;
    /**
     * Default constructor. 
     */
    public ManagerProduccion() {
        
    }
    
    public List<PrdProducto> findAllPrductos(){
    	return mDAO.findAll(PrdProducto.class, "nombre");
    }
    
    public PrdProducto insertarProducto(PrdProducto nuevoProducto) throws Exception {
    	PrdProducto producto = new PrdProducto();
    	producto.setNombre(nuevoProducto.getNombre());
    	producto.setCosto(nuevoProducto.getCosto());
    	producto.setVenta(nuevoProducto.getVenta());
    	producto.setVendible(nuevoProducto.getVendible());
    	producto.setStock(nuevoProducto.getStock());
    	producto.setActivo(true);
    	mDAO.insertar(producto);
    	return producto;
    }
    
    public void eliminarProducto(int idPrdProducto) throws Exception {
    	PrdProducto producto = (PrdProducto) mDAO.findById(PrdProducto.class, idPrdProducto);
    	if(producto.getVenDetProformas().size()>0)
    		throw new Exception("No se puede eliminar un producto que tiene proformas realizadas con el.");
    	mDAO.eliminar(PrdProducto.class, idPrdProducto);
    }
    
    public void actualizarProducto(PrdProducto edicionProducto) throws Exception {
    	PrdProducto producto = (PrdProducto) mDAO.findById(PrdProducto.class, edicionProducto.getIdProducto());
    	producto.setNombre(edicionProducto.getNombre());
    	producto.setCosto(edicionProducto.getCosto());
    	producto.setVenta(edicionProducto.getVenta());
    	mDAO.actualizar(producto);
    }

}
