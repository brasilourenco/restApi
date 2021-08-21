package home.testrestapi.controller;

import home.testrestapi.model.UserModel;
import home.testrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping(path = "/api/user")
    public List<UserModel> consultAll(){
        return (List<UserModel>) repository.findAll();
    }

    @GetMapping(path = "/api/user/{id}")
    public ResponseEntity consult(@PathVariable("id") Integer id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/api/user/post")
    public UserModel post(@RequestBody UserModel userModel){
        return repository.save(userModel);
    }

    @PutMapping (path = "/api/user/{id}")
    public UserModel edit(@RequestBody UserModel userModel, @PathVariable("id") Integer id){
        return repository.save(userModel);
    }
    @DeleteMapping(path = "/api/user/{id}")
    public void delete(@PathVariable("id") Integer id){
        repository.deleteById(id);
    }

}
