package com.example.demo.controller;

import com.example.demo.dto.ListDTO;
import com.example.demo.dto.ResponseModel;
import com.example.demo.exception.CustomException;
import com.example.demo.service.TransactionService;
import com.example.demo.utils.CommonUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/module/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/balance")
    public ResponseEntity<ResponseModel<Object>> balance(HttpServletRequest request) throws CustomException, IOException {
        log.info("start get balance");
        return ResponseEntity.ok(new ResponseModel<>(0, "Get Balance Berhasil", transactionService.balance(request)));
    }

    @PostMapping("/topup")
    public ResponseEntity<ResponseModel<Object>> topup(HttpServletRequest request, @RequestBody Map<String, Object> topupMap) throws CustomException, IOException {
        log.info("start topup");
        return ResponseEntity.ok(new ResponseModel<>(0, "Top Up Balance berhasil", transactionService.topup(request, topupMap)));
    }

    @PostMapping("/transaction")
    public ResponseEntity<ResponseModel<Object>> transaction(HttpServletRequest request, @RequestBody Map<String, String> requestMap) throws CustomException, IOException {
        log.info("start transaction with payload: {}", requestMap.get("service_code"));
        return ResponseEntity.ok(new ResponseModel<>(0, "Transaksi berhasil", transactionService.transaction(request, requestMap)));
    }

    @GetMapping("/transaction/history")
    public ResponseEntity<ResponseModel<Object>> transactionHistory(HttpServletRequest request, @ModelAttribute ListDTO dtoRequest) throws CustomException, IOException {
        log.info("start get transaction history with payload: {}", CommonUtils.convertUsingJackson(dtoRequest));
        return ResponseEntity.ok(new ResponseModel<>(0, "Get History Berhasil", transactionService.transactionHistory(request, dtoRequest)));
    }
}
