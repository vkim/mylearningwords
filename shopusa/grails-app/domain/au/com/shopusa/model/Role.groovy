package au.com.shopusa.model

class Role {

	String authority
	
	public static final String ROLE_CLIENT = 'ROLE_CLIENT'
	public static final String ROLE_ADMIN = 'ROLE_ADMIN'

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
