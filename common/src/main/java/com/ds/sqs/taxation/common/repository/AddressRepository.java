package com.ds.sqs.taxation.common.repository;

import com.ds.sqs.taxation.common.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByCityIgnoreCase(String city);

    List<Address> findByPostalCode(String postalCode);
}