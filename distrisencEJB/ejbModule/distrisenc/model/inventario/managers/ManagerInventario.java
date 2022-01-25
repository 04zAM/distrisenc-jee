package distrisenc.model.inventario.managers;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import distrisenc.model.auditoria.managers.ManagerAuditoria;
import distrisenc.model.core.entities.InventarioCa;
import distrisenc.model.core.entities.PrdProducto;
import distrisenc.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerInventario
 */
@Stateless
@LocalBean
public class ManagerInventario {

	@EJB
	private ManagerDAO mDAO;
    /**
     * Default constructor. 
     */
    public ManagerInventario() {
        
    }
    
  public List<InventarioCa> findAllInventarioCas(){
	  return mDAO.findAll(InventarioCa.class);
  }
  

  
  
  public InventarioCa inicializarInventario() {
	  InventarioCa  inventario=new InventarioCa();
	  inventario.setIdProducto(0);
	  inventario.setNombre("");
	  inventario.setFecha(new Date());
	  inventario.setCantidad(0);
	  inventario.setTipo("");
	  return inventario;
  }
  
  

    
  public void insertarInventario(InventarioCa nuevoInventario) throws Exception {
	  
	  //PrdProducto producto=(PrdProducto) mDAO.findById(PrdProducto.class, IdProducto);
	  //nuevoInventario.setPrdProducto(producto);
	  mDAO.insertar(nuevoInventario);
  }
  
  
  


    

    
    


}
