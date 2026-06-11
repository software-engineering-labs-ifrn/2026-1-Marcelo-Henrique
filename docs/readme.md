# TSFY

Uma aplicação interativa em modo texto (CLI) inspirada no Spotify, desenvolvida como projeto prático para a disciplina de Engenharia de Software. O sistema simula o gerenciamento de perfis de usuários, catálogos de músicas e organização de playlists personalizadas.

---

## Contexto e Problema
Plataformas de streaming modernas lidam com fluxos massivos de dados, autenticação de perfis e interações complexas entre usuários e bibliotecas de mídia. O TSfy foi concebido para modelar o gerenciamento e a organização dessas operações básicas de backend em um ambiente acadêmico controlado.

O software oferece uma solução estruturada para o cadastro e a autenticação de ouvintes, além de fornecer ferramentas centralizadas para gerenciar o ciclo de vida das músicas e a roteirização de playlists.

## Requisitos Gerais
O sistema foi projetado para executar as seguintes operações em alto nível:
**Gerenciamento de Usuários:** Cadastro de novos perfis com verificação básica de credenciais e sistema de autenticação de sessão.
**Gerenciamento de Músicas (CRUD):** Registro de faixas informando título, compositor, intérprete e duração, além de permitir listagem completa, busca direta por id, edição de metadados e remoção do catálogo.
**Organização de Playlists:** Usuários autenticados podem criar e personalizar até 10 playlists independentes vinculadas ao perfil, contendo nome e descrição própria.

## Papéis dos Usuários
**Usuário Anônimo (Visitante):** Pode navegar pelo menu principal do sistema, cadastrar uma nova conta, realizar login e visualizar/buscar as músicas cadastradas no acervo global.
**Usuário Autenticado (Ouvinte):** Possui todos os acessos do visitante, adicionando o privilégio exclusivo de criar e gerenciar playlists personalizadas dentro do ecossistema.

## Stack Tecnológica
A arquitetura do software foi estruturada de forma modular, utilizando as seguintes tecnologias:
**Linguagem Principal:** Java 17
**Gerenciador de Dependências e Build:** Maven
**Interface do Usuário:** CLI (Interface de Linha de Comando usando 'java.util.Scanner')
**Padrão de Projeto Utilizado:** Padrão Fachada para unificar o acesso aos controladores de domínio

---

## Modelagem do Sistema

### Diagrama de Casos de Uso

![Diagrama de Casos de Uso](./docs/modelagem/casos_de_uso.png)

### Diagrama de Classes

![Diagrama de Classes](./docs/modelagem/diagrama_classes.png)

---

## Equipe e Papéis no Desenvolvimento
**Professor Daniel Aguiar da Silva Oliveira Carvalho** - Desenvolvimento Geral do Código
**Marcelo Henrique** - Ajustes de Código

---