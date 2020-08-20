package com.example.mybatisdemo.pojo;

import java.io.Serializable;

public class GoodsOrder implements Serializable {
    private Integer id;

    private String series;

    private static final long serialVersionUID = 1L;

    public GoodsOrder(Integer id, String series) {
        this.id = id;
        this.series = series;
    }

    public GoodsOrder() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series == null ? null : series.trim();
    }

    @Override
    public String toString() {
        return "GoodsOrder{" +
                "id=" + id +
                ", series='" + series + '\'' +
                '}';
    }
}