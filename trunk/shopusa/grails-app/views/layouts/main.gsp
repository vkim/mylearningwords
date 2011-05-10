<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
		<meta name="description" content=""/>
		<meta name="keywords" content="" />
		<meta name="author" content="" />
		<link rel="stylesheet" type="text/css" href="${resource(dir:'css',file:'style.css')}" media="screen"/>
				<link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon"/>
		<title><g:layoutTitle default="Grails" /></title>
		<g:layoutHead />
		<g:javascript library="application" />
		<script type="text/javascript">
		
		  var _gaq = _gaq || [];
		  _gaq.push(['_setAccount', 'UA-23241603-1']);
		  _gaq.push(['_trackPageview']);
		
		  (function() {
		    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
		    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
		  })();
		
		</script>
	</head>
<body id="top">
	<div id="header-wrapper">
		<div id="header-wrapper-2">
			<div class="center-wrapper">

				<div id="header">

					<div id="logo">
						<h1 id="site-title">
							<a href="#">Cheap shipping <span>MobiPac</span>
							</a>
						</h1>
						<h2 id="site-slogan">Cheap shipping from USA for your purchases</h2>
					</div>

					<div id="help-wrapper">
						<div id="help">

							<a href="<g:resource dir="/" file="faq" />">FAQ</a> <span class="text-separator">|</span>
							<a href="<g:resource dir="/" file="register"/>">New account</a> <span class="text-separator">|</span> 
							
							<sec:ifLoggedIn>
								<a href="<g:resource dir="/" file="logout"/>">Sign out</a>
							</sec:ifLoggedIn>
							<sec:ifNotLoggedIn>
								<a href="<g:resource dir="/" file="login"/>">Sign in</a>
							</sec:ifNotLoggedIn>
						</div>
					</div>

				</div>

			</div>
		</div>
	</div>

<div id="navigation-wrapper">
	<div id="navigation-wrapper-2">
		<div class="center-wrapper">
	
			<div id="navigation">

				<ul class="tabbed">
					<li class="current_page_item current_page_parent"><a href="<g:resource dir="/" file="home"/>">Home</a></li>
					<li><a href="<g:resource dir="/" file="fees"/>">Fees</a></li>
					<li><a href="<g:resource dir="/" file="about"/>">About us</a></li>
					<sec:ifAllGranted roles="ROLE_CLIENT">
						<li><a href="<g:resource dir="/" file="order/list"/>">My Orders</a></li>
					</sec:ifAllGranted>
					<sec:ifAllGranted roles="ROLE_ADMIN">
						<li><a href="<g:resource dir="/" file="cmsPage/list"/>">Cms pages</a></li>					
						<li><a href="<g:resource dir="/" file="order/alllist"/>">All Orders</a></li>					
					</sec:ifAllGranted>
					
				</ul>

				<div class="clearer">&nbsp;</div>

			</div>

		</div>
	</div>
</div>
<div id="content-wrapper">
	<div class="center-wrapper">
		
		<div class="content">

			<div id="main">
				<g:if test="${flash.message}">
		           <div class="success">${flash.message}</div>
		        </g:if>
				<g:layoutBody/>

			</div>

		</div>

	</div>
</div>

<div id="footer-wrapper">

	<div class="center-wrapper">

		<div id="footer">

			<div class="left">
			</div>

			<div class="right">
			</div>
			
			<div class="clearer">&nbsp;</div>

		</div>

	</div>

</div>

<div id="bottom">

	<div class="center-wrapper">

		<div class="left">
			&copy; 2011 buycheaper.com.au - Cheaper on delivery <span class="text-separator">|</span> <a href="#">Privacy Policy</a> <span class="text-separator">|</span> <a href="#">Terms of Use</a> 
		</div>

		<div class="right">
			<a href="http://templates.arcsin.se/">Website template</a> by <a href="http://arcsin.se/">Arcsin</a> 
		</div>
		
		<div class="clearer">&nbsp;</div>

	</div>

</div>

</body>
</html>