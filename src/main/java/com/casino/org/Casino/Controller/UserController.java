package com.casino.org.Casino.Controller;


import com.casino.org.Casino.Entity.Transaction;
import com.casino.org.Casino.Entity.User;
import com.casino.org.Casino.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/casino")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/create-user")
    ResponseEntity<String> createUser(@RequestBody User user)
    {
       ResponseEntity<String>response =  userService.createUser(user);
       return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @GetMapping("/user")
    ResponseEntity<String> demo()
    {
        return new ResponseEntity<>("Working",HttpStatus.OK);
    }

    @GetMapping("draw/{id}/{amount}/{number}")
    ResponseEntity<String> drawChance(@PathVariable("amount") double amount
    ,@PathVariable("number") Integer selectedNo,@PathVariable("id") Integer id) throws Exception {
       ResponseEntity<String> response = userService.drawChance(amount,selectedNo,id);
        return new ResponseEntity<>(response.getBody(),response.getStatusCode());
    }

    @GetMapping("/add-balance/{amount}/{id}")
    ResponseEntity<?> addBalance(@PathVariable("amount") double amount,@PathVariable("id") Integer id)
    {
        ResponseEntity<?> updatedBalance = userService.addBalance(amount,id);
        return new ResponseEntity<>(updatedBalance.getBody(),updatedBalance.getStatusCode());
    }

    @GetMapping("/all-users")
    ResponseEntity<List<User>> findAllUser()
    {
        List<User> allUsers = userService.findAllUser();
        return new ResponseEntity<>(allUsers,HttpStatus.FOUND);
    }

    @GetMapping("/all-transactions/{id}")
    ResponseEntity<List<Transaction>> allTransactionByUser(@PathVariable("id") Integer id)
    {
        System.out.println("inside all transation");
        List<Transaction> allTransaction = userService.allTransactionByUser(id);
        return new ResponseEntity<>(allTransaction,HttpStatus.OK);
    }







}
