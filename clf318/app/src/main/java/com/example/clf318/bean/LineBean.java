package com.example.clf318.bean;

public class LineBean {
    private String year;
    private Integer salaries;

    public LineBean(String year, Integer salaries) {
        this.year = year;
        this.salaries = salaries;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getSalaries() {
        return salaries;
    }

    public void setSalaries(Integer salaries) {
        this.salaries = salaries;
    }
}
