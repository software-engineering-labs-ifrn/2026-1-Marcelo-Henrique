# Relatório de Correções de Design - Princípios SOLID

Este documento detalha as refatorações arquiteturais realizadas no backend do projeto. Para cada princípio SOLID violado na arquitetura inicial, são apresentados o problema, o código e diagrama violados, bem como a solução aplicada com o novo código e diagrama.

---

## 1. Violação do SRP (Single Responsibility Principle)
> *"Uma classe deve ter um, e apenas um, motivo para mudar."*

A classe MusicaControlador possui duas responsabilidades que são orquestrar as regras de negócio da música e atuar como um banco de dados em memória para armazenar os dados no ArrayList<Musica>. Qualquer mudança na forma de armazenamento, como por exemplo mudar para um banco de dados SQL, forçaria uma modificação na classe de controle.

### Código Violado
```java
public class MusicaControlador {
    private ArrayList<Musica> todasAsMusicas = new ArrayList<>(); //aqui

    public boolean registrarMusica(String titulo, String compositor, String interprete, Double duracao) {
        Musica novaMusica = new Musica(titulo, compositor, interprete, duracao);
        this.todasAsMusicas.add(novaMusica); 
        return true;
    }
}
```
#### Diagrama de classes violado
```mermaid
classDiagram
    class MusicaControlador {
        -ArrayList~Musica~ todasAsMusicas
        +registrarMusica(String, String, String, Double) boolean
    }
```

### Código Corrigido
A responsabilidade de manipulação e armazenamento dos dados foi isolada em uma nova classe chamada `MusicaRepository`.
```java
// Persistência
public class MusicaRepository {
    private ArrayList<Musica> todasAsMusicas = new ArrayList<>();

    public void salvar(Musica musica) {
        this.todasAsMusicas.add(musica);
    }
}

// Controlador
public class MusicaControlador {
    private MusicaRepository repositorio;

    public MusicaControlador(MusicaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public boolean registrarMusica(String titulo, String compositor, String interprete, Double duracao) {
        Musica nova = new Musica(titulo, compositor, interprete, duracao);
        this.repositorio.salvar(nova); 
        return true;
    }
}
```

#### Diagrama de Classes Corrigido
```mermaid
classDiagram
    class MusicaRepository {
        -ArrayList~Musica~ todasAsMusicas
        +salvar(Musica) void
    }
    class MusicaControlador {
        -MusicaRepository repositorio
        +registrarMusica(String, String, String, Double) boolean
    }
    MusicaControlador --> MusicaRepository
```
---

## 2. Violação do OCP (Open/Closed Principle)
>*"Entidades de software devem estar abertas para extensão, mas fechadas para modificação."*

O método `rodar()` da classe `TsfyUI` utilizava uma estrutura condicional switch/case para gerenciar as opções do menu do sistema. forçando toda nova modificação exige modificar esse arquivo

### Código Violado
```java
public class TsfyUI {
    public void rodar() {
        switch (op) {
            case 1: criarNovoUsuario(); break;
            case 2: fazerLogin(); break;
            case 3: criarMusica(); break;
        }
    }
}
```

#### Diagrama de classes violado
```mermaid
classDiagram
    class TsfyUI {
        -Scanner sc
        -FachadaFrontend fachada
        +rodar() void
        -criarMusica() void
        -fazerLogin() void
        -criarNovoUsuario() void
    }
```

### Código Corrigido
Utilização padrão de projeto **Command**. Cada opção do menu torna uma classe isolada que estende uma interface comum.
```java
public interface ComandoUI {
    void executar();
}

public class ComandoCriarMusica implements ComandoUI {
    private FachadaFrontend fachada;
    public ComandoCriarMusica(FachadaFrontend fachada) { this.fachada = fachada; }
    
    @Override
    public void executar() {
    }
}

public class TsfyUI {
    private Map<Integer, ComandoUI> comandos = new HashMap<>();

    public TsfyUI() {
        comandos.put(3, new ComandoCriarMusica(new FachadaFrontend()));
    }

    public void rodar() {
        ComandoUI comando = comandos.get(op);
        if (comando != null) {
            comando.executar();
        }
    }
}
```
#### Diagrama de Classes Corrigido
```mermaid
classDiagram
    class ComandoUI {
        +executar() void
    }
    class TsfyUI {
        -Map~Integer, ComandoUI~ comandos
        +rodar() void
    }
    class ComandoCriarMusica {
        -FachadaFrontend fachada
        +executar() void
    }
    TsfyUI --> ComandoUI
    ComandoUI <|.. ComandoCriarMusica
```
