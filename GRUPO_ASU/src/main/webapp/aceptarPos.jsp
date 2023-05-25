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
	postulacion mp = new postulacion();
	int cod = Integer.parseInt(request.getParameter("cod"));
	if(mp.modificarEstado(cod, "4")){
		%>
			<jsp:forward page="gestionAdmin.jsp" >
<jsp:param name="resultAceptarActividad" value="Usuario Aceptado" />
		</jsp:forward>
<%
	}
%>
</body>
</html>