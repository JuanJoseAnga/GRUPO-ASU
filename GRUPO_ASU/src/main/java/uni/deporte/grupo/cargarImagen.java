package uni.deporte.grupo;

import uni.deporte.datos.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/cargarImagen")
@MultipartConfig
public class cargarImagen extends HttpServlet{
	
	// CARGAR TODOS LOS DATOS 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream inputStream = null;
        Conexion con=new Conexion();
        PreparedStatement pr=null;

        try {
            // Obtén el archivo seleccionado desde el campo de carga de archivos
            Part filePart = request.getPart("imagen_eve");
        	String Nombre = request.getParameter("nombre_eve");
            String descripcion = request.getParameter("descripcion_eve");
            String fecha = request.getParameter("fecha_eve");
            String asis = request.getParameter("asistencia_eve");
            // Obtén el nombre del archivo
            String fileName = filePart.getSubmittedFileName();

            // Obtén el contenido del archivo como un InputStream
            inputStream = filePart.getInputStream();

            // Prepara la sentencia SQL para la inserción
            String sql = "INSERT INTO public.eventos("
            		+ "	nombre_eve, descripcion_eve, imagen_eve, asistencia_eve)"
            		+ "	VALUES (?, ?, ?, ?);";
            pr=con.getConexion().prepareStatement(sql);

            // Lee el contenido de la imagen en un arreglo de bytes
            byte[] imageBytes = inputStream.readAllBytes();
            pr.setString(1, Nombre);
			pr.setString(2, descripcion);
            pr.setBytes(3, imageBytes);
			pr.setString(4, asis);
            
            if(pr.executeUpdate()==1)
			{
				System.out.println("Insercion Correcta");
			}
			else
			{
				System.out.println("Insercion Incorrecta");
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/gestionEvento.jsp");
    }
}