
package com.us.urlshortener.service;

import com.us.urlshortener.dao.UrlDao;
import com.us.urlshortener.model.Url;
import com.us.urlshortener.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {
    private UrlDao urlDao;

    @Autowired
    public void setUrlDao(UrlDao urlDao) {
        this.urlDao = urlDao;
    }

    public String shortenUrl(String originalUrl, Integer userId, String customCode) {
        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortCode(customCode != null ? customCode : UrlUtil.generateRandomCode());
        url.setUserId(userId);
        urlDao.save(url);
        return url.getShortCode();
    }

    public String getOriginalUrl(String code) {
        return urlDao.findByShortCode(code)
                .map(Url::getOriginalUrl)
                .orElseThrow(() -> new RuntimeException("URL not found"));
    }
}
