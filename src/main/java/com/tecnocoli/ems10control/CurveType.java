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
 * @brief CurveType
 * @package com.tecnocoli.ems10control
 * @date 9/29/23 - 07:46
 */
public class CurveType implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String description;
    private String type;

    public CurveType() {
    }

    public CurveType(Integer id, String name, String description, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public CurveType(String name, String description, String type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() {  return description; }
    public void setDescription(String description) { this.description = description; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    @Override
    public String toString() {
        return "CurveType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurveType curveType = (CurveType) o;
        return Objects.equals(id, curveType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
