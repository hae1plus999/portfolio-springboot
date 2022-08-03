package com.portfolio.mvc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.portfolio.mvc.domain.Member;
import com.portfolio.mvc.domain.MemberMappingName;
import com.portfolio.mvc.repository.IMemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final IMemberRepository memberRepository;
	
	public void save(Member member) {
		memberRepository.save(member);
	}
	
	public Member findById(String memberId) {
		return memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("회원이 없습니다."));
	}
	
	public List<Member> findByName(String name) {
		return memberRepository.findByName(name);
	}

	public List<MemberMappingName> findNameMappingByName(String name) {
		return memberRepository.findNameMappingByName(name);
	}
	
	public boolean existsByName(String name) {
		return memberRepository.existsByName(name);
	}
	
	public int countByName(String name) {
		return memberRepository.countByName(name);
	}
}
