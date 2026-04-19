//package com.ds.sqs.taxation.api.controller;
//
//import com.ds.sqs.taxation.api.model.IncomeTaxRequest;
//import com.ds.sqs.taxation.api.model.IncomeTaxResponse;
//import com.ds.sqs.taxation.api.service.AllowanceService;
//import com.ds.sqs.taxation.api.service.IncomeTaxService;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/income/tax")
//public class IncomeTaxController {
//
//    private final IncomeTaxService service;
//
//    public IncomeTaxController(IncomeTaxService service, AllowanceService allowanceService) {
//        this.service = service;
//    }
//
//    @PostMapping("/taxable-income")
//    public IncomeTaxResponse getTaxableAmount(@RequestBody IncomeTaxRequest request) {
//        return service.calculate(request);
//    }
//}
