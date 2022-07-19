import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        //fazer conexão HTTPS e buscar os tops 250 filmes
        String urlMovies = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        URI endereco =  URI.create(urlMovies);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        
        //pegar só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParse();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("Título: \u001b[m\u001b[1m" + filme.get("title")+ "\u001b[m");
            System.out.println(filme.get("image"));
            System.out.println("\u001b[45m Classificação: " + filme.get("imDbRating") + "\u001b[m");
            int classificacao = (int) Float.parseFloat(filme.get("imDbRating"));
            String stars = "";            
            for(int i = 0; i<classificacao; i++) {
                stars = stars + "\u2B50";
            }
            System.out.println(stars);
            System.out.println();
        }
        
        


    }
}
