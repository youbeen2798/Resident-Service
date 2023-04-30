package com.nhnacademy.security.controller;

import com.nhnacademy.security.repository.resident.ResidentRepository;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {
  private final ResidentRepository residentRepository;

  public LoginController(ResidentRepository residentRepository) {
    this.residentRepository = residentRepository;
  }

//  public String doLogin()
}
