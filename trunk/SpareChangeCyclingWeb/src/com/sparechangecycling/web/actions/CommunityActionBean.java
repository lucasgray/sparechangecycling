package com.sparechangecycling.web.actions;

import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.action.UrlBinding;

import com.sparechangecycling.daos.ArticlesDAO;
import com.sparechangecycling.daos.UserDAO;
import com.sparechangecycling.pojos.Article;
import com.sparechangecycling.pojos.Message;

@UrlBinding("/used-bikes/community/{$event}/{id}")
public class CommunityActionBean extends BaseActionBean {
	
	@DefaultHandler
	@HandlesEvent("begin")
	public Resolution begin() {
		
		return new ForwardResolution("/community.jsp");
	}
	
	private Article article;
	private List<Article> articles;
	private Long id;
	
	private ArticlesDAO dao;
	
	public CommunityActionBean() {
		dao = new ArticlesDAO();
	}

	
	
	@HandlesEvent("populateArticles")
	public Resolution populate() {

		return null;
	}
	
	@HandlesEvent("viewSingleArticle")
	public Resolution viewSingleArticle() {
		article = dao.getArticle(id);
		getContext().setSingleArticleBean(article);
		return new ForwardResolution("/viewArticle.jsp");
	}
	
	
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public List<Article> getArticles() {
		articles = dao.getLatestArticles();
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	
	
}
