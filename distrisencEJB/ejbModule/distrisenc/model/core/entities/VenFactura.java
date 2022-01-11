package distrisenc.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ven_factura database table.
 * 
 */
@Entity
@Table(name="ven_factura")
@NamedQuery(name="VenFactura.findAll", query="SELECT v FROM VenFactura v")
public class VenFactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_factura", unique=true, nullable=false)
	private Integer idFactura;

	@Column(nullable=false, length=2147483647)
	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	@Column(length=2147483647)
	private String observaciones;

	@Column(nullable=false, precision=131089)
	private BigDecimal total;

	//bi-directional many-to-one association to VenDetFactura
	@OneToMany(mappedBy="venFactura")
	private List<VenDetFactura> venDetFacturas;

	//bi-directional many-to-one association to SegUsuario
	@ManyToOne
	@JoinColumn(name="id_seg_usuario", nullable=false)
	private SegUsuario segUsuario;

	public VenFactura() {
	}

	public Integer getIdFactura() {
		return this.idFactura;
	}

	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<VenDetFactura> getVenDetFacturas() {
		return this.venDetFacturas;
	}

	public void setVenDetFacturas(List<VenDetFactura> venDetFacturas) {
		this.venDetFacturas = venDetFacturas;
	}

	public VenDetFactura addVenDetFactura(VenDetFactura venDetFactura) {
		getVenDetFacturas().add(venDetFactura);
		venDetFactura.setVenFactura(this);

		return venDetFactura;
	}

	public VenDetFactura removeVenDetFactura(VenDetFactura venDetFactura) {
		getVenDetFacturas().remove(venDetFactura);
		venDetFactura.setVenFactura(null);

		return venDetFactura;
	}

	public SegUsuario getSegUsuario() {
		return this.segUsuario;
	}

	public void setSegUsuario(SegUsuario segUsuario) {
		this.segUsuario = segUsuario;
	}

}