

<%@ page import="au.com.shopusa.model.ShipOrder" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'shipOrder.label', default: 'ShipOrder')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${shipOrderInstance}">
            <div class="errors">
                <g:renderErrors bean="${shipOrderInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="client"><g:message code="shipOrder.client.label" default="Client" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: shipOrderInstance, field: 'client', 'errors')}">
                                    <g:select name="client.id" from="${au.com.shopusa.model.User.list()}" optionKey="id" value="${shipOrderInstance?.client?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cost"><g:message code="shipOrder.cost.label" default="Cost" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: shipOrderInstance, field: 'cost', 'errors')}">
                                    <g:textField name="cost" value="${fieldValue(bean: shipOrderInstance, field: 'cost')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="shippingInfo"><g:message code="shipOrder.shippingInfo.label" default="Shipping Info" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: shipOrderInstance, field: 'shippingInfo', 'errors')}">
                                    <g:select name="shippingInfo.id" from="${au.com.shopusa.model.ShippingInfo.list()}" optionKey="id" value="${shipOrderInstance?.shippingInfo?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="trackNumber"><g:message code="shipOrder.trackNumber.label" default="Track Number" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: shipOrderInstance, field: 'trackNumber', 'errors')}">
                                    <g:textField name="trackNumber" value="${shipOrderInstance?.trackNumber}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
