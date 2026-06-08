package br.edu.ifpar.tsi3.tsfy.UI.fachada;

import br.edu.ifpar.tsi3.tsfy.controladores.MusicaControlador;
import br.edu.ifpar.tsi3.tsfy.controladores.PlaylistControlador;
import br.edu.ifpar.tsi3.tsfy.controladores.UsuarioControlador;
import br.edu.ifpar.tsi3.tsfy.dominio.Musica;
import br.edu.ifpar.tsi3.tsfy.dominio.Playlist;
import br.edu.ifpar.tsi3.tsfy.dominio.Usuario;
import java.util.ArrayList;

public class FachadaFrontend {

    private MusicaControlador controladorDeMusica;
    private PlaylistControlador controladorDePlaylist;
    private UsuarioControlador controladorDeUsuario;

    public FachadaFrontend(){
        this.controladorDeMusica = new MusicaControlador();
        this.controladorDePlaylist = new PlaylistControlador();
        this.controladorDeUsuario = new UsuarioControlador();
    }

    public boolean registrarMusica(String titulo, String compositor, String interprete, Double duracao) {
        return this.controladorDeMusica.registrarMusica(titulo, compositor, interprete, duracao);
    }

    public ArrayList<Musica> listarMusicas() {
        return this.controladorDeMusica.listarMusicas();
    }

    public boolean editarMusica(int id, double novaDuracao) {
        return this.controladorDeMusica.editarMusica(id, novaDuracao);
    }

    public Musica buscarMusicaPorID(int id) {
        return this.controladorDeMusica.buscarMusicaPorID(id);
    }

    public boolean removerMusica(int id) {
        return this.controladorDeMusica.removerMusica(id);
    }

    public boolean registrarUsuario(String cpf, String nome, String senha) {
        return this.controladorDeUsuario.registrarUsuario(cpf, nome, senha);
    }

    public Usuario autenticar(String cpf, String senha) {
        return this.controladorDeUsuario.autenticar(cpf, senha);
    }

    public boolean criarPlaylist(Usuario dono, String nome, String descricao) {
        return this.controladorDePlaylist.criarPlaylist(dono, nome, descricao);
    }

    public Playlist[] getMinhasPlaylists() {
        return this.controladorDePlaylist.getMinhasPlaylists();
    }

    public int getQtdPlaylists() {
        return this.controladorDePlaylist.getQtdPlaylists();
    }
}