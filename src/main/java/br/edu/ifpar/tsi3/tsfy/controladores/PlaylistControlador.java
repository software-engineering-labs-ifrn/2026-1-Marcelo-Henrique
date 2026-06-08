package br.edu.ifpar.tsi3.tsfy.controladores;

import br.edu.ifpar.tsi3.tsfy.dominio.Playlist;
import br.edu.ifpar.tsi3.tsfy.dominio.Usuario;

public class PlaylistControlador {

    private Playlist[] minhasPlaylists = new Playlist[10];
    private int qtdPlaylists = 0;

    public boolean criarPlaylist(Usuario dono, String nome, String descricao) {
        if (qtdPlaylists < 10) {
            Playlist nova = new Playlist(dono, nome, descricao);
            this.minhasPlaylists[qtdPlaylists] = nova;
            this.qtdPlaylists++;
            return true;
        }
        return false;
    }

    public Playlist[] getMinhasPlaylists() {
        return minhasPlaylists;
    }

    public int getQtdPlaylists() {
        return qtdPlaylists;
    }
}