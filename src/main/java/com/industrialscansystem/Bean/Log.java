package com.industrialscansystem.Bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Log {

    @Id
    @GeneratedValue
    private Integer log_id;
    private Timestamp log_time;
    private String log_operator;
    private String log_operation;
    private Integer log_role;


    public Integer getLog_role() {
        return log_role;
    }

    public void setLog_role(Integer log_role) {
        this.log_role = log_role;
    }

    public Integer getLog_id() {
        return log_id;
    }

    public void setLog_id(Integer log_id) {
        this.log_id = log_id;
    }

    public Timestamp getLog_time() {
        return log_time;
    }

    public void setLog_time(Timestamp log_time) {
        this.log_time = log_time;
    }

    public String getLog_operator() {
        return log_operator;
    }

    public void setLog_operator(String log_operator) {
        this.log_operator = log_operator;
    }

    public String getLog_operation() {
        return log_operation;
    }

    public void setLog_operation(String log_operation) {
        this.log_operation = log_operation;
    }


}
