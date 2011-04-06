class UrlMappings {

	static mappings = {
		
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		
//		"/cmsPage/$action?/$id?"(controller:"cmsPage")
		
//		"/$pageId"(controller:"cmsPageRendering")
		

		"/"(controller:"cmsPageRendering")
		"500"(view:'/error')
	}
}
