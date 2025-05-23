# 🧙‍♂️ Trabalho CRUD RPG

## 🔗 Rotas Principais

### Swagger (Documentação da API)
[http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

### H2 Console (Banco de Dados em Memória)
- **Username:** sa  
- **Password:** *(em branco)*  
[http://localhost:8080/h2-console/login.do](http://localhost:8080/h2-console/login.do)

### Backend
[http://localhost:8080/](http://localhost:8080/)

---

## 📦 Exemplo de Requisição POST - Item Mágico

```json
{
    "nome": "Espada Mágica",
    "forca": 5,
    "defesa": 3,
    "tipo": "ARMA",
    "personagem_id": 1
}
```

## 🧝 Exemplo de Requisição POST - Personagem

```json
{
    "nome": "Aragorn",
    "nomeAventureiro": "Passolargo",
    "classe": "GUERREIRO",
    "level": 1,
    "forca": 3,
    "defesa": 5,
    "itensMagicos": [],
    "forcaTotal": 0,
    "defesaTotal": 0,
    "amuleto": null
}
```

---

## ✏️ Requisições PUT - Atualização de Personagem ou Item Mágico

Para atualizar um personagem ou item mágico, informe o `{id}` na URL e envie apenas os campos desejados com os novos valores.

**Exemplo:**

Alterar o nome do personagem:
- De: `Aragorn` (OBS: Não precisa ser entre aspas na atualização - PUT).
- Para: `Frajola` (OBS: Não precisa ser entre aspas na atualização - PUT).

Basta enviar a requisição com o novo nome, e o dado será atualizado.

---

> ✅ **Observação:** Siga o modelo do corpo da requisição. Os dados podem ser alterados livremente conforme necessário para seus testes.