package uni.deporte.grupo;

import java.sql.ResultSet;
import java.sql.SQLException;

import uni.deporte.datos.*;

public class postulacion {

	private Integer estado_pos;
	private Integer id_us;
	private String correo;
	

	public postulacion() {
		
	}
	
	public postulacion(String correo) {
		String sentencia="SELECT id_usuario FROM usuario where correo_institucional='" + correo + "';";
		Conexion con=new Conexion();
		ResultSet rs=null;
		rs=con.Consulta(sentencia);
		this.setCorreo(correo);
		try {
			while(rs.next())
			{
				this.setId_us(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
	}
	
	public boolean newPostulacion() {
	   String sentencia="INSERT INTO public.postulaciones("
	   		+ "	estado, id_usuario_fk)"
	   		+ "	VALUES (1,"
	   		+ this.getId_us() + ")";
	   System.out.println(sentencia);
		Conexion clsCon=new Conexion();
		
		if(this.verificarPostulacion()) {
			clsCon.Ejecutar(sentencia);
			return true;
		}else {
			return false;
		}
	}
	
	/*FUNCIONAR PARA VERIFICAR SI EL USUARIO YA TIENE UNA POSTULACION ACTIVA O YA ES MIEMBRO*/
	
	public boolean verificarPostulacion() {
		String sentencia="SELECT id_usuario_fk FROM public.postulaciones, usuario "
				+ "where postulaciones.id_usuario = (select id_usuario from usuario where usuario.correo_institucional = '" + this.getCorreo()
				+ "')"
				+ "AND (postulaciones.estado = 1 OR postulaciones.estado = 4)"
				+ "group by id_usuario_fk;";
		System.out.println(sentencia);
		Conexion con=new Conexion();
		ResultSet rs=null;
		rs=con.Consulta(sentencia);
		try {
			if(rs != null && rs.next()) {
				return false;
			}else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean modificarEstado(int cod, String estado) {
		boolean agregado = true;
		Conexion obj = new Conexion();
		String sql="UPDATE public.postulaciones"
				+ "	SET estado=" + estado
				+ "	WHERE id_usuario_fk=" + cod +";";
		String insert="";
		try {
			obj.Ejecutar(sql);
			if(estado.equals("4")) {
				insert = "INSERT INTO public.miembros("
						+ "	id_usuario_fk)"
						+ "	VALUES (" + cod
						+ ");";
				obj.Ejecutar(insert);
			}
			agregado = true;
		}catch (Exception e) {
			// TODO: handle exception
			agregado=false;
		}
		return agregado;
	}
	
	public String tablaPostulacionPendiente() {
		String sql="SELECT * FROM usuario, postulaciones where usuario.id_usuario = postulaciones.id_usuario_fk and estado = 1 ORDER BY id_usuario";
		Conexion con=new Conexion();
		String tabla="<table class=\"table table-striped table-hover\"><th>ID Postulacion</th><th>Nombre</th><th>Correo</th><th>Estado</th><th></th><th></th>";
		ResultSet rs=null;
		rs=con.Consulta(sql);
		try {
			while(rs.next())
			{
				String estado="";
				if(rs.getInt(12)==1)
					estado="Pendiente";
				else if(rs.getInt(12)==2) {
					estado="Rechazado";
				}
				tabla+="<tr><td>"+rs.getInt(11)+"</td>"
						+ "<td>"+rs.getString(8)+"</td>"
						+ "<td>"+rs.getString(2)+"</td>"
						+ "<td>"+estado+"</td>"
						+ "<td> <a href= aceptarPos.jsp?cod=" + rs.getInt(1) +">ACEPTAR</a></td>"
						+ "<td> <a href= rechazarPos.jsp?cod=" + rs.getInt(1) +">RECHAZAR</a></td>"
						+ "</td></tr>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		tabla+="</table>";
		return tabla;
	}
	
	
	
	public String tablaPostulacionRechazada() {
		String sql="SELECT * FROM usuario, postulaciones where usuario.id_usuario = postulaciones.id_usuario_fk and estado = 2 ORDER BY id_usuario";
		Conexion con=new Conexion();
		String tabla="<table class=\"table table-striped table-hover\"><th>ID Postulacion</th><th>Nombre</th><th>Correo</th><th>Estado</th>";
		ResultSet rs=null;
		rs=con.Consulta(sql);
		try {
			while(rs.next())
			{
				String estado="";
				if(rs.getInt(12)==1)
					estado="Pendiente";
				else if(rs.getInt(12)==2) {
					estado="Rechazado";
				}
				tabla+="<tr><td>"+rs.getInt(11)+"</td>"
						+ "<td>"+rs.getString(8)+"</td>"
						+ "<td>"+rs.getString(2)+"</td>"
						+ "<td>"+estado+"</td>"
						+ "</td></tr>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		tabla+="</table>";
		return tabla;
	}
	public String tablaPostulacionAceptada() {
		String sql="SELECT * FROM usuario, postulaciones where usuario.id_usuario = postulaciones.id_usuario_fk and estado = 4 ORDER BY id_usuario";
		Conexion con=new Conexion();
		String tabla="<table class=\"table table-striped table-hover\"><th>ID Postulacion</th><th>Nombre</th><th>Correo</th><th>Estado</th>";
		ResultSet rs=null;
		rs=con.Consulta(sql);
		try {
			while(rs.next())
			{
				String estado="";
				if(rs.getInt(12)==1)
					estado="Pendiente";
				else if(rs.getInt(12)==2) {
					estado="Rechazado";
				}else if(rs.getInt(12)==4) {
					estado="Aceptado";
				}
				tabla+="<tr><td>"+rs.getInt(11)+"</td>"
						+ "<td>"+rs.getString(8)+"</td>"
						+ "<td>"+rs.getString(2)+"</td>"
						+ "<td>"+estado+"</td>"
						+ "</td></tr>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		tabla+="</table>";
		return tabla;
	}
	public Integer getEstado_pos() {
		return estado_pos;
	}
	public void setEstado_pos(Integer estado_pos) {
		this.estado_pos = estado_pos;
	}
	public Integer getId_us() {
		return id_us;
	}
	public void setId_us(Integer id_us) {
		this.id_us = id_us;
	}
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
}