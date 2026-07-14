# Padrões de Projeto (GoF) Aplicados

Este documento descreve os 4 padrões de projeto Gang of Four (GoF) implementados para refatorar e escalar a arquitetura do sistema.

---

## 1. Facade
>**Fornecer uma interface unificada para um conjunto de interfaces em um subsistema**

### O Problema
O código atual já possui fachada. porém sem o uso de uma fachada, a classe de apresentação (`TsfyUI`) precisaria instanciar e gerenciar diretamente cada um dos controladores do sistema (`MusicaControlador`, `PlaylistControlador` e `UsuarioControlador`). Isso faria a UI conhecer detalhes complexos da lógica de negócio, gerando alto acoplamento.

#### Código Violado (Como seria sem a Fachada)
```java
public class TsfyUI {
    private MusicaControlador controladorDeMusica = new MusicaControlador();
    private PlaylistControlador controladorDePlaylist = new PlaylistControlador();
    private UsuarioControlador controladorDeUsuario = new UsuarioControlador();

    private void criarMusica() {
        controladorDeMusica.registrarMusica(titulo, compositor, interprete, duracao);
    }
}
```

### A Solução
O padrão Facade ja está implementado na classe `FachadaFrontend`. Ela centraliza todas as requisições da interface gráfica, funcionando como uma porta de entrada única que repassa os comandos aos controladores corretos. A UI conversa apenas com a Fachada.

#### Código Corrigido (Atual)
```java
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
}
```

## 2. Command
>**Encapsular uma solicitação como um objeto, permitindo parametrizar clientes com filas, comandos e operações reversíveis.**

### O Problema
O método `rodar()` da `TsfyUI` controla o menu principal através de um bloco `switch (op)` extenso. Se o sistema crescer para 20 opções, a classe ficará gigantesca. Toda nova funcionalidade exige alteração direta no `switch`.

#### Código Violado
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

### A Solução
O padrão Command transforma cada ação do menu em um objeto independente que implementa a interface `ComandoUI`. O `switch` é substituído por um `Map<Integer, ComandoUI>`, permitindo registrar novos comandos dinamicamente.

#### Código Corrigido usando command
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
        comandos.put(3, new ComandoCriarMusica(fachada));
    }

    public void rodar() {
        ComandoUI comando = comandos.get(op);
        if (comando != null) comando.executar();
    }
}
```

---

## 3. Singleton
>**Garantir que uma classe tenha apenas uma instância e fornecer um ponto de acesso global a ela.**

### O Problema
Os dados do sistema (como a lista `todasAsMusicas` no `MusicaControlador`) ficam armazenados na memória. Como a classe `TsfyUI` possui o atributo `private FachadaFrontend fachada = new FachadaFrontend();`, se o sistema criar outra tela ou instanciar a fachada novamente, novas listas vazias serão criadas, causando perda de todos os dados salvos.

#### Código Violado
```java
public class TsfyUI {
    private FachadaFrontend fachada = new FachadaFrontend(); 
}
```

### A Solução
Aplicou-se o Singleton na `FachadaFrontend`. Escondemos o construtor tornando-o `private` e criamos o método estático `getInstance()`. Isso garante que sempre exista apenas **uma** instância da Fachada e dos controladores associados a ela rodando na memória.

#### Código Corrigido usandio Singleton
```java
public class FachadaFrontend {
    private static FachadaFrontend instancia;
    private MusicaControlador controladorDeMusica;

    private FachadaFrontend() {
        this.controladorDeMusica = new MusicaControlador();
    }

    public static FachadaFrontend getInstance() {
        if (instancia == null) {
            instancia = new FachadaFrontend();
        }
        return instancia;
    }
}

public class TsfyUI {
    private FachadaFrontend fachada = FachadaFrontend.getInstance();
}
```

---

## 4. Builder
>**Separar a construção de um objeto complexo de sua representação, permitindo a criação passo a passo.**

### O Problema
O método `registrarMusica` no `MusicaControlador` constrói a entidade `Musica` utilizando um construtor com múltiplos parâmetros (`String, String, String, Double`). Essa estrutura facilita erros humanos de inversão por exemplo passar o intérprete no lugar do compositor sem que o compilador acuse erro.

#### Código Violado
```java
public class MusicaControlador {
    public boolean registrarMusica(String titulo, String compositor, String interprete, Double duracao) {
        Musica novaMusica = new Musica(titulo, compositor, interprete, duracao);
        this.todasAsMusicas.add(novaMusica);
        return true;
    }
}
```

### A Solução
Implementação da classe `MusicaBuilder`. A construção do objeto `Musica` passa a ser semântica e feita passo a passo, tornando o código à prova de inversão de argumentos e muito mais legível.

#### Código Corrigido
```java
public class MusicaBuilder {
    private String titulo;
    private String compositor;
    private String interprete;
    private double duracao;

    public MusicaBuilder comTitulo(String titulo) { this.titulo = titulo; return this; }
    public MusicaBuilder comCompositor(String compositor) { this.compositor = compositor; return this; }
    public MusicaBuilder comInterprete(String interprete) { this.interprete = interprete; return this; }
    public MusicaBuilder comDuracao(double duracao) { this.duracao = duracao; return this; }

    public Musica build() {
        return new Musica(titulo, compositor, interprete, duracao);
    }
}

public class MusicaControlador {
    public boolean registrarMusica(String titulo, String compositor, String interprete, Double duracao) {
        Musica novaMusica = new MusicaBuilder()
                                .comTitulo(titulo)
                                .comCompositor(compositor)
                                .comInterprete(interprete)
                                .comDuracao(duracao)
                                .build();
                                
        this.todasAsMusicas.add(novaMusica);
        return true;
    }
}
```
