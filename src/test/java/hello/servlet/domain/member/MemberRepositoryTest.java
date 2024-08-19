package hello.servlet.domain.member;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hellot.servlet.domain.member.Member;
import hellot.servlet.domain.member.MemberRepository;

class MemberRepositoryTest {
	MemberRepository memberRepository = MemberRepository.getInstance();

	// 테스트가 끝나고 초기화 안 되면 꼬임(테스트의 순서 보장이 안 되기 때문에)
	@AfterEach
	void afterEach(){
		memberRepository.clearStore();
	}

	@Test
	@DisplayName("findById 메서드 테스트")
	void save(){
		//given(뭔가 주어졌을 때)
		Member member = new Member("hello", 20);

		//when(이런게 실행됐을 때)
		Member savedMember = memberRepository.save(member);

		//then(결과)
		Member findMember = memberRepository.findById(savedMember.getId());
		assertThat(findMember).isEqualTo(savedMember);
	}

	@Test
	@DisplayName("findAll 메서드 테스트")
	void findAll(){
		//given
		Member member1 = new Member("member1", 20);
		Member member2 = new Member("member2", 30);

		memberRepository.save(member1);
		memberRepository.save(member2);
		//when
		List<Member> result = memberRepository.findAll();

		//then
		// 멤버의 수가 2가 맞는지 확인
		assertThat(result.size()).isEqualTo(2);
		// member1과 member2를 포함하는지 확인
		assertThat(result).contains(member1, member2);

	}

}