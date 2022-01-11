package distrisenc.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ven_det_factura database table.
 * 
 */
@Entity
@Table(name="ven_det_factura")
@NamedQuery(name="VenDetFactura.findAll", query="SELECT v FROM VenDetFactura v")
public class VenDetFactura implements Serializable {
	private static final long serialVersionUID = 1L;


	@Column(nullable=false)
	private Integer cantidad;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_det_factura", unique=true,nullable=false)
	private Integer idDetFactura;

	@Column(name="id_factura", nullable=false)
	private Integer idFactura;

	@Column(name="id_producto", nullable=false)
	private Integer idProducto;

	@Column(nullable=false, precision=131089)
	private BigDecimal total;

	public VenDetFactura() {
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getIdDetFactura() {
		return this.idDetFactura;
	}

	public void setIdDetFactura(Integer idDetFactura) {
		this.idDetFactura = idDetFactura;
	}

	public Integer getIdFactura() {
		return this.idFactura;
	}

	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}

	public Integer getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}