# Projeto Contas a Pagar

## Orientações Gerais

- Este projeto faz autenticação e autorização via Keyclok;
- Este projeto usa banco de dados PostgresSQL;
- Após clonar o projeto, entre no diretório criado e rode o comando
<strong>
docker compose up -d --build
</strong> e aguarde aparecer:

 ✔ Container postgres              Started 
 
 ✔ Container keycloak              Started
 
 ✔ Container contas-app            Started 
 
 - Acesse http://localhost:8080/swagger-ui/index.html para ver a documentação da API;
 - Também é possível importar a collection <strong>CONTAS A PAGAR.postman_collection.json</strong> localizada na raiz do projeto, no Postman;
 - o CURL para fazer login e obter o token que permite realizar as operações na API é:
```
curl --location 'http://localhost:8080/auth/token' \
--header 'Content-Type: application/json' \
--data '{
  "password": "123456",
  "clientId": "contas-a-pagar",
  "grantType": "password",
  "username": "contas"
}'
```
a resposta deve ser algo parecido com :
```
{
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJESXZZQV9HLXBsMUF2NjRodFJKOVdneW55MTRQY0VpTnNtMG9OZFF4NGxFIn0.eyJleHAiOjE3MjY4NDU4NDMsImlhdCI6MTcyNjg0NTU0MywianRpIjoiNjgyM2Q3NWQtZmI2ZC00MjdkLTlhMjItNzQ4YjQ4YWZjYWE0IiwiaXNzIjoiaHR0cDovL2tleWNsb2FrOjcwODAvcmVhbG1zL2NvbnRhcy1hLXBhZ2FyLW1zIiwic3ViIjoiMjVmYWViNGUtZDE1OC00MDBlLWI1MjEtMzYxN2I2ZmRjMjg5IiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiY29udGFzLWEtcGFnYXIiLCJzZXNzaW9uX3N0YXRlIjoiZmJiMDgxM2YtMTYxMi00ZDAwLWJkOWYtODU0NDM4MWE0MGE1IiwiYWNyIjoiMSIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJST0xFX0FETUlOIl19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJzaWQiOiJmYmIwODEzZi0xNjEyLTRkMDAtYmQ5Zi04NTQ0MzgxYTQwYTUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsInByZWZlcnJlZF91c2VybmFtZSI6ImNvbnRhcyIsImdpdmVuX25hbWUiOiIiLCJmYW1pbHlfbmFtZSI6IiJ9.hyHHNAI6mYwxTNKjJeiLDrxZxSUPVPXB0NEKVuwLlSdAhJST4SEJIsyKLNn6XZDSd2uEhOWWcYBxjWrWuAdiZJaebojT-1qpLGRc12gIfFcoSn0GQ3t3Pqzdfw9Sylyr2xoEDItwoeAUQ7IUGTUrla1Ktw4Y5hjh1viTOexibetqAKBH32DS0UDPURV7-NcVfIgMbtXx6M8-DIBFZkfap_ny7GRk9B7t36mmdtSDAKWNvkxDPCo5xkOEuz7KWtekYErnnpLH8qGlTVdHUgYTWlbMvPH2NrQxixsqceM1juHKZks1wUsmwPKQajk4r63AmgcO5jdLet5nir6a2iwhIw",
    "expires_in": 300,
    "refresh_expires_in": 1800,
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJlMDQyNzBhZC01MzNiLTQyOTAtOWEzMy1lYWYwZGFkZTIzNDAifQ.eyJleHAiOjE3MjY4NDczNDMsImlhdCI6MTcyNjg0NTU0MywianRpIjoiNmQ5YmJjMTgtMmZmMC00ZmRmLThjYmItYjA3N2I1ZTVmODVlIiwiaXNzIjoiaHR0cDovL2tleWNsb2FrOjcwODAvcmVhbG1zL2NvbnRhcy1hLXBhZ2FyLW1zIiwiYXVkIjoiaHR0cDovL2tleWNsb2FrOjcwODAvcmVhbG1zL2NvbnRhcy1hLXBhZ2FyLW1zIiwic3ViIjoiMjVmYWViNGUtZDE1OC00MDBlLWI1MjEtMzYxN2I2ZmRjMjg5IiwidHlwIjoiUmVmcmVzaCIsImF6cCI6ImNvbnRhcy1hLXBhZ2FyIiwic2Vzc2lvbl9zdGF0ZSI6ImZiYjA4MTNmLTE2MTItNGQwMC1iZDlmLTg1NDQzODFhNDBhNSIsInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsInNpZCI6ImZiYjA4MTNmLTE2MTItNGQwMC1iZDlmLTg1NDQzODFhNDBhNSJ9.nU6EIzrDPz39G3tJLbT8vSx7MetFJB74ReRNCkyKc_g",
    "token_type": "Bearer",
    "not-before-policy": 0,
    "session_state": "fbb0813f-1612-4d00-bd9f-8544381a40a5",
    "scope": "profile email"
}
```

copie o token contido em "access_token" para fazer requisições para a API;

#### Importação via CSV

- O arquivo CSV deve seguir esse formato:
```
dataVencimento,dataPagamento,valor,descricao,situacao
2024-09-14,2024-09-15,523.67,Conta de Água,PAGO
2024-09-13,,812.45,Aluguel,PENDENTE
2024-09-12,,345.12,Internet,CANCELADO
2024-09-11,,100.78,Conta de Luz,PENDENTE
2024-09-10,2024-09-11,945.30,Telefone,PAGO
2024-09-09,,156.90,Conta de Água,PENDENTE
2024-09-08,2024-09-09,620.45,Seguro,PAGO
2024-09-07,,440.78,Conta de Luz,CANCELADO
2024-09-06,,200.89,Internet,PENDENTE
2024-09-05,2024-09-06,730.12,Telefone,PAGO

```

As contas pode ter apenas um de 3 status : PAGO, PENDENTE, CANCELADO.
o campo dataPagamento apenas deve ser preenchido para status PAGO
