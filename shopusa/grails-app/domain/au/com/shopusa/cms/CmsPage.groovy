package au.com.shopusa.cms

class CmsPage {

	String pageId
	String content
	Date lastUpdated


	static constraints = {
		pageId(nullable:false, blank:false, unique:true, matches:"[a-zA-Z\\._-]+")
		content(nullable:false, blank:false)
	}

	static mapping = { content(type:"text") }
}