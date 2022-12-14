package ch.bittime.bittime.mvc.controller;

import ch.bittime.bittime.login.UserService;
import ch.bittime.bittime.login.repository.UserRepo;
import ch.bittime.bittime.reporting.ReportingService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


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
    @Autowired
    private ReportingService reportingService;

    @RequestMapping(value = {"/admin/generate/"})

    public String generatePdf(/*Model model */) /*throws IOException, DocumentException */ {

//        todo: PDF functionality
//        reportingService.createPdf();
//        System.out.println("here's the PDF ");
//        String htmlText = "Abc";
//        ReportingService thymeleaf2Pdf = new ReportingService(htmlText);
//        htmlText = thymeleaf2Pdf.parseThymeleafTemplate();
//        thymeleaf2Pdf.generatePdfFromHtml(htmlText);
//        System.out.println("here's the PDF");

        return "/admin/reportingView";
    }

    @RequestMapping(value = {"/admin/show/"})
    public String show(Model model) {

        //Liste in Browser generieren
        System.out.println("here's the List");

        return "/admin/reportingView";
    }

    @RequestMapping(value = {"/pdf/okay/"})
    public String generateUserPdf(Model model) throws IOException, DocumentException {

//        ReportingService thymeleaf2Pdf = new ReportingService();
//        String html = thymeleaf2Pdf.parseThymeleafTemplate();
//        thymeleaf2Pdf.generatePdfFromHtml(html);
        System.out.println("here's the PDF");

        /**
         * @author Dominic
         * Code taken from //https://www.baeldung.com/thymeleaf-generate-pdf
         */
        ReportingController thymeleaf2Pdf = new ReportingController();
        String html = thymeleaf2Pdf.parseThymeleafTemplate();
        thymeleaf2Pdf.generatePdfFromHtml(html);


        return "/pdf/okay/";
    }

    @RequestMapping(value = {"/user/show/"})
    public String showUser(Model model) {

        //Liste in Browser generieren
        System.out.println("here's the List");

        return "/user/reportingView";
    }


    /**
     * @author Dominic
     * Code taken from //https://www.baeldung.com/thymeleaf-generate-pdf
     */

    private String parseThymeleafTemplate() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("pdf", "txt");

        return templateEngine.process("thymeleaf_template", context);
    }

    /**
     * @author Dominic
     * Code taken from //https://www.baeldung.com/thymeleaf-generate-pdf
     */
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
