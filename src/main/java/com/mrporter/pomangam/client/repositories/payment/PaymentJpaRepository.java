//package com.mrporter.pomangam.client.repositories.payment;
//
//import com.mrporter.pomangam.client.domains.payment.Payment;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@RepositoryRestResource(exported = false)
//public interface PaymentJpaRepository extends JpaRepository<Payment, Long>, PaymentCustomRepository {
//
//}
//
//interface PaymentCustomRepository {
//
//}
//
//@Transactional(readOnly = true)
//class PaymentCustomRepositoryImpl extends QuerydslRepositorySupport implements PaymentCustomRepository {
//
//    public PaymentCustomRepositoryImpl() {
//        super(Payment.class);
//    }
//
//
//}
