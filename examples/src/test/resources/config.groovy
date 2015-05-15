environments {
    local {
        mail = "localhost"
        servers {
            linux = "0.0.0.0"
            solaris = "192.168.1.1"
        }
    }

    prod {
        mail = "prodhost"
        servers = ["1.1.1.1", "2.2.2.2.2"]
    }
}