package distrisenc.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the prd_producto database table.
 * 
 */
@Entity
@Table(name="prd_producto")
@NamedQuery(name="PrdProducto.findAll", query="SELECT p FROM PrdProducto p")
public class PrdProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_producto", unique=true, nullable=false)
	private Integer idProducto;

	@Column(precision=7, scale=2)
	private BigDecimal costo;

	@Column(nullable=false)
	private Boolean estado;

	@Column(nullable=false, unique=true, length=200)
	private String nombre;

	@Column(nullable=false, precision=7, scale=2)
	private BigDecimal venta;

	//bi-directional many-to-one association to VenDetProforma
	@OneToMany(mappedBy="prdProducto")
	private List<VenDetProforma> venDetProformas;

	public PrdProducto() {
	}

	public Integer getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public BigDecimal getCosto() {
		return this.costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getVenta() {
		return this.venta;
	}

	public void setVenta(BigDecimal venta) {
		this.venta = venta;
	}

	public List<VenDetProforma> getVenDetProformas() {
		return this.venDetProformas;
	}

	public void setVenDetProformas(List<VenDetProforma> venDetProformas) {
		this.venDetProformas = venDetProformas;
	}

	public VenDetProforma addVenDetProforma(VenDetProforma venDetProforma) {
		getVenDetProformas().add(venDetProforma);
		venDetProforma.setPrdProducto(this);

		return venDetProforma;
	}

	public VenDetProforma removeVenDetProforma(VenDetProforma venDetProforma) {
		getVenDetProformas().remove(venDetProforma);
		venDetProforma.setPrdProducto(null);

		return venDetProforma;
	}

}