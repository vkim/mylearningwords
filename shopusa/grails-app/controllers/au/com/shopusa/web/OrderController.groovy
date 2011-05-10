package au.com.shopusa.web

import grails.plugins.springsecurity.Secured
import au.com.shopusa.model.OrderItem
import au.com.shopusa.model.ShipOrder
import au.com.shopusa.model.User
import au.com.shopusa.model.ShipOrder.Status;
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
		
		def order = shipOrderService.createOrder(getCurrentUser())
		
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
			println item.errors;
			render(view: "additem", model: [orderItemInstance: item], id: orderId)
		}
	}
	
	def itemlist = {
		
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		def order = ShipOrder.get(params.id)

		def user = getCurrentUser();
		
		[orderItemInstanceList: order?.orderItems, orderItemInstanceTotal: order?.orderItems?.count(), order:order, user: user]
	}
	
	def complete = {
		
		def order = ShipOrder.get(params.id)
		
		if(order) {
			order.status = ShipOrder.Status.COMPLETE
			order.save()
		}
		
		redirect(action: 'itemlist', id: params.id);
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

	def statusupdate = { OrderUpdate form -> 
		
		log.debug "Updating status order: " + form
		
		def order = ShipOrder.get(form.id)
		
		if(order) {
			order.cost = form.cost;
			order.status = form.status;
			
			order.save()
			log.debug 'Status updated: ' + order.status + ' errors: ' + order.errors
			
		}
		
		redirect(action: 'itemlist', id: form.id);
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

class OrderUpdate {
	Long id;
	Double cost;
	Status status
	
	String toString() {
		"$id, cost: $cost, status: $status"
	}
}