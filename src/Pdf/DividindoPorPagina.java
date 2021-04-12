package Pdf;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

public class DividindoPorPagina {

	public static void main(String[] args) throws IOException, DocumentException {

		// Path de entrada
		String arquivoDeEntrada = "C:/PDF/origem/Exemplo.PDF";

		// Path de saída
		String diretorioSaida = "C:/PDF/destino/";

		// PDF de entrada
		String filename = "Exemplo.pdf";

		String original = arquivoDeEntrada.toString();

		// URL original = SplitPDF.class.getResource("/" + filename);

		PdfReader reader = new PdfReader(original);

		// obter o número de páginas
		int n = reader.getNumberOfPages();
		System.out.println("Number of pages: " + n);

		// percorrer todas as páginas

		int i = 0;
		while (i < n) {

			// criar nome de arquivo de destino
			String arquivoDestino = diretorioSaida + filename.substring(0, filename.indexOf(".pdf")) + "-"
					+ String.format("%03d", i + 1) + ".pdf";
			System.out.println("Writing " + arquivoDestino);

			// crie um novo documento com o tamanho de página correspondente
			Document document = new Document(reader.getPageSizeWithRotation(1));

			// criar escritor e atribuir documento e destino
			PdfCopy copy = new PdfCopy(document, new FileOutputStream(arquivoDestino));
			document.open();

			// leia a página original e copie para o escritor
			PdfImportedPage page = copy.getImportedPage(reader, ++i);
			copy.addPage(page);

			// feche e escreva o documento

			document.close();
		}
	}
}

