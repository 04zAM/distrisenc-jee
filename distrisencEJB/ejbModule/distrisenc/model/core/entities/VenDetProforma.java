package distrisenc.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ven_det_proforma database table.
 * 
 */
@Entity
@Table(name="ven_det_proforma")
@NamedQuery(name="VenDetProforma.findAll", query="SELECT v FROM VenDetProforma v")
public class VenDetProforma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_det_proforma", unique=true, nullable=false)
	private Integer idDetProforma;

	@Column(nullable=false)
	private Integer cantidad;

	@Column(nullable=false, precision=7, scale=2)
	private BigDecimal total;

	//bi-directional many-to-one association to PrdProducto
	@ManyToOne
	@JoinColumn(name="id_producto", nullable=false)
	private PrdProducto prdProducto;

	//bi-directional many-to-one association to VenProforma
	@ManyToOne
	@JoinColumn(name="id_proforma", nullable=false)
	private VenProforma venProforma;

	public VenDetProforma() {
	}

	public Integer getIdDetProforma() {
		return this.idDetProforma;
	}

	public void setIdDetProforma(Integer idDetProforma) {
		this.idDetProforma = idDetProforma;
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

	public VenProforma getVenProforma() {
		return this.venProforma;
	}

	public void setVenProforma(VenProforma venProforma) {
		this.venProforma = venProforma;
	}

}