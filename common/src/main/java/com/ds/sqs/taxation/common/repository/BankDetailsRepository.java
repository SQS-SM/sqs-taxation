package com.ds.sqs.taxation.common.repository;

import com.ds.sqs.taxation.common.entity.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankDetailsRepository extends JpaRepository<BankDetails, Long> {
}
