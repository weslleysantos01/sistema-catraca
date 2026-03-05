# 🚧 Sistema Catraca com QR Code

Sistema completo de controle de acesso via QR Code desenvolvido em Java com Spring Boot.

## 🚀 Tecnologias

- Java 17
- Spring Boot 4.0
- Spring Security
- Spring Data JPA
- MySQL
- ZXing (geração de QR Code)
- HTML + CSS + JavaScript (frontend)

## ✨ Funcionalidades

- ✅ Cadastro de usuários
- ✅ Geração de QR Code único por usuário
- ✅ Validação de acesso via QR Code
- ✅ Histórico completo de acessos
- ✅ Painel web de controle
- ✅ Login com autenticação segura
- ✅ Banco de dados MySQL

## 🏗️ Arquitetura
```
Frontend (HTML/JS)
       ↓
API REST (Spring Boot)
       ↓
Banco de Dados (MySQL)
```

## ⚙️ Como rodar localmente

**Pré-requisitos:**
- Java 17+
- MySQL
- Maven

**1. Clone o repositório:**
```bash
git clone https://github.com/weslleysantos01/sistema-catraca.git
```

**2. Configure o banco de dados:**
```sql
CREATE DATABASE catraca;
```

**3. Configure as variáveis de ambiente:**
```
DB_USER=root
DB_PASS=sua_senha
ADMIN_USER=admin
ADMIN_PASS=sua_senha
```

**4. Rode o projeto:**
```bash
mvn spring-boot:run
```

**5. Acesse no navegador:**
```
http://localhost:8080/login.html
```

## 📱 Como usar

1. Faça login com suas credenciais
2. Cadastre um usuário
3. O QR Code é gerado automaticamente
4. Use a opção **Validar Acesso** para simular a leitura na catraca
5. Acompanhe o histórico de acessos em tempo real

## 🔌 Integração com Hardware

O sistema foi projetado para integração com Arduino + Relê para controle de catraca física:
```
Leitor QR USB → API Spring Boot → Arduino → Relê → Catraca
```

## 👨‍💻 Autor

Weslley Santos — projeto desenvolvido do zero como aprendizado de Java e Spring Boot.