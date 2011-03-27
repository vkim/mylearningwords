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
		def mock = new MockFor(ShipOrderService)
		mock.demand.getItems { def orderId -> 
			assertEquals(108, orderId) 
			['',''] }
		controller.shipOrderService = mock.proxyInstance();
		
		//replay
		controller.params.id = '108';
		def model = controller.itemlist()
		
		//verify
		controller.renderArgs.view = 'itemlist'
		mock.verify(controller.shipOrderService)
		assertEquals(2, model.orderItemInstanceList.size())
		assertEquals('108', model.id)
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
	
}
