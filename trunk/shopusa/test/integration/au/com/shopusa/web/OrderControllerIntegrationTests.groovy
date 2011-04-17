package au.com.shopusa.web

import grails.test.*
import au.com.shopusa.model.ShipOrder
import au.com.shopusa.model.User


class OrderControllerIntegrationTests extends ControllerUnitTestCase {
	
	def controller
	
    protected void setUp() {
        super.setUp()
		
		controller = new OrderController()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCreateOrderAndPutOneItem() {

		//replay
		controller.create()
		
		//verify
		assertEquals 'itemlist', controller.redirectArgs.action
		def orderlist = ShipOrder.findAllByClient(User.findByUsername('normal@com.au'))
		assertEquals(1, orderlist.size())
		def order = orderlist.get(0)
		assertNotNull order
		
		/////############### put one item

		reset()
		
		controller.params.name = 'notebook'
		controller.params.id = order.id 
		
		//replay
		controller.saveitem()
		
		//verify
		assertEquals 'itemlist', controller.redirectArgs.action
		assertEquals(1, ShipOrder.get(order.id).orderItems.size())
    }
}
