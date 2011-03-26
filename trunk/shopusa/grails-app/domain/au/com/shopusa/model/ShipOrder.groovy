package au.com.shopusa.model

class ShipOrder {

	Double cost = 0d;
	String trackNumber = '';
	User client
	
    static constraints = {
    }
	
	static hasMany = [orderItems : OrderItem]
	static hasOne = [shippingInfo: ShippingInfo]
	static belongsTo = User
	
	String toString() {
		"$id, $client"
	} 
}
