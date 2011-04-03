package au.com.shopusa.cms

class CmsPageRenderingController {

  CmsPageRenderer cmsPageRenderer

  def renderPage = {

	  //setup default page    
	 params.pageId = params.pageId ?: 'home'

    try {
      render(view:"body", model: [content: cmsPageRenderer.renderPage(params.pageId, params)])
    } catch(CmsPageDoesNotExistException e){
      response.sendError(404)
      return
    }
  }
}
