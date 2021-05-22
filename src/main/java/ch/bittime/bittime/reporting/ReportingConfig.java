package ch.bittime.bittime.reporting;

import org.springframework.context.annotation.Configuration;

/**
 * @author André
 */

@Configuration
public class ReportingConfig {

    // Bean doen't work, "HTTP Status 500 – Internal Server Error" at login when implemented...

//    @Bean
//    public ClassLoaderTemplateResolver parseThymeleafTemplate() {
//
//        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//        templateResolver.setPrefix("templates/pdf/");
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode("HTML");
//        templateResolver.setCharacterEncoding("UTF-8");
//        templateResolver.setOrder(1);
//
//        return templateResolver;


    //       v.1 - didn't work...

//        TemplateEngine templateEngine = new TemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver);
//        Context context = new Context();
//        context.setVariable("data", "data");
//        return templateEngine.process("thymeleaf_template", context);

//    }

}
