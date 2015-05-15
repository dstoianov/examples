class Config {

    final String CONFIG_FILE = 'examples/src/test/resources/config.groovy'
    final String ENVIRONMENTS = 'environments'

    def read() {
        new File(CONFIG_FILE).text
    }

    def getEnv(String envName) {

        def config = new ConfigSlurper(envName)
        config.registerConditionalBlock(ENVIRONMENTS, envName)
        config.parse(read())
    }
}