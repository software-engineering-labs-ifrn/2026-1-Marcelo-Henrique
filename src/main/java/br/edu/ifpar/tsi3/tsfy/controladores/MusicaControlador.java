package br.edu.ifpar.tsi3.tsfy.controladores;

import br.edu.ifpar.tsi3.tsfy.dominio.Musica;
import java.util.ArrayList;

public class MusicaControlador {

    private ArrayList<Musica> todasAsMusicas = new ArrayList<>();

    public boolean registrarMusica(String titulo, String compositor, String interprete, Double duracao) {
        Musica novaMusica = new Musica(titulo, compositor, interprete, duracao);
        this.todasAsMusicas.add(novaMusica);
        return true;
    }

    public ArrayList<Musica> listarMusicas() {
        return this.todasAsMusicas;
    }

    public boolean editarMusica(int id, double novaDuracao) {
        if (id >= 0 && id < todasAsMusicas.size()) {
            todasAsMusicas.get(id).setDuracao(novaDuracao);
            return true;
        }
        return false;
    }

    public Musica buscarMusicaPorID(int id) {
        if (id >= 0 && id < todasAsMusicas.size()) {
            return todasAsMusicas.get(id);
        }
        return null;
    }

    public boolean removerMusica(int id) {
        if (id >= 0 && id < todasAsMusicas.size()) {
            todasAsMusicas.remove(id);
            return true;
        }
        return false;
    }
}