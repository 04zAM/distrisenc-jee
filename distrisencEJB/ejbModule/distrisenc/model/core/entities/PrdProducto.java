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

	private Boolean activo;

	@Column(precision=7, scale=2)
	private BigDecimal costo;

	@Column(nullable=false, length=200)
	private String nombre;

	@Column(nullable=false, precision=131089)
	private BigDecimal stock;

	@Column(nullable=false)
	private Boolean vendible;

	@Column(precision=7, scale=2)
	private BigDecimal venta;

	//bi-directional many-to-one association to VenDetFactura
	@OneToMany(mappedBy="prdProducto")
	private List<VenDetFactura> venDetFacturas;

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

	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public BigDecimal getCosto() {
		return this.costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getStock() {
		return this.stock;
	}

	public void setStock(BigDecimal stock) {
		this.stock = stock;
	}

	public Boolean getVendible() {
		return this.vendible;
	}

	public void setVendible(Boolean vendible) {
		this.vendible = vendible;
	}

	public BigDecimal getVenta() {
		return this.venta;
	}

	public void setVenta(BigDecimal venta) {
		this.venta = venta;
	}

	public List<VenDetFactura> getVenDetFacturas() {
		return this.venDetFacturas;
	}

	public void setVenDetFacturas(List<VenDetFactura> venDetFacturas) {
		this.venDetFacturas = venDetFacturas;
	}

	public VenDetFactura addVenDetFactura(VenDetFactura venDetFactura) {
		getVenDetFacturas().add(venDetFactura);
		venDetFactura.setPrdProducto(this);

		return venDetFactura;
	}

	public VenDetFactura removeVenDetFactura(VenDetFactura venDetFactura) {
		getVenDetFacturas().remove(venDetFactura);
		venDetFactura.setPrdProducto(null);

		return venDetFactura;
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