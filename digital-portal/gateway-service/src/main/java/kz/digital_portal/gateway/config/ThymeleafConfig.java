package kz.digital_portal.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.ISpringWebFluxTemplateEngine;
import org.thymeleaf.spring6.view.reactive.ThymeleafReactiveViewResolver;

@Configuration
public class ThymeleafConfig {

    @Bean
    public ThymeleafReactiveViewResolver thymeleafReactiveViewResolver(ISpringWebFluxTemplateEngine templateEngine) {
        ThymeleafReactiveViewResolver viewResolver = new ThymeleafReactiveViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }
}