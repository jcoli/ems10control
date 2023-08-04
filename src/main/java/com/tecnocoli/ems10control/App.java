/*
 *  Copyright (c) 2023.
 *  http://www.tecnocoli.com.br
 *  All rights reserved
 */

package com.tecnocoli.ems10control;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.LocalDevice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;


import java.io.IOException;

/**
 * @author jcoli - Jeferson Coli - Tecnocoli
 * @brief App
 * @package com.tecnocoli.ems10control
 * @date 8/3/23 - 09:49
 */
public class App extends Application{
    private static Scene scene;
    static final Logger logger = LogManager.getLogger(App.class.getName());
    static final Boolean debug = true;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"));
        if (debug) {
            logger.info("start app");
            logger.info("BluetoothTCKAgentApp: Setting Device to Discoverable");
        }
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
