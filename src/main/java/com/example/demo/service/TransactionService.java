package com.example.demo.service;

import com.example.demo.dto.BalanceDTO;
import com.example.demo.dto.BaseListDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Membership membership = membershipRepository.findByEmail(email);

        Balance balance = balanceRepository.findByMembershipId(membership.getId());
        return balanceMapper.toDto(balance);
    }

    public BalanceDTO topup(HttpServletRequest request, Map<String, Object> topupMap) throws CustomException, IOException {
        String email = ValidationUtils.validateLogin(request);
        Object obj = CommonUtils.getMapValue("top_up_amount", topupMap);
        Integer topupAmount = ValidationUtils.validateTopup(obj);
        Membership membership = membershipRepository.findByEmail(email);

        Balance balance = balanceRepository.findByMembershipId(membership.getId());
        Integer amount = Integer.sum(balance.getBalance(), topupAmount);
        balance.setBalance(amount);

        transactionRepository.save(new Transaction(membership, "INV".concat(String.valueOf(Instant.now().toEpochMilli())), "TOPUP", "Top Up balance", topupAmount));

        return balanceMapper.toDto(balance);
    }

    public PaymentDTO transaction(HttpServletRequest request, Map<String, String> requestMap) throws CustomException, IOException {
        String email = ValidationUtils.validateLogin(request);
        Membership membership = membershipRepository.findByEmail(email);
        Balance balance = balanceRepository.findByMembershipId(membership.getId());

        String serviceCode = CommonUtils.getMapValue("service_code", requestMap);
        Partner partner = partnerRepository.findByServiceCode(serviceCode);
        if (Objects.isNull(partner)) {
            throw new CustomException(102, "Service ataus Layanan tidak ditemukan", null, HttpStatus.BAD_REQUEST);
        }
        String invoiceNumber;
        Transaction transaction;

        if (balance.getBalance().compareTo(partner.getServiceTariff()) < 0) {
            throw new CustomException(102, "Saldo tidak mencukupi untuk melakukan pembayaran");
        } else {
            Integer finalAmount = balance.getBalance() - partner.getServiceTariff();
            balance.setBalance(finalAmount);
            balanceRepository.save(balance);

            invoiceNumber = "INV".concat(String.valueOf(Instant.now().toEpochMilli()));
            transaction = new Transaction(membership, invoiceNumber, "PAYMENT", partner.getServiceName(), partner.getServiceTariff());
            transactionRepository.save(transaction);
        }

        return this.mappingToResponse(partner, transaction, invoiceNumber);
    }

    public TransactionHistoryDTO transactionHistory(HttpServletRequest request, BaseListDTO dtoRequest) throws CustomException, IOException {
        String email = ValidationUtils.validateLogin(request);

        List<Transaction> transactions;
        Membership membership = membershipRepository.findByEmail(email);

        if (null == dtoRequest.getLimit()) {
            transactions = transactionRepository.findByMembershipIdOrderByCreatedOnDesc(membership.getId());
            return new TransactionHistoryDTO(dtoRequest.getOffset(), dtoRequest.getLimit(), transactionMapper.toDto(transactions));
        } else {
            Pageable pageable = PageRequest.of(dtoRequest.getOffset(), dtoRequest.getLimit(), Sort.by("createdOn").descending());
            Page<Transaction> transactionPage = transactionRepository.findByMembershipIdOrderByCreatedOnDesc(membership.getId(), pageable);
            return new TransactionHistoryDTO(dtoRequest.getOffset(), dtoRequest.getLimit(), transactionMapper.toDto(transactionPage.getContent()));
        }
    }

    private PaymentDTO mappingToResponse(Partner partner, Transaction transaction, String invoiceNumber) {
        return new PaymentDTO(invoiceNumber, partner.getServiceCode(), partner.getServiceName(), transaction.getTransactionType(), transaction.getTotalAmount(), transaction.getCreatedOn());
    }
}
