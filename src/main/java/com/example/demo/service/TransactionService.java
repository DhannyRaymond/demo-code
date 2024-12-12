package com.example.demo.service;

import com.example.demo.dto.BalanceDTO;
import com.example.demo.dto.ListDTO;
import com.example.demo.dto.PaymentDTO;
import com.example.demo.dto.TransactionHistoryDTO;
import com.example.demo.entity.Balance;
import com.example.demo.entity.Membership;
import com.example.demo.entity.Partner;
import com.example.demo.entity.Transaction;
import com.example.demo.exception.CustomException;
import com.example.demo.mapper.BalanceMapper;
import com.example.demo.mapper.TransactionMapper;
import com.example.demo.repository.BalanceRepository;
import com.example.demo.repository.MembershipRepository;
import com.example.demo.repository.PartnerRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.utils.CommonUtils;
import com.example.demo.utils.ValidationUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TransactionService {

    private final BalanceRepository balanceRepository;
    private final MembershipRepository membershipRepository;
    private final TransactionRepository transactionRepository;
    private final PartnerRepository partnerRepository;
    private final BalanceMapper balanceMapper;
    private final TransactionMapper transactionMapper;

    public BalanceDTO balance(HttpServletRequest request) throws CustomException, IOException {
        String email = ValidationUtils.validateLogin(request);

        log.info("start processing get balance with email: {}", email);
        Membership membership = membershipRepository.findByEmail(email);

        Balance balance = balanceRepository.getBalanceByMembershipId(membership.getId());
        log.info("end processing get balance with email: {}", email);
        return balanceMapper.toDto(balance);
    }

    public BalanceDTO topup(HttpServletRequest request, Map<String, Object> topupMap) throws CustomException, IOException {
        String email = ValidationUtils.validateLogin(request);

        log.info("start processing topup with email: {}", email);
        Object obj = CommonUtils.getMapValue("top_up_amount", topupMap);
        Integer topupAmount = ValidationUtils.validateTopup(obj);
        Membership membership = membershipRepository.findByEmail(email);

        Balance balance = balanceRepository.getBalanceByMembershipId(membership.getId());
        Integer amount = Integer.sum(balance.getBalance(), topupAmount);
        balance.setBalance(amount);

        Instant now = Instant.now();
        String transactionId = "TRANSACTION_".concat(String.valueOf(now.toEpochMilli()));
        transactionRepository.insertTransaction(transactionId, now, "Top Up balance", "INV".concat(String.valueOf(now.toEpochMilli())), topupAmount, "TOPUP", membership.getId());

        log.info("end processing topup with email: {}", email);
        return balanceMapper.toDto(balance);
    }

    public PaymentDTO transaction(HttpServletRequest request, Map<String, String> requestMap) throws CustomException, IOException {
        String email = ValidationUtils.validateLogin(request);

        log.info("start processing transaction with email: {}", email);
        Membership membership = membershipRepository.findByEmail(email);
        Balance balance = balanceRepository.getBalanceByMembershipId(membership.getId());

        String serviceCode = CommonUtils.getMapValue("service_code", requestMap);
        Partner partner = partnerRepository.findByServiceCode(serviceCode);
        if (Objects.isNull(partner)) {
            log.error("transaction with service_code: {} not found", serviceCode);
            throw new CustomException(102, "Service ataus Layanan tidak ditemukan", null, HttpStatus.BAD_REQUEST);
        }
        String invoiceNumber;
        Transaction transaction;

        if (balance.getBalance().compareTo(partner.getServiceTariff()) < 0) {
            log.error("service cost: {}, balance: {} - transaction with service_code: {} insufficient balance",partner.getServiceTariff(), balance.getBalance(), serviceCode);
            throw new CustomException(102, "Saldo tidak mencukupi untuk melakukan pembayaran");
        } else {
            Integer finalAmount = balance.getBalance() - partner.getServiceTariff();
            balanceRepository.updateBalance(balance.getId(), membership.getId(), finalAmount);

            Instant now = Instant.now();
            String transactionId = "TRANSACTION_".concat(String.valueOf(now.toEpochMilli()));
            invoiceNumber = "INV".concat(String.valueOf(now.toEpochMilli()));
            transaction = transactionRepository.insertTransaction(transactionId, now, partner.getServiceName(), invoiceNumber, partner.getServiceTariff(), "PAYMENT", membership.getId());
            log.info("transaction: {} success with email: {}", serviceCode, email);
        }

        log.info("end processing get profile with email: {}", email);
        return this.mappingToResponse(partner, transaction, invoiceNumber);
    }

    public TransactionHistoryDTO transactionHistory(HttpServletRequest request, ListDTO dtoRequest) throws CustomException, IOException {
        String email = ValidationUtils.validateLogin(request);

        log.info("start processing get transaction history with email: {}", email);
        List<Transaction> transactions;
        Membership membership = membershipRepository.findByEmail(email);

        if (null == dtoRequest.getLimit()) {
            transactions = transactionRepository.findByMembershipIdOrderByCreatedOnDesc(membership.getId());
            log.info("end processing get transaction history with email: {}", email);
            return new TransactionHistoryDTO(dtoRequest.getOffset(), dtoRequest.getLimit(), transactionMapper.toDto(transactions));
        } else {
            transactions = transactionRepository.findByMembershipIdOrderByCreatedOnDesc(membership.getId(), dtoRequest.getLimit(), dtoRequest.getOffset());
            log.info("end processing get transaction history pagination with email: {}", email);
            return new TransactionHistoryDTO(dtoRequest.getOffset(), dtoRequest.getLimit(), transactionMapper.toDto(transactions));
        }
    }

    private PaymentDTO mappingToResponse(Partner partner, Transaction transaction, String invoiceNumber) {
        return new PaymentDTO(invoiceNumber, partner.getServiceCode(), partner.getServiceName(), transaction.getTransactionType(), transaction.getTotalAmount(), transaction.getCreatedOn());
    }
}
