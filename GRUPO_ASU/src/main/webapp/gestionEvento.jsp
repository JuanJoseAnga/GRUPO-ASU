<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="uni.deporte.grupo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="css/estilo.css">
</head>



<body>


<header>
	
	<div class= "logo">
  	<img src="img/logo_asu_ping_completo2.png" class="logo-image" alt="LOGO UNIVERSIDAD POLITECNICA SALESIANA" >
  	</div>
  	<h1>GRUPO ASU - TENIS DE MESA</h1>
  	<nav>
		<ul>
			<li><a href="index.jsp">Inicio</a></li>
			<li><a href="eventos.jsp">Eventos</a></li>
			<li><a href="login.jsp">Iniciar Sesión</a></li>
		</ul>
	</nav>	
</header>

<%
		String usuario = "";
		HttpSession sesion = request.getSession();
		Evento ac = new Evento();

		if (sesion.getAttribute("usuario") == null) {
		%>
		<jsp:forward page="inicioSesion.jsp">
			<jsp:param name="error" value="Debe registrarse en el sistema." />
		</jsp:forward>
		<%
		} else {
		usuario = (String) sesion.getAttribute("usuario");
		int perfil = (Integer) sesion.getAttribute("perfil");
		sesion.setAttribute("correo", sesion.getAttribute("correo"));
		
		out.print("<div id=\"centrarAct\">"
				+ "<div id = \"divEditarAct\">" 
				+ "<h2>Gestion de actividades</h2>"
				+ "<h3>Actividades dentro de la base de datos</h3>"
				+ ac.mostrarEditarEliminar()
				+ "<a href=\"nuevoEvento.jsp\">"
				+ "<button>Nueva actividad</button>"
				+ "</a>"
				+"</div></div>");
		}
		%>
		
	<footer>
  <div class="columna-izquierda">
    <p>
    Creador: Juan Jose Angamarca Casa
    <br>Creado en: Mayo-2023
    <br><a href="https://www.flowcode.com/page/juan-jose-angamarca" target="_blank"> Mis redes sociales </a>
    
    </p>
    
  </div>
  <div class="columna-centro">
    <p>
    DERECHOS RESERVADOS - GRUPO ASU TENIS DE MESA
    </p>
  </div>
  <div class="columna-derecha">
   <p>
   <a href="https://www.facebook.com/UPSalesianaEc/?locale=es_LA" target="_blank"><img src="img/facebook.png" class="logo-footer"></a>
   <a href="https://www.instagram.com/upsalesianaec/" target="_blank"><img src="img/instagram.png" class="logo-footer"></a>
   <a href="https://twitter.com/upsalesianaec" target="_blank"><img src="img/twitter.png" class="logo-footer"></a>
   <a href="https://www.tiktok.com/@upsalesianaec" target="_blank"><img src="img/tiktok.png" class="logo-footer"></a>   
   </p>
  </div>
</footer>		
		
</body>
</html>