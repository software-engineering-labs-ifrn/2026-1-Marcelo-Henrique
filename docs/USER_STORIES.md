# Histórias de Usuário - TSFY

Este documento mostra o que o sistema deve fazer com base no que o usuário precisa no dia a dia.

---

### US01 - Criar Conta de Usuário
**Como** Usuário Visitante  
**Eu quero** cadastrar uma conta com meu nome, CPF e uma senha  
**Para que eu possa** ter um perfil no sistema e acessar as opções restritas  

**Critérios de Aceitação:**
**Dado que** o visitante escolhe a opção "1 - Criar Usuario" no menu  
**Quando** ele digita o seu CPF, seu nome e uma senha válidos  
**Então** o sistema salva o usuário e mostra uma mensagem de sucesso.

---

### US02 - Fazer Login
**Como** Usuário Visitante  
**Eu quero** digitar o meu CPF e a minha senha  
**Para que eu possa** entrar no meu perfil e mexer nas minhas playlists  

**Critérios de Aceitação:**
**Dado que** o visitante escolhe a opção "2 - Login"  
**Quando** ele digita o CPF e a senha corretos de uma conta cadastrada  
**Então** o sistema valida o acesso, faz o login e mostra o nome dele no menu principal.

---

### US03 - Cadastrar Música
**Como** Usuário do Sistema  
**Eu quero** cadastrar uma música com título, compositor, intérprete e duração  
**Para que eu possa** adicionar novas músicas na lista global do sistema  

**Critérios de Aceitação:**
**Dado que** o usuário escolhe a opção "3 - Criar Musica"  
**Quando** ele digita as informações da música corretamente  
**Então** o sistema adiciona a música na lista geral e avisa que deu certo.

---

### US04 - Editar Música
**Como** Usuário do Sistema  
**Eu quero** mudar o tempo de duração de uma música usando o número do ID dela  
**Para que eu possa** corrigir o tempo caso ele tenha sido cadastrado errado  

**Critérios de Aceitação:**
**Dado que** o usuário escolhe a opção "4 - Editar Musica"  
**Quando** ele digita o ID de uma música que existe e coloca o tempo novo  
**Então** o sistema altera a duração da música e confirma a mudança.

---

### US05 - Listar Músicas
**Como** Usuário do Sistema  
**Eu quero** ver a lista de todas as músicas cadastradas  
**Para que eu possa** saber quais músicas estão disponíveis no aplicativo  

**Critérios de Aceitação:**
**Dado que** o usuário escolhe a opção "5 - Listar Musicas do Sistema"  
**Quando** existem músicas cadastradas  
**Então** o sistema mostra na tela o ID, o nome, o cantor e o tempo de cada uma delas.

---

### US06 - Buscar Música por ID
**Como** Usuário do Sistema  
**Eu quero** pesquisar uma música direto pelo número do ID dela  
**Para que eu possa** achar a música que eu quero rápido, sem precisar ler a lista inteira  

**Critérios de Aceitação:**
**Dado que** o usuário escolhe a opção "6 - Buscar Musica por ID"  
**Quando** ele digita o número do ID de uma música cadastrada  
**Então** o sistema encontra a música e mostra todos os detalhes dela na tela.

---

### US07 - Remover Música
**Como** Usuário do Sistema  
**Eu quero** apagar uma música do sistema pelo ID dela  
**Para que eu possa** tirar do aplicativo as músicas que não devem mais ficar lá  

**Critérios de Aceitação:**
**Dado que** o usuário escolhe a opção "7 - Remover Musica"  
**Quando** ele digita o ID da música que deseja apagar  
**Então** o sistema deleta a música da lista geral e avisa que ela foi removida.

---

### US08 - Criar Playlist
**Como** Usuário Autenticado (Logado)  
**Eu quero** criar uma playlist com um nome e uma descrição  
**Para que eu possa** organizar as minhas músicas favoritas em pastas  

**Critérios de Aceitação:**
**Dado que** o usuário está logado na sua conta e escolhe a opção "8 - Criar Playlist"  
**Quando** ele digita o nome e a descrição da playlist, e ainda não tem 10 playlists criadas  
**Então** o sistema cria a playlist e salva ela dentro do perfil dele.

---

### US09 - Ver Minhas Playlists
**Como** Usuário Autenticado (Logado)  
**Eu quero** ver a lista das playlists que eu criei  
**Para que eu possa** escolher qual playlist eu quero visualizar  

**Critérios de Aceitação:**
**Dado que** o usuário está logado na sua conta e escolhe a opção "9 - Listar Minhas Playlists"  
**Quando** ele acessa essa opção  
**Então** o sistema mostra na tela os nomes e as descrições de todas as playlists criadas por ele.