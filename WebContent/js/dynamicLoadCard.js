var xmlHttp;
function search(page){
	xmlHttp=createXMLHttpRequest();
	xmlHttp.onreadystatechange=updateData;
	xmlHttp.open("POST","${pageContext.request.contextPath}/search.do",true);
	xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	var data={
		title:'${param.title}',
		author:'${param.author}',
		price:'${param.price}',
		type:'${param.type}',
		createDate:'${param.createDate}',
		updateDate:'${param.updateDate}',
		start:page,
		offset:'${offset}'
	};
	var param='';
	var isFirst=true;
	for(var k in data){
		if(data[k]!=''){
			if(isFirst){
				isFirst=false;
			}else{
				param+='&';
			}
			param+=k+'='+data[k];
		}
	}
	console.log(param);
	xmlHttp.send(param);
}

var cardTemplateNode;
var contentNode;
cardTemplateNode = document.getElementsByClassName("card")[0];
contentNode = cardTemplateNode.parentNode;

function initData() {
	var n=contentNode.childNodes.length;
	for(var i=0;i<n;i++){
		contentNode.removeChild(contentNode.childNodes[0]);
	}
}

function updateData(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		initData();
		console.log(xmlHttp.responseText);
		data = JSON.parse(xmlHttp.responseText);
		for (var book of data) {
			appendCard(book);
		}
	}
}

function appendCard(book) {
	console.log(book.id);
	var newNode = cardTemplateNode.cloneNode(true);
	var imgArea = newNode.getElementsByClassName("imgArea")[0];
	imgArea.getElementsByTagName("img")[0].src = "${pageContext.request.contextPath }/"+ book.imgPath;
	console.log(newNode.getElementsByTagName("a")[0].getAttribute('href'));
	newNode.getElementsByTagName("a")[0].setAttribute("href","${pageContext.request.contextPath }/detailPage.do?id="+book.id);
	console.log(newNode.getElementsByTagName("a")[0].getAttribute('href'));
	newNode.getElementsByClassName("card-name")[0].innerText = book.title;
	newNode.getElementsByClassName("card-desc")[0].innerText = book.author;
	contentNode.appendChild(newNode);
}