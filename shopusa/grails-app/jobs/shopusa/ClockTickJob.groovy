package shopusa

import au.com.shopusa.model.User


class ClockTickJob {

		
	static triggers = {
		simple name:'everyTwoHour', startDelay:0, repeatInterval: 7164000, repeatCount: -1
	}
	
    def execute() {
		println 'Clock ticks at: ' + new Date() + ' with UserCounts: ' + User.count()
    }
}
