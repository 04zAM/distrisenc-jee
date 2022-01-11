package distrisenc.controller.facturas;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import distrisenc.controller.JSFUtil;
import distrisenc.model.core.entities.VenFactura;
import distrisenc.model.facturas.managers.ManagerFacturas;

@Named
@SessionScoped
public class BeanFactEmpleado implements Serializable {
	@EJB
	private ManagerFacturas mFacturas;
	private VenFactura nuevaFactura;
	private List<VenFactura> listaFacturas;
		
	public BeanFactEmpleado() {	
	}
	
	@PostConstruct
	public void inicializar() {
		listaFacturas =mFacturas.findAllFacturas();
		nuevaFactura=mFacturas.inicializarFactura();
		}
	
	public void actionListenerInsertarFactura() {
		try {
		mFacturas.insertarFactura(nuevaFactura);
		JSFUtil.crearMensajeINFO("Factura realizada");
		listaFacturas=mFacturas.findAllFacturas();
		nuevaFactura=mFacturas.inicializarFactura();
		}catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	

	public VenFactura getNuevaFactura() {
		return nuevaFactura;
	}

	public void setNuevaFactura(VenFactura nuevaFactura) {
		this.nuevaFactura = nuevaFactura;
	}

	public List<VenFactura> getListaFacturas() {
		return listaFacturas;
	}

	public void setListaFacturas(List<VenFactura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}
	

}