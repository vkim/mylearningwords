import grails.util.GrailsUtil

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
		
		//Paypal
		"/paypal/buy/$transactionId?"(controller:"paypal", action:"buy")
		"/paypal/notify/$buyerId/$transactionId"(controller:"paypal", action:"notify")
		"/paypal/success/$buyerId/$transactionId"(controller:"paypal", action:"success")
		"/paypal/cancel/$buyerId/$transactionId"(controller:"paypal", action:"cancel")
		if (GrailsUtil.isDevelopmentEnv()) {
			"/paypal/test"(view:"/paypal/test")
		}
		
		"/$pageId"(controller:"cmsPageRendering")
		
		"/"(controller:"cmsPageRendering")
		"500"(view:'/error')
	}
}
