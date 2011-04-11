class UrlMappings {

	static mappings = {
		
		/*"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}*/
		
		"/register/$action?"(controller:"register")
		
		"/cmsPage/$action?/$id?"(controller:"cmsPage")
		
		"/login/$action?"(controller: "login")
		"/logout/$action?"(controller: "logout")
		
		"/order/$action?/$id?"(controller:"order")
		
		"/$pageId"(controller:"cmsPageRendering")
		

		"/"(controller:"cmsPageRendering")
		"500"(view:'/error')
	}
}
