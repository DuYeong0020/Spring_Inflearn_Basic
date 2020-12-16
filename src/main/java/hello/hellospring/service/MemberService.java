package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepsitory;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// @Service
public class MemberService {
    private final MemberRepsitory memberRepository;

//  @Autowired
    public MemberService(MemberRepsitory memberRepository) {
        this.memberRepository = memberRepository;
    } // 다른 인스턴스를 만들지 말고 생성자 초기화로



    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 X
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m-> { // ifPresent: 값이 있으면
//            throw new IllegalStateException("이미 존재한는 회원입니다.");
//        }); -> 코드가 안이쁘다.
        validateDuplicateMember(member); // 중복 회원 검증, CTRL + T 로 리펙토링
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m-> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    // 서비스는 비지니스에 의존적으로 repository는 기계적으로 네이밍해야한다.
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
