<%@ page import="au.com.shopusa.cms.CmsPage" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'cmsPage.label', default: 'CmsPage')}" />
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
            <div class="success">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'cmsPage.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="pageId" title="${message(code: 'cmsPage.pageId.label', default: 'Page Id')}" />
                        
                            <g:sortableColumn property="lastUpdated" title="${message(code: 'cmsPage.lastUpdated.label', default: 'Last updated')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${cmsPageInstanceList}" status="i" var="cmsPageInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${cmsPageInstance.id}">${fieldValue(bean: cmsPageInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: cmsPageInstance, field: "pageId")}</td>
                        
                            <td>${fieldValue(bean: cmsPageInstance, field: "lastUpdated")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${cmsPageInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
