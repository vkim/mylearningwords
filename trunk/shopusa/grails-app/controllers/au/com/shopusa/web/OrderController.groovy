package au.com.shopusa.web

import grails.plugins.springsecurity.Secured
import au.com.shopusa.model.OrderItem
import au.com.shopusa.model.ShipOrder
import au.com.shopusa.model.User
import au.com.shopusa.service.ShipOrderService


class OrderController {
	
	ShipOrderService shipOrderService
	def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

	@Secured(['ROLE_ADMIN'])
    def alllist = {
		
		def orders = ShipOrder.findAll() 
		
        render(view: 'list', model: [shipOrderInstanceList: orders, shipOrderInstanceTotal: orders.count()])
    }
	
	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		def user = getCurrentUser()
		
		def orders = shipOrderService.getOrders(user)
		
		[shipOrderInstanceList: orders, shipOrderInstanceTotal: orders.count()]
	}
	

    def create = {
		
		def order = shipOrderService.createOrder(User.findByUsername('normal@com.au'))
		
		log.debug 'Order created, id = ' + order.id
		
		redirect(action: "itemlist", id: order.id)
    }
	
	def additem = {
		def orderItemInstance = new OrderItem()
		orderItemInstance.properties = params
		return [orderItemInstance: orderItemInstance, id: params.id]
	}
	
	def saveitem = {
		
		def item = new OrderItem()
		item.properties = params
		
		def orderId = params.id
		
		if(shipOrderService.addOrderItem(orderId, item)) {
			redirect(action: "itemlist", id: orderId)
		}
		else {
			render(view: "additem", model: [orderItemInstance: item], id: orderId)
		}
	}
	
	def itemlist = {
		
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		def items = shipOrderService.getItems(Long.parseLong(params.id))
		
		def user = getCurrentUser();
		
		[orderItemInstanceList: items, orderItemInstanceTotal: items.count(), id: params.id, user: user]
	}

    def save = {
        def orderItemInstance = new OrderItem(params)
        if (orderItemInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'orderItem.label', default: 'OrderItem'), orderItemInstance.id])}"
            redirect(action: "show", id: orderItemInstance.id)
        }
        else {
            render(view: "create", model: [orderItemInstance: orderItemInstance])
        }
    }

    def show = {
        def orderItemInstance = OrderItem.get(params.id)
        if (!orderItemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'orderItem.label', default: 'OrderItem'), params.id])}"
            redirect(action: "list")
        }
        else {
            [orderItemInstance: orderItemInstance]
        }
    }

    def edit = {
        def orderItemInstance = OrderItem.get(params.id)
        if (!orderItemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'orderItem.label', default: 'OrderItem'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [orderItemInstance: orderItemInstance]
        }
    }

	def statusupdate = { ShipOrder form -> 
		
		def order = shipOrderService.getOrder(form.id)
		
		order.cost = form.cost;
		order.status = form.status;
		
		shipOrderService.save(order)
		
		redirect(action: itemlist);
	}
	
    def update = {
        def orderItemInstance = OrderItem.get(params.id)
        if (orderItemInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (orderItemInstance.version > version) {
                    
                    orderItemInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'orderItem.label', default: 'OrderItem')] as Object[], "Another user has updated this OrderItem while you were editing")
                    render(view: "edit", model: [orderItemInstance: orderItemInstance])
                    return
                }
            }
            orderItemInstance.properties = params
            if (!orderItemInstance.hasErrors() && orderItemInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'orderItem.label', default: 'OrderItem'), orderItemInstance.id])}"
                redirect(action: "show", id: orderItemInstance.id)
            }
            else {
                render(view: "edit", model: [orderItemInstance: orderItemInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'orderItem.label', default: 'OrderItem'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def orderItemInstance = OrderItem.get(params.id)
        if (orderItemInstance) {
            try {
                orderItemInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'orderItem.label', default: 'OrderItem'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'orderItem.label', default: 'OrderItem'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'orderItem.label', default: 'OrderItem'), params.id])}"
            redirect(action: "list")
        }
    }
	
	
	def getCurrentUser() {
		return springSecurityService.currentUser
	}
}