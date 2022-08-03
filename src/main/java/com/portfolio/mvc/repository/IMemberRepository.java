package com.portfolio.mvc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.portfolio.mvc.domain.Member;
import com.portfolio.mvc.domain.MemberMappingName;

public interface IMemberRepository extends CrudRepository<Member, String>{
	
	List<Member> findByName(String name);
	boolean existsByName(String name);
	int countByName(String name);
	List<MemberMappingName> findNameMappingByName(String name);
}
