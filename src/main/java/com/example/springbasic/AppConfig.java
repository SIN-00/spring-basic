package com.example.springbasic;

import discount.DiscountPolicy;
import discount.FixDiscountPolicy;
import discount.RateDiscountPolicy;
import member.MemberRepository;
import member.MemberService;
import member.MemberServiceImpl;
import member.MemoryMemberRepository;
import order.OrderService;
import order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()
    //Singleton깨지는거 아니야?? -> 테스트해보자 ConfigurationTest => 똑같다

    /*
        원래 예상 호출 코드
        call AppConfig.memberService
        call AppConfig.memberRepository
        call AppConfig.memberRepository
        call AppConfig.orderService
        call AppConfig.memberRepository
     */

    /*
        실제 호출
        call AppConfig.memberService
        call AppConfig.memberRepository
        call AppConfig.orderService
        => call AppConfig.memberRepository 메서드가 한번 호출된다
     */
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    @Bean
    public DiscountPolicy discountPolicy() {
    //    return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
