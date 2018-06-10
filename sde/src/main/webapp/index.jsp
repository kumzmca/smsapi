<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>
	SMS
</title>
<script>
$(document).ready(function(){
	$("#invoke").click(function(){
		var method = $('#opt').find(":selected").val();
		 var obj = {};
			obj.text=$("#txt").val();
			obj.from=$("#from").val();
			obj.to=$("#to").val();
			obj = JSON.stringify(obj);
			if(method!="POST")
				obj = encodeURI(obj);
			 var credentials=$('#name').val()+":"+$('#pwd').val().trim();
			 //http://localhost:8080/api/inbound/sms
			 $.ajax({  
		           type: method,
		           url: $("#url").val(),
		           dataType: "json",
		           headers: { 
		               'Accept': 'application/json',
		               'Content-Type': 'application/json',
		               'Authorization' : window.btoa(credentials)
		           }, 
		           success: function (msg) {
		               if (msg) {
		             		$("#message").val(msg.message);
		             		$("#error").val(msg.error);
		               } else {
		             		$("#error").val("no response");		
	             		}
		           },
		           error: function (textStatus, errorThrown) {
		        	   	console.log(textStatus.status)
	             		$("#message").val(textStatus.statusText);
	             		$("#error").val(textStatus.status);
		            },
					data: obj,
		       }); 
	})
}); 
</script>  
</head>
<body>
<center style="margin-top:10%">
	<table>
		<tr>
			<td>URL</td>
			<td><input id="url" style="width:285px" type="text" value="http://165.227.228.201:8080/sde/api/inbound/sms" name="url"><br></td>
		</tr>
		<tr>
			<td>USER NAME</td>
			<td><input id="name" type="text" value="admin" name="name"><br></td>
		</tr>
		<tr>
			<td>PASSWORD</td>
			<td><input id="pwd" type="password"  value="admin" name="pwd"><br></td>
		</tr>
				<tr>
			<td>FROM</td>
			<td><input id="from" type="text" name="pwd"><br></td>
		</tr>
				<tr>
			<td>TO</td>
			<td><input id="to" type="text" name="pwd"><br></td>
		</tr>
		<tr>
			<td>TEXT</td>
			<td><textarea id="txt" rows="4" cols="50"> </textarea><br></td>
		</tr>
				<tr>
			<td>METHOD</td>
			<td><select id="opt">
	  <option value="POST">POST</option>
	  <option value="GET">GET</option>
	  <option value="DELETE">DELETE</option>
	  <option value="OPTIONS">OPTIONS</option>
			</td>
		</tr>
			<tr>
			<td><input id="message" placeholder="Message" rows="4" cols="50"></input></td>
			<td><input id="error" placeholder="Error" rows="4" cols="50"></input></td>
		</tr>
	</table>
	
	 	 

	  	 		 
	  	 	 		 
	  	 		 		
	     	       
	  	 <button id="invoke" type="butotn">Invoke</button>
  </center>
</body>
</html>