package distrisenc.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the prd_orden database table.
 * 
 */
@Entity
@Table(name="prd_orden")
@NamedQuery(name="PrdOrden.findAll", query="SELECT p FROM PrdOrden p")
public class PrdOrden implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_orden", unique=true, nullable=false)
	private Integer idOrden;

	@Column(nullable=false, length=50)
	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	@Column(length=250)
	private String observaciones;

	//bi-directional many-to-one association to VenProforma
	@ManyToOne
	@JoinColumn(name="id_proforma", nullable=false)
	private VenProforma venProforma;

	public PrdOrden() {
	}

	public Integer getIdOrden() {
		return this.idOrden;
	}

	public void setIdOrden(Integer idOrden) {
		this.idOrden = idOrden;
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

	public VenProforma getVenProforma() {
		return this.venProforma;
	}

	public void setVenProforma(VenProforma venProforma) {
		this.venProforma = venProforma;
	}

}