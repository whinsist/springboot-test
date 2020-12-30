package com.zimug.bootlaunch.service.impl;

import com.zimug.bootlaunch.generator.testdb.Article;
import com.zimug.bootlaunch.generator.testdb.ArticleMapper;
import com.zimug.bootlaunch.model.ArticleVO;
import com.zimug.bootlaunch.service.ArticleRestService;
import com.zimug.bootlaunch.utils.DozerUtils;

import org.dozer.Mapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArticleMybatisRestServiceImpl implements ArticleRestService {

    @Resource
    protected Mapper dozerMapper;

    @Resource
    private ArticleMapper articleMapper;


    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "article", key = "#article.getId()"),
                    @CacheEvict(value = "articleAll",allEntries = true)
            }
    )
    public ArticleVO saveArticle(ArticleVO article) {
        Article articlePO = dozerMapper.map(article, Article.class);
        articleMapper.insert(articlePO);

        //TODO 把readers村到数据库里面
        article.setId(articlePO.getId());
        return article;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "article", key = "#id"),
                    @CacheEvict(value = "articleAll",allEntries = true)
            }
    )
    public void deleteArticle(Long id) {
        articleMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "article",key = "#article.getId()"),
                    @CacheEvict(value = "articleAll",allEntries = true)
            }
    )
    public ArticleVO updateArticle(ArticleVO article) {
        Article articlePO = dozerMapper.map(article, Article.class);
        articleMapper.updateByPrimaryKeySelective(articlePO);
        return article;
    }

    @Override
    @Cacheable(value = "article", key = "#id", condition = "#id > 0", unless = "#result == null")
    public ArticleVO getArticle(Long id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        if (article == null) {
            return null;
        }
        ArticleVO articleVO = dozerMapper.map(article, ArticleVO.class);
        //TODO 把读者信息查询出来赋值给ArticleVo
        return articleVO;
    }

    @Override
    @Cacheable(value="articleAll")
    public List<ArticleVO> getAll() {
        List<Article> articles = articleMapper.selectByExample(null);
        return DozerUtils.mapList(articles, ArticleVO.class);
    }
}
