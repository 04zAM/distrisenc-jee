package distrisenc.controller.inventario;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.MatchMode;

import distrisenc.controller.JSFUtil;
import distrisenc.model.core.entities.InventarioCa;
import distrisenc.model.core.entities.PrdProducto;
import distrisenc.model.inventario.managers.ManagerInventario;
import distrisenc.model.produccion.managers.ManagerProduccion;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;




@Named
@SessionScoped
public class BeanInvBodeguero implements Serializable {
	
	@EJB
	private ManagerInventario mInventario;
	private InventarioCa nuevoInventario;
	private List<InventarioCa> listaInventario;
	private List<PrdProducto> ListaProductos;
	
	public BeanInvBodeguero() {
		
	}
	
	@PostConstruct
	public void inicializar() {
		listaInventario=mInventario.findAllInventarioCas();
		nuevoInventario=new InventarioCa();
		ListaProductos=mInventario.findAllPrdProductos();
	}
	
	public void actionListenerInsertarInven() {
		try {
			mInventario.insertarInventario(nuevoInventario);
			JSFUtil.crearMensajeINFO("Ingreso Correcto");
			listaInventario=mInventario.findAllInventarioCas();
			ListaProductos=mInventario.findAllPrdProductos();
		}catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public InventarioCa getNuevoInventario() {
		return nuevoInventario;
	}

	public void setNuevoInventario(InventarioCa nuevoInventario) {
		this.nuevoInventario = nuevoInventario;
	}

	public List<InventarioCa> getListaInventario() {
		return listaInventario;
	}

	public void setListaInventario(List<InventarioCa> listaInventario) {
		this.listaInventario = listaInventario;
	}

	public List<PrdProducto> getListaProductos() {
		return ListaProductos;
	}

	public void setListaProductos(List<PrdProducto> listaProductos) {
		ListaProductos = listaProductos;
	}

	public String actionReporte(){
		Map<String,Object> parametros=new HashMap<String,Object>();
		/*parametros.put("p_titulo_principal",p_titulo_principal);
		parametros.put("p_titulo",p_titulo);*/
		FacesContext context=FacesContext.getCurrentInstance();
		ServletContext servletContext=(ServletContext)context.getExternalContext().getContext();
		String ruta=servletContext.getRealPath("/inventario/bodeguero/reporteInv.jasper");
		System.out.println(ruta);
		HttpServletResponse response=(HttpServletResponse)context.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename=reporte.pdf");
		response.setContentType("application/pdf");
		try {
		Class.forName("org.postgresql.Driver");
		Connection connection = null;
		connection = DriverManager.getConnection(
		"jdbc:postgresql://localhost:5432/distrisenc","postgres", "root");
		JasperPrint impresion=JasperFillManager.fillReport(ruta, parametros,connection);
		JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());
		context.getApplication().getStateManager().saveView ( context ) ;
		System.out.println("reporte generado.");
		context.responseComplete();
		} catch (Exception e) {
		JSFUtil.crearMensajeERROR(e.getMessage());
		e.printStackTrace();
		}
		return "";
		}
   
	
	
	
	

	
	
	

}
	


	
	



