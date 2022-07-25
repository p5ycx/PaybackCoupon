package com.example.paybackcoupon.domain;


import javax.persistence.*;
import java.util.*;

//Model for the Member Contains memberid,name
//Many to Many relationship between member and coupon
//Create

@Entity
@Table(
        name = "MemberData")
public class MemberData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberid", nullable = false)
    private Long memberid;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.ALL,
            })
    @JoinTable(name = "member_coupons",
            joinColumns = { @JoinColumn(name = "memberid") },
            inverseJoinColumns = { @JoinColumn(name = "couponid") })
    private Set<CouponData> coupons = new LinkedHashSet<>();

    public MemberData() {

    }

    public MemberData(Long memberid, String name) {
        this.memberid = memberid;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CouponData> getCoupons() {
        return coupons;
    }

    public void setCoupons(Set<CouponData> coupons) {
        this.coupons = coupons;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }


    public MemberData(Long memberid, String name, Set<CouponData> coupons) {
        this.memberid = memberid;
        this.name = name;
        this.coupons = coupons;
    }

    public MemberData(String name) {
        this.name = name;
    }
}
