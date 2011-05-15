package au.com.shopusa.web

import grails.test.*


class ShippingAddressFormTests extends GrailsUnitTestCase {
	
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testConstraints() {

		mockForConstraintsTests ShippingAddressCommand, [new ShippingAddressCommand()]
		
		def shipaddress = new ShippingAddressCommand() 
		
		shipaddress.validate()
		
		assertEquals( 3, shipaddress.errors.errorCount)
    }
}
