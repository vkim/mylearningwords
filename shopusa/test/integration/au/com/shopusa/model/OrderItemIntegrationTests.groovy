package au.com.shopusa.model

import grails.test.*

class OrderItemIntegrationTests extends GroovyTestCase {
	
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

	void testSaveThroughOrder() {

		def order = new ShipOrder(client: User.findByUsername('normal@com.au'), shippingInfo: new ShippingInfo())
		assert order.save()
				
		order.addToOrderItems(new OrderItem(name: 'item3', quantity: 1))
		order.save(flash: true)
		
		//verify
		assertNotNull OrderItem.findByName('item3')
	}
	
	void testGetItemsThrougOrder() {
		
		def order = new ShipOrder(client: User.findByUsername('normal@com.au'), shippingInfo: new ShippingInfo())
		order.save()
		order.addToOrderItems(new OrderItem(name: 'item3', quantity: 1))
		order.save(flash: true)
		
		def value = ShipOrder.get(order.id)
		assertEquals value.orderItems.size(), 1
		
	}
}
