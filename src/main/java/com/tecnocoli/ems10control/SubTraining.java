/*
 *  Copyright (c) 2023.
 *  http://www.tecnocoli.com.br
 *  All rights reserved
 */

package com.tecnocoli.ems10control;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author jcoli - Jeferson Coli - Tecnocoli
 * @brief SubTraining
 * @package com.tecnocoli.ems10control
 * @date 9/29/23 - 07:38
 */
public class SubTraining implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String description;
    private Integer frequency1;
    private Integer frequency2;
    private Integer frequency3;
    private Integer frequency4;
    private Integer timeActive;
    private Integer timePause;
    private Integer timeTraining;
    private CurveType curveType;

    public SubTraining() {
    }

    public SubTraining(Integer id, String name, String description,
                       Integer frequency1, Integer frequency2, Integer frequency3, Integer frequency4,
                       Integer timeActive, Integer timePause, Integer timeTraining, CurveType curveType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.frequency1 = frequency1;
        this.frequency2 = frequency2;
        this.frequency3 = frequency3;
        this.frequency4 = frequency4;
        this.timeActive = timeActive;
        this.timePause = timePause;
        this.timeTraining = timeTraining;
        this.curveType = curveType;
    }

    public SubTraining(String name, String description, Integer frequency1, Integer frequency2,
                       Integer frequency3, Integer frequency4, Integer timeActive,
                       Integer timePause, Integer timeTraining, CurveType curveType) {
        this.name = name;
        this.description = description;
        this.frequency1 = frequency1;
        this.frequency2 = frequency2;
        this.frequency3 = frequency3;
        this.frequency4 = frequency4;
        this.timeActive = timeActive;
        this.timePause = timePause;
        this.timeTraining = timeTraining;
        this.curveType = curveType;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getFrequency1() { return frequency1; }
    public void setFrequency1(Integer frequency1) { this.frequency1 = frequency1; }
    public Integer getFrequency2() { return frequency2; }
    public void setFrequency2(Integer frequency2) { this.frequency2 = frequency2; }
    public Integer getFrequency3() { return frequency3; }
    public void setFrequency3(Integer frequency3) { this.frequency3 = frequency3; }
    public Integer getFrequency4() { return frequency4; }
    public void setFrequency4(Integer frequency4) { this.frequency4 = frequency4; }
    public Integer getTimeActive() { return timeActive; }
    public void setTimeActive(Integer timeActive) { this.timeActive = timeActive; }
    public Integer getTimePause() { return timePause; }
    public void setTimePause(Integer timePause) { this.timePause = timePause; }
    public Integer getTimeTraining() { return timeTraining; }
    public void setTimeTraining(Integer timeTraining) { this.timeTraining = timeTraining; }
    public CurveType getCurveType() { return curveType; }
    public void setCurveType(CurveType curveType) { this.curveType = curveType; }

    @Override
    public String toString() {
        return "SubTraining{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", frequency1=" + frequency1 +
                ", frequency2=" + frequency2 +
                ", frequency3=" + frequency3 +
                ", frequency4=" + frequency4 +
                ", timeActive=" + timeActive +
                ", timePause=" + timePause +
                ", timeTraining=" + timeTraining +
                ", curveType=" + curveType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubTraining that = (SubTraining) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
