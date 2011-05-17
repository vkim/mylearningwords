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
	
    OrderItem addOrderItem(orderid, OrderItem item) {
		
		log.debug('adding item to order: ' + orderid + ' with item: ' + item);
		
		def order
		
		if(!orderid || (order = ShipOrder.get(orderid)) == null) {
			return item 
		}
		
		item.shipOrder = order
		
		if(item && item.validate()) {
			item.save()
		}
		
		log.info 'OrderItem added to order: ' + order
		return item;		
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
	
	def save(order) {
		
		if(order) {
			return order.save();
		}
		
		return order
	}
	
	
}
