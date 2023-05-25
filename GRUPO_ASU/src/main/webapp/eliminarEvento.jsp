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
	int cod = Integer.parseInt(request.getParameter("cod"));
	Evento mp = new Evento();
	
	if(mp.eliminarEvento(cod)){
		%>
		<jsp:forward page="gestionEvento.jsp">
		<jsp:param name="eliminarActividad"
			value="Actividad eliminada del registro" />
		</jsp:forward>
		<%
	}
%>

</body>
</html>