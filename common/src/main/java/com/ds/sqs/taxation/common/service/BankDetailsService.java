package com.ds.sqs.taxation.common.service;

import com.ds.sqs.taxation.common.dto.BankDetailsDto;

import java.util.List;

public interface BankDetailsService {

    BankDetailsDto create(BankDetailsDto dto);

    BankDetailsDto getById(Long id);

    List<BankDetailsDto> findAll();

    BankDetailsDto update(Long id, BankDetailsDto dto);

    void delete(Long id);
}