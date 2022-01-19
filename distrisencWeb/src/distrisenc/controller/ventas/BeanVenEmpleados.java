package distrisenc.controller.ventas;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import distrisenc.controller.JSFUtil;
import distrisenc.model.core.entities.SegUsuario;
import distrisenc.model.core.entities.VenProforma;
import distrisenc.model.ventas.managers.ManagerVentas;

@Named
@SessionScoped
public class BeanVenEmpleados implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerVentas mVentas;
	private List<VenProforma> listaPedidos;

	public BeanVenEmpleados() {
		
	}

	@PostConstruct
	public void inicializacion() {
		System.out.println("BeanVenCliente inicializado...");
		listaPedidos = mVentas.findAllProformas();
	}

	public void actionListenerPagar50(VenProforma proforma, String obs) {
		try {
			mVentas.autorizarProforma(proforma, obs);
			JSFUtil.crearMensajeINFO("Enviada a produccion.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerPagarTotal(VenProforma proforma, String obs, int usuario) {
		try {
			mVentas.confirmarPagoTotal(proforma, obs , usuario);
			JSFUtil.crearMensajeINFO("Pago total.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public List<VenProforma> getlistaPedidos() {
		return listaPedidos;
	}

	public void setlistaPedidos(List<VenProforma> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

}
