package com.example.schedulemanage.repository;

import com.example.schedulemanage.dto.ScheduleResponseDto;
import com.example.schedulemanage.entity.Schedule;
import com.example.schedulemanage.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository{
    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {
        // INSERT Query를 직접 작성하지 않아도 된다.

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Date today = new Date();
        Locale currentLocale = new Locale("KOREAN", "KOREA");
        String pattern = "yyyyMMddHHmm"; //hhmmss로 시간,분,초만 뽑기도 가능
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);

        Map<String, Object> parameters = new HashMap<>();
        //parameters.put("id", schedule.getId());
        parameters.put("userId", schedule.getUserId());
        parameters.put("schedule_name", schedule.getSchedule_name());
        parameters.put("schedule_content", schedule.getSchedule_content());
        parameters.put("schedule_pw", schedule.getSchedule_pw());
        parameters.put("schedule_st_date", schedule.getSchedule_st_date());
        parameters.put("schedule_ed_date", schedule.getSchedule_ed_date());
        parameters.put("schedule_cr_date", formatter.format(today));


        // 저장 후 생성된 key값을 Number 타입으로 반환하는 메서드
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        String message = "";
        if(key.intValue() > 0){
            message = "success";
        }else{
            message = "fail";
        }
        return new ScheduleResponseDto(message);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule() {
        return jdbcTemplate.query("select * from schedule", scheduleRowMapper());
    }

    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return (rs, rowNum) -> {
            //Schedule result = new Schedule();
            return new ScheduleResponseDto(
                    rs.getLong("id"),
                    rs.getString("schedule_name"),
                    rs.getString("schedule_content")
            );
        };
    }

    @Override
    public Optional<Schedule> findScheduleById(Long id) {
        List<Schedule> result = jdbcTemplate.query("select" +
                "       id" +
                "     , userId" +
                "     , schedule_name" +
                "     , schedule_content" +
                "     , schedule_st_date" +
                "     , schedule_ed_date" +
                "     , DATA_FORMAT(schedule_cr_date, \"%Y-%m-%d\")" +
                "     , DATA_FORMAT(schedule_up_date, \"%Y-%m-%d\")" +
                "from schedule; where id = ?", scheduleRowMapper2(), id);

        return result.stream().findAny();
    }
    private RowMapper<Schedule> scheduleRowMapper2() {
        return (rs, rowNum) -> {
            //Schedule result = new Schedule();
            return new Schedule(
                    rs.getLong("id"),
                    rs.getString("schedule_name"),
                    rs.getString("schedule_content"),
                    rs.getString("schedule_cr_date"),
                    rs.getString("schedule_up_date")
            );
        };
    }

    @Override
    public Optional<Schedule> findScheduleById(Long id) {
        List<Schedule> result = jdbcTemplate.query("select" +
                "       id" +
                "     , userId" +
                "     , schedule_name" +
                "     , schedule_content" +
                "     , schedule_st_date" +
                "     , schedule_ed_date" +
                "     , DATA_FORMAT(schedule_cr_date, \"%Y-%m-%d\")" +
                "     , DATA_FORMAT(schedule_up_date, \"%Y-%m-%d\")" +
                "from schedule; where id = ?", scheduleRowMapper2(), id);

        return result.stream().findAny();
    }
    private RowMapper<User> scheduleRowMapper3() {
        return (rs, rowNum) -> {
            return new User(
                    rs.getLong("userId"),
                    rs.getString("email"),
                    rs.getString("user_cr_date"),
                    rs.getString("user_up_date")
            );
        };
    }

    @Transactional
    @Override
    public int updateSchedule(Schedule schedule){
        String sql = "update schedule set schedule_name=?, schedule_content=?, userNm=? where id=?";
       return jdbcTemplate.update(sql, schedule.getSchedule_name(), schedule.getSchedule_content(), schedule.getUserNm(), schedule.getId());
    }

    @Override
    public int deleteSchedule(Long id) {
        return jdbcTemplate.update("delete from schedule where id=?", id);
    }

    public List<ScheduleResponseDto> findPagingSchedule(int pageno, int pagesize){
        return jdbcTemplate.query("SELECT * \n" +
                "FROM \n" +
                "(\n" +
                "  SELECT ROW_NUMBER() OVER(ORDER BY A.id ASC) AS ROWNUM,\n" +
                "  A.*\n" +
                "  FROM schedulemanage.schedule A\n" +
                ")B\n" +
                "WHERE B.ROWNUM BETWEEN "+ pageno +" AND "+ pagesize, scheduleRowMapper());
    }

}
