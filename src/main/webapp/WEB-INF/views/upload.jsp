<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="web-resources/css/bootstrap.min.css" rel="stylesheet">
<script src="web-resources/js/lib/bootstrap.min.js"></script>
<script src="web-resources/js/lib/jquery.min.js"></script>

<base href="${pageContext.request.contextPath}/" />
</head>

<body>

	<div class="container mt-3">
		<h2>Custom File</h2>		
		<form method="POST" action="/upload" enctype="multipart/form-data">
			<p>Custom file:</p>
			<div class="custom-file mb-3">
				<input type="file" class="custom-file-input" id="customFile"
					name="file">
					<label class="custom-file-label" for="file">Choose file</label>
			</div>		
			<div class="mt-3">
				<button type="submit" class="btn btn-primary">Submit</button>
			</div>
		</form>
		
		<div class="mt-3">
				<button type="button" class="btn btn-primary" id="load">Load images</button>
		</div>
		
		<div id="resultImages">
			
		</div>
		
		
	</div>


<script>
		var basepath="${pageContext.request.contextPath}/";
	</script>

	<script>
// Add the following code if you want the name of the file appear on select
$(".custom-file-input").on("change", function() {
  var fileName = $(this).val().split("\\").pop();
  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
});


$("#load").click(function(){
	  $.ajax({url: basepath+"images", 
		  success: function(result){	  
	    	$("#resultImages").empty();
	    	for(let i=0;i<result.length;i++){
	    		console.log(result[i]);
	    		let img ='<div><img src="web-resources/images/'+result[i]+'" style="width: 50px !important;height: 50px !important;"/></div>';
	    		$("#resultImages").append(img);
	    		
	    		
	    	}
	  	}
	  });
	});

</script>

</body>
</html>
