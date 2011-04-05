class UrlMappings {

	static mappings = {
		
		"/cmsPage/$action?/$id?"(controller:"cmsPage")
		
		"/$pageId"(controller:"cmsPageRendering")
		
		
		
		
		/*"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}*/

		"/"(controller:"cmsPageRendering")
		"500"(view:'/error')
	}
}
