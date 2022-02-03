package distrisenc.controller.produccion;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


import distrisenc.model.abastecimiento.managers.*;
import distrisenc.model.core.entities.AbsMaterialPrima;


@Named
@SessionScoped
public class BeanAbsMateriaPrima implements Serializable {

	@EJB
	private ManagerAbastecimiento mAbastecimiento;
	
	private List<AbsMaterialPrima> listaMaterial;
	private AbsMaterialPrima nuevoMaterial;
	private  AbsMaterialPrima edicionMaterial;
	
	public BeanAbsMateriaPrima() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void inicializacion() {
		listaMaterial = mAbastecimiento.findAllAbsMateriaPrima();
		nuevoMaterial = new AbsMaterialPrima();
	}
	
	public void insertarMaterilPrimo() throws Exception {
		nuevoMaterial.setMatEstado(true);
		mAbastecimiento.createAbsMateriaPrima(nuevoMaterial);
		listaMaterial = mAbastecimiento.findAllAbsMateriaPrima();
		nuevoMaterial = new AbsMaterialPrima();
	}
	
	public void actionSeleccionarEdicionMaterial(AbsMaterialPrima material) {
		edicionMaterial=material;
	}
	
	//Actualizar
	
	public void actualizarRegistroMaterial() {
		try {
			mAbastecimiento.updateRegistroCliente(edicionMaterial);
			//JSFUtil.crearMensajeINFO("Cliente actualizado.");
			listaMaterial = mAbastecimiento.findAllAbsMateriaPrima();
			edicionMaterial=new AbsMaterialPrima();
		} catch (Exception e) {
			//JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	//Eliminar
	public void eliminarRegistroMaterial(AbsMaterialPrima material) throws Exception {
		try {		
			mAbastecimiento.deleteRegistroMateria(material);
			//JSFUtil.crearMensajeINFO("Cliente Eliminado.");
			
			listaMaterial = mAbastecimiento.findAllAbsMateriaPrima();
		} catch (Exception e) {
			//JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	

	public List<AbsMaterialPrima> getListaMaterial() {
		
		return listaMaterial;
	}

	public void setListaMaterial(List<AbsMaterialPrima> listaMaterial) {
		this.listaMaterial = listaMaterial;
	}

	public AbsMaterialPrima getNuevoMaterial() {
		return nuevoMaterial;
	}

	public void setNuevoMaterial(AbsMaterialPrima nuevoMaterial) {
		this.nuevoMaterial = nuevoMaterial;
	}

}