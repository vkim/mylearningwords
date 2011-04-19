package au.com.shopusa.web;

import static org.junit.Assert.*
import grails.test.ControllerUnitTestCase
import groovy.mock.interceptor.MockFor
import au.com.shopusa.model.*
import au.com.shopusa.service.ShipOrderService

class OrderControllerTest extends ControllerUnitTestCase {

	public void setUp() {
		super.setUp()
		mockDomain OrderItem
		mockDomain User
		
		controller.metaClass.getCurrentUser = { new User(email:'normal@com.au') }
	}
	
	
	public void testGetAllListByAdmin() {
		
		//mock
		mockDomain(ShipOrder, [new ShipOrder(), new ShipOrder(), new ShipOrder(), new ShipOrder()])
		
		//replay
		def model = controller.alllist()
		
		//verify
		controller.renderArgs.view = 'list'
		assertEquals(4, model.shipOrderInstanceList.size())
	}
	
	public void testCreateOrder() {
		
		//mock
		def mock = new MockFor(ShipOrderService)
		mock.demand.createOrder(1..1) { new ShipOrder(id: 2) }
		controller.shipOrderService = mock.proxyInstance();
		
		//replay
		def model = controller.create()
		
		//verify
		assertEquals 'itemlist', controller.redirectArgs.action
		println controller.redirectArgs
		assertEquals 2, controller.redirectArgs.id
		mock.verify(controller.shipOrderService)
	}
	
	public void testSaveItemOrder() {
	
		def mock = new MockFor(ShipOrderService)
		mock.demand.addOrderItem(1..1) {def orderId, OrderItem item -> new ShipOrder() }
		
		controller.shipOrderService = mock.proxyInstance(); 
		
		controller.params.id = 1 
		
		//replay
		controller.saveitem() 
		
		//verify
		mock.verify(controller.shipOrderService)
		assertEquals 'itemlist', controller.redirectArgs.action
		assertEquals 1, controller.redirectArgs.id
	}
	
	public void testSaveItemOrderWithError() { 
		
		def mock = new MockFor(ShipOrderService)
		mock.demand.addOrderItem(1..1) {def orderId, OrderItem item -> null }
		
		controller.shipOrderService = mock.proxyInstance(); 
		
		controller.params.id = 2
		
		//replay
		controller.saveitem() 
		
		//verify
		mock.verify(controller.shipOrderService)
		assertEquals 'additem', controller.renderArgs.view
		assertEquals 2, controller.renderArgs.id
	}
	
	public void testItemList() {
		
		//mock
		mockDomain(ShipOrder,[new ShipOrder(id: 108, orderItems: [new OrderItem(), new OrderItem()])])
		
		//replay
		controller.params.id = 108;
		def model = controller.itemlist()
		
		//verify
		controller.renderArgs.view = 'itemlist'
		assertNotNull(model.order)
		assertEquals(108, model.order.id)
		assertNotNull(model.user)
	}
	
	
	public void testGetOrders() {
		
		def tuser = new User(email: 'testuser')
		
		//mock
		def mock = new MockFor(ShipOrderService)
		mock.demand.getOrders { user -> 
			assertEquals(tuser, user) 
			['','', ''] }
		controller.shipOrderService = mock.proxyInstance();
		
		controller.metaClass.getCurrentUser = {tuser}
		
		//replay
		def model = controller.list()
		
		//verify
		controller.renderArgs.view = 'list'
		assertEquals(3, model.shipOrderInstanceList.size())
	}
	
	public void testUpdateOrderStatusByManager() {
		
		//mock
		mockDomain(ShipOrder, [new ShipOrder(id: 11, cost: 0)])
		
		def order = new OrderUpdate(id: 11, status: ShipOrder.Status.PAYMENT_WAITING, cost: 14.00d)
		
		//replay
		controller.statusupdate(order)
		
		//verify
		assertEquals(14d, ShipOrder.get(11).cost, 0)
		assertEquals(ShipOrder.Status.PAYMENT_WAITING, ShipOrder.get(11).status)
	}
	
}
