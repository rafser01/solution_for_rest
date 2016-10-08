/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartstocktrader.controller;

import com.smartstocktrader.pojo.Display;
import com.smartstocktrader.pojo.Review;
import com.smartstocktrader.pojo.User;

import com.smartstocktrader.service.UserService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HP
 */
@RestController
public class UserControllerRest {
    @Autowired
    UserService service;
    
    
    
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUser(){
          
         List<User> list=service.getAllUser();
          
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/login/{email}/{password}/", method = RequestMethod.GET)
    public ResponseEntity<User>  login(
    @PathVariable("email") String email, @PathVariable("password") String password
    )
    {
           
         User user=service.getUser(email, password);
         if(user.getEmail()==null){
              return new ResponseEntity<User>(HttpStatus.CONFLICT);
         }
          
        return new ResponseEntity<User>(user,  HttpStatus.OK);
    }
    
    @RequestMapping(value = "/register/",method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody User user){
        String reult=service.register(user);
        
        return new ResponseEntity<String>(reult,HttpStatus.CREATED);
        
    }
    
    @RequestMapping(value = "/display/", method = RequestMethod.GET)
    public ResponseEntity<List<Display>> getAllDisplay(
   
    ){
        List<Display> list=new ArrayList<Display>();
        list=service.getAllDisplayItems();
        
        return new ResponseEntity<List<Display>>(list,HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/reviews/{user_id}/", method = RequestMethod.GET)
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable("user_id") Integer user_id){
         
        List<Review> list=service.getById(user_id);
        return new ResponseEntity<List<Review>>(list,HttpStatus.OK);
    }
    
     
    
//    @RequestMapping(value = "/reviews/", method = RequestMethod.POST)
//    public ResponseEntity<List<Review>> saveReview(@RequestBody Review review){
//         
//        
//        return new ResponseEntity<List<Review>>(list,HttpStatus.OK);
//    }
    
    @RequestMapping(value = "/testAndroid/", method = RequestMethod.GET)
    public ResponseEntity<User> androidTest(
   
    ){
         User user=service.androidTest();
        
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/testAndroid/",method = RequestMethod.POST)
    public @ResponseBody boolean saveUser(@RequestBody User user){
        String result=service.register(user);
        if(result.equals("success")){
            return true;
        }else return false;
    }
    @RequestMapping(value = "/testRestFul/",method = RequestMethod.POST)
    public @ResponseBody String testRestFul(){
         return "This is rest ful web-service";
    }
    
    
}
