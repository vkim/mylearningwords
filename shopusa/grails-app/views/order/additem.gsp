<%@ page import="au.com.shopusa.model.OrderItem" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'orderItem.label', default: 'OrderItem')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${orderItemInstance}">
            <div class="errors">
                <g:renderErrors bean="${orderItemInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="saveitem" id="${id}">
                <fieldset>
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="orderItem.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: orderItemInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" size="40" value="${orderItemInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="quantity"><g:message code="orderItem.quantity.label" default="Quantity" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: orderItemInstance, field: 'quantity', 'errors')}">
                                    <g:textField name="quantity" value="${fieldValue(bean: orderItemInstance, field: 'quantity')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="throwWrap"><g:message code="orderItem.throwWrap.label" default="Throw wrappings" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: orderItemInstance, field: 'throwWrap', 'errors')}">
                                    <g:checkBox name="throwWrap" value="${orderItemInstance?.throwWrap}" />
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="comment"><g:message code="orderItem.comment.label" default="Comment" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: orderItemInstance, field: 'comment', 'errors')}">
                                    <g:textArea name="comment" cols="40" rows="4" value="${orderItemInstance?.comment}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                
                <div class="form-row form-row-submit">
                    <div class="form-value"><g:submitButton name="create" class="button" value="${message(code: 'orderItem.button.create.label', default: 'Create')}" /></div>
                </div>
                
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
