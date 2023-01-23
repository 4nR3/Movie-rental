package com.msbills.repositories;

import com.msbills.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, String> {
  List<Bill> findByCustomerBill(String customerBill);

}
