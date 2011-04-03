class UrlMappings {

	static mappings = {
		
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
