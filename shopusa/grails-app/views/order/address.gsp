<%@ page import="au.com.shopusa.model.OrderItem" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'orderItem.label', default: 'OrderItem')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <style type="text/css">
        .form-value label {
			display: block;
			float: left;
			width: 160px;
			text-align: right;
			margin-right: 9px;
		}
        
        </style>
    </head>
    <body>
    	<g:if test="${order.status == au.com.shopusa.model.ShipOrder.Status.OPENED}">
	        <div class="nav">
	            
	        </div>
	    </g:if>
        <div class="body">
            <h1>Shipping address</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
				            
            
			<g:if test="${order.status == au.com.shopusa.model.ShipOrder.Status.OPENED}">
				
	            <form action="" method="post" id="reply">

					<fieldset>
						<div class="form-row">
							
							<div class="form-value"><label>Full name </label><input type="text" size="32" name="email" value="" class="text" /></div>

						</div>
						
						<div class="form-row">
							
							<div class="form-value"><label>Street address</label><input type="text" size="32" name="website" value="" class="text" /></div>

						</div>

						<div class="form-row">
							
							<div class="form-value"><label>City</label><input type="text" size="32" name="name" value="" class="text" /></div>

						</div>
						
						<div class="form-row">
							
							<div class="form-value"><label>Postcode</label><input type="text" size="32" name="name" value="" class="text" /></div>

						</div>

						<div class="form-row">
							
							<div class="form-value"><label>Suburb</label><input type="text" size="32" name="website" value="" class="text" /></div>

						</div>
						
						<div class="form-row">
							
							<div class="form-value"><label>State</label><input type="text" size="32" name="website" value="" class="text" /></div>

						</div>
						
						<div class="form-row">
							
							<div class="form-value"><label>Contact Phone</label><input type="text" size="32" name="website" value="" class="text" /></div>

						</div>

						<div class="form-row form-row-submit">
							
							<div class="form-value"><input type="submit" class="button" value="Submit Comment &#187;" /></div>

							<div class="clearer">&nbsp;</div>

						</div>
					
					</fieldset>

				</form>	
			</g:if>            
            
        </div>
    </body>
</html>