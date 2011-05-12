

<%@ page import="au.com.shopusa.model.ShippingInfo" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'shippingInfo.label', default: 'ShippingInfo')}" />
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
            <g:hasErrors bean="${shippingInfoInstance}">
            <div class="errors">
                <g:renderErrors bean="${shippingInfoInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="fullname"><g:message code="shippingInfo.fullname.label" default="Fullname" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: shippingInfoInstance, field: 'fullname', 'errors')}">
                                    <g:textField name="fullname" value="${shippingInfoInstance?.fullname}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="addressLine"><g:message code="shippingInfo.addressLine.label" default="Address Line" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: shippingInfoInstance, field: 'addressLine', 'errors')}">
                                    <g:textField name="addressLine" value="${shippingInfoInstance?.addressLine}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="postcode"><g:message code="shippingInfo.postcode.label" default="Postcode" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: shippingInfoInstance, field: 'postcode', 'errors')}">
                                    <g:textField name="postcode" value="${shippingInfoInstance?.postcode}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="suburb"><g:message code="shippingInfo.suburb.label" default="Suburb" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: shippingInfoInstance, field: 'suburb', 'errors')}">
                                    <g:textField name="suburb" value="${shippingInfoInstance?.suburb}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="state"><g:message code="shippingInfo.state.label" default="State" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: shippingInfoInstance, field: 'state', 'errors')}">
                                    <g:textField name="state" value="${shippingInfoInstance?.state}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="contactPhone"><g:message code="shippingInfo.contactPhone.label" default="Contact Phone" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: shippingInfoInstance, field: 'contactPhone', 'errors')}">
                                    <g:textField name="contactPhone" value="${shippingInfoInstance?.contactPhone}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="city"><g:message code="shippingInfo.city.label" default="City" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: shippingInfoInstance, field: 'city', 'errors')}">
                                    <g:textField name="city" value="${shippingInfoInstance?.city}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="shipOrder"><g:message code="shippingInfo.shipOrder.label" default="Ship Order" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: shippingInfoInstance, field: 'shipOrder', 'errors')}">
                                    <g:select name="shipOrder.id" from="${au.com.shopusa.model.ShipOrder.list()}" optionKey="id" value="${shippingInfoInstance?.shipOrder?.id}"  />
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
