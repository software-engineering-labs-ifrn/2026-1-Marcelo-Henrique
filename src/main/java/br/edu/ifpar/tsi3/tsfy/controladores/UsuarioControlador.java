package br.edu.ifpar.tsi3.tsfy.controladores;

import br.edu.ifpar.tsi3.tsfy.dominio.Usuario;
import java.util.ArrayList;

public class UsuarioControlador {

    private ArrayList<Usuario> todosOsPerfis = new ArrayList<>();

    public boolean registrarUsuario(String cpf, String nome, String senha) {
        Usuario novo = new Usuario(cpf, nome, senha);
        this.todosOsPerfis.add(novo);
        return true;
    }

    public Usuario autenticar(String cpf, String senha) {
        for (int i = 0; i < this.todosOsPerfis.size(); i++) {
            Usuario u = this.todosOsPerfis.get(i);
            if (u.getCpf().equals(cpf) && u.getSenha().equals(senha)) {
                return u;
            }
        }
        return null;
    }
}