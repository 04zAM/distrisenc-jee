package distrisenc.controller.produccion;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import distrisenc.controller.JSFUtil;
import distrisenc.model.core.entities.PrdProducto;
import distrisenc.model.produccion.managers.ManagerProduccion;

@Named
@SessionScoped
public class BeanPrdProductos implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerProduccion mProduccion;
	private List<PrdProducto> listaProductos;
	private PrdProducto nuevoProducto;
	private PrdProducto edicionProducto;
	private PrdProducto productoSeleccionado;

	public BeanPrdProductos() {
		
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
	
	public void actionListenerInsertarProducto() {
		try {
			mProduccion.insertarProducto(nuevoProducto);
			listaProductos=mProduccion.findAllPrductos();
			JSFUtil.crearMensajeINFO("Producto creado.");
			nuevoProducto=new PrdProducto();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerCargarProducto(PrdProducto producto) {
		edicionProducto=producto;
	}
	
	public void actionListenerGuardaredicionProducto() {
		try {
			mProduccion.actualizarProducto(edicionProducto);
			JSFUtil.crearMensajeINFO("Producto editado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void actionListenerEliminarProducto(int idPrdProducto) {
		try {
			mProduccion.eliminarProducto(idPrdProducto);
			listaProductos=mProduccion.findAllPrductos();
			JSFUtil.crearMensajeINFO("Producto eliminado");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void checkMessage() {
        String summary = nuevoProducto.getVendible() ? "Marcado como Vendible" : "Marcado como Materia Prima";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }

	public List<PrdProducto> getlistaProductos() {
		return listaProductos;
	}

	public void setlistaProductos(List<PrdProducto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	
	public PrdProducto getnuevoProducto() {
		return nuevoProducto;
	}
	public void setnuevoProducto(PrdProducto nuevoProducto) {
		this.nuevoProducto = nuevoProducto;
	}
	public PrdProducto getedicionProducto() {
		return edicionProducto;
	}
	public void setedicionProducto(PrdProducto edicionProducto) {
		this.edicionProducto = edicionProducto;
	}
	public PrdProducto getproductoSeleccionado() {
		return productoSeleccionado;
	}
	public void setproductoSeleccionado(PrdProducto productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}
	

}
