package com.example.paybackcoupon;

public class MemberRequest {

    private Long memberid;
    private Float latitude;
    private Float longitude;

    public MemberRequest(Long memberid, Float latitude, Float longitude) {
        this.memberid = memberid;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
}
