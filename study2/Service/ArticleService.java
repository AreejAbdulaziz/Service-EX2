package com.example.study2.Service;

import com.example.study2.Model.Article;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class ArticleService {
    ArrayList<Article>articles=new ArrayList<>();
    public ArrayList<Article>getAll(){
        return articles;
    }
    public void addArticle(Article article){
        articles.add(article);
    }
    public boolean updateArticle(String id,Article article){
        for(int i=0;i<articles.size();i++){
            if(articles.get(i).getId().equals(id)){
                articles.set(i,article);
                return true;
            }
        } return false;
    }
    public boolean deleteArticle(String id){
        for(int i=0;i<articles.size();i++){
            if(articles.get(i).getId().equals(id)){
                articles.remove(articles.get(i));
                return true;
            }
        } return false;
    }
    ArrayList<Article>published=new ArrayList<>();
    public boolean publish(String id){
        for(Article a:articles){
            if(a.getId().equals(id)){
                a.setPublished(true);
                a.setPublishDate(LocalDate.now());
                published.add(a);
                return true;
            }
        } return false;
    }
    public ArrayList<Article>allPublished(){
        return published;
    }
    ArrayList<Article>categories=new ArrayList<>();
    public ArrayList<Article>category(String category){
        if(category.equals("politics")|category.equals("sports")|category.equals("technology")){
            categories.clear();
            for(Article e:articles){
                if(e.getCategory().equals(category)){
                    categories.add(e);
                }
            }
            return categories;
        } return null;
    }
}
