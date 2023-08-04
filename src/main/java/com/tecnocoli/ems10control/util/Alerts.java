/*
 * Copyright 2023 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br
 * All rights reserved
 */

package com.tecnocoli.ems10control.util;

import javafx.scene.control.Alert;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * @author jcoli - Jeferson Coli - Tecnocoli
 * @brief Alerts
 * @package com.tecnocoli.ems10control.util
 * @date 8/3/23 - 09:39
 */
public class Alerts {
    public static void showAlert(String title, String header, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

}