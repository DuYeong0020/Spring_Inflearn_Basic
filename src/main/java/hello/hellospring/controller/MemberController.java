package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 컴포넌트 스캔 방식
public class MemberController { // 객체를 생성해서 스프링 컨테이너에서 관리한다.

    private final MemberService memberService; // 계속 new해서 쓰지말고 한번만 해서 쓰자

/*    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }*/ // Setter 주입, public으로 노출이 된다.

    @Autowired // 이걸 쓰면 스프링빈에 등록되어있는 객체를 가져다가 넣어준다. 의존관계를 주입해준다.
    public MemberController(MemberService memberService) { // 오류가 뜨는 이유는 MemberService의 클래스는 그냥 클래스이기때문에 아무도 안봐준다
        this.memberService = memberService; // 생성자 주입이라고 한다.
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
