package com.ds.sqs.taxation.api.controller;

import com.ds.sqs.taxation.api.model.PayslipDto;
import com.ds.sqs.taxation.api.model.SampleData;
import com.ds.sqs.taxation.api.pdf.SalaryDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pdf")
public class PdfController {

    private final SalaryDocumentService pdfService;

    @GetMapping("/payslip")
    public ResponseEntity<byte[]> generatePdf() {

        PayslipDto payslipDto = SampleData.buildSample("1168");

        byte[] pdf = pdfService.generatePayslipPdf(payslipDto);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=payslip.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
