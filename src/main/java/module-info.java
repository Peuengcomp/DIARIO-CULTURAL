module com.pbl3.pbl
{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires junit;

    opens com.pbl3.pbl to javafx.fxml;
    exports com.pbl3.pbl;

    exports com.pbl3.pbl.Testes.Integracao;
    exports com.pbl3.pbl.Testes.TestesDeSerializaçao;
    exports com.pbl3.pbl.Testes.Unidades;
    exports com.pbl3.pbl.Model.Repositorios;
    exports com.pbl3.pbl.Model.Entidades;
    exports com.pbl3.pbl.Controller.Buscar;
    exports com.pbl3.pbl.Controller.Carregar;
    exports com.pbl3.pbl.Controller.Criar;
    exports com.pbl3.pbl.Controller.Filtrar;
    exports com.pbl3.pbl.Controller.Salvar;

}