package io.github.cursodesousa.arquiteturaspring;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.lang.module.Configuration;

@SpringBootApplication // Define que esta é a classe de configuração, busca componentes e ativa a autoconfiguração.
public class ArquiteturaSpringApplication {

    public static void main(String[] args) {
        // Linha padrão comentada: o SpringApplication.run é a forma mais simples de subir a app.
        // SpringApplication.run(ArquiteturaSpringApplication.class, args);

        // Instancia um Builder para permitir configurações personalizadas antes da inicialização.
        SpringApplicationBuilder builder =
                new SpringApplicationBuilder(ArquiteturaSpringApplication.class);

        // Desativa a exibição do logotipo "Spring" (ASCII Art) no console durante o startup.
        builder.bannerMode(Banner.Mode.OFF);

        // Obtém uma referência ao Contexto da Aplicação (onde ficam todos os Beans/objetos gerenciados).
        ConfigurableApplicationContext applicationContext = builder.context();

        // Recupera manualmente um Bean (objeto) do Spring pelo seu ID/Nome.
        var produtoRepository = applicationContext.getBean("produtoRepository");

        // Define programaticamente o Profile (perfil) ativo como "producao".
        builder.profiles("producao");

        // Injeta uma propriedade de configuração diretamente via código (sobrescreve arquivos .properties).
        builder.properties("spring.datasource.url=jdbc://");

        // Inicia a aplicação de fato, processando os argumentos de linha de comando.
        builder.run(args);

        // Acessa o ambiente da aplicação (Environment) para ler propriedades e variáveis.
        ConfigurableEnvironment environment = applicationContext.getEnvironment();

        // Busca o valor da chave "spring.application.name" definida nas configurações do projeto.
        String applicationName = environment.getProperty("spring.application.name");
    }

}
