package distrisenc.controller.produccion;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import distrisenc.controller.JSFUtil;
import distrisenc.model.core.entities.PrdOrden;
import distrisenc.model.produccion.managers.ManagerProduccion;

@Named
@SessionScoped
public class BeanPrdOrdenes implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerProduccion mProduccion;
	private List<PrdOrden> listaOrdenes;

	public BeanPrdOrdenes() {
		
	}

	@PostConstruct
	public void inicializacion() {
		System.out.println("BeanPrdOrdenes inicializado...");
		listaOrdenes = mProduccion.findAllOrdenes();
	}
	
	public void actionListenerAutorizar(PrdOrden orden) {
		try {
			mProduccion.confirmarAutorizacion(orden);
			JSFUtil.crearMensajeINFO("Pedido Autorizado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public List<PrdOrden> getListaOrdenes() {
		return listaOrdenes;
	}

	public void setListaOrdenes(List<PrdOrden> listaOrdenes) {
		this.listaOrdenes = listaOrdenes;
	}

}
