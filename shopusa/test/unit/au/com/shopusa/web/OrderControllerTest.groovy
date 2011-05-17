package au.com.shopusa.web;

import static org.junit.Assert.*
import grails.test.ControllerUnitTestCase
import groovy.mock.interceptor.MockFor

import org.junit.Before
import org.junit.Test

import au.com.shopusa.model.*
import au.com.shopusa.service.ShipOrderService

class OrderControllerTest extends ControllerUnitTestCase {

	@Before
	public void setUp() {
		super.setUp()
		mockDomain OrderItem
		mockDomain ShipOrder
		mockDomain User

		mockCommandObject(ShippingAddressCommand)
				
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
	
	public void testCreateOrderUnderUser() {
		//mock
		def mock = new MockFor(ShipOrderService)
		mock.demand.createOrder(1..1) {User user ->
				assertEquals("underuser@com.au", user.username)
				new ShipOrder(id: 2) 
			}
		controller.shipOrderService = mock.proxyInstance();
		controller.metaClass.getCurrentUser = { new User(username:'underuser@com.au') }
		
		//replay
		def model = controller.create()
		
		//verify
		assertEquals 'itemlist', controller.redirectArgs.action
		println controller.redirectArgs
		assertEquals 2, controller.redirectArgs.id
		mock.verify(controller.shipOrderService)
	}
	
	public void testSaveItemOrder() {
	
		mockDomain(OrderItem)
		
		def mock = new MockFor(ShipOrderService)
		mock.demand.addOrderItem(1..1) {def orderId, OrderItem item -> new OrderItem() }
		
		controller.shipOrderService = mock.proxyInstance(); 
		
		controller.params.id = 1 
		
		//replay
		controller.saveitem() 
		
		//verify
		mock.verify(controller.shipOrderService)
		assertEquals 'itemlist', controller.redirectArgs.action
		assertEquals 1, controller.redirectArgs.id
	}
	
	public void testSaveItemOrderWithErrorFromService() { 
		
		def mock = new MockFor(ShipOrderService)
		mock.demand.addOrderItem(1..1) {def orderId, OrderItem item -> null }
		
		controller.shipOrderService = mock.proxyInstance(); 
		
		controller.params.id = 2
		
		//replay
		controller.saveitem() 
		
		//verify
		mock.verify(controller.shipOrderService)
		assertEquals 'additem', controller.renderArgs.view
		assertEquals 2, controller.renderArgs.model.id
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
	
	public void testCompleteStatusChange() {
		
		//mock
		mockDomain(ShipOrder, [new ShipOrder(id: 12, status: ShipOrder.Status.OPENED)])
		
		controller.params.id = "12"
		
		//replay
		controller.complete()
		
		//verify
		assertEquals(ShipOrder.Status.COMPLETE, ShipOrder.get(12)?.status)
		assertEquals 'itemlist', controller.redirectArgs.action
	}
	
	void testShippingAddressConstraints() {

		def form = new ShippingAddressCommand()
		assertFalse form.validate()
		assertEquals 7, form.errors.errorCount
		assertEquals "nullable", form.errors["suburb"]
		assertEquals "nullable", form.errors["state"]
		
		
		form = new ShippingAddressCommand(suburb: '', state: '')
		assertFalse form.validate()
		assertEquals 7, form.errors.errorCount
		assertEquals "blank", form.errors["suburb"]
		assertEquals "blank", form.errors["state"]
		
		form = new ShippingAddressCommand(suburb: 'kingsford', state: 'nsw')
		assertFalse form.validate()
		assertEquals 5, form.errors.errorCount
	}
	
	void testAddShippingAddressToOrderInformation() {
		
		def info = new ShippingInfo()
		def order = new ShipOrder(id: 5, shippingInfo: info)
		info.shipOrder = order
		mockDomain ShippingInfo, [info]
		mockDomain ShipOrder, [order]
		
		//replay
		controller.address(new ShippingAddressCommand(id: 5, fullname: 'vitaliy', addressLine: 'my address'
			, postcode: '2032', city: 'sydney', suburb: 'kingsford', state: 'nsw', contactPhone: '1320000'))
		
		//verify
		assertEquals('vitaliy', ShipOrder.get(5)?.shippingInfo.fullname)
	}

}