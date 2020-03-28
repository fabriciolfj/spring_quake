# Apuração de logs

Aplicação tem por objetivo apurar as partidas realizadas no game quake arena.

## Instalação

Sistema e baseado em spring boot, para isso execute o comando abaixo, no diretório raiz da app,  para gerar o arquivo .jar executável.

```
mvn clean install
```

## Funcionamento

Para realizar o upload do arquivo e como resultado a apuração, consuma o endpoint abaixo.

![alt text](https://github.com/fabriciojacob211/quake/blob/master/src/main/resources/docs/exemplo_requisicao.png)

---
## Lombok
Este projeto utiliza o [Lombok](https://projectlombok.org/) em VOs, Entity e objetos que serão serializados, e [MapStruct](https://mapstruct.org/) para conversão entre POJOs.

Instale o plugin do lombok e map-struct no IntelliJ de acordo com a imagem abaixo (após a instalação reinicie a IDE para
 habilitar o plugin): 

[![Configuracao plugin][2]][2]

[![Configuracao plugin][3]][3]

Habilite a opção **annotation processors**
[![Annotation processors][1]][1]

  [1]: https://i.stack.imgur.com/vAHeL.png
  [2]: https://i.stack.imgur.com/kXvVB.png
  [3]: settings/map-struct-idea-plugin.png "Gradle map-struct plugin"
---  

