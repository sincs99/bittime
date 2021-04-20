package ch.bittime.bittime.mvc;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {




        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/admin/userPanel").setViewName("/admin/userPanel");
            //registry.addViewController("/user/home").setViewName("user/home");

        }

//        @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry){
//            registry
//                    .addResourceHandler("/static/**")
//                    .addResourceLocations("file:/static/**");
//        }
    }

