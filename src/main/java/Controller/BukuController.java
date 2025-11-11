/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Buku;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author M S I
 */
public class BukuController  {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField tfJudul;
    @FXML
    private TextField tfPenulis;
    @FXML
    private DatePicker dpTanggalSelesai;
    @FXML
    private TextField tfHalaman;
    @FXML
    private Spinner<Integer> spRating; // Komponen UI tambahan
    
    @FXML
    private TableView<Buku> tvLogBuku;
    @FXML
    private TableColumn<Buku, String> tcJudul;
    @FXML
    private TableColumn<Buku, String> tcPenulis;
    @FXML
    private TableColumn<Buku, LocalDate> tcTanggalSelesai;
    @FXML
    private TableColumn<Buku, Integer> tcRating;
    @FXML
    private TableColumn<Buku, Integer> tcHalaman;

    // untuk tombolnya 
    @FXML
    private Button btnTambah;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnHapus;
    
    private ObservableList<Buku> bukuList = FXCollections.observableArrayList();
    
        @FXML
    public void initialize() {
        // Konfigurasi Spinner untuk rating (1-5)
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
        spRating.setValueFactory(valueFactory);

        // Hubungkan kolom TableView dengan atribut di Model
        tcJudul.setCellValueFactory(new PropertyValueFactory<>("judul"));
        tcPenulis.setCellValueFactory(new PropertyValueFactory<>("penulis"));
        tcTanggalSelesai.setCellValueFactory(new PropertyValueFactory<>("tanggalSelesai"));
        tcRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        tcHalaman.setCellValueFactory(new PropertyValueFactory<>("halaman"));

        // Masukkan data awal (opsional, untuk testing)
        bukuList.add(new Buku("Laskar Pelangi", "Andrea Hirata", LocalDate.of(2025, 10, 1), 5, 529));
        bukuList.add(new Buku("Sapiens", "Yuval Noah Harari", LocalDate.of(2025, 9, 15), 4, 443));

        // Tampilkan data di TableView
        tvLogBuku.setItems(bukuList);

        // 4. Logika untuk menampilkan data yang diklik ke form
        tvLogBuku.setRowFactory(tv -> {
            TableRow<Buku> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    Buku rowData = row.getItem();
                    showDataToForm(rowData);
                }
            });
            return row;
        });
    }
    
    // (Read) Ini Method untuk menampilkan data dari Tableview ke from Input 
    private void showDataToForm(Buku buku) {
        tfJudul.setText(buku.getJudul());
        tfPenulis.setText(buku.getPenulis());
        dpTanggalSelesai.setValue(buku.getTanggalSelesai());
        tfHalaman.setText(String.valueOf(buku.getHalaman()));
        spRating.getValueFactory().setValue(buku.getRating());
    }
    // Ini Method untuk membersihkan form
    private void clearForm() {
        tfJudul.clear();
        tfPenulis.clear();
        dpTanggalSelesai.setValue(null);
        tfHalaman.clear();
        spRating.getValueFactory().setValue(1);
    }
    
    // Create
    @FXML
    private void handleTambahAction(ActionEvent event) {
        // Ambil data dari form
        String judul = tfJudul.getText();
        String penulis = tfPenulis.getText();
        LocalDate tanggal = dpTanggalSelesai.getValue();
        int rating = spRating.getValue();
        int halaman;
        
    if (judul.isEmpty() || penulis.isEmpty() || tanggal == null || tfHalaman.getText().isEmpty()) {
            showAlert("Error", "Semua field harus diisi!");
            return;
        }

        try {
            halaman = Integer.parseInt(tfHalaman.getText());
        } catch (NumberFormatException e) {
            showAlert("Error", "Halaman harus berupa angka!");
            return;
        }
        
        Buku bukuBaru = new Buku(judul, penulis, tanggal, rating, halaman);
        bukuList.add(bukuBaru);

        // Bersihkan form
        clearForm();
    }
    
    // Update
    @FXML
    private void handleUpdateAction(ActionEvent event) {
        // Ambil data yang dipilih di tabel
        Buku selectedBuku = tvLogBuku.getSelectionModel().getSelectedItem();

        if (selectedBuku == null) {
            showAlert("Error", "Pilih data buku yang akan diupdate!");
            return;
        }
        
        if (tfJudul.getText().isEmpty() || tfPenulis.getText().isEmpty() || dpTanggalSelesai.getValue() == null || tfHalaman.getText().isEmpty()) {
            showAlert("Error", "Semua field harus diisi!");
            return;
        }
        
        selectedBuku.setJudul(tfJudul.getText());
        selectedBuku.setPenulis(tfPenulis.getText());
        selectedBuku.setTanggalSelesai(dpTanggalSelesai.getValue());
        selectedBuku.setRating(spRating.getValue());
        selectedBuku.setHalaman(Integer.parseInt(tfHalaman.getText()));
        
         tvLogBuku.refresh();
        clearForm();
    }
    
    // Delete
    @FXML
    private void handleHapusAction(ActionEvent event) {
        // Ambil data yang dipilih
        Buku selectedBuku = tvLogBuku.getSelectionModel().getSelectedItem();

        if (selectedBuku == null) {
            showAlert("Error", "Pilih data buku yang akan dihapus!");
            return;
        }

        // Hapus dari list
        bukuList.remove(selectedBuku);
        clearForm();
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
     
