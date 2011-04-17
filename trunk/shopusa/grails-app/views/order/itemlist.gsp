<%@ page import="au.com.shopusa.model.OrderItem" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'orderItem.label', default: 'OrderItem')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="additem" params="[id: id]"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'orderItem.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="comment" title="${message(code: 'orderItem.comment.label', default: 'Comment')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'orderItem.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="quantity" title="${message(code: 'orderItem.quantity.label', default: 'Quantity')}" />
                        
                            <th><g:message code="orderItem.shipOrder.label" default="Ship Order" /></th>
                        
                            <g:sortableColumn property="throwWrap" title="${message(code: 'orderItem.throwWrap.label', default: 'Throw Wrapping')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${orderItemInstanceList}" status="i" var="orderItemInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${orderItemInstance.id}">${fieldValue(bean: orderItemInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: orderItemInstance, field: "comment")}</td>
                        
                            <td>${fieldValue(bean: orderItemInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: orderItemInstance, field: "quantity")}</td>
                        
                            <td>${fieldValue(bean: orderItemInstance, field: "shipOrder")}</td>
                        
                            <td><g:formatBoolean boolean="${orderItemInstance.throwWrap}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${orderItemInstanceTotal}" />
            </div>
            <paypal:button 
					itemName="iPod Nano"
					itemNumber="IPD0843403"
					transactionId="${payment?.transId}"
					amount="99.00"
					buyerId="${user.id}"
					/>
        </div>
    </body>
</html>
