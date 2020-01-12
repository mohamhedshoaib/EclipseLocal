package com.shoaib.springboot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller("/error")
public class ErrorController {
   @ExceptionHandler({Exception.class})
   public ModelAndView getRunTimeCustomrror(HttpServletRequest req, HttpServletResponse resp, Exception ex) {
      ModelAndView mv = new ModelAndView();
      mv.addObject("exception", "Generic Exception " + ex.getLocalizedMessage());
      mv.addObject("url", req.getRequestURL());
      mv.setViewName("error");
      return mv;
   }
}