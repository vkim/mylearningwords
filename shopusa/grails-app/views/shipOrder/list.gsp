
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
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'shipOrder.id.label', default: 'Id')}" />
                        
                            <th><g:message code="shipOrder.client.label" default="Client" /></th>
                        
                            <g:sortableColumn property="cost" title="${message(code: 'shipOrder.cost.label', default: 'Cost')}" />
                        
                            <th><g:message code="shipOrder.shippingInfo.label" default="Shipping Info" /></th>
                        
                            <g:sortableColumn property="trackNumber" title="${message(code: 'shipOrder.trackNumber.label', default: 'Track Number')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${shipOrderInstanceList}" status="i" var="shipOrderInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${shipOrderInstance.id}">${fieldValue(bean: shipOrderInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: shipOrderInstance, field: "client")}</td>
                        
                            <td>${fieldValue(bean: shipOrderInstance, field: "cost")}</td>
                        
                            <td>${fieldValue(bean: shipOrderInstance, field: "shippingInfo")}</td>
                        
                            <td>${fieldValue(bean: shipOrderInstance, field: "trackNumber")}</td>
                        
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
