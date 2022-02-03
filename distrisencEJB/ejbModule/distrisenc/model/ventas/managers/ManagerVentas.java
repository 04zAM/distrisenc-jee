package distrisenc.model.ventas.managers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import distrisenc.model.auditoria.managers.ManagerAuditoria;
import distrisenc.model.core.entities.PrdOrden;
import distrisenc.model.core.entities.PrdProducto;
import distrisenc.model.core.entities.SegUsuario;
import distrisenc.model.core.entities.VenDetFactura;
import distrisenc.model.core.entities.VenDetProforma;
import distrisenc.model.core.entities.VenFactura;
import distrisenc.model.core.entities.VenProforma;
import distrisenc.model.core.managers.ManagerDAO;
import distrisenc.model.core.utils.ModelUtil;
import distrisenc.model.ventas.dtos.CarritoDTO;

/**
 * Implementa la logica de manejo de usuarios y autenticacion. Funcionalidades
 * principales:
 * <ul>
 * <li>Verificacion de credenciales de usuario.</li>
 * <li>Asignacion de modulos a un usuario.</li>
 * </ul>
 * 
 * @author evasquez
 */
@Stateless
@LocalBean
public class ManagerVentas {
	@EJB
	private ManagerDAO mDAO;
	@EJB
	private ManagerAuditoria mAuditoria;

	private CarritoDTO carrito;

	/**
	 * Default constructor.
	 */
	public ManagerVentas() {
		carrito = new CarritoDTO();
	}

	public List<PrdProducto> findAllProductos() {
		return mDAO.findWhere(PrdProducto.class, "o.vendible=true", "o.nombre");
	}

	public List<SegUsuario> findAllClientes() {
		return mDAO.findAll(SegUsuario.class, "idSegUsuario");
	}

	public List<VenFactura> findAllFacturas() {
		return mDAO.findAll(VenFactura.class, "idFactura", false);
	}

	public List<VenProforma> findAllProformas() {
		return mDAO.findAll(VenProforma.class, "idProforma", false);
	}

	public List<VenProforma> findAllPedidos() {
		return mDAO.findWhere(VenProforma.class, "o.estado!='Pagado' AND o.estado!='Entregado'", "o.idProforma desc");
	}

	public List<VenDetProforma> findCarritoProducts() {
		return carrito.getListaDetalles();
	}

	public void guardarFacturaVenta(VenFactura nuevaFactura) throws Exception {
		VenFactura factura = new VenFactura();
		factura.setEstado("Activa");
		factura.setFecha(new Date());
		factura.setObservaciones(nuevaFactura.getObservaciones());
		SegUsuario cliente = (SegUsuario) mDAO.findById(SegUsuario.class,
				nuevaFactura.getSegUsuario().getIdSegUsuario());
		factura.setSegUsuario(cliente);
		factura.setTotal(nuevaFactura.getTotal());
		factura.setVenDetFacturas(nuevaFactura.getVenDetFacturas());
		mDAO.insertar(factura);
		for (VenDetFactura detalle : factura.getVenDetFacturas()) {
			detalle.setVenFactura(factura);
			mDAO.insertar(detalle);
		}
	}

	public void agregarDetalle(VenDetProforma nuevoDetalle) throws Exception {
		boolean esNuevo = true;
		VenDetProforma detalle = new VenDetProforma();
		PrdProducto producto = new PrdProducto();
		producto = nuevoDetalle.getPrdProducto();
		detalle.setPrdProducto(producto);
		int cantidad = nuevoDetalle.getCantidad();
		producto.setStock(producto.getStock().subtract(new BigDecimal(cantidad)));
		mDAO.actualizar(producto);
		detalle.setCantidad(cantidad);
		detalle.setTotal(nuevoDetalle.getTotal());
		if (!carrito.getListaDetalles().isEmpty()) {
			for (VenDetProforma det : carrito.getListaDetalles()) {
				if (det.getPrdProducto().equals(detalle.getPrdProducto())) {
					esNuevo = false;
					det.setCantidad(det.getCantidad() + detalle.getCantidad());
					det.setTotal(det.getTotal().add(detalle.getTotal()));
					break;
				}
			}
		}
		if (esNuevo) {
			System.out.println("Se ha agregado " + nuevoDetalle.getCantidad() + " producto/s "
					+ nuevoDetalle.getPrdProducto().getNombre() + " a la cesta");
			carrito.getListaDetalles().add(detalle);
			carrito.setTotal(carrito.getTotal().add(detalle.getTotal()));
		}
	}

	public void eliminarDetalle(VenDetProforma detalle) throws Exception {
		carrito.getListaDetalles().remove(carrito.getListaDetalles().indexOf(detalle));
	}

	public void confirmarPedido(int cliente, String obs) throws Exception {
		SegUsuario usuario = (SegUsuario) mDAO.findById(SegUsuario.class, cliente);
		VenProforma proforma = new VenProforma();
		proforma.setVenDetProformas(carrito.getListaDetalles());
		proforma.setEstado("Confirmado");
		proforma.setTotal(carrito.getTotal());
		proforma.setSegUsuario(usuario);
		proforma.setFecha(new Date());
		proforma.setObservaciones(obs);
		mDAO.insertar(proforma);
		for (VenDetProforma det : carrito.getListaDetalles()) {
			det.setVenProforma(proforma);
			mDAO.insertar(det);
		}
	}

	public void autorizarProforma(VenProforma p, String obs) throws Exception {
		VenProforma proforma = (VenProforma) mDAO.findById(VenProforma.class, p.getIdProforma());
		proforma.setEstado("Autorizado");
		mDAO.actualizar(proforma);
		System.out.println("Proforma: " + proforma.getIdProforma() + " " + proforma.getEstado() + "...");
		PrdOrden orden = new PrdOrden();
		orden.setEstado("Pendiente");
		orden.setFecha(new Date());
		orden.setObservaciones(obs);
		orden.setVenProforma(proforma);
		mDAO.insertar(orden);
	}

	public void confirmarPagoTotal(VenProforma p, String obs, int usuario) throws Exception {
		SegUsuario vendedor = (SegUsuario) mDAO.findById(SegUsuario.class, usuario);
		VenProforma proforma = (VenProforma) mDAO.findById(VenProforma.class, p.getIdProforma());
		proforma.setEstado("Pagado");
		mDAO.actualizar(proforma);
		System.out.println("Proforma: " + proforma.getIdProforma() + " " + proforma.getEstado() + "...");
		VenFactura factura = new VenFactura();
		factura.setEstado("Activa");
		factura.setFecha(new Date());
		factura.setObservaciones(obs);
		factura.setSegUsuario(vendedor);
		factura.setTotal(proforma.getTotal());
		List<VenDetFactura> detallesFactura = new ArrayList<>();
		for (int i = 0; i < detallesFactura.size(); i++) {
			VenDetFactura detalleFactura = new VenDetFactura();
			detalleFactura.setCantidad(proforma.getVenDetProformas().get(i).getCantidad());
			detalleFactura.setPrdProducto(proforma.getVenDetProformas().get(i).getPrdProducto());
			detalleFactura.setTotal(proforma.getVenDetProformas().get(i).getTotal());
			detalleFactura.setVenFactura(factura);
			detallesFactura.set(i, detalleFactura);
			mDAO.insertar(detalleFactura);
		}
		factura.setVenDetFacturas(detallesFactura);
		mDAO.insertar(factura);
	}

	public void confirmarEntrega(VenProforma proforma) throws Exception {
		proforma.setEstado("Entregado");
		mDAO.actualizar(proforma);
	}

}
