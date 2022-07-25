package com.example.paybackcoupon;

import com.example.paybackcoupon.domain.CouponData;
import com.example.paybackcoupon.domain.CouponRepo;
import com.example.paybackcoupon.domain.MemberData;
import com.example.paybackcoupon.domain.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PaybackCouponApplication implements CommandLineRunner {

    @Autowired
    private MemberRepo memrepo;

    @Autowired
    private CouponRepo courepo;

    public static void main(String[] args) {
        SpringApplication.run(PaybackCouponApplication.class, args);



    }
    @Override
    public void run(String... args) throws Exception
    {
        MemberData mem1 = new MemberData("Akash");
        MemberData mem2 = new MemberData("Ramesh");
        MemberData mem3 = new MemberData("lkajkj");


        List<MemberData> members = new ArrayList<MemberData>();


        List<CouponData> coupons = new ArrayList<>();

        CouponData coupon1 = new CouponData("EXX201",
                new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2021"),
                new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2022"),
                49.00F, 123.00F);

        CouponData coupon2 = new CouponData("EXX2013",
                new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2021"),
                new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2022"),
                41.00F, 133.03F);

        CouponData coupon3 = new CouponData("YXX2113",
                new SimpleDateFormat("dd/MM/yyyy").parse("12/1/2021"),
                new SimpleDateFormat("dd/MM/yyyy").parse("12/6/2021"),
                421.00F, 114.03F);

        CouponData coupon4 = new CouponData("KCX2415",
                new SimpleDateFormat("dd/MM/yyyy").parse("12/1/2020"),
                new SimpleDateFormat("dd/MM/yyyy").parse("12/6/2020"),
                121.00F, 114.03F);

        coupons.add(coupon1);
        coupons.add(coupon2);
        coupons.add(coupon3);
        coupons.add(coupon4);


        mem1.getCoupons().add(coupon1);
        mem1.getCoupons().add(coupon2);
        mem2.getCoupons().add(coupon3);
        mem2.getCoupons().add(coupon4);
        mem3.getCoupons().add(coupon1);
        mem3.getCoupons().add(coupon2);
        mem3.getCoupons().add(coupon3);

        coupon1.getMembers().add(mem1);
        coupon1.getMembers().add(mem3);
        coupon2.getMembers().add(mem2);
        coupon2.getMembers().add(mem3);// adding test data for the coupons.
        coupon3.getMembers().add(mem3);
        coupon3.getMembers().add(mem2);
        coupon4.getMembers().add(mem2);

        members.add(mem1);
        members.add(mem2);
        members.add(mem3);

        this.memrepo.saveAll(members);
    }
}
