package com.example.exercise_service.Service;

import com.example.exercise_service.Model.NewsArticle;
import jakarta.validation.constraints.Pattern;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class NewsArticleService {

    ArrayList<NewsArticle> newsArticles = new ArrayList<>();

    public ArrayList<NewsArticle> getNewsArticles(){
        return newsArticles;
    }

    public void addNewsArticles(NewsArticle newsArticle){
        newsArticle.setPublished(false);
        newsArticle.setPublishDate(null);
        newsArticles.add(newsArticle);
    }

    public boolean updateNewsArticles( String id , NewsArticle newsArticle){
        for( int i=0 ; i<newsArticles.size() ; i++){
            if(newsArticles.get(i).getID().equalsIgnoreCase(id)){
                newsArticle.setPublished( newsArticles.get(i).isPublished() );
                newsArticles.set(i,newsArticle);
                return true;
            }
        }
        return false;
    }

    public boolean deleteNewsArticles( String id){
        for( int i=0 ; i<newsArticles.size() ; i++){
            if(newsArticles.get(i).getID().equalsIgnoreCase(id)){
                newsArticles.remove(i);
                return true;
            }
        }
        return false;
    }

    public int publish( String id){
        for( int i=0 ; i<newsArticles.size() ; i++){
            if(newsArticles.get(i).getID().equalsIgnoreCase(id)){
                if(newsArticles.get(i).isPublished()){
                    return 1;
                }

                newsArticles.get(i).setPublishDate( LocalDate.now() );
                newsArticles.get(i).setPublished(true);
                return 2;
            }
        }
        return 3;
    }

    public ArrayList<NewsArticle> getPublished(){
        ArrayList<NewsArticle> temp= new ArrayList<>();
        for( int i=0 ; i<newsArticles.size() ; i++){
            if(newsArticles.get(i).isPublished()){
                temp.add(newsArticles.get(i));
            }
        }
        return temp;
    }

    public ArrayList<NewsArticle> getByCategory(String category){

        ArrayList<NewsArticle> temp= new ArrayList<>();

        if(!category.equalsIgnoreCase("politics") && !category.equalsIgnoreCase("sports") &&  !category.equalsIgnoreCase("technology")) {
            NewsArticle noMatch= new NewsArticle("000" , "nOmAtCh" , "nOmAtCh" , "nOmAtCh" , "nOmAtCh" , "nOmAtCh" , false , LocalDate.now());
            temp.add(noMatch);
            return temp;
        }

        for( int i=0 ; i<newsArticles.size() ; i++){
            if(newsArticles.get(i).getCategory().equalsIgnoreCase(category)){
                temp.add(newsArticles.get(i));
            }
        }
        return temp;
    }

}
