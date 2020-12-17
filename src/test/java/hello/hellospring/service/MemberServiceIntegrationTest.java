package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepsitory;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // 테스트가 끝나면 롤백을 해준다는 장점이 있다.
class MemberServiceIntegrationTest {

    // MemberService memberService = new MemberService();
    // MemoryMemberRepository memberRepository = new MemoryMemberRepository(); // 다른 인스턴스를 만들어서 비효율적
    @Autowired MemberService memberService;
    @Autowired MemberRepsitory memberRepository;

    @Test
    void 회원가입() { // 테스트코드는 한글로 해도 된다.
        // given
        Member member = new Member();
        member.setName("spring5");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        // Assertions.assertThat(member.getName()).isEqualTo(findMember.getName()); opion + enter
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring5");

        Member member2 = new Member();
        member2.setName("spring5");
        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        // then 
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}