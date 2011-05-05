package shopusa


class ClockTickJob {
	
	static triggers = {
		simple name:'everyTwoHour', startDelay:5400000, repeatInterval: 7164000, repeatCount: -1
	}
	
    def execute() {
		println 'Clock ticks at: ' + new Date() 
    }
}
