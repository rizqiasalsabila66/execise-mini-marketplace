package com.trollMarket.TrollMarket.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//agar url bisa memanggil method
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    //    set halaman landing, agar url bisa panjnag
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/profile").setViewName("forward:/profile/index");
        registry.addViewController("/shop").setViewName("forward:/shop/index");
        registry.addViewController("/merchandise").setViewName("forward:/merchandise/index");
        registry.addViewController("/shipment").setViewName("forward:/shipment/index");
        registry.addViewController("/history").setViewName("forward:/history/index");
        registry.addViewController("/cart").setViewName("forward:/cart/index");

    }

}

