package com.example.study2.Controller;

import com.example.study2.Model.Article;
import com.example.study2.Service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    @GetMapping("/get")
    public ResponseEntity getAll(){
        ArrayList<Article>articles=articleService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(articles);
    }
    @PostMapping("/add")
    public ResponseEntity addArticle(@RequestBody@Valid Article article, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        articleService.addArticle(article);
        return ResponseEntity.status(HttpStatus.OK).body("Article added!");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateArticle(@PathVariable String id,@RequestBody@Valid Article article,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        boolean isChange=articleService.updateArticle(id,article);
        if(isChange){
            return ResponseEntity.status(HttpStatus.OK).body("Article changed");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong id");
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteArticle(@PathVariable String id){
    boolean isDeleted=articleService.deleteArticle(id);
    if(isDeleted){
        return ResponseEntity.status(HttpStatus.OK).body("Article deleted");
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong id");
    }
    @PutMapping("/publish/{id}")
    public ResponseEntity publish(@PathVariable String id){
        boolean isChanged=articleService.publish(id);
        if(isChanged){
            return ResponseEntity.status(HttpStatus.OK).body("is published");
        }
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong id");
    }
    @GetMapping("/allPublished")
    public ResponseEntity allPublished(){
        ArrayList<Article>published=articleService.allPublished();
        return ResponseEntity.status(HttpStatus.OK).body(published);
    }
    @GetMapping("/category/{category}")
    public ResponseEntity category(@PathVariable String category){
    ArrayList<Article>categories=articleService.category(category);
    if(categories==null){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong category");
    }
    return ResponseEntity.status(HttpStatus.OK).body(categories);
    }
}
