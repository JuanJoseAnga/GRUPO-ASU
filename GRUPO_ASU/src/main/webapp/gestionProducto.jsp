<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1" session="true" import="uni.deporte.grupo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>GRUPO ASU - TENIS DE MESA</title>
	<link rel="stylesheet" href="css/estilo.css">
	 <link rel="shortcut icon" href="img/logo_ping2.png" type="image/x-icon">
</head>
<body>
<ul id="ruta-navegacion"></ul> 
  
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
        <br><a href="index.jsp"><img src="img/innofc.png" alt="Logo INNO FUTBOL"></a>

        <nav>
            <ul>
                <li><a href="catalogo.jsp">VER PRODUCTOS</a></li>
                <li><a href="busqueda.jsp">BUSCAR POR COMPETICION</a></li>
                <li><a href="login.jsp">REGISTRAR</a></li>
            </ul>
        </nav>
    </header>
<body>
<% 
estudiante est= new estudiante();
out.print(est.consultarProducto());
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