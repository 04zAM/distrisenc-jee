package distrisenc.controller.ventas;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import distrisenc.controller.JSFUtil;
import distrisenc.model.core.entities.PrdProducto;
import distrisenc.model.core.entities.SegUsuario;
import distrisenc.model.core.entities.VenFactura;
import distrisenc.model.core.entities.VenProforma;
import distrisenc.model.ventas.managers.ManagerVentas;

@Named
@SessionScoped
public class BeanVenEmpleados implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerVentas mVentas;
	private List<VenProforma> listaPedidos;
	private List<VenFactura> listaFacturas;
	private List<PrdProducto> listaProductos;
	private String observacion;

	public BeanVenEmpleados() {

	}

	@PostConstruct
	public void inicializacion() {
		System.out.println("BeanVenCliente inicializado...");
		listaPedidos = mVentas.findAllPedidos();
		observacion = "Ninguna";
	}

	public String actionCargarMenu() {
		listaPedidos = mVentas.findAllPedidos();
		return "menu";
	}
	
	public String actionCargarMenuPedidos() {
		listaPedidos = mVentas.findAllProformas();
		return "pedidos?faces-redirect=true";
	}

	public String actionCargarMenuFacturas() {
		listaFacturas = mVentas.findAllFacturas();
		return "facturas?faces-redirect=true";
	}
	
	public String actionCargarFormFactura() {
		listaProductos = mVentas.findAllProductos();
		return "formulario?faces-redirect=true";
	}

	public void actionListenerAutorizarProforma(VenProforma proforma) {
		try {
			mVentas.autorizarProforma(proforma, observacion);
			JSFUtil.crearMensajeINFO("Se ha generado una orden de la proforma: " + proforma.getIdProforma()
			+ ". \nCon observacion: " + observacion);
			listaPedidos = mVentas.findAllPedidos();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerPagarProforma(VenProforma proforma, int usuario) {
		try {
			mVentas.confirmarPagoTotal(proforma, observacion, usuario);
			JSFUtil.crearMensajeINFO("Se ha generado una factura de la proforma: " + proforma.getIdProforma()
					+ ". \nCon observacion: " + observacion);
			listaPedidos = mVentas.findAllPedidos();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<VenProforma> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(List<VenProforma> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	public List<VenFactura> getListaFacturas() {
		return listaFacturas;
	}

	public void setListaFacturas(List<VenFactura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

}
