class ConfigTest {

    public static void main(String[] args) {

        def local = new Config().getEnv('local')
        println "Local"
        println local.mail
        println local.servers.linux
        println local.servers.solaris

        def prod = new Config().getEnv('prod')
        println "Production"
        println prod.mail
        println prod.servers
    }
}