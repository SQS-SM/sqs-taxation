package com.ds.sqs.taxation.api.controller;

import com.ds.sqs.taxation.api.model.PayslipDto;
import com.ds.sqs.taxation.api.model.SampleData;
import com.ds.sqs.taxation.api.pdf.SalaryDocumentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pdf")
@Tag(name = "PDF Documents", description = "APIs for generating PDF documents such as payslips")
public class PdfController {

    private final SalaryDocumentService pdfService;

    @Operation(
            summary = "Generate payslip PDF",
            description = "Generates a payslip document in PDF format for a sample employee"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "PDF generated successfully",
                    content = @Content(
                            mediaType = "application/pdf",
                            schema = @Schema(type = "string", format = "binary")
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/payslip", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generatePdf() {

        PayslipDto payslipDto = SampleData.buildSample("1168");

        byte[] pdf = pdfService.generatePayslipPdf(payslipDto);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=payslip.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}