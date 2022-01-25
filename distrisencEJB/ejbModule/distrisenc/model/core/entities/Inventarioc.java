package distrisenc.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the inventarioc database table.
 * 
 */
@Entity
@Table(name="inventarioc")
@NamedQuery(name="Inventarioc.findAll", query="SELECT i FROM Inventarioc i")
public class Inventarioc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="inv_id", unique=true, nullable=false)
	private Integer invId;

	@Column(name="id_seg_usuario")
	private Integer idSegUsuario;

	@Temporal(TemporalType.DATE)
	@Column(name="inv_fecha")
	private Date invFecha;

	public Inventarioc() {
	}

	public Integer getInvId() {
		return this.invId;
	}

	public void setInvId(Integer invId) {
		this.invId = invId;
	}

	public Integer getIdSegUsuario() {
		return this.idSegUsuario;
	}

	public void setIdSegUsuario(Integer idSegUsuario) {
		this.idSegUsuario = idSegUsuario;
	}

	public Date getInvFecha() {
		return this.invFecha;
	}

	public void setInvFecha(Date invFecha) {
		this.invFecha = invFecha;
	}

}