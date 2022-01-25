package distrisenc.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the inventario_ca database table.
 * 
 */
@Entity
@Table(name="inventario_ca")
@NamedQuery(name="InventarioCa.findAll", query="SELECT i FROM InventarioCa i")
public class InventarioCa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_inv", unique=true, nullable=false)
	private Integer idInv;

	private Integer cantidad;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(length=50)
	private String nombre;

	@Column(length=50)
	private String tipo;

	//bi-directional many-to-one association to PrdProducto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private PrdProducto prdProducto;

	public InventarioCa() {
	}

	public Integer getIdInv() {
		return this.idInv;
	}

	public void setIdInv(Integer idInv) {
		this.idInv = idInv;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public PrdProducto getPrdProducto() {
		return this.prdProducto;
	}

	public void setPrdProducto(PrdProducto prdProducto) {
		this.prdProducto = prdProducto;
	}

}