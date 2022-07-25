package com.example.paybackcoupon;

import com.example.paybackcoupon.domain.CouponData;
import com.example.paybackcoupon.domain.CouponRepo;
import com.example.paybackcoupon.domain.MemberData;
import com.example.paybackcoupon.domain.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/")
public class PaybackController {

    @Autowired
    public MemberRepo memrepo;
    @Autowired
    public CouponRepo courepo;

    @GetMapping("helloWorld")
    public String hello() {
        return "I can see you";
    }

    //get all coupons
    @GetMapping("/v1/GetMemberCoupons/{id}")
    public ResponseEntity<MemberData> getCouponsById(@PathVariable(value = "id") long id) throws Exception {
        MemberData md = memrepo.findById(id).orElseThrow(() -> new Exception("Transaction unavailable"));

        return ResponseEntity.ok().body(md);
    }

    @GetMapping("/v2/GetMemberCoupons/{id}")
    public ResponseEntity<MemberData> getValidById(@PathVariable(value = "id") long id) throws Exception {
        MemberData md = memrepo.findById(id).orElseThrow(() -> new Exception("Transaction unavailable"));
        Set<CouponData> coupons = md.getCoupons();
        Set<CouponData> validcoupons = new LinkedHashSet<>();
        for(CouponData cd : coupons)
        {
            Date now = new Date();
            Date validfrom = cd.getValidFrom();
            Date validto = cd.getValidTo();
            if(now.after(validfrom) && now.before(validto))
            {validcoupons.add(cd);}
        }
        md.setCoupons(validcoupons);

        return ResponseEntity.ok().body(md);
    }


    @GetMapping("/v3/GetMemberCoupons")
    public ResponseEntity<MemberData> getAllByNearestCity(@RequestBody MemberRequest Data) throws Exception {
        MemberData md = memrepo.findById(Data.getMemberid()).orElseThrow(() -> new Exception("User Not found"));

        Set<CouponData> coupons = md.getCoupons();
        HashMap<CouponData, Float> Distance = new HashMap<CouponData, Float>();

        for (CouponData coupon : coupons) {
            Distance.put(coupon, GeoCompare.distanceCalculation(Data.getLatitude(), Data.getLongitude(),
                    coupon.getLatitude(), coupon.getLongitude(), "K"));
        }

        List<Map.Entry<CouponData, Float>> sortedlist = new ArrayList<>(Distance.entrySet());

        Collections.sort(sortedlist, new Comparator<Map.Entry<CouponData, Float>>() {
            @Override
            public int compare(Map.Entry<CouponData, Float> o1, Map.Entry<CouponData, Float> o2) {
                return Math.round(o1.getValue() - o2.getValue());
            }
        });


        Set<CouponData> sortedcoupons = new LinkedHashSet<>();

        for(Map.Entry<CouponData, Float> cd : sortedlist)
        {
            CouponData filterCheck = cd.getKey();
            Date now = new Date();
            Date validfrom = cd.getKey().getValidFrom();
            Date validto = cd.getKey().getValidTo();
            if(now.after(validfrom) && now.before(validto))
            {sortedcoupons.add(cd.getKey());}
        }
        MemberData mm = new MemberData(md.getMemberid(), md.getName(),sortedcoupons);
        return ResponseEntity.ok().body(mm);
    }
}