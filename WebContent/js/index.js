var showImg;
var currentImgIndex=0;
var imgTitleList;
function init(){
	showImg = document.getElementsByClassName("showImg");
	imgTitleList= document.getElementsByClassName("imgTitle");
	
	for(let index in imgTitleList){
		imgTitleList[index].onmouseover=()=>{
			currentImgIndex=index;
			changeShowPanelImg();
		};
	}
	changeShowPanelImg();
}

function changeShowPanelImg(){
	for(var img of showImg){
		img.style.display="none";
	}
	showImg[currentImgIndex].style.display="inline-block";
	console.log(currentImgIndex);
}

window.onload=init;