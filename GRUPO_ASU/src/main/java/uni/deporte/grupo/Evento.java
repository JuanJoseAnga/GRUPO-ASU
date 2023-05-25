package uni.deporte.grupo;

import uni.deporte.datos.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;






public class Evento {
	
	private String nombre;
	private String descripcion;
	private String asistencia;
	private int cod;
	
	public  Evento() {
		
	}
	public Evento(int cod, String nombre, String desc, String asis) {
		this.setCod(cod);
		this.setNombre(nombre);
		this.setDescripcion(desc);
		this.setAsistencia(asis);
	}
	
	public String mostrarActividades() throws IOException{
		String sentencia="SELECT * FROM eventos";
		Conexion con=new Conexion();
		ResultSet rs=null;
		rs=con.Consulta(sentencia);
		String resul = "<table>";

		File ruta = new File("C:\\Users\\juanf\\Documents\\UNIVERSIDAD UPS\\SEMESTRE 5\\PROGRAMACION WEB\\GRUPO_ASU\\src\\main\\webapp\\temporales"); 
		File tempFile;
		FileOutputStream fos;
		int cont=0;


		try {
			while(rs.next())
			{
				tempFile = new File(ruta+"\\actividad"+cont+".jpg");
				fos = new FileOutputStream(tempFile);
				byte[] foto = rs.getBytes("imagen_eve");
				fos.write(foto); 
				fos.close();
				 		
				resul+="<tr>"
						+ "<td>"
						+ "<h2>" + rs.getString(2) + "</h2>"
						+ "<p>" + rs.getString(3) + "</p>"
						+ "</td>"
						+ "<td>"

						+ "<img src='temporales/actividad"+cont+".jpg' alt='Imagen de la actividad' width='550px' height='auto' class=\"imgInicio\">"
		+ "</td>"
		+"</tr>";
						cont++;
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		resul+="</table>";
		return resul;
	}
	
	public String mostrarEditarEliminar() {
		String sql="SELECT * FROM eventos ORDER BY id_evento";
		Conexion con=new Conexion();
		String tabla="<table class=\"table table-hover\" id=\"editar-table\"><th>ID Evento</th><th>Nombre del Evento</th><th></th><th></th>";
		ResultSet rs=null;
		rs=con.Consulta(sql);
		try {
			while(rs.next())
			{

				tabla+="<tr><td>"+rs.getInt(1)+"</td>"
						+ "<td>"+rs.getString(2)+"</td>"
						+ "<td> <a href= modificarEvento.jsp?cod=" + rs.getInt(1) +"><pre style=\"text-align: center\">Modificar</pre></a></td>"
						+ "<td> <a href= eliminarEvento.jsp?cod=" + rs.getInt(1) + " \"><pre style=\"text-align: center\">Eliminar</pre></a></td>"
						+ "</td></tr>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tabla+="</table>";
		return tabla;
	}
	public boolean eliminarEvento(int cod) {
		boolean f = false;
		Conexion obj = new Conexion();
		String sql="DELETE FROM eventos WHERE id_evento='" + cod + "';";
		try {
			obj.Ejecutar(sql);
			f = true;
		}catch (Exception e) {
			// TODO: handle exception
			f=false;
		}
		return f;
	}
	

	public void consulEditarEvento(int cod) {
		Conexion obj = new Conexion();
		ResultSet rs = null;
		String sql = "SELECT * FROM eventos where id_evento = " + cod;
		try {
			rs=obj.Consulta(sql);
			while(rs.next()) {
				setCod(cod);
				setNombre(rs.getString(2));
				setDescripcion(rs.getString(4));
				setAsistencia(rs.getString(6));
			}
		}catch (Exception e) {
			// TODO: handle exception
		}

	}

	public boolean updateActividad(Evento mp) {
		boolean agregado = true;
		Conexion obj = new Conexion();
		String sql="UPDATE public.eventos"
				+ "	SET nombre_eve='" + mp.getNombre() + "'" 
				+ ", descripcion_eve='" + mp.getDescripcion()+"'"
				+ ", asistencia_eve='"+ mp.getAsistencia() + "'"
				+ "	WHERE id_evento='" + mp.getCod()+ "';";
		try {
			obj.Ejecutar(sql);
			agregado = true;
		}catch (Exception e) {
			// TODO: handle exception
			agregado=false;
		}
		return agregado;
	}
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAsistencia() {
		return asistencia;
	}

	public void setAsistencia(String asistencia) {
		this.asistencia = asistencia;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}
	
	
	
	
}
