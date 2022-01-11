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

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_det_factura", unique=true, nullable=false)
	private Integer idDetFactura;

	@Column(nullable=false)
	private Integer cantidad;

	@Column(nullable=false, precision=131089)
	private BigDecimal total;

	//bi-directional many-to-one association to PrdProducto
	@ManyToOne
	@JoinColumn(name="id_producto", nullable=false)
	private PrdProducto prdProducto;

	//bi-directional many-to-one association to VenFactura
	@ManyToOne
	@JoinColumn(name="id_factura", nullable=false)
	private VenFactura venFactura;

	public VenDetFactura() {
	}

	public Integer getIdDetFactura() {
		return this.idDetFactura;
	}

	public void setIdDetFactura(Integer idDetFactura) {
		this.idDetFactura = idDetFactura;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public PrdProducto getPrdProducto() {
		return this.prdProducto;
	}

	public void setPrdProducto(PrdProducto prdProducto) {
		this.prdProducto = prdProducto;
	}

	public VenFactura getVenFactura() {
		return this.venFactura;
	}

	public void setVenFactura(VenFactura venFactura) {
		this.venFactura = venFactura;
	}

}