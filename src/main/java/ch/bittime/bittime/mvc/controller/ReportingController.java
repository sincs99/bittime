package ch.bittime.bittime.mvc.controller;

import ch.bittime.bittime.login.User;
import ch.bittime.bittime.login.UserService;
import ch.bittime.bittime.login.repository.UserRepo;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.List;
import java.util.Optional;


/**
 * @author André
 */

//thanks to:
    //https://artificialintelligence.oodles.io/dev-blogs/Tutorial-for-Generating-Dynamic-PDFs-Using-Thymeleaf
    //https://tuhrig.de/generating-pdfs-with-java-flying-saucer-and-thymeleaf/
    //https://github.com/tuhrig/Flying_Saucer_PDF_Generation/blob/master/src/test/java/FlyingSaucerTest.java

@Controller
public class ReportingController {


    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

    @RequestMapping("/admin/reportingView/generatePdf")
    public String generatePdf(Model model) throws DocumentException, IOException {

        ReportingController thymeleaf2Pdf = new ReportingController();
        String html = thymeleaf2Pdf.parseThymeleafTemplate();
        thymeleaf2Pdf.generatePdfFromHtml(html);
        System.out.println("here's the PDF");

        return "/admin/reportingView";
    }

    public String parseThymeleafTemplate() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("data", "data");

        return templateEngine.process("thymeleaf_template", context);
    }

    public void generatePdfFromHtml(String html) throws IOException, DocumentException {
        String outputFolder = System.getProperty("user.home") + File.separator + "thymeleaf.pdf";

        OutputStream outputStream = new FileOutputStream(outputFolder);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }
}
