package au.com.shopusa.cms

import groovy.text.Template

import org.codehaus.groovy.grails.web.pages.GroovyPagesTemplateEngine

class CmsPageRenderer {

	GroovyPagesTemplateEngine groovyPagesTemplateEngine
	private Map pageTemplateCache = new HashMap()

	String renderPage(String pageId, Map model){
		StringWriter sw = new StringWriter()
		PrintWriter pw = new PrintWriter(sw)
		String pageName = pageId+'.gsp'
		Template template = pageTemplateCache.get(pageId)
		if(!template){
			CmsPage cmsPage = CmsPage.findByPageId(pageId)
			if(!cmsPage){
				throw new CmsPageDoesNotExistException(pageId)
			}
			template = groovyPagesTemplateEngine.createTemplate(cmsPage.content, pageName)
			pageTemplateCache.put(pageId, template)
		}
		template.make(model).writeTo(pw)
		return sw.toString()
	}

	void removeCmsPageFromCache(String pageId){
		pageTemplateCache.remove(pageId)
	}
}
