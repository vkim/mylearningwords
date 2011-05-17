package au.com.shopusa.model

class OrderItem implements Serializable {

	String name
	String comment = ''
	Boolean throwWrap = true
	Byte quantity = 1

	ShipOrder shipOrder
	
	static belongsTo = ShipOrder  
	
	
    static constraints = {
		name(nullable: false, blank: false)
		quantity(nullable: false)
    }
	
	String toString() {
		"$name, $quantity"
	}
	
}
