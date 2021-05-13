<%@page import="product.model.ProductImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/products.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Product Management</h1>
<form id="formProduct" name="formProduct">
 Product Title: 
 <input id="productTitle" name="productTitle" type="text" 
 class="form-control form-control-sm">
 <br> Product Type: 
 <input id="productType" name="productType" type="text" 
 class="form-control form-control-sm">
 <br> Product Description: 
 <input id="productDescription" name="productDescription" type="text" 
 class="form-control form-control-sm">
 <br> Product Category: 
 <input id="productCategory" name="productCategory" type="text" 
 class="form-control form-control-sm">
 <br> Researcher ID: 
 <input id="researcherId" name="researcherId" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidProductIDSave" 
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divProductsGrid">
 <%
 ProductImpl product = new ProductImpl(); 
 out.print(product.readAllProducts()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>