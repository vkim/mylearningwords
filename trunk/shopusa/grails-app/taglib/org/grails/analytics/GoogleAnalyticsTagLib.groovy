package org.grails.analytics
import grails.util.Environment
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class GoogleAnalyticsTagLib {

    static namespace = "ga"

    def trackPageview = {
        //def enabled = ConfigurationHolder.config.google.analytics.enabled
        //def webPropertyID = getWebPropertyID()
        // enable google analytics by default for production environment
        //if (webPropertyID && (enabled || (!(enabled instanceof Boolean) && Environment.current == Environment.PRODUCTION))) {
		if (isEnabled()) {
            out << """
<script type="text/javascript">
    var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
    document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
    try {
        var pageTracker = _gat._getTracker("${getWebPropertyID()}");
        pageTracker._trackPageview();
    }
    catch (err) {
    }
</script>"""
        }
    }

    def trackPageviewAsynch = {
        if (isEnabled()) {
            out << """
<script type="text/javascript">
    var _gaq = _gaq || [];
    _gaq.push(['_setAccount', '${getWebPropertyID()}']);
    _gaq.push(['_trackPageview']);

    (function() {
        var ga = document.createElement('script');
        ga.type = 'text/javascript';
        ga.async = true;
        ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
        (document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(ga);
    })();
</script>"""
        }
    }

    private isEnabled() {
        def enabled = ConfigurationHolder.config.google.analytics.enabled
        
        // disable google analytics if web property id is not defined 
        if (!getWebPropertyID()) {
            enabled = false
        }
        else { 
            // enable google analytics by default for production environment        
            if (!(enabled instanceof Boolean) && Environment.current == Environment.PRODUCTION) {
                enabled = true
            }
        }

        return enabled
    }

    private getWebPropertyID() {
        return ConfigurationHolder.config.google.analytics.webPropertyID
    }
}
