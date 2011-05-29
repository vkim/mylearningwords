package au.com.shopusa.model

import org.codehaus.groovy.grails.commons.ApplicationHolder

class ShipOrder {

	Double cost = 0d;
	String trackNumber = '';
	User client
	Status status = Status.OPENED
	
	Date dateCreated
	Date lastUpdated
	
    static constraints = {
		status(blank:false)
    }
	
	static hasMany = [orderItems : OrderItem]
	static hasOne = [shippingInfo: ShippingInfo]
	static belongsTo = User

	enum Status {
		OPENED
		,COMPLETE
		,PAYMENT_WAITING
		,SHIPPED
		
		public final String getValue() {
			ApplicationHolder.application.getMainContext().getBean("messageSource").getMessage('shiporder.status.' + name(), null, '', Locale.ENGLISH)
		}
	}
	
	String toString() {
		"$id, $client"
	} 
}
