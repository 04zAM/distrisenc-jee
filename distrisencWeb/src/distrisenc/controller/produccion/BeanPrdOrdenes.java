package distrisenc.controller.produccion;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import distrisenc.controller.JSFUtil;
import distrisenc.model.core.entities.VenProforma;
import distrisenc.model.ventas.managers.ManagerVentas;

@Named
@SessionScoped
public class BeanPrdOrdenes implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerVentas mVentas;
	private List<VenProforma> listaPedidos;

	public BeanPrdOrdenes() {
		
	}

	@PostConstruct
	public void inicializacion() {
		System.out.println("BeanVenCliente inicializado...");
		listaPedidos = mVentas.findAllProformas();
	}

	public void actionListenerPagar50(VenProforma proforma, String obs) {
		try {
			mVentas.confirmarPago50(proforma, obs);
			JSFUtil.crearMensajeINFO("Pago 50%.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerPagarTotal(VenProforma proforma) {
		try {
			mVentas.confirmarPagoTotal(proforma);
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
