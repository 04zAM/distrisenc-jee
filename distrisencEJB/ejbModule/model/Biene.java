package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the bienes database table.
 * 
 */
@Entity
@Table(name="bienes")
@NamedQuery(name="Biene.findAll", query="SELECT b FROM Biene b")
public class Biene implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;

	@Column(name="correo_propietario")
	private String correoPropietario;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_compra")
	private Date fechaCompra;

	@Column(name="nombre_propietario")
	private String nombrePropietario;

	private BigDecimal tamanio;

	public Biene() {
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getCorreoPropietario() {
		return this.correoPropietario;
	}

	public void setCorreoPropietario(String correoPropietario) {
		this.correoPropietario = correoPropietario;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaCompra() {
		return this.fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public String getNombrePropietario() {
		return this.nombrePropietario;
	}

	public void setNombrePropietario(String nombrePropietario) {
		this.nombrePropietario = nombrePropietario;
	}

	public BigDecimal getTamanio() {
		return this.tamanio;
	}

	public void setTamanio(BigDecimal tamanio) {
		this.tamanio = tamanio;
	}

}