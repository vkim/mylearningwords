import au.com.shopusa.cms.CmsPageRenderer

// Place your Spring DSL code here
beans = {
	cmsPageRenderer(CmsPageRenderer){
		groovyPagesTemplateEngine = ref('groovyPagesTemplateEngine')
	 }
	  
}
