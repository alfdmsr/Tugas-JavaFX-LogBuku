/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;

/**
 *
 * @author M S I
 */
public class Buku {
    private String judul;
    private String penulis;
    private LocalDate tanggalSelesai;
    private int rating; // dari 1-5
    private int halaman;
    
    
    public Buku(String judul, String penulis, LocalDate tanggalSelesai, int rating, int halaman) {
        this.judul = judul;
        this.penulis = penulis;
        this.tanggalSelesai = tanggalSelesai;
        this.rating = rating;
        this.halaman = halaman;
    }
    public String getJudul() {
        return judul;
    }
    
    public void setJudul(String judul) {
        this.judul = judul;
    }
    
    public String getPenulis() {
        return penulis;
    }
    
    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }
    
    public LocalDate getTanggalSelesai() {
        return tanggalSelesai;
    }
    
    public void setTanggalSelesai(LocalDate tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }
    
    public int getRating() {
        return rating;
    }
    
    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public int getHalaman() {
        return halaman;
    }
    
    public void setHalaman(int halaman) {
        this.halaman = halaman;
    }
}
