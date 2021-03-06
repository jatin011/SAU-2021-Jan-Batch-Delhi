package com.au2021.controller;


import com.au2021.Exception.DivisionByZeroSelf;
import com.au2021.Exception.LargeNumberException;
import com.au2021.service.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServlet;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class calculatorController {

    @Autowired
    private Calculator service;

    @RequestMapping(value = "/calculator", method = RequestMethod.GET)
    public String loginToApp()throws Exception{
        return "calculator";
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String welcomeUser(@RequestParam String result, ModelMap model){
        model.put("result", result);
        return "result";
    }



    @RequestMapping(value = "/calculator", method = RequestMethod.POST)
    public String handleLoginRequest (@RequestParam String firstNum, @RequestParam String secondNum,@RequestParam String operation, ModelMap model) throws Exception{
        int result = service.calculate(firstNum, secondNum , operation);
        if(result>1000)
        {
            throw new LargeNumberException();
        }
        model.put("result", result);
        return "result";
    }
}
