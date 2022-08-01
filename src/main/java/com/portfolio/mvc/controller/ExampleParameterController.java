package com.portfolio.mvc.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/example/parameter")
public class ExampleParameterController {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/example1")
	public void example1(@RequestParam String id,
			@RequestParam String code, Model model) {
		
		model.addAttribute("id", id);
		model.addAttribute("code", code);
		
	}
	
	/**
	 * Map을 활용한 파라미터 받는방법
	 * @param paramMap
	 * @param model
	 */
	@GetMapping("/example2")
	public void example2(@RequestParam Map<String, Object> paramMap, Model model) {
		
		model.addAttribute("paramMap", paramMap);
		
	}
	
	/**
	 * class을 활용한 파라미터 받는방법
	 * @param paramMap
	 * @param model
	 */
	@GetMapping("/example3")
	public void example3(ExampleParameter parameter, Model model) {
		
		model.addAttribute("parameter", parameter);
		
	}
	
	/**
	 * @PathVariable을 활용한 파라미터 받는방법
	 * @param paramMap
	 * @param model
	 */
	@GetMapping("/example4/{id}/{code}")
	public String example4(@PathVariable String id, @PathVariable String code, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("code", code);
		return "/example/parameter/example4";
		
	}
	
	/**
	 * String[] 배열을 받는방법
	 * @param paramMap
	 * @param model
	 */
	@GetMapping("/example5/")
	public String example5(@RequestParam String[] ids, Model model) {
		model.addAttribute("ids", ids);
		return "/example/parameter/example4";
		
	}
}
