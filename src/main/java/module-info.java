module com.tecnocoli.ems10control {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires bluecove;


    opens com.tecnocoli.ems10control to javafx.fxml;
    exports com.tecnocoli.ems10control;
}