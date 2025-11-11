/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.example.catatanbuku;

/**
 *
 * @author M S I
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application{ 
 @Override
    public void start(Stage stage) throws IOException {
        // Muat file FXML
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/View/BukuView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        // Set judul dan tampilkan stage
        stage.setTitle("Aplikasi Catatan Buku");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
