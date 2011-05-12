
<%@ page import="au.com.shopusa.model.ShippingInfo" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'shippingInfo.label', default: 'ShippingInfo')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="shippingInfo.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: shippingInfoInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="shippingInfo.fullname.label" default="Fullname" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: shippingInfoInstance, field: "fullname")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="shippingInfo.addressLine.label" default="Address Line" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: shippingInfoInstance, field: "addressLine")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="shippingInfo.postcode.label" default="Postcode" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: shippingInfoInstance, field: "postcode")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="shippingInfo.suburb.label" default="Suburb" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: shippingInfoInstance, field: "suburb")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="shippingInfo.state.label" default="State" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: shippingInfoInstance, field: "state")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="shippingInfo.contactPhone.label" default="Contact Phone" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: shippingInfoInstance, field: "contactPhone")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="shippingInfo.city.label" default="City" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: shippingInfoInstance, field: "city")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="shippingInfo.shipOrder.label" default="Ship Order" /></td>
                            
                            <td valign="top" class="value"><g:link controller="shipOrder" action="show" id="${shippingInfoInstance?.shipOrder?.id}">${shippingInfoInstance?.shipOrder?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${shippingInfoInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
