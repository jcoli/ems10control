/*
 *  Copyright (c) 2023.
 *  http://www.tecnocoli.com.br
 *  All rights reserved
 */

package com.tecnocoli.ems10control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jcoli - Jeferson Coli - Tecnocoli
 * @brief Training
 * @package com.tecnocoli.ems10control
 * @date 9/29/23 - 07:34
 */
public class Training implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String description;
    private List<SubTraining> subTrainings = new ArrayList<>();




}
