package au.com.shopusa.model

class ShippingInfoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [shippingInfoInstanceList: ShippingInfo.list(params), shippingInfoInstanceTotal: ShippingInfo.count()]
    }


    def save = {
        def shippingInfoInstance = new ShippingInfo(params)
        if (shippingInfoInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'shippingInfo.label', default: 'ShippingInfo'), shippingInfoInstance.id])}"
            redirect(action: "show", id: shippingInfoInstance.id)
        }
        else {
            render(view: "create", model: [shippingInfoInstance: shippingInfoInstance])
        }
    }

    def show = {
        def shippingInfoInstance = ShippingInfo.get(params.id)
        if (!shippingInfoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'shippingInfo.label', default: 'ShippingInfo'), params.id])}"
            redirect(action: "list")
        }
        else {
            [shippingInfoInstance: shippingInfoInstance]
        }
    }

    def edit = {
        def shippingInfoInstance = ShippingInfo.get(params.id)
        if (!shippingInfoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'shippingInfo.label', default: 'ShippingInfo'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [shippingInfoInstance: shippingInfoInstance]
        }
    }

    def update = {
        def shippingInfoInstance = ShippingInfo.get(params.id)
        if (shippingInfoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (shippingInfoInstance.version > version) {
                    
                    shippingInfoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'shippingInfo.label', default: 'ShippingInfo')] as Object[], "Another user has updated this ShippingInfo while you were editing")
                    render(view: "edit", model: [shippingInfoInstance: shippingInfoInstance])
                    return
                }
            }
            shippingInfoInstance.properties = params
            if (!shippingInfoInstance.hasErrors() && shippingInfoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'shippingInfo.label', default: 'ShippingInfo'), shippingInfoInstance.id])}"
                redirect(action: "show", id: shippingInfoInstance.id)
            }
            else {
                render(view: "edit", model: [shippingInfoInstance: shippingInfoInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'shippingInfo.label', default: 'ShippingInfo'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def shippingInfoInstance = ShippingInfo.get(params.id)
        if (shippingInfoInstance) {
            try {
                shippingInfoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'shippingInfo.label', default: 'ShippingInfo'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'shippingInfo.label', default: 'ShippingInfo'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'shippingInfo.label', default: 'ShippingInfo'), params.id])}"
            redirect(action: "list")
        }
    }
}
