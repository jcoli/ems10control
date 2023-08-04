/*
 * Copyright 2023 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br
 * All rights reserved
 */

package com.tecnocoli.ems10control.util;

import javafx.scene.control.TextField;

/**
 * @author jcoli - Jeferson Coli - Tecnocoli
 * @brief Constraints
 * @package com.tecnocoli.ems10control.util
 * @date 8/3/23 - 09:39
 */
public class Constraints {
    public static void setTextFieldNull(TextField txt) {
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null && !newValue.matches("\\d*([\\.]\\d*)?")) {
                txt.setText(oldValue);
            }
        });
    }
}