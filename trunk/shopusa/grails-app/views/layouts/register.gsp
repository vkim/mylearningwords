<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
		<meta name="description" content=""/>
		<meta name="keywords" content="" />
		<meta name="author" content="" />
		<link rel="stylesheet" type="text/css" href="${resource(dir:'css',file:'style.css')}" media="screen"/>
		<link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon"/>
		<title><g:layoutTitle default='User Registration'/></title>
		
		<g:javascript library='jquery' plugin='jquery' />
	
		<link rel="stylesheet" media="screen" href="${resource(dir:'css',file:'reset.css')}"/>
		<link rel="stylesheet" media="screen" href="${resource(dir:'css',file:'spring-security-ui.css')}"/>
		<jqui:resources />
		<link rel="stylesheet" media="screen" href="${resource(dir:'css/smoothness',file:'jquery-ui-1.8.2.custom.css',plugin:'spring-security-ui')}"/>
		<link rel="stylesheet" media="screen" href="${resource(dir:'css',file:'jquery.jgrowl.css')}"/>
		<link rel="stylesheet" media="screen" href="${resource(dir:'css',file:'jquery.safari-checkbox.css')}"/>
		<link rel="stylesheet" media="screen" href="${resource(dir:'css',file:'auth.css')}"/>
		
		<g:layoutHead />
		<g:javascript library="application" />
	</head>

<body>

<div id="header-wrapper">
		<div id="header-wrapper-2">
			<div class="center-wrapper">

				<div id="header">

					<div id="logo">
						<h1 id="site-title">
							<a href="#">Cheap shipping <span>MobiPac</span>
							</a>
						</h1>
						<h2 id="site-slogan">Another cheap shopping website</h2>
					</div>

					<div id="help-wrapper">
						<div id="help">

							<a href="#">Contact us</a> <span class="text-separator">|</span>
							<a href="#">F.A.Q</a> <span class="text-separator">|</span> <a
								href="#">Sitemap</a>

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
					<li><a href="index.html">Home</a></li>
					<li class="current_page_item current_page_parent"><a href="product_details.html">Product Details</a></li>
					<li><a href="blog_post.html">Blog Post</a></li>
					<li><a href="archives.html">Archives</a></li>
					<li><a href="style_demo.html">Style Demo</a></li>
					<li><a href="empty_page.html">Empty Page</a></li>
				</ul>

				<div class="clearer">&nbsp;</div>

			</div>

		</div>
	</div>
</div>

<g:javascript src='jquery/jquery.jgrowl.js'/>
<g:javascript src='jquery/jquery.checkbox.js'/>
<g:javascript src='spring-security-ui.js'/>

<div id="content-wrapper">
	<div class="center-wrapper">
		
		<div class="content">

			<div id="main">

				<g:layoutBody/>

			</div>

		</div>

	</div>
</div>




<s2ui:showFlash/>

<div id="bottom">

	<div class="center-wrapper">

		<div class="left">
			&copy; 2009 Website.com - Your Website Slogan <span class="text-separator">|</span> <a href="#">Privacy Policy</a> <span class="text-separator">|</span> <a href="#">Terms of Use</a> 
		</div>

		<div class="right">
			<a href="http://templates.arcsin.se/">Website template</a> by <a href="http://arcsin.se/">Arcsin</a> 
		</div>
		
		<div class="clearer">&nbsp;</div>

	</div>

</div>


</body>
</html>
