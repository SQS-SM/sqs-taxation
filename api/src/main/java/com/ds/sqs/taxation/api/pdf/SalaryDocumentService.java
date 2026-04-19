package com.ds.sqs.taxation.api.pdf;
import com.ds.sqs.taxation.api.model.PayslipDto;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.io.File;

@Service
@RequiredArgsConstructor
public class SalaryDocumentService {
    private final TemplateEngine templateEngine;

    public byte[] generatePayslipPdf(PayslipDto payslip) {
        Context context = new Context();
        context.setVariable("employeeName", payslip.getEmployeeName());
        context.setVariable("employeeCode", payslip.getEmployeeCode());
        context.setVariable("jobTitle", payslip.getJobTitle());
        context.setVariable("payPeriod", payslip.getPayPeriod());
        context.setVariable("earnings", payslip.getEarnings());
        context.setVariable("deductions", payslip.getDeductions());
        context.setVariable("netPay", payslip.getNetPay());

        String html = templateEngine.process("payslip", context);


        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            ClassLoader cl = getClass().getClassLoader();
            File imageFolder = new File(cl.getResource("static/images/").getFile());
            String baseUri = imageFolder.toURI().toString();


            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(html, baseUri);
            builder.toStream(os);
            builder.run();
            return os.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate PDF", e);
        }
    }
}
