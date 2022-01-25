package distrisenc.model.ventas.dtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import distrisenc.model.core.entities.VenDetProforma;

public class CarritoDTO {
	private List<VenDetProforma> listaDetalles;
	private BigDecimal total;
	private String observaciones;

	public CarritoDTO() {
		this.listaDetalles = new ArrayList<VenDetProforma>();
		total = new BigDecimal(0);
		observaciones = "Ninguna";
	}

	public List<VenDetProforma> getListaDetalles() {
		return listaDetalles;
	}

	public void setListaDetalles(List<VenDetProforma> listaDetalles) {
		this.listaDetalles = listaDetalles;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal bigDecimal) {
		this.total = bigDecimal;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
