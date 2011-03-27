package au.com.shopusa.service

import au.com.shopusa.model.OrderItem
import au.com.shopusa.model.ShipOrder
import au.com.shopusa.model.ShippingInfo
import au.com.shopusa.model.User

class ShipOrderService {

    static transactional = true

	public ShipOrder createOrder(User client) {
		
		def order = new ShipOrder(client: client, shippingInfo: new ShippingInfo())
		order.save()
		
		log.info('Order created: ' + order);
		
		return order
	}
	
    ShipOrder addOrderItem(orderid, OrderItem item) {
		
		log.debug('adding item to order: ' + orderid + ' with item: ' + item);
		
		if(!orderid) {
			return null 
		}
		
		def order = ShipOrder.get(orderid)
		
		order.addToOrderItems(item)
		order.save()
		
		log.info 'OrderItem added to order: ' + order
		return order;		
    }
	
	def getItems(orderId) {
		
		if(!orderId) {
			return []
		}
		
		return OrderItem.findAll('from OrderItem as i where i.shipOrder.id = ?', [orderId])
	}
	
	def getOrders(user) {
		
		if(! user) {
			return []
		}
		
		return ShipOrder.findAllByClient(user)
	}
}
