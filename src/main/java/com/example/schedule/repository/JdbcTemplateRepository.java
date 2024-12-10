package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcTemplateRepository implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto addSchedule(ScheduleRequestDto dto) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", dto.getTitle());
        parameters.put("contents", dto.getContents());
        parameters.put("author", dto.getAuthor());
        parameters.put("pw", dto.getPw());
        parameters.put("date", LocalDateTime.now());


        // 저장 후 생성된 key값을 Number 타입으로 반환하는 메서드
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(key.longValue(), dto.getAuthor(), dto.getTitle(), dto.getContents(), LocalDateTime.now());
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule(String author, String date) {
        String sql = "select * from schedule where 1 = 1 ";
        List<Object> params = new ArrayList<>();
        if (author != null && !author.isEmpty()) {
            sql += "and author = ? ";
            params.add(author);
        }
        if (date != null && !date.isEmpty()) {
            sql += "and date(date) = ? ";
            params.add(date);
        }
        sql += "order by date desc";
        return jdbcTemplate.query(sql, params.toArray(), scheduleRowMapper());
    }

    @Override
    public ScheduleResponseDto fineScheduleById(Long id) {
        List<ScheduleResponseDto> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapper(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A value matching the id value could not be found."));
    }

    @Override
    public int modifySchedule(Long id, String pw, String author, String contents) {
        return jdbcTemplate.update("update schedule set author = ?, contents = ?, date = now() where id = ? and pw = ?", author, contents, id, pw);
    }

    @Override
    public int deleteSchedule(Long id, String pw) {
        return jdbcTemplate.update("delete from schedule where id = ? and pw = ?", id, pw);
    }

    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("author"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("date").toLocalDateTime());
            }
        };
    }
}