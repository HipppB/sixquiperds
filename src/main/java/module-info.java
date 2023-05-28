module com.isep.gone.sixquiperd {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires lombok;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires annotations;
    requires spring.web;


    exports com.isep.gone.sixquiperd;
    opens com.isep.gone.sixquiperd to javafx.fxml;
    exports com.isep.gone.sixquiperd.ui;
    opens com.isep.gone.sixquiperd.ui to javafx.fxml;

}
