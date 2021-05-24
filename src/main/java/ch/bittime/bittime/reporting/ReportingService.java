package ch.bittime.bittime.reporting;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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

    // v.1 - was not working...

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
