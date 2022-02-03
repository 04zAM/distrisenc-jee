package distrisenc.controller.ventas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import distrisenc.controller.JSFUtil;
import distrisenc.model.core.entities.PrdProducto;
import distrisenc.model.core.entities.SegUsuario;
import distrisenc.model.core.entities.VenDetFactura;
import distrisenc.model.core.entities.VenFactura;
import distrisenc.model.core.entities.VenProforma;
import distrisenc.model.ventas.managers.ManagerVentas;

@Named
@SessionScoped
public class BeanVenEmpleados implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerVentas mVentas;
	private List<VenProforma> listaPedidos;
	private VenDetFactura nuevoDetalle;
	private List<VenFactura> listaFacturas;
	private VenFactura nuevaFactura;
	private List<PrdProducto> listaProductos;
	private List<VenDetFactura> listaFacDetalles;
	private List<SegUsuario> listaClientes;
	private String observacion;

	public BeanVenEmpleados() {

	}

	@PostConstruct
	public void inicializacion() {
		System.out.println("BeanVenCliente inicializado...");
		listaPedidos = mVentas.findAllPedidos();
		observacion = "Ninguna";
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String actionCargarMenu() {
		listaPedidos = mVentas.findAllPedidos();
		return "menu";
	}

	public String actionCargarMenuPedidos() {
		listaPedidos = mVentas.findAllProformas();
		return "pedidos?faces-redirect=true";
	}

	public String actionCargarMenuFacturas() {
		listaFacturas = mVentas.findAllFacturas();
		return "facturas?faces-redirect=true";
	}

	public String actionCargarFormFactura() {
		nuevaFactura = new VenFactura();
		nuevaFactura.setObservaciones(observacion);
		nuevaFactura.setSegUsuario(new SegUsuario());
		nuevoDetalle = new VenDetFactura();
		nuevoDetalle.setPrdProducto(new PrdProducto());
		listaProductos = mVentas.findAllProductos();
		listaClientes = mVentas.findAllClientes();
		listaFacDetalles = new ArrayList<VenDetFactura>();
		return "formulario?faces-redirect=true";
	}

	public String actionImprimirFactura() {
		JSFUtil.crearMensajeWARN("Guarde la Factura primero");
		Map<String, Object> parametros = new HashMap<String, Object>();
		/*
		 * parametros.put("p_titulo_principal",p_titulo_principal);
		 * parametros.put("p_titulo",p_titulo);
		 */
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		String ruta = servletContext.getRealPath("ventas/vendedor/venta.jasper");
		System.out.println(ruta);
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename=comprobante.pdf");
		response.setContentType("application/pdf");
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/distrisenc", "postgres",
					"root");
			JasperPrint impresion = JasperFillManager.fillReport(ruta, parametros, connection);
			JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());
			context.getApplication().getStateManager().saveView(context);
			JSFUtil.crearMensajeINFO("Reporte generado.");
			context.responseComplete();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
		return "";
	}

	public void actionListenerGuardarFactura() {
		try {
			nuevaFactura.setVenDetFacturas(listaFacDetalles);
			nuevaFactura.setObservaciones(observacion);
			mVentas.guardarFacturaVenta(nuevaFactura);
			listaFacturas = mVentas.findAllFacturas();
			nuevaFactura = new VenFactura();
			nuevaFactura.setSegUsuario(new SegUsuario());
			listaFacDetalles = new ArrayList<VenDetFactura>();
			JSFUtil.crearMensajeINFO("Factura Guardada Correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerSeleccionarProducto() {
		nuevoDetalle.setCantidad(1);
	}

	public void actionListenerSeleccionarCliente() {
		JSFUtil.crearMensajeINFO("Cliente " + nuevaFactura.getSegUsuario().getIdSegUsuario() + " seleccionado");
	}

	public void actionListenerAgregarDetalle() {
		try {
			nuevoDetalle.setTotal(
					nuevoDetalle.getPrdProducto().getVenta().multiply(new BigDecimal(nuevoDetalle.getCantidad())));
			JSFUtil.crearMensajeINFO("Detalle agregado");
			listaFacDetalles.add(nuevoDetalle);
			nuevoDetalle = new VenDetFactura();
			calcularTotalFactura();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public void calcularTotalFactura() {
		BigDecimal totalFactura = new BigDecimal(0);
		for (VenDetFactura detalle : listaFacDetalles) {
			totalFactura = totalFactura.add(detalle.getTotal());
		}
		nuevaFactura.setTotal(totalFactura);
	}

	public void actionListenerQuitarDetalle(VenDetFactura detalle) {
		try {
			listaFacDetalles.remove(detalle);
			calcularTotalFactura();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerAutorizarProforma(VenProforma proforma) {
		try {
			mVentas.autorizarProforma(proforma, observacion);
			JSFUtil.crearMensajeINFO("Se ha generado una orden de la proforma: " + proforma.getIdProforma()
					+ ". \nCon observacion: " + observacion);
			listaPedidos = mVentas.findAllPedidos();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerPagarProforma(VenProforma proforma, int usuario) {
		try {
			mVentas.confirmarPagoTotal(proforma, observacion, usuario);
			JSFUtil.crearMensajeINFO("Se ha generado una factura de la proforma: " + proforma.getIdProforma()
					+ ". \nCon observacion: " + observacion);
			listaPedidos = mVentas.findAllPedidos();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<VenProforma> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(List<VenProforma> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	public List<VenFactura> getListaFacturas() {
		return listaFacturas;
	}

	public VenFactura getNuevaFactura() {
		return nuevaFactura;
	}

	public void setNuevaFactura(VenFactura nuevaFactura) {
		this.nuevaFactura = nuevaFactura;
	}

	public void setListaFacturas(List<VenFactura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}

	public List<PrdProducto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<PrdProducto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public List<VenDetFactura> getListaFacDetalles() {
		return listaFacDetalles;
	}

	public void setListaFacDetalles(List<VenDetFactura> listaFacDetalles) {
		this.listaFacDetalles = listaFacDetalles;
	}

	public VenDetFactura getNuevoDetalle() {
		return nuevoDetalle;
	}

	public void setNuevoDetalle(VenDetFactura nuevoDetalle) {
		this.nuevoDetalle = nuevoDetalle;
	}

	public List<SegUsuario> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<SegUsuario> listaClientes) {
		this.listaClientes = listaClientes;
	}
}
