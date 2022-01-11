package distrisenc.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ven_factura database table.
 * 
 */
@Entity
@Table(name="ven_factura")
@NamedQuery(name="VenFactura.findAll", query="SELECT v FROM VenFactura v")
public class VenFactura implements Serializable {
	private static final long serialVersionUID = 1L;


	@Column(nullable=false, length=2147483647)
	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_factura", unique=true, nullable=false)
	private Integer idFactura;

	@Column(name="id_seg_usuario", nullable=false)
	private Integer idSegUsuario;

	@Column(length=2147483647)
	private String observaciones;

	@Column(nullable=false, precision=131089)
	private BigDecimal total;

	public VenFactura() {
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

	public Integer getIdFactura() {
		return this.idFactura;
	}

	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}

	public Integer getIdSegUsuario() {
		return this.idSegUsuario;
	}

	public void setIdSegUsuario(Integer idSegUsuario) {
		this.idSegUsuario = idSegUsuario;
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


	
	
}