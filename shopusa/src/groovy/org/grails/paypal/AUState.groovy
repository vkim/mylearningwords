package org.grails.paypal

enum AUState {
ACT('Australian Capital Territory'),
	NSW('New South Wales'),
	VIC('Victoria'),
	QLD('Queensland'),
	SA('South Australia'),
	WA('Western Australia'),
	TAS('Tasmania'),
	NT('Northern Territory')

	private AUState(String longName) {
		this.longName = longName
	}

	final String longName
}
