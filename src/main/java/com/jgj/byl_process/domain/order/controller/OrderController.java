package com.jgj.byl_process.domain.order.controller;

import com.jgj.byl_process.domain.boards.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class OrderController {

    private final ProductService productService;

    @PostMapping("/kakaoPay")
    public String kakaoPay() {
        try {
            URL url = new URL("https://kapi.kakao.com/v1/payment/ready");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "KakaoAK 232d37a166b5adbb2979676830e75b00");
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            conn.setDoOutput(true);
//
//            String productId = "YOUR_PRODUCT_ID";
//            String params = "cid=TC0ONETIME&partner_order_id=partner_order_id&partner_user_id=partner_user_id&item_name=초코파이&quantity=1&total_amount=2200&vat_amount=200&tax_free_amount=0&approval_url=" + approval_url + "&cancel_url=http://localhost:8080/order/kakaoPayCancel&fail_url=http://localhost:8080/order/kakaoPaySuccessFail";
            String approval_url = "http://localhost:8080/purchase-complete";
            String params = "cid=TC0ONETIME&partner_order_id=partner_order_id&partner_user_id=partner_user_id&item_name=초코파이&quantity=1&total_amount=2200&vat_amount=200&tax_free_amount=0&approval_url=" + approval_url + "&cancel_url=http://localhost:8080/order/kakaoPayCancel&fail_url=http://localhost:8080/order/kakaoPaySuccessFail";
            OutputStream os = conn.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeBytes(params);
            dos.close();

            int responseCode = conn.getResponseCode();

            InputStream is;
            if(responseCode == 200) {
                is = conn.getInputStream();
            }else {
                is = conn.getErrorStream();
            }
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            return br.readLine();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "(\"result\":\"No\")";
    }

}