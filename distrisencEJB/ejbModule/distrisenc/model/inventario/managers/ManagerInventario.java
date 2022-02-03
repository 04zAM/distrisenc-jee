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

	public List<InventarioCa> findAllInventarioCas() {
		return mDAO.findAll(InventarioCa.class);
	}

	public List<PrdProducto> findAllPrdProductos() {
		return mDAO.findAll(PrdProducto.class);
	}

	public InventarioCa insertarInventario(InventarioCa nuevoInventario) throws Exception {
		InventarioCa inventario = new InventarioCa();
		inventario.setIdProducto(nuevoInventario.getIdProducto());
		inventario.setNombre(nuevoInventario.getNombre());
		inventario.setFecha(new Date());
		inventario.setCantidad(nuevoInventario.getCantidad());
		inventario.setTipo(nuevoInventario.getTipo());
		mDAO.insertar(inventario);
		return inventario;
	}
}
