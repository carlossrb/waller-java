<p align="center">
  <img src="logo.svg" />
</p>

# Waller-Java

A API Waller fornece alguns endpoints para realizar transações financeiras em uma aplicação cliente web. A mesma foi construída usando Java, com o framework Spring (boot, web, jpa, etc.). O nome da mesma foi obtido pelo site [namelix](http://namelix.com "namelix").  Essa api construída em java, no entanto, utiliza de um banco volátil, apenas para demonstração. Isto é, esse banco de dados relacional não mantém os dados armazenados após reiniciar o projeto em execução. Para realizar a relação entre classes e um modelo relacional de banco, utilizou-se o Spring JPA, que por sua vez cria as entidades (tabelas) por meio de classes e faz todo o processo de inserção/criação no banco automático, assim que o projeto *starta* pela primeira vez. 

**O intuito maior deste projeto foi testar conceitos de orientação a objetos com a linguagem java.**

## Estrutura

O projeto obedece, em partes, um padrão MVC de arquitetura. Isto é: model, view, controller. Temos o controller onde se realiza as requisições. Nesse controller se chama os services ou o model diretamente. Evita-se nessa camada qualquer cálculo demasiado. A função dessa classe é principalmente como um ponto de entrada das requisições http. Temos dois controllers neste projeto, AccountController e TransactionsController.

Os services, por sua vez, é onde se realizam todos os cálculos referentes às regras de negócio. Isto é, todo o coração do projeto se encontra nele. Neste projeto temos dois services. Um responsável por cadastro de novos usuários e outro pelas transações referentes a estes usuários.

Os models é onde ficam armazenadas as entidades. As entidades são classes que determinam a forma que as tabelas do banco irão ser. O Spring JPA utiliza dessas entidades de classe, extendendo-as para criar os repositórios. Estes repositórios são interfaces que são utilizadas para facilitar as queries (insert, select, update...) no banco de dados.

O sistema conta com *decorators*, onde alguns são *açucares sintáticos* para facilitar a leitura e interpretação do código, todavia, outros atribuem funcionalidades aos métodos e classes e delegam funções essenciais na hora da compilação do projeto, como exemplo do `@Autowired` e `@GetMapping()` usados nos controllers para injeção de atributos não nulos na classe e para roteamento http, respectivamente.

Foi trabalhado ainda para que o sistema tivesse suas responsabilidades separadas. Isso satisfaz os princípios da orientação a objetos, torna a arquitetura mais bem trabalhada e também o código fácil de se fazer uma manutenção posterior.

## Orientação a objetos

Como principal motivo para criação dessa API está a prática em um caso real dos principais conceitos da orientação a objetos. 

No projeto em questão é possível verificar os quatro principais fundamentos norteadores desse paradigma, que são:
 - **Abstração**: Os nossos objetos e classes representam e definem as partes do negocio como o todo, que desacopladas cada qual com sua responsabilidade;
 - **Encapsulamento**: Tem-se também atributos e métodos privados em cada classe que garantem a segurança e integridade do projeto;
 - **Herança**: Indiretamente, essa propriedade é usada várias vezes herdando métodos dos principais frameworks utilizados para as classes implementadas. Essa característica otimiza a produção da aplicação em tempo e linhas de código;
 - **Polimorfismo**: Este conceito é intimamente ligado ao anterior. Pode-se observar o mesmo sendo utilizado nas modificações de métodos herdados em classes filhas, por exemplo.
 
 Alem desses princípios, lançou-se mão de muitas outras técnicas da linguagem, podendo citar alguns como:
 
- métodos estáticos;
- enums;
- interfaces;
- modularização (packages);
- criação de instâncias;
-  tratamento de exceções;

## Tecnologias e libs

Para o desenvolvimento do mesmo, fez-se necessário a utilização de algumas ferramentas de desenvolvimento como o `vscode`, assim como frameworks e bibliotecas para que o mesmo pudesse ser executado. Dentre as principais, pode-se destacar:

- Spring boot para a configuração inicial do projeto;
- Lombock para gerar os getters e setters automaticamente após criar os atributos;
- H2 database para um banco de dados rápido de configurar para os testes;
- Spring DevTools para facilitar no processo de debug da aplicação;
- Spring data JPA como ORM e para fazer a persistência no banco de dados sql;
- Spring web para configurações REST da api.