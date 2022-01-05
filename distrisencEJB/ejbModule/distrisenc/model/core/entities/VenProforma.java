package distrisenc.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ven_proforma database table.
 * 
 */
@Entity
@Table(name="ven_proforma")
@NamedQuery(name="VenProforma.findAll", query="SELECT v FROM VenProforma v")
public class VenProforma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_proforma", unique=true, nullable=false)
	private Integer idProforma;

	@Column(nullable=false, length=50)
	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	@Column(length=250)
	private String observaciones;

	@Column(nullable=false, precision=7, scale=2)
	private BigDecimal total;

	//bi-directional many-to-one association to PrdOrden
	@OneToMany(mappedBy="venProforma")
	private List<PrdOrden> prdOrdens;

	//bi-directional many-to-one association to VenDetProforma
	@OneToMany(mappedBy="venProforma")
	private List<VenDetProforma> venDetProformas;

	//bi-directional many-to-one association to SegUsuario
	@ManyToOne
	@JoinColumn(name="id_cliente", nullable=false)
	private SegUsuario segUsuario;

	public VenProforma() {
	}

	public Integer getIdProforma() {
		return this.idProforma;
	}

	public void setIdProforma(Integer idProforma) {
		this.idProforma = idProforma;
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

	public List<PrdOrden> getPrdOrdens() {
		return this.prdOrdens;
	}

	public void setPrdOrdens(List<PrdOrden> prdOrdens) {
		this.prdOrdens = prdOrdens;
	}

	public PrdOrden addPrdOrden(PrdOrden prdOrden) {
		getPrdOrdens().add(prdOrden);
		prdOrden.setVenProforma(this);

		return prdOrden;
	}

	public PrdOrden removePrdOrden(PrdOrden prdOrden) {
		getPrdOrdens().remove(prdOrden);
		prdOrden.setVenProforma(null);

		return prdOrden;
	}

	public List<VenDetProforma> getVenDetProformas() {
		return this.venDetProformas;
	}

	public void setVenDetProformas(List<VenDetProforma> venDetProformas) {
		this.venDetProformas = venDetProformas;
	}

	public VenDetProforma addVenDetProforma(VenDetProforma venDetProforma) {
		getVenDetProformas().add(venDetProforma);
		venDetProforma.setVenProforma(this);

		return venDetProforma;
	}

	public VenDetProforma removeVenDetProforma(VenDetProforma venDetProforma) {
		getVenDetProformas().remove(venDetProforma);
		venDetProforma.setVenProforma(null);

		return venDetProforma;
	}

	public SegUsuario getSegUsuario() {
		return this.segUsuario;
	}

	public void setSegUsuario(SegUsuario segUsuario) {
		this.segUsuario = segUsuario;
	}

}