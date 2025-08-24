package com.patbaumgartner.couponbooster.coop.service;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.Proxy;
import com.patbaumgartner.couponbooster.coop.properties.CoopPlaywrightProperties;
import com.patbaumgartner.couponbooster.util.proxy.ProxyAddress;
import com.patbaumgartner.couponbooster.util.proxy.ProxyProperties;
import com.patbaumgartner.couponbooster.util.proxy.ProxyResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Factory for creating and configuring Playwright browser instances for Coop SuperCard
 * automation. Handles browser setup with appropriate configuration for web automation
 * tasks including headless mode, timeout settings, and browser arguments.
 */
@Component
public class CoopBrowserFactory {

	private static final Logger log = LoggerFactory.getLogger(CoopBrowserFactory.class);

	private final CoopPlaywrightProperties browserConfiguration;

	private final ProxyProperties proxyProperties;

	private final ProxyResolver proxyResolver;

	/**
	 * Creates a new browser factory with the given configuration.
	 * @param browserConfiguration the Playwright configuration properties
	 */
	public CoopBrowserFactory(CoopPlaywrightProperties browserConfiguration, ProxyProperties proxyProperties,
			ProxyResolver proxyResolver) {
		this.proxyProperties = proxyProperties;
		this.browserConfiguration = browserConfiguration;
		this.proxyResolver = proxyResolver;
	}

	/**
	 * Creates and configures a new Chromium browser instance with the configured options.
	 * @param playwrightInstance the Playwright instance to create the browser from
	 * @return a configured Browser instance ready for automation
	 */
	public Browser createBrowser(Playwright playwrightInstance) {
		if (log.isDebugEnabled()) {
			log.debug("Creating browser with headless: {}, slowMo: {}ms", browserConfiguration.headless(),
					browserConfiguration.slowMoMs());
		}

		BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
		launchOptions.setHeadless(browserConfiguration.headless())
			.setSlowMo(browserConfiguration.slowMoMs())
			.setTimeout(browserConfiguration.timeoutMs());

		if (proxyProperties.enabled()) {

			ProxyAddress proxy = proxyResolver.getCurrentProxy();
			if (log.isDebugEnabled()) {
				log.debug("Setting proxy to browser with: {}", proxy);
			}

			launchOptions
				.setProxy(new Proxy("http://" + proxy.host() + ":" + proxy.port()).setUsername(proxy.username())
					.setPassword(proxy.password()));
		}

		return playwrightInstance.chromium().launch(launchOptions);
	}

}
