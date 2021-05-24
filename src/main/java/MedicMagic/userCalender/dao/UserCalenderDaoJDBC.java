package MedicMagic.userCalender.dao;

import MedicMagic.sqlService.SqlService;
import MedicMagic.exception.DuplicateDateException;
import MedicMagic.exception.NegativeException;
import MedicMagic.userCalender.domain.UserCalender;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserCalenderDaoJDBC implements UserCalenderDao {
    private JdbcTemplate jdbcTemplate;
    private SqlService sqlService;

    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setSqlService(SqlService sqlService) {
        this.sqlService = sqlService;
    }

    private RowMapper<UserCalender> userCalenderRowMapper =
            new RowMapper<UserCalender>() {
                @Override
                public UserCalender mapRow(ResultSet resultSet, int i) throws SQLException {
                    UserCalender userCalender = new UserCalender();
                    userCalender.setId(resultSet.getString("id"));
                    userCalender.setDate(resultSet.getString("date"));
                    userCalender.setWeigh(resultSet.getDouble("weigh"));
                    userCalender.setSleepTime(resultSet.getString("sleepTime"));
                    userCalender.setExerciseTime(resultSet.getString("exerciseTime"));
                    userCalender.setWaterIntake(resultSet.getDouble("waterIntake"));
                    userCalender.setStartDay(resultSet.getString("startDay"));
                    userCalender.setEndDay(resultSet.getString("endDay"));
                    userCalender.setEmotion(resultSet.getString("emotion"));
                    userCalender.setSymptom(resultSet.getBoolean("symptom"));
                    userCalender.setMucus(resultSet.getBoolean("mucus"));

                    return userCalender;
                }
            };

    @Override
    public void add(UserCalender userCalender) throws DuplicateDateException {
        try {
            this.jdbcTemplate.update(this.sqlService.getSql("userCalenderAdd"),
                    userCalender.getId(),
                    userCalender.getDate(),
                    nullCheck("weigh", userCalender.getWeigh()),
                    nullCheck("sleepTime", userCalender.getSleepTime()),
                    nullCheck("exerciseTime", userCalender.getExerciseTime()),
                    nullCheck("waterIntake", userCalender.getWaterIntake()),
                    nullCheck("startDay", userCalender.getStartDay()),
                    nullCheck("endDay", userCalender.getEndDay()),
                    userCalender.getEmotion(),
                    userCalender.isSymptom(),
                    userCalender.isMucus());
        } catch(DuplicateKeyException e) {
            throw new DuplicateDateException("중복된 날짜입니다", e);
        }
    }

    @Override
    public UserCalender get(String id, String date) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userCalenderGet"),
                new Object[]{id, date}, this.userCalenderRowMapper);
    }

    @Override
    public List<UserCalender> getAll() {
        return this.jdbcTemplate.query(this.sqlService.getSql("userCalenderGetAll"), this.userCalenderRowMapper);
    }

    @Override
    public List<UserCalender> getEachId(String id) {
        return this.jdbcTemplate.query(this.sqlService.getSql("userCalenderGetEachId"),
                new Object[]{id}, this.userCalenderRowMapper);
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update(this.sqlService.getSql("userCalenderDeleteAll"));
    }

    @Override
    public void deleteEachIdAndDate(String id, String date) {
        this.jdbcTemplate.update(this.sqlService.getSql("userCalenderDeleteEachIdAndDate"), id, date);
    }

    @Override
    public int getCount() {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userCalenderGetCount"), Integer.class);
    }

    @Override
    public int getCountEachId(String id) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userCalenderGetCountEachId"), new Object[]{id}, Integer.class);
    }

    @Override
    public int getCountEachIdAndDate(String id, String date) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userCalenderGetCountEachIdAndDate"), new Object[]{id, date}, Integer.class);
    }

    @Override
    public void update(UserCalender userCalender) {
        this.jdbcTemplate.update(
                this.sqlService.getSql("userCalenderUpdate"),
                nullCheck("weigh", userCalender.getWeigh()),
                nullCheck("sleepTime", userCalender.getSleepTime()),
                nullCheck("exerciseTime", userCalender.getExerciseTime()),
                nullCheck("waterIntake", userCalender.getWaterIntake()),
                nullCheck("startDay", userCalender.getStartDay()),
                nullCheck("endDay", userCalender.getEndDay()),
                userCalender.getEmotion(),
                userCalender.isSymptom(),
                userCalender.isMucus(),
                userCalender.getId(),
                userCalender.getDate()
        );
    }

    private Double negativeInspection(Double object) throws NegativeException {

        if(object < 0.0) { throw new NegativeException("양수를 입력해주세요!"); }

        return object;
    };

    private Object nullCheck(String column, Object object) {
        if(column == "weigh" && object != null) {
            object = negativeInspection(Double.parseDouble(object.toString()));
        }
        else if(column == "sleepTime" && object != null) {
            object = java.sql.Time.valueOf(object.toString());
        }
        else if(column == "exerciseTime" && object != null) {
            object = java.sql.Time.valueOf(object.toString());
        }
        else if(column == "waterIntake" && object != null) {
            object = negativeInspection(Double.parseDouble(object.toString()));
        }
        else if(column == "startDay" && object != null) {
            object = java.sql.Date.valueOf(object.toString());
        }
        else if(column == "endDay" && object != null) {
            object = java.sql.Date.valueOf(object.toString());
        }

        return object;
    }
}
