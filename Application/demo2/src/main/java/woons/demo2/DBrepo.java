package woons.demo2;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Random;


public class DBrepo {
    private final JdbcTemplate jdbcTemplate;

    public DBrepo(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public void insert() {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        String sql = "insert into test values(?)";
        int res = jdbcTemplate.update(sql, random.nextInt());
    }

    public List<Integer> find() throws SQLException {
        List<Integer> res = jdbcTemplate.query("select * from test", rowMapper());
        return res;
    }

    RowMapper<Integer>rowMapper(){
        return (rs, rowNum) -> {
            Integer res;
            res = rs.getInt("count");
            return res;
        };
    }
}
