package com.portfolio.mvc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.mvc.domain.Member;
import com.portfolio.mvc.domain.MemberMappingName;
import com.portfolio.mvc.domain.MemberType;
import com.portfolio.mvc.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class MemberContoroller {

	private final MemberService memberService;
	
	@GetMapping("/save")
	public Member save() {
		Member member = new Member();
		member.setMemberId("");
//		member.setMemberType(MemberType.S);
		member.setEmail(null);
		member.setAddress(null);
		member.setJoinDate(null);
		member.setMemberState(null);
		member.setName(null);
		member.setPassword(null);
		member.setPhonNumber(null);
		member.setUpdateDate(null);
		member.setZipcode(null);
		memberService.save(member);
		return member;
	}
	
	@GetMapping("findById/{memberId}")
	public Member findById(@PathVariable String memberId) {
		return memberService.findById(memberId);
	}
	
	@GetMapping("/findByName/{name}")
	public List<Member> findByName(@PathVariable String name) {
		return memberService.findByName(name);
	}
	
	@GetMapping("/findNameMappingByName/{name}")
	public List<MemberMappingName> findNameMappingByName(@PathVariable String name) {
		return memberService.findNameMappingByName(name);
	}
	
	@GetMapping("/existsByName/{name}")
	public boolean existsByName(@PathVariable String name) {
		return memberService.existsByName(name);
	}
	
	@GetMapping("/countByName/{name}")
	public int countByName(@PathVariable String name) {
		return memberService.countByName(name);
	}
}
