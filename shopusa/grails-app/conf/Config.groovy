// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }
 
grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// whether to install the java.util.logging bridge for sl4j. Disable for AppEngine!
grails.logging.jul.usebridge = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// set per-environment serverURL stem for creating absolute links
environments {
    production {
        grails.serverURL = "http://www.changeme.com"
    }
    development {
        grails.serverURL = "http://123.243.65.92:8080/shopusa"
    }
    test {
        grails.serverURL = "http://localhost:8080/shopusa"
    }

}

grails {
	mail {
	  host = "smtp.gmail.com"
	  port = 465
	  username = "info@buycheaper.com.au"
	  password = "gamesofthrone"
	  props = ["mail.smtp.auth":"true",
			   "mail.smtp.socketFactory.port":"465",
			   "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
			   "mail.smtp.socketFactory.fallback":"false"]
	}
 }

// log4j configuration
log4j.logger.grails.app.filters='debug'
grails.debug.enabled='true'
log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    appenders {
	    console name:'stdout', layout:pattern(conversionPattern: '%p [%t] %d{dd/MM/yyyy HH:mm:ss} %c | %m%n')
			
			//undocumented NullAppender to prevent stacktrace log file creation 
			'null' name:'stacktrace'
    }

	root {
		info 'stdout'
		additivity = true
	}
	
	debug 'grails.app'
	
    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

    warn   'org.mortbay.log'
}

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'au.com.shopusa.model.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'au.com.shopusa.model.UserRole'
grails.plugins.springsecurity.authority.className = 'au.com.shopusa.model.Role'

grails.plugins.springsecurity.useSwitchUserFilter = true
grails.plugins.springsecurity.switchUser.targetUrl = '/secure/'

grails.plugins.springsecurity.controllerAnnotations.staticRules = [
	'/':                              ['permitAll'],
	'/register/**':                   ['permitAll'],
	'/index.gsp':                     ['permitAll'],
	'/js/**':                         ['permitAll'],
	'/css/**':                        ['permitAll'],
	'/images/**':                     ['permitAll'],
	'/img/**':                     	  ['permitAll'],
	'/j_spring_security_switch_user': ['ROLE_SUPERVISOR'],
	'/**':                            ['permitAll']
]

//Spring security-ui plugin
grails.plugins.springsecurity.ui.register.defaultRoleNames = ['ROLE_CLIENT']
grails.plugins.springsecurity.ui.register.emailBody = '''\
Hi,<br/>
<br/>
You (or someone pretending to be you) created an account with this email address.<br/>
<br/>
If you made the request, please click <a href="$url">here</a> to finish the registration.
''' 

//PayPal settings
environments {
	production {
	   grails.paypal.server = "https://www.paypal.com/cgi-bin/webscr"
	   grails.paypal.email = "pavin_au@yahoo.com"
	}
	development {
	   grails.paypal.server = "https://www.sandbox.paypal.com/cgi-bin/webscr"
	   grails.paypal.email = "vitali_1302611850_biz@gmail.com"
	}
  }


ckeditor.upload.image.browser=true
ckeditor.upload.image.upload=true 

google.analytics.webPropertyID = 'UA-23241603-1'

//CloudBees
cloudbees.api.url='https://api.cloudbees.com/api'
cloudbees.api.key='8EAB9B1F46075707'
cloudbees.api.secret='HB4MOWKXHFIFLZ+F+K3+KWPSYQYJOJ1OX6AHTEUSEKY='