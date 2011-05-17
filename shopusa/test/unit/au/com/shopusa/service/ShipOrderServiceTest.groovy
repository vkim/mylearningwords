package au.com.shopusa.service;

import static org.junit.Assert.*
import grails.test.GrailsUnitTestCase
import au.com.shopusa.model.OrderItem
import au.com.shopusa.model.ShipOrder
import au.com.shopusa.model.ShippingAddress
import au.com.shopusa.model.ShippingInfo
import au.com.shopusa.model.User

class ShipOrderServiceTest extends GrailsUnitTestCase {

	def user
	def service
	
	public void setUp() {
		super.setUp();
		
		mockDomain User
		mockDomain ShipOrder	
		mockDomain OrderItem

		mockLogging ShipOrderService
				
		user = new User(username: 'email@com.ru', password: 'qioewprqer', shippingAddress: new ShippingAddress())
		user.save()
		
		service = new ShipOrderService()
	}
	
	
	void testCreateOrder() {
		
		//replay
		def order = service.createOrder(user)
		
		//verify
		assertNotNull(order)
		assertNotNull order.id
		assertFalse order.hasErrors()
	}
	
	public void testAddOrderItem() {
		
		def order = new ShipOrder(id: 1, client: user, shippingInfo: new ShippingInfo())
		//mock
		mockDomain(ShipOrder, [order])
		mockDomain(OrderItem) 
		
		OrderItem item = new OrderItem(name: 'test item1', quantity: 1)
		
		//replay
		assertNotNull service.addOrderItem(order.id, item)
		
		//verify
		println item.errors
		assert OrderItem.count() == 1
	}
	
	void testAddOrderItemWithoutRequiredFields() {
		
		def order = new ShipOrder(id: 1, client: user, shippingInfo: new ShippingInfo())
		//mock
		mockDomain(ShipOrder, [order])
		
		OrderItem item = new OrderItem()
		
		//replay
		item = service.addOrderItem(order.id, item)
			
		//verify
		assert item		
		assertTrue item.hasErrors()
	}
	
	public void testGetItems() {
		
		//mock
		OrderItem.metaClass.static.findAll = { query, args -> 
			assertEquals([10], args)
			[new OrderItem(), new OrderItem()]
		}
		
		//replay
		def orderId = 10
		def items = service.getItems(orderId)
		
		//verify
		assertEquals(2, items.size())
		
		//recover
		OrderItem.metaClass.static = null
	}
	
	public void testGetItemsWithEmptyOrderId() {
		
		def items = service.getItems(null)
		assertEquals(0, items.size())
	}
	
	public void testGetOrders() {

		def user = new User(email: 'useru')
		
		mockDomain(ShipOrder, [new ShipOrder(client: user), new ShipOrder(client: user)
			, new ShipOrder(client: new User(email: 'other'))])		
		
		
		//replay
		def orders = service.getOrders(user)
		
		//verify
		assertEquals(2, orders.size())
	}
	
}
