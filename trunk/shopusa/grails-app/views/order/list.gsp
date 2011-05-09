
<%@ page import="au.com.shopusa.model.ShipOrder" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'shipOrder.label', default: 'ShipOrder')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="create" action="create"><g:message code="order.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="order.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'shipOrder.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="cost" title="${message(code: 'shipOrder.cost.label', default: 'Cost')}" />
                        
                            <g:sortableColumn property="status" title="${message(code: 'shipOrder.status.label', default: 'Status')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${shipOrderInstanceList}" status="i" var="shipOrderInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="itemlist" id="${shipOrderInstance.id}">${fieldValue(bean: shipOrderInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: shipOrderInstance, field: "cost")}</td>
                        
                            <td>${fieldValue(bean: shipOrderInstance, field: "status")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${shipOrderInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
