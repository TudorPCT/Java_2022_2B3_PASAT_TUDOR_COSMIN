<html>
<head>
  	<title>${name}</title>
</head>
<body>
	<h1>Catalog ${name}!</h1>
   <ol>
    	<#list items as item>
     	 	<a href="${item.location}">${item.title}</a><br>
   	</#list>
   </ol>
</body>
</html>