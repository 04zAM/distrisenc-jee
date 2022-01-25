package distrisenc.controller.inventario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.MatchMode;

import distrisenc.controller.JSFUtil;
import distrisenc.model.core.entities.InventarioCa;
import distrisenc.model.core.entities.PrdProducto;
import distrisenc.model.inventario.managers.ManagerInventario;
import distrisenc.model.produccion.managers.ManagerProduccion;




@Named
@SessionScoped
public class BeanInvBodeguero implements Serializable {
	@EJB
	private ManagerInventario mInventario;
	private InventarioCa nuevoInventario;
	private List<InventarioCa> listaInventario;


	

	public BeanInvBodeguero() {
		
	}
	
	@PostConstruct
	public void inicializar() {
		listaInventario=mInventario.findAllInventarioCas();
		nuevoInventario=mInventario.inicializarInventario();

	}
	
	public void actionListenerInsertarInven() {
		try {
			mInventario.insertarInventario(nuevoInventario);
			JSFUtil.crearMensajeINFO("Ingreso Correcto");
			listaInventario=mInventario.findAllInventarioCas();
		}catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public InventarioCa getNuevoInventario() {
		return nuevoInventario;
	}

	public void setNuevoInventario(InventarioCa nuevoInventario) {
		this.nuevoInventario = nuevoInventario;
	}

	public List<InventarioCa> getListaInventario() {
		return listaInventario;
	}

	public void setListaInventario(List<InventarioCa> listaInventario) {
		this.listaInventario = listaInventario;
	}

	
   

	
	
	

	
	
	

}
	


	
	



