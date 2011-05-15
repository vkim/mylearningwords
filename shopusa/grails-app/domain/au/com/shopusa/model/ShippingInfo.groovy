package au.com.shopusa.model

class ShippingInfo {

	String fullname
	String addressLine
	String city
	String postcode
	String suburb
	String state
	String contactPhone
	
	ShipOrder shipOrder
	
    static constraints = {
		fullname(nullable: true)
		addressLine(nullable: true)
		city(nullable: true)
		postcode(nullable: true)
		suburb(nullable: true)
		state(nullable: true)
		contactPhone(nullable: true)
    }
}
