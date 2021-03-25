package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:${env}.properties"})

public interface WebConfig extends Config {


    @Key("browser")
    @DefaultValue("chrome")
    String getWebDriverBrowser();

    @Key("browserVersion")
    String getWebDriverBrowserVersion();

    @Key("remote_driver")
    String getWebDriverURL();

    @Key("baseURL")
    @DefaultValue("https://demoqa.com/automation-practice-form")
    String getBaseURL();


}
