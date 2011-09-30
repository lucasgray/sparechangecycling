package com.sparechangecycling.web.actions;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.sparechangecycling.daos.ArticlesDAO;
import com.sparechangecycling.pojos.Article;

@UrlBinding("/used-bikes/articles/{$event}/{id}")
public class ArticleActionBean extends BaseActionBean {
	
	
	private Article article;
	private Long id;
	private ArticlesDAO articlesDAO;
	
	public ArticleActionBean() {
		articlesDAO = new ArticlesDAO();
	}
	
	@HandlesEvent("edit")
	public Resolution editArticle() {
		
		article = articlesDAO.getArticle(id);
		
		
		return new ForwardResolution("/createArticle.jsp");
	}
	

	@HandlesEvent("saveArticle")
	public Resolution save() {
		
		articlesDAO.saveArticle(article);
		return new ForwardResolution("/index.jsp");
		
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
