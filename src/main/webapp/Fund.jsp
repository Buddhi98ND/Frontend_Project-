<%@page import="model.Fund"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/main.js"></script>

<meta charset="ISO-8859-1">
<title>Fund </title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>

<div class="container"> 
		<div class="row">  
		
			<div class="col-8">       
				<h1 class="m-3">Fund Management</h1>        
				
				<form id="formFund" name="formFund" method="post" action="Fund.jsp">  
					Fund ID:  
					<input id="fundID" name="fundID" type="text" class="form-control form-control-sm">  
					
					<br> 
					Fund Name:  
					<input id="fundName" name="fundName" type="text" class="form-control form-control-sm">  
					
					<br>
					 Fund Date:  
					 <input id="fundDate" name="fundDate" type="text" class="form-control form-control-sm">  
					 
					 <br> 
					 Description:  
					 <input id="description" name="description" type="text" class="form-control form-control-sm">  
					  <br> 
					  
					   <br> 
					 Amount:  
					 <input id="amount" name="amount" type="text" class="form-control form-control-sm">  
					  <br> 
					  
					   <br> 
					 Recipiet Name:  
					 <input id="recipiet_Name" name="recipiet_Name" type="text" class="form-control form-control-sm">  
					  <br> 
					  
					  
					  
					  
					   
					 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					 <input type="hidden" id="fundIDSave" name="fundIDSave" value=""> 
					 
				</form> 
				
				<div id="alertSuccess" class="alert alert-success"></div>  
				<div id="alertError" class="alert alert-danger"></div> 
				
				<br>
				 <br>
                   <div id="divItemsGrid">   
					<%    
						Fund fundObj = new Fund();
						out.print(fundObj.readFund());   
					%>  
					
					<br>
					<br>
					 <a href="Login.jsp" class="btn btn-success">Logout</a>
				</div> 
                   
                </div>
            </div>
				  
 			</div>
 		 
 		    
 		
 
	
</body>
</html>