package com.example.springbasic;

import com.example.springbasic.discount.DiscountPolicy;
import com.example.springbasic.discount.RateDiscountPolicy;
import com.example.springbasic.member.MemberRepository;
import com.example.springbasic.member.MemberService;
import com.example.springbasic.member.MemberServiceImpl;
import com.example.springbasic.member.MemoryMemberRepository;
import com.example.springbasic.order.OrderService;
import com.example.springbasic.order.OrderServiceImpl;
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
