package com.nhnacademy.security.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeCtorlller {

  @GetMapping("/A")
  public String chedkRole(){
    Authentication a = SecurityContextHolder.getContext().getAuthentication();
    return "0";
  }

}
