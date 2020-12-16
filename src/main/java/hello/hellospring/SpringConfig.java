package hello.hellospring;

import hello.hellospring.repository.MemberRepsitory;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepsitory());
    }

    @Bean
    public MemberRepsitory memberRepsitory() {
        return new MemoryMemberRepository();
    }
}
