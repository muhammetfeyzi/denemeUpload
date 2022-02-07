package com.reading.getirreading.base.domain.model;

import javax.persistence.Column;
import java.io.Serializable;

public class Adress implements Serializable {
    @Column(name = "city_name")
    private String cityName;
    @Column(name = "town_name")
    private String townName;
    @Column(name = "district_name")
    private String districtName;
    @Column(name = "line1")
    private String line;
}
