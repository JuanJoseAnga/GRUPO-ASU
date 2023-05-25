package uni.deporte.seguridad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import uni.deporte.datos.*;

import uni.deporte.datos.Conexion;
public class usuario {
	
	public usuario(){
		
	}
	
	private String nombre;
	private int perfil;
	private String login;
	private String clave;
	
	public boolean verificarUsuario(String nlogin, String nclave)
	{
	boolean respuesta=false;
	String sentencia= "Select * from usuario where correo_institucional='"+nlogin+
	"' and contrasena_usu='"+nclave+"';";
	//System.out.print(sentencia);
	try
	{
	ResultSet rs;
	Conexion clsCon=new Conexion();
	rs=clsCon.Consulta(sentencia);
	if(rs.next())
	{
	respuesta=true;
	this.setLogin(nlogin);
	this.setClave(nclave);
	this.setPerfil(rs.getInt(7));
	this.setNombre(rs.getString(2));
	}
	else
	{
	respuesta=false;
	rs.close();
	}
	}
	catch(Exception ex)
	{
	System.out.println( ex.getMessage());
	}
	return respuesta;
	}
	
	
	public boolean verificarExistencia(String ced, String correo) {
		boolean resp=true;
		String sentencia= "Select cedula,correo_institucional from usuario";
		try
		{
			ResultSet rs;
			Conexion clsCon=new Conexion();
			rs=clsCon.Consulta(sentencia);
			while(rs.next())
			{
				
				if(ced.equals(rs.getString(1)) || correo.equals(rs.getString(2))) {
					resp=false;
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println( ex.getMessage());
		}
		return resp;
		
	}
	
	public void insertarUsuario(String nombre, String ced, String correo, String carrera, String contra, String celular, String sede) {
		String sentencia="INSERT INTO public.usuario(correo_institucional, contrasena_usu, carrera, sede, celular, id_tipo_usuario_fk, nombre_usu, cedula)"
				+ "VALUES(" +"'"+correo+"',"
						+"'"+contra+"',"
						+"'"+carrera+"',"
						+"'"+sede+"',"
						+"'"+celular+"',"
						+"3,"
						+"'"+nombre+"',"
						+"'"+ced+"'"
						+");";
		Conexion clsCon=new Conexion();
		String resu = clsCon.Ejecutar(sentencia);
	}
	
	public String mostrarMiembros() {
		String sentencia="SELECT * FROM miembros, usuario where miembros.id_usuario = usuario.id_usuario";
		Conexion con=new Conexion();
		ResultSet rs=null;
		rs=con.Consulta(sentencia);

		String resul = "<table class=\"table table-striped table-hover\" id=\"tabla-miembros\"><th>Nombre</th><th>Correo Institucional</th><th>Carrera</th><th>Celular</th>";
		try {
			while(rs.next())
			{

				resul+="<tr>"
						+ "<td><p>" + rs.getString(4) + "</p></td>"
						+ "<td><p>" + rs.getString(6) + "</p></td>"
						+ "<td><p>" + rs.getString(9) + "</p></td>"
						+ "<td><p>" + rs.getString(5) + "</p></td>"
						+"</tr>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		resul+="</table>";
		return resul;
	}
	
	public int getPerfil() {
		return perfil;
	}

	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}


}
