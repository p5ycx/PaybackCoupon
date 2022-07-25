package com.example.paybackcoupon.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepo extends JpaRepository<MemberData, Long> {

}
