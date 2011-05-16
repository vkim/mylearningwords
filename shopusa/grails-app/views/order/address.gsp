<%@ page import="au.com.shopusa.model.ShipOrder" %>
				 
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
				margin-top: 0.5em;			
				padding: 4px;
			}
	        
        </style>
    </head>
    <body>
        <div class="body">
            <h1>Shipping address</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
				            
            <g:form action="address" id="${shipinfo?.id}">
            	<g:hiddenField name="shipinfo.id" value="${shipinfo?.id}"/>
				<fieldset>
					<div class="form-row">
						<div class="form-value ${hasErrors(bean: shipinfo, field: 'fullname', 'error')}"><label>Full name </label>
							<g:textField  size="32" name="fullname" value="${fieldValue(bean: shipinfo, field: 'fullname')}" class="text" />
						</div>
					</div>
					
					<div class="form-row">
						<div class="form-value ${hasErrors(bean: shipinfo, field: 'addressLine', 'error')}"><label>Street address </label>
							<g:textField  size="32" name="addressLine" value="${fieldValue(bean: shipinfo, field: 'addressLine')}" class="text" />
						</div>
					</div>

					<div class="form-row">
						<div class="form-value ${hasErrors(bean: shipinfo, field: 'city', 'error')}"><label>City </label>
							<g:textField  size="32" name="city" value="${fieldValue(bean: shipinfo, field: 'city')}" class="text" />
						</div>
					</div>
					
					<div class="form-row">
						<div class="form-value ${hasErrors(bean: shipinfo, field: 'postcode', 'error')}"><label>Postcode </label>
							<g:textField  size="32" name="postcode" value="${fieldValue(bean: shipinfo, field: 'postcode')}" class="text" />
						</div>
					</div>

					<div class="form-row">
						<div class="form-value ${hasErrors(bean: shipinfo, field: 'suburb', 'error')}"><label>Suburb </label>
							<g:textField  size="32" name="suburb" value="${fieldValue(bean: shipinfo, field: 'suburb')}" class="text" />
						</div>
					</div>
					
					<div class="form-row">
						<div class="form-value ${hasErrors(bean: shipinfo, field: 'state', 'error')}"><label>State </label>
							<g:textField  size="32" name="state" value="${fieldValue(bean: shipinfo, field: 'state')}" class="text" />
						</div>
					</div>
					
					<div class="form-row">
						<div class="form-value ${hasErrors(bean: shipinfo, field: 'contactPhone', 'error')}"><label>Contact Phone </label>
							<g:textField  size="32" name="contactPhone" value="${fieldValue(bean: shipinfo, field: 'contactPhone')}" class="text" />
						</div>	
					</div>

					<div class="form-row form-row-submit">
						<div class="form-value"><label></label><input type="submit" class="button" value="Save &#187;" /></div>

					</div>
				
				</fieldset>

			</g:form>	
            
        </div>
    </body>
</html>
