dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    username = "shopusa"
    password = "shopusa"
//	logSql = true
	properties {
		minEvictableIdleTimeMillis = 1800000
		timeBetweenEvictionRunsMillis = 1800000
		numTestsPerEvictionRun=3
		
		validationQuery = "SELECT 1"
		testOnBorrow=true
		testWhileIdle=true
		testOnReturn=true
	}
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
    development {
        dataSource {
			dbname = "shopusa_dev" 
            dbCreate = "create-drop" // one of 'create', 'create-drop','update'
            url = "jdbc:mysql://localhost/$dbname?autoReconnect=true"
        }
    }
    test {
        dataSource {
			dbname = "shopusa_dev"
            dbCreate = "create-drop"
            url = "jdbc:mysql://localhost/$dbname?autoReconnect=true"
        }
    }
    production {
        dataSource {
			dbname = "shopusa"
            dbCreate = "create-drop"
			username = "usershop"
			password = "b3auty"
			driverClassName = "javax.sql.DataSource"
            url = "jdbc:cloudbees://shopusa"
        }
    }
}
