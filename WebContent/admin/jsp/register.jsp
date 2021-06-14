<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="js/ajax.js"></script>
<script type="text/javascript">
	function validateUsername(){
		var username = document.getElementById("username");
		url = "${pageContext.request.contextPath}/validate";
		var params = "username="+username.value;
		sendRequest(url,params,"GET",validation);
	}
	function validation(){
		if(xmlHttp.readyState == 4){
			if(xmlHttp.status == 200){
				var messageArea= document.getElementById("id1");
				messageArea.innerText=xmlHttp.responseText;
			}
		}
	}
</script>
<div>
		<h4>新用户注册</h4>
		<form action="RegisterServlet" method="post">
			<table border="0">
				<tr>
					<td>昵称：</td>
					<td><input id="username" type="text" name="username" onblur="validateUsername()"/></td>
					<td id="id1" style="color:red"></td>
				</tr>	
				<tr>
					<td>密码：</td>
					<td><input type="password" name="password"/></td>
					<td></td>
				</tr>	
				<tr>
					<td>邮箱：</td>
					<td><input type="text" name="email"/></td>
					<td></td>
				</tr>	
				<tr>
					<td>电话：</td>
					<td><input type="text" name="telephone"/></td>
					<td></td>
				</tr>
				<tr>
					<td><input type="submit" value="注册"/></td>
					<td><input type="reset" name="重置"/></td>
					<td></td>
				</tr>				
			</table>		
		</form>	
	</div>
</body>
</html>