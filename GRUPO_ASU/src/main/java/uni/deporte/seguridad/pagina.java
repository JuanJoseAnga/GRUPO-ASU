package uni.deporte.seguridad;
import java.sql.ResultSet;
import java.sql.SQLException;

import uni.deporte.datos.*;
public class pagina {
	
	public String mostrarMenu(int nperfil)
	{
	String menu="<ul>";
	String sql="SELECT * FROM pagina pag, tipo_login per, \"paginaxtipo\" pper "
	+
	"WHERE pag.id_pagina=pper.id_paginafk2 AND pper.id_tipo_loginfk1=per.id_tipo_login AND pper.id_tipo_loginfk1= "+nperfil;
	Conexion con = new Conexion();
	ResultSet rs=null;
	try
	{
	rs=con.Consulta(sql);
	while(rs.next())
	{
	menu+="<li><a href="+rs.getString(3)+" accesskey="+rs.getInt(1)+">"+rs.getString(2)+
	"</A></li>";
	}
	menu+="</ul>";
	}
	catch(SQLException e)
	{
		System.out.print(e.getMessage());
	}
	return menu;
	}


}
/*
"WHERE pag.id_pagina=pper.id_pagina AND pper.id_tipo_login=per.id_per AND pper.id_per= "+nperfil;*/