package distrisenc.model.alquileres.dtos;

import java.util.ArrayList;
import java.util.List;

import distrisenc.model.core.entities.VenDetProforma;

public class CarritoDTO {
	private List<VenDetProforma> listaDetalles;
	private double total;

	public CarritoDTO() {
		this.listaDetalles = new ArrayList<VenDetProforma>();
		total = 0;
	}

	public List<VenDetProforma> getListaDetalles() {
		return listaDetalles;
	}

	public void setListaDetalles(List<VenDetProforma> listaDetalles) {
		this.listaDetalles = listaDetalles;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
