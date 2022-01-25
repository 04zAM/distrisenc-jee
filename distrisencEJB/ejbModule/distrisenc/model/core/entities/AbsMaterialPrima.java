package distrisenc.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the abs_material_prima database table.
 * 
 */
@Entity
@Table(name="abs_material_prima")
@NamedQuery(name="AbsMaterialPrima.findAll", query="SELECT a FROM AbsMaterialPrima a")
public class AbsMaterialPrima implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mat_id", unique=true, nullable=false)
	private Integer matId;

	@Column(name="mat_estado", nullable=false)
	private Boolean matEstado;

	@Column(name="mat_nombre", nullable=false, length=50)
	private String matNombre;

	@Column(name="mat_precio", nullable=false, precision=131089)
	private BigDecimal matPrecio;

	@Column(name="mat_stock", nullable=false)
	private Integer matStock;

	public AbsMaterialPrima() {
	}

	public Integer getMatId() {
		return this.matId;
	}

	public void setMatId(Integer matId) {
		this.matId = matId;
	}

	public Boolean getMatEstado() {
		return this.matEstado;
	}

	public void setMatEstado(Boolean matEstado) {
		this.matEstado = matEstado;
	}

	public String getMatNombre() {
		return this.matNombre;
	}

	public void setMatNombre(String matNombre) {
		this.matNombre = matNombre;
	}

	public BigDecimal getMatPrecio() {
		return this.matPrecio;
	}

	public void setMatPrecio(BigDecimal matPrecio) {
		this.matPrecio = matPrecio;
	}

	public Integer getMatStock() {
		return this.matStock;
	}

	public void setMatStock(Integer matStock) {
		this.matStock = matStock;
	}

}