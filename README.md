# Chuck Phrases
Chuck Norris App é uma aplicação que consume uma API do [ChuckNorris.io](https://api.chucknorris.io)

### Tecnologias
 Uma aplicação Android Nativa em Kotlin, utilizando:
 | Nome | Função | Link |
| ------ | ------ |  ------ |
| **Dagger2**  | para a injeção de dependência entre os elementos | [Link](https://github.com/google/dagger) |
| **RXJava:** | para utilizar chamadas reativas as APIs | [Link](https://github.com/ReactiveX/RxAndroid) |
| **Retrofit:** | para facilitar as chamadas as APIs | [Link](hhttps://square.github.io/retrofit/) |
| **Picasso:** | para consumo de imagens em URL | [Link](https://github.com/square/picasso) |
| **Glide:** | para animação | [Link](https://github.com/bumptech/glide) |

### Instalação
Para compilação do projeto
```sh
gradlew build
gradlew assembleDebug
gradlew installDebug
```
para instalação da apk
```sh
gradlew assembleDebug
adb install app\build\outputs\apk\debug\app-debug.apk
```

![Alt Text](https://media.giphy.com/media/BIuuwHRNKs15C/giphy.gif)