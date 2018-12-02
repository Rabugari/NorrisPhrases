# Chuck Phrases
Chuck Norris App é uma aplicação que consume uma API do [ChuckNorris.io](https://api.chucknorris.io)

### Tecnologias
 Uma aplicação Android Nativa em Kotlin, utilizando:
 
 * [Dagger2](https://github.com/google/dagger) - para a injeção de dependência entre os elementos
 * [RXJava](https://github.com/ReactiveX/RxAndroid) - para utilizar chamadas reativas as APIs
 * [Retrofit](hhttps://square.github.io/retrofit/) - para facilitar as chamadas as APIs
 * [Picasso](https://github.com/square/picasso) -  para consumo de imagens em URL
 * [Glide](https://github.com/bumptech/glide) - para animação

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