package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepsitory memberRepsitory;

    // @Autowired
    public SpringConfig(MemberRepsitory memberRepsitory) {
        this.memberRepsitory = memberRepsitory;
    }
    /*private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/
   /* private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

   /* @Bean
    public MemberService memberService() {
        return new MemberService(memberRepsitory());
    }*/
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepsitory);
    }
    /*@Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }*/ // 컴포넌트를 써도 된다.

  /*  @Bean
    public MemberRepsitory memberRepsitory() {*/
        // return new JpaMemberRepository(em);
        // return new JdbcTemplateMemberRepository(dataSource);
        // return new JdbcMemberRepository(dataSource);
        // return new MemoryMemberRepository();
/*    }*/
}
