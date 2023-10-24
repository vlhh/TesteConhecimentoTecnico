# Teste Conhecimento Técnico Back-end

Uma agência de banco de sangue forneceu uma lista de candidatos a doadores e precisa de um sistema
que processe esses dados para extrair algumas informações.
Implemente um sistema web que receba um JSON com os dados e mostre os seguintes resultados:

• Quantos candidatos temos nessa lista em cada estado do Brasil? <br />
• IMC médio em cada faixa de idade de dez em dez anos: 0 a 10; 11 a 20; 21 a 30, etc. (IMC = peso /
altura^2) <br />
• Qual o percentual de obesos entre os homens e entre as mulheres? (É obeso quem tem IMC > 30) <br />
• Qual a média de idade para cada tipo sanguíneo? <br />
• A quantidade de possíveis doadores para cada tipo sanguíneo receptor

ATENÇÃO: Somente pessoas com idade de 16 a 69 anos e com peso acima de 50 Kg podem doar sangue

| Tipo sanguíneo  | Pode doar para                   | Pode receber de                 |
| --------------- | -------------------------------- | ------------------------------- | 
| A+              | AB+ e A+                         | A+, A-, O+ e O                  |
| A-              | A+, A-, AB+ e AB-                | A- e O                          |
|B+               |B+ e AB+                          |B+, B-, O+ e O                   |
|B-               |B+, B-, AB+ e AB-                 |B- e O                           |
|AB+              |AB+                               |A+, B+, O+, AB+, A-, B-, O- e AB |
|AB-              |AB+ e AB-                         |A-, B-, O- e AB                  |
|O+               |A+, B+, O+ e AB+                  |O+ e O                           |

## Instalação
Após clonar o projeto usar npm install na pasta raiz

## Requisitos
node: 14.21.3 <br />
npm: 6.14.18
