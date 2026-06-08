/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifpar.tsi3.tsfy.dominio;

/**
 *
 * @author 1071759
 */
public class Musica {
    
    private String titulo;
    private String compositor;
    private String interprete;
    private double duracao;

    public Musica(String titulo, String compositor, String interprete, double duracao) {
        this.titulo = titulo;
        this.compositor = compositor;
        this.interprete = interprete;
        this.duracao = duracao;
    }
    
    public Musica(String titulo, String compositor) {
        this.titulo = titulo;
        this.compositor = compositor;
    }
    
    public Musica(){
        //Contrutor padrão
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCompositor() {
        return compositor;
    }

    public void setCompositor(String compositor) {
        this.compositor = compositor;
    }

    public String getInterprete() {
        return interprete;
    }

    public void setInterprete(String interprete) {
        this.interprete = interprete;
    }

    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(double duracao) {
        this.duracao = duracao;
    }
    
}
