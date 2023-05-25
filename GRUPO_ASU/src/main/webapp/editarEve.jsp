<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="uni.deporte.grupo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String nombre = request.getParameter("editarnom");
	String descrp = request.getParameter("editardesc");
	String asis = request.getParameter("editarAsis");
	HttpSession sesion = request.getSession();
	int cod = (int)sesion.getAttribute("id_evento");
	
	Evento mp = new Evento(cod,nombre,descrp, asis);

	String result = "";
	if(mp.updateActividad(mp)){
		response.sendRedirect("gestionEvento.jsp");
	}else{
		out.println("Algo salio mal");
	}
%>
</body>
</html>