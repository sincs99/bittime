package ch.bittime.bittime.reporting;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.transaction.Transactional;
import java.io.*;

/**
 * @author André
 */

@Service
@Component
public class ReportingService {

    @Autowired
    private TemplateEngine templateEngine;

    // v.2
    public void createPdf() throws IOException, DocumentException {

        Context context = new Context();
        context.setVariable("context", "helloWorld");
        String processHtml = templateEngine.process("okay", context);
        OutputStream outputStream = new FileOutputStream("time.pdf");
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(processHtml);
        renderer.layout();
        renderer.createPDF(outputStream, false);

        renderer.finishPDF();
        outputStream.close();
    }

    // v.1 - not working...

//    @Autowired
//    SpringTemplateEngine templateEngine;

//    public void generatePdfFromHtml(String html) throws IOException, DocumentException {
//        String outputFolder = System.getProperty("user.home") + File.separator + "thymeleaf.pdf";
//
//        OutputStream outputStream = new FileOutputStream(outputFolder);
//        ITextRenderer renderer = new ITextRenderer();
//        renderer.setDocumentFromString(html);
//        renderer.layout();
//        renderer.createPDF(outputStream);
//
//        outputStream.close();
//    }


}
