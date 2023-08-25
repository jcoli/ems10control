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
 * @brief Channel
 * @package com.tecnocoli.ems10control
 * @date 8/24/23 - 18:26
 */
public class Channel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Boolean actived;
    private Boolean detected;
    private Integer intensity;
    private String name;
    private String id_index;

    public Channel() {
    }

    public Channel(Boolean actived, Boolean detected, Integer intensity, String name, String id_index) {
        this.actived = actived;
        this.detected = detected;
        this.intensity = intensity;
        this.name = name;
        this.id_index = id_index;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getActived() {
        return actived;
    }

    public void setActived(Boolean actived) {
        this.actived = actived;
    }

    public Boolean getDetected() {
        return detected;
    }

    public void setDetected(Boolean detected) {
        this.detected = detected;
    }

    public Integer getIntensity() {
        return intensity;
    }

    public void setIntensity(Integer intensity) {
        this.intensity = intensity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_index() {
        return id_index;
    }

    public void setId_index(String id_index) {
        this.id_index = id_index;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "name='" + name + '\'' +
                ", id_index='" + id_index + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Channel channel = (Channel) o;
        return Objects.equals(id, channel.id) && Objects.equals(name, channel.name) && Objects.equals(id_index, channel.id_index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, id_index);
    }
}
