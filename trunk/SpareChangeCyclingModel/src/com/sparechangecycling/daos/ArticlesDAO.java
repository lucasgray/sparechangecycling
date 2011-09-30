package com.sparechangecycling.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sparechangecycling.pojos.Article;

public class ArticlesDAO extends SccDAO {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void saveArticle(Article article) {
		Session session = getSession();
		
		try {
			session.merge(article);
		} catch (Exception e) {
			logger.info("prob saving articles: "+e.getMessage());

		}
	}

	@SuppressWarnings("unchecked")
	public List<Article> getLatestArticles() {
		Session session = getSession();

		List<Article> articles = null;
		try {
			articles = session.createCriteria(Article.class).list();
			logger.info("num of articles: "+articles.size());

		} catch (Exception e) {
			logger.info("prob getting articles: "+e.getMessage());

		}	
		return articles;
	}

	public Article getArticle(Long id) {
		Session session = getSession();

		Article article = null;
		try {
			article = (Article)session.createCriteria(Article.class).add(Restrictions.eq("id", id)).uniqueResult();
			logger.info("found article: "+article.getTitle());
			
		} catch (Exception e) {
			logger.info("prob getting article: "+e.getMessage());
		}
		
		return article;
	}
	
}
