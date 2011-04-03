<%@ page import="au.com.shopusa.cms.CmsPage" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'cmsPage.label', default: 'CmsPage')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
   		${content}
    </body>
</html>
