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
		mockDomain(OrderItem)

		mockLogging ShipOrderService
				
		user = new User(email: 'email@com.ru', password: 'qioewprqer', shippingAddress: new ShippingAddress())
		user.save()
		
		service = new ShipOrderService()
	}
	
	
	public void testCreateOrder() {
		
		//replay
		def order = service.createOrder(user)
		
		//verify
		assertNotNull(order)
		assertNotNull order.id
	}
	
	public void testAddOrderItem() {
		
		def order = new ShipOrder(id: 1, client: user, shippingInfo: new ShippingInfo())
		//mock
		mockDomain(ShipOrder, [order]) 
		
		OrderItem item = new OrderItem(name: 'test item1', quantity: 1)
		
		//replay
		order = service.addOrderItem(order.id, item)
		
		//verify
		assertNotNull order
		assert order.orderItems.size() == 1
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
