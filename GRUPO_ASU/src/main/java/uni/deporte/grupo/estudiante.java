package uni.deporte.grupo;

import uni.deporte.datos.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;

public class estudiante {

	private int id;
	private int idcat;
	private String nombre;
	private int cantidad;
	private double precio;
	private String descripcion_pr;
	private int id_pr;
	private int id_cat;
	private double precio_pr;
	private int cantidad_pr;
	
	
	public String getDescripcion_pr() {
		return descripcion_pr;
	}
	public void setDescripcion_pr(String descripcion_pr) {
		this.descripcion_pr = descripcion_pr;
	}
	public int getId_pr() {
		return id_pr;
	}
	public void setId_pr(int id_pr) {
		this.id_pr = id_pr;
	}
	public int getId_cat() {
		return id_cat;
	}
	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}
	public double getPrecio_pr() {
		return precio_pr;
	}
	public void setPrecio_pr(double precio_pr) {
		this.precio_pr = precio_pr;
	}
	public int getCantidad_pr() {
		return cantidad_pr;
	}
	public void setCantidad_pr(int cantidad_pr) {
		this.cantidad_pr = cantidad_pr;
	}
	public estudiante() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdcat() {
		return idcat;
	}
	public void setIdcat(int idcat) {
		this.idcat = idcat;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String consultarTodo()
	{
		String sql="SELECT * FROM tb_producto ORDER BY id_pr";
		Conexion con=new Conexion();
		String tabla="<table><th>ID</th><th>Producto</th><th>Cantidad</th><th>Precio</th>";
		ResultSet rs=null;
		rs=con.Consulta(sql);
		try {
			while(rs.next())
			{
				tabla+="<tr><td>"+rs.getInt(1)+"</td>"
						+ "<td>"+rs.getString(3)+"</td>"
						+ "<td>"+rs.getInt(5)+"</td>"
						+ "<td>"+rs.getDouble(4)+"</td>"
						
						+ "</td></tr>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		tabla+="</table>";
		return "<html><head><style>table"
				+ "{width:100%;border-collapse:collapse;}th,td{padding:15px;text-align:center;border-bottom:1px solid #ddd;}"
				+ "th{background-color:#f2f2f2;}tr:hover{background-color:#f5f5f5;}</style></head><body>" + tabla + "</body></html>";
	}
	public String buscarProductoCategoria(int cat)
	{
		String sentencia="SELECT descripcion_pr, precio_pr FROM tb_producto WHERE id_cat="+cat;
		Conexion con=new Conexion();
		ResultSet rs=null;
		String resultado="<table><th>Producto</th><th>Precio</th>";
		try
		{
			rs=con.Consulta(sentencia);
			while(rs.next())
			{
				resultado+="<tr><td>"+ rs.getString(1)+"</td>"
						+ "<td>"+rs.getDouble(2)+"</td></tr>";
			}
			resultado+="</table>";
		}
		catch(SQLException e)
		{
			System.out.print(e.getMessage());
		}
		System.out.print(resultado);
		return "<html><head><style>table"
		+ "{width:100%;border-collapse:collapse;}th,td{padding:15px;text-align:center;border-bottom:1px solid #ddd;}"
		+ "th{background-color:#f2f2f2;}tr:hover{background-color:#f5f5f5;}</style></head><body>" + resultado + "</body></html>";
	}
	
	public String ingresarProducto( int cat,String nombre, int cantidad, double precio)
			{
			String result="";
			Conexion con=new Conexion();
			PreparedStatement pr=null;
			String sql="INSERT INTO tb_producto (id_cat,"
			+ "descripcion_pr,cantidad_pr,precio_pr) "
			+ "VALUES(?,?,?,?)";
			try{
			pr=con.getConexion().prepareStatement(sql);
			pr.setInt(1,cat);
			pr.setString(2, nombre);
			pr.setInt(3, cantidad);
			pr.setDouble(4, precio);
			//File fichero=new File(directorio);
			//FileInputStream streamEntrada=new FileInputStream(fichero);
			//pr.setBinaryStream(6, streamEntrada,(int)fichero.length());
			if(pr.executeUpdate()==1)
			{
			result="Inserción correcta";
			}
			else
			{
			result="Error en inserción";
			}
			}
			catch(Exception ex)
			{
				result=ex.getMessage();
				}
				finally
				{
				try
				{
				pr.close();
				con.getConexion().close();
				}
				catch(Exception ex)
				{
				System.out.print(ex.getMessage());
				}
				}
				return result;
				}
	
	public String consultarProducto() {
		
		String sql= "SELECT * FROM tb_producto ORDER BY id_pr";
		String tabla="<table border=1>";
		ResultSet rs = null; //variable donde se guarda log datos extraidos de la tabla
		tabla += "<tr>"
		+ "<th>Codigo</th>" 
		+ "<th>Descripcion</th>" 
		+ "<th>Precio</th>" 
		+ "<th>Cantidad</th>"
		+ "</tr>";
		Conexion con = new Conexion();
		try {
			rs = con.Consulta (sql) ;
			while(rs.next()) {
				tabla+= "<tr>"
						+ "<td><style=\"text-align: center\">" + rs.getInt (1) + "</td>" 
						+ "<td><style=\"text-align: center\">" + rs.getString (3)+ "</td>" 
						+ "<td><style=\"text-align: center\">" + rs.getDouble(4) + "</td>"
						+ "<td><style=\"text-align: center\">" + rs.getInt (5) + "></td>"
						+ "<td> <a href= busquedaProducto.jsp?cod=" + rs.getInt (1) + "><style=\"text-align: center\">Modificar</pre></a></td>" 
						+ "<td> <a href= EliminarProducto.jsp?cod=" + rs.getInt (1) + " \"><style=\"text-align: center\">Eliminar</pre></a></td>" 
						+ "</tr>";

			}
			tabla +="</table>";
			}catch (SQLException e) {
				System.out.print(e.getMessage());
			}
		return tabla;	
	}
	
	public void ConsulEditarProductos(int cod) {
		Conexion obj = new Conexion();
		ResultSet rs = null;
		
		String sql = "SELECT id_pr, id_cat, descripcion_pr, precio_pr, cantidad_pr FROM tb_producto where id_pr = '" + cod + "'";
		try {
		rs = obj.Consulta(sql);
		while (rs.next()) {
		setId_pr (rs.getInt (1)); 
		setId_cat (rs.getInt (2));
		setDescripcion_pr (rs.getString (3)); 
		setPrecio_pr (rs.getFloat (4)); 
		setCantidad_pr (rs.getInt (5)) ;
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public boolean ModificarProducto(int cod, String desc, float prec, int cant ) {
		boolean agregado = false;
		Conexion obj= new Conexion();
		String sql= "UPDATE tb_producto SET descripcion_pr='"+ desc+"', precio_pr = '"+prec+"',"
		+ "cantidad_pr = '"+cant+"'WHERE \"id_pr\"='"+cod+"'";
		
		try {
			obj.Ejecutar(sql);
			agregado=true;
		}catch (Exception e) {
			agregado=false;
		}
		return agregado;
	}
	
	public boolean EliminarProducto (int cod) {
		boolean f = false;
		Conexion con = new Conexion();
		String sql="delete from tb_producto where id_pr='" + cod +"';";
		try {
			con.Ejecutar(sql);
			f = true;
		}catch (Exception e) {
			// TODO: handle exception
			f=false;
		}
		
		return f;
	}
	
	public int verificarCategoria(String nom) {
		String sentencia="SELECT * FROM tb_categoria WHERE descripcion_cat='"+nom+"'";
		Conexion con=new Conexion();
		ResultSet rs=null;
		rs=con.Consulta(sentencia);
		int categoria=0;
		try {
			while(rs.next())
			{
				if(rs.getString(2).equals(nom)) {
					categoria=rs.getInt(1);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		return categoria;
	}

	

	
}
