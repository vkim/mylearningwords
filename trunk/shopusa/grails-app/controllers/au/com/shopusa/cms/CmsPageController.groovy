package au.com.shopusa.cms

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class CmsPageController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	CmsPageRenderer cmsPageRenderer
	
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [cmsPageInstanceList: CmsPage.list(params), cmsPageInstanceTotal: CmsPage.count()]
    }

    def create = {
        def cmsPageInstance = new CmsPage()
        cmsPageInstance.properties = params
        return [cmsPageInstance: cmsPageInstance]
    }

    def save = {
        def cmsPageInstance = new CmsPage(params)
        if (cmsPageInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'cmsPage.label', default: 'CmsPage'), cmsPageInstance.id])}"
			
            redirect(action: "show", id: cmsPageInstance.id)
        }
        else {
            render(view: "create", model: [cmsPageInstance: cmsPageInstance])
        }
    }

    def show = {
		println params.id
        def cmsPageInstance = CmsPage.get(params.id)
        if (!cmsPageInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cmsPage.label', default: 'CmsPage'), params.id])}"
            redirect(action: "list")
        }
        else {
            [cmsPageInstance: cmsPageInstance]
        }
    }

    def edit = {
        def cmsPageInstance = CmsPage.get(params.id)
        if (!cmsPageInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cmsPage.label', default: 'CmsPage'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [cmsPageInstance: cmsPageInstance]
        }
    }

    def update = {
        def cmsPageInstance = CmsPage.get(params.id)
        if (cmsPageInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (cmsPageInstance.version > version) {
                    
                    cmsPageInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'cmsPage.label', default: 'CmsPage')] as Object[], "Another user has updated this CmsPage while you were editing")
                    render(view: "edit", model: [cmsPageInstance: cmsPageInstance])
                    return
                }
            }
            cmsPageInstance.properties = params
            if (!cmsPageInstance.hasErrors() && cmsPageInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'cmsPage.label', default: 'CmsPage'), cmsPageInstance.id])}"
				
				//reset cache
				cmsPageRenderer.removeCmsPageFromCache cmsPageInstance.pageId
				
                redirect(action: "show", id: cmsPageInstance.id)
            }
            else {
                render(view: "edit", model: [cmsPageInstance: cmsPageInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cmsPage.label', default: 'CmsPage'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def cmsPageInstance = CmsPage.get(params.id)
        if (cmsPageInstance) {
            try {
                cmsPageInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'cmsPage.label', default: 'CmsPage'), params.id])}"
				
				cmsPageRenderer.removeCmsPageFromCache cmsPageInstance.pageId
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'cmsPage.label', default: 'CmsPage'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cmsPage.label', default: 'CmsPage'), params.id])}"
            redirect(action: "list")
        }
    }
}
