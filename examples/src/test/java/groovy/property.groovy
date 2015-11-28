import java.text.SimpleDateFormat

// Environment specific settings override settings with the same name.
environments {

    dev {
        description = 'env for development'
        server.URL = 'http://dev:8081'
        app {
            version = 2.0
            dataset = false
            value = 20
        }
    }

    test {
        server.URL = 'http://test:9080'
        app {
            random = 1000
        }
    }

    qa {
        server.URL = 'http://qa:9080'
    }

    uat {
        server.URL = 'http://uat:9080'
    }

    prod {
        server.URL = 'http://prod/url'
    }

}

app {
    description = 'description for all env'
    version = 1.0
    dataset = true
    value = 10
    // We can use Java objects
    random = new Random().nextInt(10)
    date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());

}

