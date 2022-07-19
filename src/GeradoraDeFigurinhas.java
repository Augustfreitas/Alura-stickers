import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    
    public void cria() throws Exception {
        String path = "/saida";
        File pathAsFile = new File(path);

        if (!Files.exists(Paths.get(path))) {
	        pathAsFile.mkdir();
}
        
        //leitura da imagem
        BufferedImage imagemOriginal = ImageIO.read(new File("Entrada/filme.jpg"));

        //cria uma nova imagem com transparencia e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //copiar a imagem original para a nova imagem (em memoria)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);


        //escrever uma nova frase na imagem

        //escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File("saida/figurinha"));
    }

    public static void main(String[] args) throws Exception {
        GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();
        geradora.cria();
    }

}
