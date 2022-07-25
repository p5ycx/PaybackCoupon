package com.example.paybackcoupon.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepo extends JpaRepository<CouponData,Long> {
}
