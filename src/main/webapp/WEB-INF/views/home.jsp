<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>groovy测试</title>

<style>


div.outer {
	margin: 50px 50px;
	padding: 20px 100px;
	text-align: center
}


</style>
<script src="<%=path%>/resources/js/jquery/jquery-1.11.1.min.js"></script>
<script>
	$.ajaxSetup({
		timeout : 300000,
		dataType : 'json',
		success : function(data) {
			
		},
		error : function(xhr, status, e) {
			$("#div_data").attr("style","color:red");
			$("#div_data").html("读取配置错误！");
		},
		complete : function(xhr, status) {
			
		},
		beforeSend : function(xhr) {
		}
	});
	$(function() {
		$.getJSON("<%=path%>/getData",function(data) {
				var html = "";
				$.each(data, function(i, item) {
					var returnHtml = "<center><table border='1px'>"
					if(data.length>1){
						returnHtml += "<tr><td colspan='2' align='center'><b>模板"+(i + 1)+"</b></td></tr>"
					}else{
						returnHtml += "<tr><td colspan='2' align='center'><b>模板</b></td></tr>"
					}
					returnHtml += "<tr><td><b>标题</b></td><td>" + item.title + "</td></tr>"
					returnHtml += "<tr><td><b>内容</b></td><td>" + (item.msg?item.msg:"") + "</td></tr>"
					returnHtml += "</table></center><br>"
					html += returnHtml;
				});
				$("#div_data").removeAttr("style");
				$("#div_data").html(html);
			});
		});
</script>
</head>
<body>
	<div id="outer_div" class="outer">
		<div style="font-size: 30px;">groovy测试</div>
		<br>
		<div id="div_data">正在读取配置，请稍后...</div>
	</div>
</body>
</html>
