package distrisenc.controller.administracion;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import distrisenc.controller.JSFUtil;
import distrisenc.model.core.entities.VenProforma;
import distrisenc.model.alquileres.managers.ManagerAlquileres;

@Named
@SessionScoped
public class BeanAlqAdministrador implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerAlquileres mVentas;
	private List<VenProforma> listaPedidos;

	public BeanAlqAdministrador() {
		
	}

	@PostConstruct
	public void inicializacion() {
		System.out.println("BeanVenCliente inicializado...");
		listaPedidos = mVentas.findAllProformas();
	}
	
	public void actionListenerAprobarAlquiler(VenProforma proforma) {
		try {
			mVentas.confirmarPagoTotal(proforma);
			JSFUtil.crearMensajeINFO("Alquiler Aprobado.");
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
