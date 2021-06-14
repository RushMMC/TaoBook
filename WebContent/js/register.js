var gifs = ["../img/gif/g1.gif","../img/gif/g2.gif",
			"../img/gif/g3.gif"]
window.onload=function(){
	var i=0;
	setInterval(()=>{
		document.body.style.backgroundImage='url('+gifs[i++%gifs.length]+')';
	},1000)
}