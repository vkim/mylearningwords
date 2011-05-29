<%@ page import="au.com.shopusa.model.OrderItem" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'orderItem.label', default: 'OrderItem')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
    	<g:if test="${order.status == au.com.shopusa.model.ShipOrder.Status.OPENED}">
	        <div class="nav">
	            <span class="menuButton"><g:link class="create" action="additem" params="[id: order.id]"><g:message code="orderItem.new.label" args="[entityName]" /></g:link></span>
	        </div>
	    </g:if>
        <div class="body">
            <h1><g:message code="orderItem.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                            <g:sortableColumn property="name" title="${message(code: 'orderItem.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="quantity" title="${message(code: 'orderItem.quantity.label', default: 'Quantity')}" />
                        
                            <g:sortableColumn property="throwWrap" title="${message(code: 'orderItem.throwWrap.label', default: 'Throw Wrapping')}" />
                            
                            <g:sortableColumn property="comment" title="${message(code: 'orderItem.comment.label', default: 'Comment')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${orderItemInstanceList}" status="i" var="orderItemInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${fieldValue(bean: orderItemInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: orderItemInstance, field: "quantity")}</td>
                        
                            <td><g:formatBoolean boolean="${orderItemInstance.throwWrap}" /></td>
                            
                            <td>${fieldValue(bean: orderItemInstance, field: "comment")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${orderItemInstanceTotal}" />
            </div>
            
            <sec:ifNotGranted roles="ROLE_ADMIN">
            
				<g:if test="${order.status == au.com.shopusa.model.ShipOrder.Status.OPENED}">
				
					<g:if test="${order.orderItems?.size() > 0 }">
						<g:form action="addressform" id="${order.id}">
			           		<div class="form-value"><input type="submit" class="button" value="Complete" /></div>
			            </g:form>
						
					</g:if>
		            	
				</g:if>            
				<g:else>
				
					<g:formatNumber number="${order?.cost}" type="number" format="########.##" minFractionDigits="2"/>
					
					<paypal:button 
						itemName="Shipping fee for order N ${order.id}"
						transactionId="${payment?.transId}"
						amount="${formatNumber(number:order?.cost, type:'number', format:'########.##', minFractionDigits:'2')}"
						buyerId="${user.id}"/>
				</g:else>            
            	
            </sec:ifNotGranted>
            
            <sec:ifAllGranted roles="ROLE_ADMIN">

				<g:form name="statusOrderForm" action="statusupdate" id="${order.id}">
					<table>
					<tr class="prop">
	                        <td valign="top" class="name">
	                            <label for="cost"><g:message code="shipOrder.cost.label" default="Cost" /></label>
	                        </td>
	                        <td valign="top" class="value ${hasErrors(bean: shipOrderInstance, field: 'cost', 'errors')}">
	                            <g:textField name="cost" value="${fieldValue(bean: order, field: 'cost')}" />
	                        </td>
	                    </tr>
					
					<tr class="prop">
	                        <td valign="top" class="name">
	                            <label for="status"><g:message code="shipOrder.status.label" default="Status" /></label>
	                        </td>
	                        <td valign="top" class="value ${hasErrors(bean: order, field: 'status', 'errors')}">
	                            <g:select name="status" from="${au.com.shopusa.model.ShipOrder$Status?.values()}" keys="${au.com.shopusa.model.ShipOrder$Status?.values()*.name()}" value="${order?.status?.name()}"  />
	                        </td>
	                    </tr>
	                    
					</table>	
				
				 	<div class="buttons">
		                  <span class="button"><g:submitButton name="update" class="save" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
		              </div>
		         </g:form>
	         
	       </sec:ifAllGranted>
        </div>
    </body>
</html>
