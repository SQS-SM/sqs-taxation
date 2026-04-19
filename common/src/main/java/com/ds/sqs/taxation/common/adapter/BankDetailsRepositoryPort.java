package com.ds.sqs.taxation.common.adapter;

import com.ds.sqs.taxation.common.dto.BankDetailsDto;
import com.ds.sqs.taxation.common.entity.BankDetails;

import java.util.List;
import java.util.Optional;

public interface BankDetailsRepositoryPort {

    BankDetailsDto save(BankDetailsDto bankDetails);

    Optional<BankDetailsDto> findById(Long id);

    List<BankDetailsDto> findAll();

    void deleteById(Long id);
}
