<div align="center">

<!-- HEADER -->
<img src="https://capsule-render.vercel.app/api?type=waving&color=0:0D1117,50:161B22,100:1F6FEB&height=220&section=header&text=🎓%20Nexus%20Academy&fontSize=52&fontColor=FFFFFF&fontAlignY=35&desc=Sistema%20de%20Gestão%20Escolar&descSize=18&descAlignY=55&animation=fadeIn" width="100%"/>

<br/>

[![Java](https://img.shields.io/badge/Java-25-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-22C55E?style=for-the-badge)](LICENSE)

<br/>

<p>
  <strong>Nexus Academy</strong> é um sistema de gestão escolar desenvolvido em <b>Java</b>,
  projetado para simplificar e centralizar o gerenciamento acadêmico — desde o cadastro de alunos e professores
  até o controle de turmas e disciplinas — tudo com integração a banco de dados <b>MySQL</b>.
</p>

<br/>

[Sobre](#-sobre) •
[Funcionalidades](#-funcionalidades) •
[Tecnologias](#-tecnologias) •
[Arquitetura](#-arquitetura) •
[Instalação](#-instalação) •
[Uso](#-uso) •
[Contribuição](#-contribuição)

<br/>

---

</div>

<br/>

## 📋 Sobre

O **Nexus Academy** nasceu como um projeto acadêmico com a proposta de resolver problemas reais de gestão escolar. O sistema oferece uma solução completa para o dia a dia de instituições de ensino, permitindo o gerenciamento de dados de forma organizada, segura e eficiente.

> *"Conectando pessoas ao conhecimento — o futuro da gestão acadêmica começa aqui."*

<br/>

## ✨ Funcionalidades

<div align="center">

| Módulo | Descrição |
|:---:|:---|
| 👨‍🎓 **Alunos** | Cadastro, consulta, edição e remoção de alunos |
| 👨‍🏫 **Professores** | Gerenciamento completo do corpo docente |
| 📚 **Turmas** | Organização e controle de turmas e séries |
| 📖 **Disciplinas** | Registro e vinculação de matérias |
| 🔗 **Matrículas** | Associação de alunos a turmas e disciplinas |
| 🗄️ **Persistência** | Dados armazenados com segurança em banco MySQL |

</div>

<br/>

## 🛠️ Tecnologias

<div align="center">

```
╔══════════════════════════════════════════════════════╗
║                   TECH STACK                         ║
╠══════════════════════════════════════════════════════╣
║                                                      ║
║   ☕  Java 25            Linguagem principal          ║
║   📦  Maven              Gerenciamento de build       ║
║   🐬  MySQL 8.x          Banco de dados relacional    ║
║   🔌  MySQL Connector/J  Driver JDBC 8.3.0            ║
║   💡  IntelliJ IDEA      IDE de desenvolvimento       ║
║                                                      ║
╚══════════════════════════════════════════════════════╝
```

</div>

<br/>

## 🏗️ Arquitetura

O projeto segue uma estrutura Maven padrão com separação clara de responsabilidades:

```
Nexus-Academy/
│
├── 📄 pom.xml                          # Configuração Maven e dependências
│
└── 📂 src/
    └── 📂 main/
        └── 📂 java/
            └── 📂 br/
                └── 📂 escola/          # Pacote principal
                    ├── 📂 model/       # Entidades (Aluno, Professor, Turma...)
                    ├── 📂 dao/         # Acesso a dados (JDBC/MySQL)
                    ├── 📂 service/     # Regras de negócio
                    ├── 📂 util/        # Classes utilitárias
                    └── 📄 Main.java    # Ponto de entrada da aplicação
```

<br/>

## 🚀 Instalação

### Pré-requisitos

Certifique-se de ter instalado em sua máquina:

- **JDK 25** ou superior → [Download](https://jdk.java.net/)
- **Apache Maven 3.9+** → [Download](https://maven.apache.org/download.cgi)
- **MySQL 8.x** → [Download](https://dev.mysql.com/downloads/)

### Passo a passo

**1. Clone o repositório**

```bash
git clone https://github.com/JPaschoalDev/Nexus-Academy.git
cd Nexus-Academy
```

**2. Configure o banco de dados**

Crie o banco de dados no MySQL:

```sql
CREATE DATABASE nexus_academy;
```

> ⚠️ Atualize as credenciais de conexão (host, usuário e senha) no arquivo de configuração do projeto antes de executar.

**3. Compile o projeto**

```bash
mvn clean compile
```

**4. Execute a aplicação**

```bash
mvn exec:java -Dexec.mainClass="br.escola.Main"
```

<br/>

## 💻 Uso

Após iniciar a aplicação, utilize o menu interativo no terminal para navegar entre os módulos disponíveis:

```
╔════════════════════════════════════════╗
║       🎓 NEXUS ACADEMY - MENU         ║
╠════════════════════════════════════════╣
║                                        ║
║   [1]  Gerenciar Alunos                ║
║   [2]  Gerenciar Professores           ║
║   [3]  Gerenciar Turmas                ║
║   [4]  Gerenciar Disciplinas           ║
║   [5]  Matrículas                      ║
║   [0]  Sair                            ║
║                                        ║
╚════════════════════════════════════════╝
```

<br/>

## 🤝 Contribuição

Contribuições são muito bem-vindas! Se quiser colaborar com o projeto:

1. Faça um **fork** do repositório
2. Crie uma **branch** para sua feature (`git checkout -b feature/minha-feature`)
3. Faça o **commit** das alterações (`git commit -m 'feat: minha nova feature'`)
4. Envie para a branch (`git push origin feature/minha-feature`)
5. Abra um **Pull Request**

<br/>

## 📝 Licença

Distribuído sob a licença MIT. Veja `LICENSE` para mais informações.

<br/>

</div>

<br/>

<div align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:0D1117,50:161B22,100:1F6FEB&height=120&section=footer" width="100%"/>

</div>
