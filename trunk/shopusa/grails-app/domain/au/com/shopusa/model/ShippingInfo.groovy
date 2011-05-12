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
		fullname(blank: false)
		addressLine(blank: false)
		postcode(blank: false)
		suburb(blank: false)
		state(blank: false)
		contactPhone(blank: false)
    }
}
