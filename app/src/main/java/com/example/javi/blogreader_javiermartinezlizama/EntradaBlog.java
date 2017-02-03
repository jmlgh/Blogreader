package com.example.javi.blogreader_javiermartinezlizama;

public class EntradaBlog {
    private String titulo;
    private String autor;
    private String url;

    public EntradaBlog(){}
    public EntradaBlog(String titulo, String autor, String url) {
        this.titulo = titulo;
        this.autor = autor;
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
