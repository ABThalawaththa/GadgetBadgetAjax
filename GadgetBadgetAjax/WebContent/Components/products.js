$(document).ready(function()
{ 
	if ($("#alertSuccess").text().trim() == "") 
 		{ 
			 $("#alertSuccess").hide(); 
 		} 
 		$("#alertError").hide(); 
}); 

$(document).on("click", "#btnSave", function(event)
{ 
	// Clear alerts---------------------
	$("#alertSuccess").text(""); 
 	$("#alertSuccess").hide(); 
 	$("#alertError").text(""); 
 	$("#alertError").hide(); 
	// Form validation-------------------
	var status = validateProductForm(); 
	if (status != true) 
 	{ 
 		$("#alertError").text(status); 
 		$("#alertError").show(); 
 	return; 
 	} 

	// If valid------------------------
	var type = ($("#hidProductIDSave").val() == "") ? "POST" : "PUT"; 
 	$.ajax( 
 	{ 
 		url : "ProductsAPI", 
 		type : type, 
 		data : $("#formProduct").serialize(), 
 		dataType : "text", 
 		complete : function(response, status) 
 		{ 
 			onItemSaveComplete(response.responseText, status); 
 		} 
 	}); 
});

function onItemSaveComplete(response, status){ 
	if (status == "success") { 
 		var resultSet = JSON.parse(response); 
 		if (resultSet.status.trim() == "success") { 
 			$("#alertSuccess").text("Successfully saved."); 
 			$("#alertSuccess").show(); 
 			$("#divProductsGrid").html(resultSet.data); 
 		} else if (resultSet.status.trim() == "error") { 
 				$("#alertError").text(resultSet.data); 
 				$("#alertError").show(); 
 		} 
 	} else if (status == "error") { 
 			$("#alertError").text("Error while saving."); 
 			$("#alertError").show(); 
 	} 
	else{ 
 		$("#alertError").text("Unknown error while saving.."); 
 		$("#alertError").show(); 
 	} 
 	$("#hidItemIDSave").val("");
	$("#researcherId").prop("disabled",false); 
 	$("#formProduct")[0].reset(); 
}

$(document).on("click", ".btnUpdate", function(event)
{ 
	$("#hidProductIDSave").val($(this).data("productid")); 
 	$("#productTitle").val($(this).closest("tr").find('td:eq(0)').text()); 
 	$("#productType").val($(this).closest("tr").find('td:eq(1)').text()); 
 	$("#productDescription").val($(this).closest("tr").find('td:eq(2)').text()); 
 	$("#productCategory").val($(this).closest("tr").find('td:eq(3)').text()); 
	$("#researcherId").val($(this).closest("tr").find('td:eq(4)').text()); 
	$("#researcherId").prop("disabled",true);
});

$(document).on("click", ".btnRemove", function(event)
{ 
 $.ajax( 
 { 
 url : "ProductsAPI", 
 type : "DELETE", 
 data : "productID=" + $(this).data("productid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 	onItemDeleteComplete(response.responseText, status); 
 } 
 }); 
});

function onItemDeleteComplete(response, status){ 
	if (status == "success") { 
		 var resultSet = JSON.parse(response); 
 		 if (resultSet.status.trim() == "success") { 
 			$("#alertSuccess").text("Successfully deleted."); 
 			$("#alertSuccess").show(); 
 			$("#divProductsGrid").html(resultSet.data); 
 		} else if (resultSet.status.trim() == "error") { 
 			$("#alertError").text(resultSet.data); 
 			$("#alertError").show(); 
 		} 
 } else if (status == "error") { 
 		$("#alertError").text("Error while deleting."); 
 		$("#alertError").show(); 
 } else{ 
 	$("#alertError").text("Unknown error while deleting.."); 
 	$("#alertError").show(); 
 } 
}

function validateProductForm() { 
	// Product Title
	if ($("#productTitle").val().trim() == "") { 
 		return "Insert Product Title."; 
 	} 
	// Product Type
	if ($("#productType").val().trim() == "") { 
 		return "Insert Product Type."; 
 	} 

	// Product Description
	if ($("#productDescription").val().trim() == "") { 
 		return "Insert Product Description."; 
 	} 

	// Product Category
	if ($("#productCategory").val().trim() == "") { 
 		return "Insert Product Category."; 
 	} 

	if ($("#researcherId").val().trim() == "") { 
 		return "Insert Researcher ID."; 
 	} 

	var researcherID = $("#researcherId").val().trim();
    if (!$.isNumeric(researcherID)) {
        return "Insert a valid researcher ID.";
    }
	
	return true; 
}
