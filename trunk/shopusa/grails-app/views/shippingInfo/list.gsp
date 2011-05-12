
<%@ page import="au.com.shopusa.model.ShippingInfo" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'shippingInfo.label', default: 'ShippingInfo')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'shippingInfo.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="fullname" title="${message(code: 'shippingInfo.fullname.label', default: 'Fullname')}" />
                        
                            <g:sortableColumn property="addressLine" title="${message(code: 'shippingInfo.addressLine.label', default: 'Address Line')}" />
                        
                            <g:sortableColumn property="postcode" title="${message(code: 'shippingInfo.postcode.label', default: 'Postcode')}" />
                        
                            <g:sortableColumn property="suburb" title="${message(code: 'shippingInfo.suburb.label', default: 'Suburb')}" />
                        
                            <g:sortableColumn property="state" title="${message(code: 'shippingInfo.state.label', default: 'State')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${shippingInfoInstanceList}" status="i" var="shippingInfoInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${shippingInfoInstance.id}">${fieldValue(bean: shippingInfoInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: shippingInfoInstance, field: "fullname")}</td>
                        
                            <td>${fieldValue(bean: shippingInfoInstance, field: "addressLine")}</td>
                        
                            <td>${fieldValue(bean: shippingInfoInstance, field: "postcode")}</td>
                        
                            <td>${fieldValue(bean: shippingInfoInstance, field: "suburb")}</td>
                        
                            <td>${fieldValue(bean: shippingInfoInstance, field: "state")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${shippingInfoInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
