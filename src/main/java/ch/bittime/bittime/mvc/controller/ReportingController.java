package ch.bittime.bittime.mvc.controller;

import ch.bittime.bittime.login.User;
import ch.bittime.bittime.login.UserService;
import ch.bittime.bittime.login.repository.UserRepo;
import ch.bittime.bittime.reporting.ReportingService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @Autowired
    private ReportingService reportingService;

    @RequestMapping(value = {"/admin/generate/"})

    public String generatePdf(Model model) throws IOException, DocumentException {

        reportingService.createPdf();
        System.out.println("here's the PDF ");
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

    @RequestMapping(value = {"/user/generate/"})
    public String generateUserPdf(Model model) throws IOException, DocumentException {

//        ReportingService thymeleaf2Pdf = new ReportingService();
//        String html = thymeleaf2Pdf.parseThymeleafTemplate();
//        thymeleaf2Pdf.generatePdfFromHtml(html);
        System.out.println("here's the PDF");

        return "/user/reportingView";
    }

    @RequestMapping(value = {"/user/show/"})
    public String showUser(Model model) {

        //Liste in Browser generieren
        System.out.println("here's the List");

        return "/user/reportingView";
    }

}
