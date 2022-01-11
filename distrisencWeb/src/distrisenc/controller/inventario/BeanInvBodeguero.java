package distrisenc.controller.inventario;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import distrisenc.controller.JSFUtil;
import distrisenc.model.core.entities.PrdProducto;
import distrisenc.model.produccion.managers.ManagerProduccion;

@Named
@SessionScoped
public class BeanInvBodeguero implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerProduccion mProduccion;
	private List<PrdProducto> listaProductos;
	
	
	private PrdProducto nuevoProducto;
	private PrdProducto edicionProducto;
	private PrdProducto productoSeleccionado;

	public BeanInvBodeguero() {
		
	}
	@PostConstruct
	public void inicializacion() {
		System.out.println("BeanPrdProductos inicializado...");
		nuevoProducto=new PrdProducto();
	}
	
	public String actionCargarMenuProductos() {
		listaProductos=mProduccion.findAllPrductos();
		return "productos?faces-redirect=true";
	}
	
	
	

	

	public List<PrdProducto> getlistaProductos() {
		return listaProductos;
	}

	public void setlistaProductos(List<PrdProducto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	
	

}

