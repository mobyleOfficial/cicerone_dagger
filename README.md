# Cicerone com Dagger 2
Esse repositório apresenta uma implementação de uma Bottom Navigation Bar, 
utilizando a lib Cicerone para navegação, e Dagger2 para injeção de dependências.

## Bibliotecas
- [Cicerone](https://github.com/terrakok/Cicerone)
- [Dagger2](https://github.com/google/dagger)

## Como funciona?
![alt text](https://i.imgur.com/ZM0SIYJ.png)

Como pode ser visto na imagem acima, a ideia é utilizar dois contêineres, um para a Activity (que contém a Bottom Navigation Bar), e outro para o Fragment responsável por administrar os fluxos das abas. Sempre que há uma mudança de aba, a visibilidade dos Fragments das abas é trocada. Cada uma das abas possui a própria instância do Cicerone (criadas com o Dagger2)

## Referências
[Artigo base](https://medium.com/@yurimachioni/creating-an-instagram-like-flow-using-cicerone-and-dagger2-bottomnavigation-with-fragments-777771ff4401)

## Autor
Bruno Abe

## License
[MIT](https://choosealicense.com/licenses/mit/)
