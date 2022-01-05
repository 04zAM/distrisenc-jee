package distrisenc.controller.ventas;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import distrisenc.controller.JSFUtil;
import distrisenc.model.core.entities.PrdProducto;
import distrisenc.model.core.entities.SegUsuario;
import distrisenc.model.core.entities.VenDetProforma;
import distrisenc.model.core.entities.VenProforma;
import distrisenc.model.ventas.managers.ManagerVentas;

@Named
@SessionScoped
public class BeanVenClientes implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerVentas mVentas;
	private List<PrdProducto> listaProductos;
	private List<VenDetProforma> listaProductosCarrito;
	private List<VenProforma> listaPedidos;
	private PrdProducto productoSeleccionado;
	private int cantidad;

	public BeanVenClientes() {
		cantidad = 1;
	}

	@PostConstruct
	public void inicializacion() {
		System.out.println("BeanVenCliente inicializado...");
		listaProductos = mVentas.findAllProductos();
	}

	public String actionCargarMenuCarrito() {
		listaProductosCarrito = mVentas.findCarritoProducts();
		return "carrito?faces-redirect=true";
	}

	public String actionCargarMenuPedidos() {
		listaPedidos = mVentas.findAllProformas();
		return "pedidos?faces-redirect=true";
	}

	// Agrega a la cesta
	public void actionListenerAgregarProducto(PrdProducto nuevoProducto) {
		try {
			mVentas.agregarDetalle(nuevoProducto, getCantidad());
			setCantidad(1);
			System.out.println("Se ha agregado " + cantidad + " producto a la cesta: " + nuevoProducto.getNombre());
			JSFUtil.crearMensajeINFO("Producto agregado al carrito.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	// Eliminar de la cesta
	public void actionListenerEliminarProducto(VenDetProforma detalle) {
		try {
			mVentas.eliminarDetalle(detalle);
			listaProductosCarrito = mVentas.findCarritoProducts();
			JSFUtil.crearMensajeINFO("Producto fuera del carrito");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerConfirmarPedido(int usuario, String obs) {
		try {
			mVentas.confirmarPedido(usuario, obs);
			JSFUtil.crearMensajeINFO("Pedido confirmado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerConfirmarEntrega(VenProforma proforma) {
		try {
			mVentas.confirmarEntrega(proforma);
			JSFUtil.crearMensajeINFO("Entrega del pedido confirmada.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public List<PrdProducto> getlistaProductos() {
		return listaProductos;
	}

	public void setlistaProductos(List<PrdProducto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public PrdProducto getproductoSeleccionado() {
		return productoSeleccionado;
	}

	public void setproductoSeleccionado(PrdProducto productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}

	public List<VenDetProforma> getListaProductosCarrito() {
		return listaProductosCarrito;
	}

	public void setListaProductosCarrito(List<VenDetProforma> listaProductosCarrito) {
		this.listaProductosCarrito = listaProductosCarrito;
	}

	public List<VenProforma> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(List<VenProforma> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
