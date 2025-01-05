package order;

import discount.DiscountPolicy;
import discount.FixDiscountPolicy;
import member.Member;
import member.MemberRepository;
import member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private MemberRepository memberRepository;
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //할인정책을 변경하려면 이 코드를 수정해햐함 -> OCP위반
    private DiscountPolicy discountPolicy;
    //이렇게하면 인터페이스만 의존, 구현체에 의존하지 않음
    //여기서 할당하는 대신에 OrderServiceImpl에 구현 객체를 대신 생성하고 주입시켜주어야한다.

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
