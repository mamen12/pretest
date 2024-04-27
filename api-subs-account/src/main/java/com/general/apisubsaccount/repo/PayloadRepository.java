package com.general.apisubsaccount.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.general.apisubsaccount.entity.PayloadProperties;


@Repository
public interface PayloadRepository extends JpaRepository<PayloadProperties, String>{

}
