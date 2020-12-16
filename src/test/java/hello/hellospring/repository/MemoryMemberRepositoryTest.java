package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() { // 콜백, 끝날때마다 비워준다.
        repository.clearStore();
    }

    @Test // JUNIT으로 테스트를 한다.
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // System.out.println("result = " + (result == member)); member랑 result랑 같은지 비교
        // Assertions.assertEquals(member, result);
        
        // Assertions.assertThat(member).isEqualTo(result); // 요즘 많이 쓰는 것
        // option + enter으로 간편하게 쓸 수 있다.
        assertThat(member).isEqualTo(result);
    }
    @Test
    public  void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        
        Member Member2 = new Member();
        Member2.setName("spring2");
        repository.save(Member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }
    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
    // 테스트를 먼저 만들고, 클래스를 만드는것, 테스트 주도 개발, TDD
}
