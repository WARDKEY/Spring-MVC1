package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

	// static으로 생성했기 때문에 MemberRepository가 많아도 하나씩만 생성됨
	// 싱글톤으로 해서 static 안 붙여도 됨
	private static Map<Long, Member> store = new HashMap<>();
	// 아이디가 하나씩 증가하는 시퀀스로 사용
	private static long sequence = 0L;

	private static final MemberRepository instance = new MemberRepository();

	// 생성된 MemberRepository 조회
	public static MemberRepository getInstance(){
		return instance;
	}

	// 싱글톤으로 만들때는 private으로 생성자를 막아서 아무나 생성하지 못 하게 해야 함.
	private MemberRepository(){

	}

	// 회원 저장
	public Member save(Member member){
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	// 아이디로 해당 회원 찾음
	public Member findById(Long id){
		return store.get(id);
	}

	// 모든 회원 리스트로 불러옴
	public List<Member> findAll(){
		// store에 있는 값은 건들지 않고 새로운 ArrayList를 생성하여 값을 넘겨줌
		return new ArrayList<>(store.values());
	}

	// store에 있는 값 모두 날림
	public void clearStore(){
		store.clear();
	}

}
