package com.example.exercise_service.Controller;

import com.example.exercise_service.Api.ApiResponse;
import com.example.exercise_service.Model.NewsArticle;
import com.example.exercise_service.Service.NewsArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/article")
@RequiredArgsConstructor
public class NewsArticleController {

    private final NewsArticleService newsArticleService;

    @GetMapping("/get")
    public ResponseEntity<?> getNewsArticle(){
        return ResponseEntity.status(200).body(newsArticleService.getNewsArticles());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewsArticle(@RequestBody @Valid NewsArticle newsArticle , Errors errors){
        if(errors.hasErrors()){return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());}

        newsArticleService.addNewsArticles(newsArticle);
        return ResponseEntity.status(200).body(new ApiResponse("Article added"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateNewsArticle(@PathVariable String id, @RequestBody @Valid NewsArticle newsArticle , Errors errors){
        if(errors.hasErrors()){return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());}

        if (newsArticleService.updateNewsArticles(id , newsArticle)){
            return ResponseEntity.status(200).body(new ApiResponse("Article updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("id not found"));

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNewsArticle(@PathVariable String id){

        if (newsArticleService.deleteNewsArticles(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Article deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("id not found"));

    }

    @PutMapping("/publish/{id}")
    public ResponseEntity<?> publish(@PathVariable String id){

        int temp=newsArticleService.publish(id);

        if (temp==1){
            return ResponseEntity.status(400).body(new ApiResponse("Article is already published"));
        }

        if (temp==2){
            return ResponseEntity.status(200).body(new ApiResponse("Article now published"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("id not found"));

    }

    @GetMapping("/get/published")
    public ResponseEntity<?> getPublished(){

        ArrayList<NewsArticle> temp = newsArticleService.getPublished();

        if (temp.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("all article are not published"));
        }
        return ResponseEntity.status(200).body(temp);
    }

    @GetMapping("/get/category/{category}")
    public ResponseEntity<?> getByCategory(@PathVariable String category){

        ArrayList<NewsArticle> temp = newsArticleService.getByCategory(category);

        if (temp.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("no article in this category"));
        }

        NewsArticle noMatch= new NewsArticle("000" , "nOmAtCh" , "nOmAtCh" , "nOmAtCh" , "nOmAtCh" , "nOmAtCh" , false , LocalDate.now());

        if (temp.get(0).equals(noMatch)){
            return ResponseEntity.status(400).body(new ApiResponse("The category must be politics or sports or technology"));
        }
        return ResponseEntity.status(200).body(temp);
    }


}
