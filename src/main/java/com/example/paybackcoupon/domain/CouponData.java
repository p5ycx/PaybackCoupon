package com.example.paybackcoupon.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
@Table (name="CouponData")
public class CouponData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "couponid", nullable = false)
    private Long couponid;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.ALL
            },
            mappedBy = "coupons")
    @JsonIgnore
    private List<MemberData> members = new ArrayList<>();

    @Column(name = "CouponName", nullable = false)
    private String CouponName;

    @Column(name = "ValidFrom", nullable = false)
    private Date ValidFrom;

    @Column(name = "ValidTo", nullable = false)
    private Date ValidTo;

    @Column(name = "longitude")
    private Float longitude;

    @Column(name ="latitude")
    private Float latitude;

    public CouponData() {

    }

    public CouponData(String couponName, Date validFrom, Date validTo, Float longitude, Float latitude) {
        CouponName = couponName;
        ValidFrom = validFrom;
        ValidTo = validTo;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Long getCouponid() {
        return couponid;
    }

    public void setCouponid(Long couponid) {
        this.couponid = couponid;
    }

    public List<MemberData> getMembers() {
        return members;
    }

    public void setMembers(List<MemberData> members) {
        this.members = members;
    }

    public String getCouponName() {
        return CouponName;
    }

    public void setCouponName(String couponName) {
        CouponName = couponName;
    }

    public Date getValidFrom() {
        return ValidFrom;
    }

    public void setValidFrom(Date validFrom) {
        ValidFrom = validFrom;
    }

    public Date getValidTo() {
        return ValidTo;
    }

    public void setValidTo(Date validTo) {
        ValidTo = validTo;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }
}
