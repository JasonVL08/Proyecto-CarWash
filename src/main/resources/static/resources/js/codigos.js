window.addEventListener("load",rotacion);

var ban=document.getElementsByTagName("img")[1];
var c=1;
function rotacion(){
	ban.setAttribute("src","../../Imagenes/servicio"+c+".jpg");
	c++;
	if (c>=4)c=1;
	setTimeout("rotacion()",2000);
}
$(document).ready(function(){
	
	$("aside img").mouseover(function(){
		$(this).css({
			"transform":"scale(1.2)",
			"box-shadow":"0 0 20px aquamarine",
			"transition":"all 1s"
		})
	})
	$("aside img").mouseout(function(){
		$(this).css({
			"transform":"scale(1)",
			"box-shadow":"0 0 0px",
			"transition":"all 1s"
		})
	})
	
});