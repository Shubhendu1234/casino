package com.casino.org.Casino.Service;

import com.casino.org.Casino.Entity.Transaction;
import com.casino.org.Casino.Entity.User;
import com.casino.org.Casino.Repository.TransactionRepository;
import com.casino.org.Casino.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {


    private final UserRepository userRepository;

    private final TransactionRepository transactionRepository;

    public  ResponseEntity<String> createUser(User user)
    {
        userRepository.save(user);

        return new ResponseEntity<>("User Created Successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<String> drawChance(double amount,Integer selectedNo,Integer id) throws Exception {
       Optional<User> user = userRepository.findById(id);
       Transaction transaction = new Transaction();

       transaction.setTransactionDateTime(LocalDateTime.now());

       if(user.isEmpty())
       {
           throw new Exception("User not found");
       }
       else
       {

         User foundUser =  user.get();
         transaction.setUserId(foundUser.getUserId());

         if(foundUser.getBalance()>amount && selectedNo>0 && selectedNo<37)
         {
             foundUser.setBalance(foundUser.getBalance()-amount);
             User save = userRepository.save(foundUser);
             Integer drawNumber = (int)(Math.random()*36 ) +1;
             transaction.setStatus((byte) 1);
            if(selectedNo.equals(drawNumber))
            {
                transaction.setMessage("Congratulation you won!! ");
                transactionRepository.save(transaction);
                double amountWon = amount * 36;
                save.setBalance(save.getBalance()+amountWon);
                userRepository.save(save);
                return new ResponseEntity<>("Congratulation you won!! "+ Double.toString(amountWon), HttpStatus.OK);

            }
            else {

                userRepository.save(save);
                transaction.setMessage("You lose Play Again!!!");
                transactionRepository.save(transaction);
                transactionRepository.save(transaction);
                return new ResponseEntity<>("You lose Play Again!!!",HttpStatus.OK);
            }
         }
         else
         {
             transaction.setStatus((byte) 0);
             transaction.setMessage("Insufficient Balance ");
             transactionRepository.save(transaction);
             return new ResponseEntity<>("Insufficient Balance " +
                     "add more balance",HttpStatus.OK);
         }
       }
    }


    public ResponseEntity<?> addBalance(double amount, Integer id) {

        Optional<User> foundUser = userRepository.findById(id);

        if(foundUser.isPresent())
        {
            foundUser.get().setBalance(foundUser.get().getBalance()+amount);
            return new ResponseEntity<User>(foundUser.get(),HttpStatus.OK);
        }

        return new ResponseEntity<String>("User not found",HttpStatus.OK);


    }

    public List<User> findAllUser() {
        List<User> allUser = userRepository.findAll();

        return allUser;
    }

    public List<Transaction> allTransactionByUser(Integer id) {
        List<Transaction> allTransaction = transactionRepository.findAllByUserId(id);

        return allTransaction;
    }
}
