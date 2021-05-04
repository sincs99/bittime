package ch.bittime.bittime.reporting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * @author André
 */

@Configuration
public class ReportingConfig {

    // Bean doen't work, program crashes when implemented.

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


////       Other approach didn't work aswell
////        TemplateEngine templateEngine = new TemplateEngine();
////        templateEngine.setTemplateResolver(templateResolver);
////        Context context = new Context();
////        context.setVariable("data", "data");
////        return templateEngine.process("thymeleaf_template", context);

//    }

}
