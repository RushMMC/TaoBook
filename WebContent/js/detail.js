window.onload = function() {
	var itemList = document.getElementById("itemList");
	for (let i=1;i<121;i++ ) {
		var node = document.createElement("li");
		node.appendChild(document.createTextNode(i));
		itemList.appendChild(node);
	}
};
