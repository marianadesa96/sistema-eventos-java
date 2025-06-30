# Sistema de Cadastro e Notificação de Eventos

Projeto desenvolvido para a disciplina de Programação, com o objetivo de criar um sistema em Java que permita cadastrar, listar e notificar eventos acontecendo na cidade do usuário. O sistema roda em console e utiliza persistência em arquivos.

## ✨ Funcionalidades

- Cadastro de usuários;
- Cadastro de eventos com:
  - Nome
  - Endereço
  - Categoria (ex: show, esporte, festa, etc.)
  - Descrição
  - Data e hora (`LocalDateTime`);
- Participação e cancelamento em eventos;
- Listagem de eventos futuros e eventos passados;
- Notificações de eventos que estão acontecendo no momento;
- Ordenação de eventos por data/hora;
- Salvamento automático em arquivo (`events.data`);
- Leitura automática dos eventos ao iniciar o programa.

## 💻 Tecnologias Utilizadas

- Java
- IDE: Visual Studio Code
- Biblioteca padrão do Java (`java.util`, `java.io`, `java.time`)
- Programação orientada a objetos

## 📁 Estrutura de Arquivos

- `Main.java` → classe principal para iniciar o sistema  
- `Usuario.java` → classe que representa os usuários  
- `Evento.java` → classe que representa os eventos  
- `SistemaEventos.java` → classe com a lógica principal do sistema  
- `events.data` → arquivo onde os eventos são armazenados  

## 🚀 Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/marianadesa96/sistema-eventos-java.git
