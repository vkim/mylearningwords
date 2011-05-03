import grails.util.Environment
import au.com.shopusa.cms.CmsPage
import au.com.shopusa.model.Role
import au.com.shopusa.model.ShipOrder
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
//				createIntegrationTestData()
				break
				
			case Environment.CUSTOM:
				createIntegrationTestData()
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
		def testUser = new User(enabled: true, password: password, username: 'admin@com.au', shippingAddress: new ShippingAddress())
		testUser.save(flush: true)

		assert User.count() == 1

		UserRole.create testUser, adminRole, true
		UserRole.create testUser, clientRole, true

		assert UserRole.count() == 2

		//normal user
		def normalUser = new User(enabled: true, password: password, username: 'normal@com.au', shippingAddress: new ShippingAddress())
		normalUser.save(flush: true)
		assert User.count() == 2

		UserRole.create normalUser, clientRole, true
		assert UserRole.count() == 3
		
		//Test orders
		new ShipOrder(client: normalUser, status: ShipOrder.Status.OPENED, cost: 0d)
		
		
		//default home page
		new CmsPage(pageId: 'home', content: homePage).save()
		new CmsPage(pageId: 'about', content: about).save()
		new CmsPage(pageId: 'faq', content: faq).save()
		new CmsPage(pageId: 'fees', content: fees).save()
		
		println '####################### Data has been loaded ####################'
	}

	def about = """
	Don't want to spend much money on new...? Do you want to buy cheaper ... for you, your family or friends? Do you want to buy modern (latest) model ...? If the answer is YES, then we can help you to turn your dream into reality! With our company you can buy the cheapest in the world ... from the best shops of the USA and get it delivered right to your door even if they don’t deliver to Australia. You will be surprised with our low prices on delivery that are not much higher than local delivery. All you need to do is to buy product from your favorite shop in the USA and place the delivery order on our website, the rest we will do for you.
	"""
	
	def fees = """
	<div> <b class="bt"><b></b></b>
  <h2 class="blue">What are MY fees?</h2>
    <h3 class="green">Service Fee for Australian Orders</h3>
    <p><strong>My service fee is 5% of the order price.</strong> All other charges are at cost.</p>
    <p>My service fee is calculated by adding the <em>Item Cost + Delivery Protection
   + USA shipping + Shipping to Australia + Return Protection.</em> The USA dollar total is then converted to Australian dollar at the current spot exchange rate - 2.5 cents. For direct debit payments my fee is 5% of this value. <strong>My minimum  fee is A\$15.</strong></p>
    <p><strong>For credit card or paypal payments (only available for orders under \$4000) my service fee is 10% with a minimum fee of A\$20.</strong> This  fee is higher due to the extra charges and risks I face with these payment methods. Non-standard order (such as in-store purchases) can be accommodated on a case-by-case basis for an extra fee.</p>
    <h3 class="green">Service Fees for Other Countries</h3>
    <p><strong>My service fee for all other countries is 8%.</strong> You are quoted in USA dollars. You may pay either by credit card or paypal. If you wish to pay by wire transfer or if you have an Australian bank account please contact me. <strong>The minimum  fee for all non-Australian orders is US\$20.</strong></p>
    <h3 class="green">Shipping</h3>
    <p><strong>0%.</strong> My USA partner charges shipping via the <a href="http://pe.usps.com/text/Imm/ab_012.htm#ep1572941" target="_blank" rel="nofollow">United States Postal Service</a> (USPS) at the standard  rate. For oversized items or expensive (i.e. items too large to ship via USPS) my USA partner ships using FedEx at approximately 65% off the standard rates.</p>
    <h3 class="green">Delivery Protection</h3>
    <p><strong>There are three rate: 0.75% of the total item(s) price for Express Mail and FedEx shipping, 1.25% for Priority Mail shipping and 2.5% for First Class Mail shipping.</strong> The delivery protection covers replacement of an order if it is lost during shipping to Australia (or your country) and reflects the relative risk of parcels being lost with each method.</p>
    <h3 class="green">Return Protection (optional)</h3>
    <p><strong>20% of the total shipping charges.</strong> This is an optional warranty that covers the shipping costs involved in returning an item for warranty replacement or repair. It covers the shipping charges from Australia (or your country) to the USA, any shipping charge within the USA, and return shipping to Australia (or your country). This optional warranty also covers the cost of returning an item to the USA if the item was damaged during shipping, or if the store shipped the wrong item. <em>Return protection must be purchased at time of item purchase and can't be purchased after shipment.</em></p>
    <h3 class="green">Australia Orders Exchange Rate</h3>
    <p><strong>2.5 cents less than the <a href="http://www.xe.com" target="_blank" rel="nofollow">current spot rate</a>.</strong> This is the same (or less) than what your bank or Paypal will charge you on foreign credit card purchases. The reason my rate is not the same as the spot rate is I don't receive the spot rate when I forward your payment to my USA partner. I also have to pay wire transfer fees. <strong>Most importantly the exchange rate in your quote is fixed.</strong> Since it can take your payment up to 3 to 4 days to clear, a fixed exchange rate exposes me to the very volatile Australian dollar. My exchange rate is updated every four hours and is shown within the Price Estimator on my <a href="order/index.php">order page</a>. </p>
    <h3 class="green">Mail-in Rebates</h3>
    <p>You are refunded  the full the rebate amount and my service fee on the rebate amount minus US\$10 for my USA partner to process the rebate. Rebate payments typically take 8 to 16 weeks to process, although they can take longer.</p>
    <h3 class="green"> Cancellations and Refunds</h3>
    <p>If you cancel your order after payment you are charged  any expenses either my USA partner or I may have incurred processing your order, including my service fee. My fee will be waived if you apply your credit to a new order within one week.</p>
  <b class="bb"><b></b></b> 
  </div>
	
	"""
	
	def homePage = """
	<div class="inWrpr">
					

<div id="htmlContent">
<h2>Purchase of the good in shops of the USA</h2><h4>Why it is favorable</h4>
<ol>
<li>Purchases in Online shops of the USA will manage to you more cheaply, even taking into account delivery, than the same goods bought in shops of your city.<br><br>Still it is possible to save if to watch discounts for the goods for sites: Deals.Yahoo.com,&nbsp;Savings.com, RetailMeNot.com, SmartBargains.com, Groupon.com, BensBargains.net, CouponCabin.com, CouponChief.com, CouponWinner.com, Dealighted.com, DeaLoco.com, DealsOfAmerica.com, FatWallet.com, FreeShipping.org, GottaDeal.com, MyCoupons.com, Slickdeals.net.<br><br>It is even more possible to save on delivery if to increase parcel weight. That is the more you will send, the delivery counting on 1 kg will be cheaper.<br>
</li><li>The wide choice of the goods (different colors, styles, the sizes)<br>
</li><li>The goods necessary to you, can will appear in shops of your city much later or in general can not appear.</li></ol>
<h4>Why then very few people buy in Internet shops of the USA from inhabitants of the CIS countries?</h4>
<ul>
<li><strong>Many do not simply know about such possibility</strong>. You know now. 
</li><li><strong>Many do not have credit cards </strong>with which it is possible to pay purchases. Company Polar Express can help you with it. You pay the goods in those ways&nbsp; which can (for example, PayPal, Web Money), and we pay your purchases by a credit card. 
</li><li><strong>Almost all Internet shops do not deliver purchase for limits of the USA</strong> and if deliver, cost of delivery is very high, or the parcel goes very long. Company Polar Express can deliver your purchases from the USA in times cheaper, than shop. In addition a company gives the storage in the USA, where an Online shop will be able to send your purchases which will wait for a dispatch.</li></ul>
<h4>What to buy</h4>
<p>For all your native and friends of any sex, age and an occupation and interests you can find in Internet shops of the USA gifts and the necessary things for a life.</p>
<ul>
<li>Books and the professional literature 
</li><li>Music and movies 
</li><li>Electronics ― computers, laptops, phones, smart phones, cameras, players, tablets 
</li><li>Winter, summer, spring-autumn clothes and footwear for men, women and children ― jeans, trousers, skirts, dresses, vests, linen, jackets, sweaters, socks, running shoes, boots 
</li><li>Accessories ― bags, belts, glasses, hats, gloves 
</li><li>Auto spare parts, tuning elements, disks, rubber 
</li><li>Perfumery and make-up 
</li><li>Toys 
</li><li>Musical instruments 
</li><li>Tools 
</li><li>The goods for the house, sports (sportswear, footwear, rollers, the fads, tennis rackets) and rest, fishing (a fishing tackle, the coil, sonic depth finders, baits) 
</li><li>Souvenirs (clothes, balls with symbols of favorite teams) 
</li><li>Collections, rare subjects</li></ul>
<h4>Where to buy, how to search for the goods</h4>
<p>We recommend to buy the goods only in large and known Online shops (for example, Amazon.com, Buy.com), and also in official shops of brands (for example, hp.com, victoriassecret.com, nike.com, adidas.com, puma.com).</p>
<p>The rare goods can be found at Online auctions (for example, eBay, Auction.com, Bidtopia.com, Bonanzle.com, eBid.net, ePier.com, iOffer.com, OnlineAuction.com, Overstock.com, SalvageSale.com, TheSOCExchange.com, Webidz.com).</p>
<p>If you know that wish to buy, it is possible to find it at bargain price in special search systems (for example, Google Product Search, Live Product Search, Yahoo! Shopping, Become.com, PriceGrabber.com, BizRate.com).</p>
<p>If you cannot be defined what to buy, you will be helped by special managements how to pick up any electronics (for example, the laptop, the camera, phone) depending on different parameters.</p>
<p>Definitively to be defined with a choice, you can esteem opinions of other buyers on these goods on sites of responses: epinions.com, consumersearch.com, consumerreports.org, reviews.cnet.com, sazze.com, reevoo.com, retrevo.com, buzzillions.com, crowdstorm.com, pebuzz.com, rateitall.com, reviews.omgili.com.</p>
<p>You can find people with similar tastes, to communicate with them, learn that your acquaintances in the services devoted to purchases (for example, Kaboodle.com, Crowdstorm.com, Etsy.com, Zebo.com, ThisNext.com, Wists.com, ShopWiki.com, Wisheus.com, Woot.com, BzzAgent.com, Wishpot.com, Buzzilions.com, Stylehive.com, Glimpse.com, StyleFeeder.com, ShopStyle.com, Reesycakes.com, Osoyou.com, Yub, wishlistr.com, yagoodza.ru, mywishlist.ru) want as a gift.</p>
<p>For fans of reading services are created (for example, Goodreads.com, Shelfari.com, Librarything.com) in which you can collect the book shelf, having specified what books you have read and why they were pleasant to you, can look that known people, your friends read, to learn about new books in style which is pleasant to you.</p>
<h4>How to pay</h4>
<p>Usually credit cards, but if for you the credit card is not present, we can buy it for you, and you pay in that way which is accessible to you.<br>If you are in Russia for purchases in Internet shops of the USA can use virtual card VISA (<a href="http://visa.1pb.ru/">http://visa.1pb.ru/</a> and <a href="https://cards.webmoney.ru/asp/wmsafepay.asp">https://cards.webmoney.ru/asp/wmsafepay.asp</a>).</p></div><!--

						
					<i class="cnrs lc"></i><i class="cnrs rc"></i></div>
	"""
	
	def faq = """
	<div> <b class="bt"><b></b></b>
  <h2 class="pink">Quick Answers</h2>
    <h4 class="green">What fees do you charge for ordering from the USA?</h4>
    <p> <strong>5% of the total order price with a A\$15 minimum.</strong> Please see the <a href="fees.html">fees page</a> for more information.</p>
    <h4 class="green">How do I place an order?</h4>
    <p>Fill in our <a href="https://www.priceusa.com.au/order/?mod=order">order form</a> and tell us what you want to buy, or click on a <a href="faq-ordering.html#quickbuy" rel="nofollow">quick buy</a> button. We do the rest.</p>
    <h4 class="green">I don't live in Australia. Can I use your service?</h4>
    <p><strong>Yes.</strong> Just fill in my order form and you will be sent a quote in US\$ which can be paid by paypal, credit card or wire transfer. The service fee for non-Australian orders is 8%. Please see the <a href="fees.html">fees page</a> for more information.</p>
    <h4 class="green">What is your test address and credit card number?</h4>
    <p>1850 NE 92nd Ave<br>
        Portland OR 97220<br>
    email. spamme@example.com<br>
    ph. 541-870-1500<br>
    </p>
    <p>Test Visa Card: 4111 1111 1111 1111, Expiry 10/12, CVC 789</p>
    <p>This is not where any of my USA partners live so don't ship items to this address!</p>
    <h4 class="green">How long will it take for my order to arrive?</h4>
    <p> 10 to 21 days depending on your chosen <a href="faq-shipping.html">shipping option</a>.</p>
    <h4 class="green">Can I order from eBay?</h4>
    <p><strong>Yes.</strong> Please see <a href="faq-ordering.html#ebay">how eBay purchases work</a> for more information.</p>
    <h4 class="green">How do I order NikeID Shoes?</h4>
    <p>See this post on <a href="faq-fashion.html#nike_id_ordering">how to order nikeid shoes</a> on my Fashion FAQ page.</p>
    <h4 class="green">How can I contact you?</h4>
    <p>You can email me at
      <script language="javascript" type="text/javascript">var a = "&#115&#117&#112&#112&#111&#114&#116"; var b = "&#112&#114&#105&#099&#101&#117&#115&#097&#046&#099&#111&#109&#046&#097&#117"; at = "@"; document.write('<a href="' + "mai" + "lto" + ":" + a+at+b + '">support@priceusa.com.au</a>');</script><a href="https://mail.google.com/mail/?view=cm&amp;fs=1&amp;tf=1&amp;to=support@priceusa.com.au" target="_blank">support@priceusa.com.au</a>
  </p>
    <h2 class="pink">Ordering Questions</h2>
    <p><a href="faq-ordering.html#placing_order">How do I place an order?</a></p>
    <p><a href="faq-ordering.html#committed">If I put in an order am I committed to paying?</a></p>
    <p><a href="faq-ordering.html#multiple_orders">How do I order multiple items at once?</a></p>
    <p><a href="faq-ordering.html#cancel_order">Can I cancel an order once I have paid?</a></p>
    <p><a href="faq-ordering.html#quickbuy">What is Quick Buy?</a></p>
    <p><a href="faq-ordering.html#order_cost">What will my order cost?</a></p>
    <p><a href="faq-ordering.html#shop_tax">What USA shops charge state sales tax?</a></p>
    <p><a href="faq-ordering.html#tax_shipping">How can I work out what the USA shipping will be?</a></p>
    <p><a href="faq-ordering.html#order_fee">What is your fee?</a></p>
    <p><a href="faq-ordering.html#mail_in_rebates">Can I claim USA only mail-in rebates?</a></p>
    <p><a href="faq-ordering.html#coupon_code">Can I use a coupon code with my order?</a></p>
    <p><a href="faq-ordering.html#where_to_order">Can you suggest where to order an item from?</a></p>
    <p><a href="faq-ordering.html#dont_know_details">What should I do if I don't know all the details of my order?</a></p>
    <p><a href="faq-ordering.html#where_to_buy">How do I find the best place to buy my item?</a></p>
    <p><a href="faq-ordering.html#delivery_insurance">Is my order insured?</a></p>
    <p><a href="faq-ordering.html#order_tracking">How can I track my order?</a></p>
    <p><a href="faq-ordering.html#consolidate">Can you combine my orders so that I pay only one shipping fee?</a></p>
    <p><a href="faq-ordering.html#cant_order">What can't I order?</a></p>
    <p><a href="faq-ordering.html#wrong_item">My order arrived, but it is not what I ordered?</a></p>
    <p><a href="faq-ordering.html#open_orders">Why was my order opened?</a></p>
    <p><a href="faq-ordering.html#out_of_stock">Some of my items have gone out of stock since I placed my order. What happens now?</a></p>
    <p><a href="faq-ordering.html#original_invoice">Why am I not given the original invoice from the store?</a></p>
    <p><a href="faq-ordering.html#unlocked_iphones">Can I order a 3G iPhone from the USA?</a></p>
    <h3 class="pink">Shipping Questions </h3>
    <p><a href="faq-shipping.html#shipping_method">What shipping method should I use?</a></p>
    <p><a href="faq-shipping.html#item_weight">I don't know how much my item weighs? What should I do?</a></p>
    <p><a href="faq-shipping.html#ship_est_wrong">What happens if my item weights more or less than estimated?</a></p>
    <p><a href="faq-shipping.html#return_protection">What is return protection?</a></p>
    <p><a href="faq-shipping.html#not_working">Does delivery protection cover replacement of items that arrive not working?</a></p>
    <p><a href="faq-shipping.html#size_limits">What are the shipping size limits?</a></p>
    <p><a href="faq-shipping.html#oversize">My item is larger than the USPS 79" size limit. How can I ship it?</a></p>
    <p><a href="faq-shipping.html#imperial">What are lbs and inches?</a></p>
    <p><a href="faq-shipping.html#shipping_times">How long will it take for my order to arrive?</a></p>
    <p><a href="faq-shipping.html#shipping_options">How long do each of the different shipping options take?</a></p>
    <p><a href="faq-shipping.html#order_not_arrived">My order hasn't arrive yet. What should I do?</a></p>
    <p><a href="faq-shipping.html#doa">What happens if my goods arrive damaged or not working?</a></p>
    <p><a href="faq-shipping.html#different_address">Can I have my goods shipped to a different address?</a></p>
    <p><a href="faq-shipping.html#declared_value">Can you change the declared value of my order?</a></p>
    <h3 class="pink">Payment Questions </h3>
    <p><a href="faq-payment.html#paying">How do I pay for my order?</a></p>
    <p><a href="faq-payment.html#invoice">Do I get a tax invoice?</a></p>
    <p><a href="faq-payment.html#credit_cards">Do you accept credits cards?</a></p>
    <p><a href="faq-payment.html#paypal">Can I use Paypal to pay for my order?</a></p>
    <p><a href="faq-payment.html#gst">Will I be charged GST?</a></p>
    <p><a href="faq-payment.html#pay_quickly">I have one day special that I want to order. How can I pay you quickly?</a></p>
    <h3 class="pink">FASHION QUESTions</h3>
    <p><a href="faq-fashion.html#order_shoes">Can I order shoes from the USA?</a></p>
    <p><a href="faq-fashion.html#shoe_weight">How much do shoes weigh?</a></p>
    <p><a href="faq-fashion.html#good_clothes_shop">What are  good shops to buy clothes from?</a></p>
    <p><a href="faq-fashion.html#buy_jewelry">Can I buy jewelry from the USA?</a></p>
    <p><a href="faq-fashion.html#clothes_weight">How much will my clothes order cost to ship?</a></p>
    <p><a href="faq-fashion.html#nike_id_ordering">How do I order NikeID Shoes?</a></p>
    <p class="pink">General Questions</p>
    <p><a href="faq-general.html#contact">How can I contact you?</a></p>
    <p><a href="faq-general.html#voltage">Doesn't the USA use a different power voltage to Australia?</a></p>
    <p><a href="faq-general.html#how_long">How long have you been in business?</a></p>
    <p><a href="faq-general.html#trust">Can I trust you?</a></p>
    <p><a href="faq-general.html#return_order">What should I do if I need to return my order?</a></p>
    <p><a href="faq-general.html#warranty_repair">What happens if I need an item repaired under warranty?</a></p>
 <b class="bb"><b></b></b> 
  </div>
	"""
	
}

