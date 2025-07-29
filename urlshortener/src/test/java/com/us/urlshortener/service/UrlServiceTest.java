package com.us.urlshortener.service;

import com.us.urlshortener.dao.UrlDao;
import com.us.urlshortener.model.Url;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UrlServiceTest {
    @Test
    void testShortenUrlGeneratesCode() {
        UrlDao dao = mock(UrlDao.class);
        UrlService service = new UrlService();
        service.setUrlDao(dao);

        String shortCode = service.shortenUrl("https://us.com", null, null);
        assertNotNull(shortCode);
    }
}