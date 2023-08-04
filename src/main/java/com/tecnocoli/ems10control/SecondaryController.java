/*
 *  Copyright (c) 2023.
 *  http://www.tecnocoli.com.br
 *  All rights reserved
 */

package com.tecnocoli.ems10control;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 * @author jcoli - Jeferson Coli - Tecnocoli
 * @brief SecondaryController
 * @package com.tecnocoli.ems10control
 * @date 8/3/23 - 10:13
 */
public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}
