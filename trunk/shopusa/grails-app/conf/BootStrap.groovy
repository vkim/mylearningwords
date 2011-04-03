import grails.util.Environment
import au.com.shopusa.cms.CmsPage;
import au.com.shopusa.model.Role
import au.com.shopusa.model.ShippingAddress
import au.com.shopusa.model.User
import au.com.shopusa.model.UserRole

class BootStrap {

	def springSecurityService

	def init = { servletContext ->

		switch(Environment.current) {

			case Environment.TEST:
				createIntegrationTestData()
				break

			case Environment.DEVELOPMENT:
			    createIntegrationTestData()
				break

			case Environment.PRODUCTION:
				break
		}

	}
	def destroy = {
	}


	void createIntegrationTestData() {

		//Security and users
		//test users
		def clientRole = new Role(authority: Role.ROLE_CLIENT).save(flush: true)
		def adminRole = new Role(authority: Role.ROLE_ADMIN).save(flush: true)

		assert Role.count() == 2

		//expert test user
		String password = springSecurityService.encodePassword('password')
		def testUser = new User(enabled: true, password: password, email: 'admin@com.au', shippingAddress: new ShippingAddress())
		testUser.save(flush: true)

		assert User.count() == 1

		UserRole.create testUser, adminRole, true
		UserRole.create testUser, clientRole, true

		assert UserRole.count() == 2

		//normal user
		def normalUser = new User(enabled: true, password: password, email: 'normal@com.au', shippingAddress: new ShippingAddress())
		normalUser.save(flush: true)
		assert User.count() == 2

		UserRole.create normalUser, clientRole, true
		assert UserRole.count() == 3
		
		//default home page
		new CmsPage(pageId: 'home', content: homePage).save()
		new CmsPage(pageId: 'about', content: about).save()
		
		println '####################### Data has been loaded ####################'
	}

	def about = """
	Don't want to spend much money on new...? Do you want to buy cheaper ... for you, your family or friends? Do you want to buy modern (latest) model ...? If the answer is YES, then we can help you to turn your dream into reality! With our company you can buy the cheapest in the world ... from the best shops of the USA and get it delivered right to your door even if they don’t deliver to Australia. You will be surprised with our low prices on delivery that are not much higher than local delivery. All you need to do is to buy product from your favorite shop in the USA and place the delivery order on our website, the rest we will do for you.
	"""
	
	def homePage = """
	<div id="content-wrapper">
	<div class="center-wrapper">
		
		<div class="content" id="content-two-columns">

			<div id="main-wrapper">
				<div id="main">

					<div class="post">

						<div class="post-title"><h1>Super Software</h1></div>
						
						<div class="post-body">

							<p><img src="sample-thumbnail-2.jpg" width="640" height="200" alt="" class="bordered" /></p>

							<h2 class="quiet">Introducing the next generation software</h2>
						
							<p class="large">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut tincidunt ultricies lectus eget molestie. Nullam ut neque risus, eu consectetur sem.</p>
							
							<p>Duis iaculis, velit sit amet molestie scelerisque, neque leo ullamcorper dui, vitae suscipit risus metus non nulla. Vivamus convallis mattis erat in tempus. Curabitur vitae risus risus, vitae bibendum nisi. Aliquam erat volutpat.</p>
							
							<p>Aenean lectus eros, consectetur pulvinar blandit non, varius nec magna. Praesent posuere sapien vel sem egestas commodo. Fusce bibendum nulla sit amet metus iaculis pharetra. Maecenas venenatis ullamcorper pharetra. Cras tincidunt lobortis congue. Sed aliquet purus sit amet massa fringilla auctor. Suspendisse nec nulla neque, eu eleifend leo.</p>

							<p class="large"><a href="#">Try it out</a> <span class="text-separator">|</span> <a href="#">Buy now</a> <span class="text-separator">|</span> <a href="#">Detailed information</a></p>
						
						</div>
						
					</div>

				</div>
			</div>

			<div id="sidebar-wrapper">
				<div id="sidebar">

					<div class="box">

						<div class="box-title">Products</div>

						<div class="box-content">
							<ul class="nice-list">
								<li><strong><a href="#">Super Software</a></strong></li>
								<li><a href="#">Nullam ut neque</a></li>
								<li><a href="#">Consectetur</a></li>
								<li><a href="#">Justo Convallis</a></li>
								<li><a href="#">Nunc Malesuada</a></li>
							</ul>
						</div>

					</div>

					<div class="box">

						<div class="box-title">Screenshots</div>

						<div class="box-content">

							<div class="thumbnails">
								
								<a href="#" class="thumb"><img src="sample-thumbnail.jpg" width="64" height="64" alt="" /></a>
								<a href="#" class="thumb"><img src="sample-thumbnail.jpg" width="64" height="64" alt="" /></a>
								<a href="#" class="thumb"><img src="sample-thumbnail.jpg" width="64" height="64" alt="" /></a>
								<a href="#" class="thumb"><img src="sample-thumbnail.jpg" width="64" height="64" alt="" /></a>
								<a href="#" class="thumb"><img src="sample-thumbnail.jpg" width="64" height="64" alt="" /></a>
								<a href="#" class="thumb"><img src="sample-thumbnail.jpg" width="64" height="64" alt="" /></a>

								<div class="clearer">&nbsp;</div>

							</div>

						</div>
					
					</div>

					<div class="box nobborder">

						<div class="box-title">Troubleshooting</div>

						<div class="box-content">
							<p>Aenean sit amet dui at felis lobortis dignissim. Pellentesque risus nibh, feugiat in, convallis id, congue ac, sem. Sed tempor neque in quam.</p>
						</div>

					</div>

				</div>
			</div>

			<div class="clearer">&nbsp;</div>

		</div>

	</div>
</div>
	"""
	
}

