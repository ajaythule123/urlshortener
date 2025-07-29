package com.us.urlshortener.dao;

import com.us.urlshortener.model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class UrlDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Url url) {
        jdbcTemplate.update("INSERT INTO urls(original_url, short_code, user_id) VALUES (?, ?, ?)",
                url.getOriginalUrl(), url.getShortCode(), url.getUserId());
    }

    public Optional<Url> findByShortCode(String code) {
        String sql = "SELECT * FROM urls WHERE short_code = ?";
        return jdbcTemplate.query(sql, new Object[]{code}, rs -> {
            if (rs.next()) return Optional.of(mapRow(rs));
            return Optional.empty();
        });
    }

    private Url mapRow(ResultSet rs) throws SQLException {
        Url url = new Url();
        url.setId(rs.getInt("id"));
        url.setOriginalUrl(rs.getString("original_url"));
        url.setShortCode(rs.getString("short_code"));
        url.setUserId(rs.getInt("user_id"));
        return url;
    }
}
