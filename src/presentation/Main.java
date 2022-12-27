package presentation;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import access.VerificareClient;
import access.VerificareProdus;
import business.Validare;
import model.Client;
import model.Order;
import model.Product;

/**
 * Clasa Main creeaza rapoartele pentru Clienti, Produse ,Comenzi, preia datele
 * din fisier si le adauga in baza de date folosind metodele din clase si se face
 * o chitanta cu comenzile care au putut fi efectuate si o chitanta cu cele care
 * nu au putut fi efectuate
 * 
 * @author ANA
 *
 */
public class Main {

	public static void main(String[] args) {

		String nume = new String();
		String prenume = new String();
		String adresa = new String();
		Validare valid = new Validare();
		VerificareClient c = new VerificareClient();
		ArrayList<Client> clienti = new ArrayList<Client>();

		String produs = new String();
		String pret = new String();
		String cantitate = new String();
		ArrayList<Product> produse = new ArrayList<Product>();
		VerificareProdus p = new VerificareProdus();

		String numeC = new String();
		String prenumeC = new String();
		String produsC = new String();
		String cantitateC = new String();
		int time = 0;
		int time1 = 0;

		ArrayList<Order> comenzi = new ArrayList<Order>();

		File file = new File(args[0]);

		try {

			Scanner sc = new Scanner(file);
			Document document = new Document(PageSize.LETTER.rotate());
			PdfWriter.getInstance(document, new FileOutputStream(new File("TabelClient.pdf")));

			document.open();
			Document document1 = new Document(PageSize.LETTER.rotate());

			PdfWriter.getInstance(document1, new FileOutputStream(new File("TabelProduse.pdf")));

			document1.open();
			Document document2 = new Document(PageSize.LETTER.rotate());

			PdfWriter.getInstance(document2, new FileOutputStream(new File("TabelComenzi.pdf")));

			document2.open();
			Document document3 = new Document(PageSize.LETTER.rotate());

			PdfWriter.getInstance(document3, new FileOutputStream(new File("TabelOrder.pdf")));

			document3.open();
			Document document4 = new Document(PageSize.LETTER.rotate());

			PdfWriter.getInstance(document4, new FileOutputStream(new File("FailedOrder.pdf")));

			document4.open();

			while (sc.hasNext()) {

				if (sc.findInLine("Insert client ") != null) {

					nume = sc.next();
					prenume = sc.next();
					adresa = sc.next();
					Client client1 = new Client(nume, prenume, adresa);
					clienti.add(client1);
					valid.adaugaClient(nume, prenume, adresa);

				} else if (sc.findInLine("Delete client ") != null) {
					nume = sc.next();
					prenume = sc.next();
					adresa = sc.next();
					Client client = new Client(nume, prenume, adresa);

					for (int j = 0; j < clienti.size(); j++) {

						if (clienti.get(j).getNume().equals(nume)) {
							clienti.remove(j);
						}
					}
					c.deleteClient(client);

				} else if (sc.findInLine("Report client") != null) {
					time1++;
					String m = "Time: " + time1;
					String[] headers = new String[] { "Nume", "Prenume", "Adresa" };
					Font fontHeader = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
					PdfPTable table = new PdfPTable(3);
					Paragraph para = new Paragraph();
					para.add(m);
					for (String header : headers) {
						PdfPCell cell = new PdfPCell();
						cell.setGrayFill(0.9f);
						cell.setPhrase(new Phrase(header.toUpperCase(), fontHeader));
						table.addCell(cell);
					}

					table.completeRow();

					for (int i = 0; i < clienti.size(); i++) {
						table.setHeaderRows(1);
						table.addCell(clienti.get(i).getNume());
						table.addCell(clienti.get(i).getPrenume());
						table.addCell(clienti.get(i).getAdresa());
						table.setSpacingAfter(10);
					}
					document.add(para);
					document.add(table);

				} else if (sc.findInLine("Insert product ") != null) {

					int suma = 0;
					produs = sc.next();
					cantitate = sc.next();
					pret = sc.next();
					Product prod = new Product(produs, Integer.parseInt(cantitate), Float.parseFloat(pret));
					produse.add(prod);

					for (int i = 0; i < produse.size(); i++) {
						for (int j = i + 1; j < produse.size(); j++) {
							if (produse.get(i).getProdus().equals(produse.get(j).getProdus())) {

								suma = produse.get(i).getCantitate() + produse.get(j).getCantitate();
								produse.get(i).setCantitate(suma);
								valid.adaugaProdus(produs, Integer.parseInt(cantitate), Float.parseFloat(pret));
								produse.remove(j);

							}

						}

					}

					if (!produs.equals("apple")) {
						valid.adaugaProdus(produs, Integer.parseInt(cantitate), Float.parseFloat(pret));
					}

				} else if (sc.findInLine("Delete Product ") != null) {
					produs = sc.next();

					p.deleteProdus(produs);
					for (int j = 0; j < clienti.size(); j++) {

						if (produse.get(j).getProdus().equals(produs)) {
							produse.remove(j);
						}
					}
				} else if (sc.findInLine("Report product") != null) {
					time++;
					String m = "Time: " + time;
					String[] headers = new String[] { "Produs", "Cantitate", "Pret" };
					Font fontHeader = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
					PdfPTable table1 = new PdfPTable(3);
					Paragraph para = new Paragraph();
					para.add(m);
					for (String header : headers) {
						PdfPCell cell = new PdfPCell();
						cell.setGrayFill(0.9f);
						cell.setPhrase(new Phrase(header.toUpperCase(), fontHeader));
						table1.addCell(cell);
					}

					table1.completeRow();
					for (int i = 0; i < produse.size(); i++) {
						table1.setHeaderRows(1);
						table1.addCell(produse.get(i).getProdus());
						table1.addCell(String.valueOf(produse.get(i).getCantitate()));
						table1.addCell(String.valueOf(produse.get(i).getPret()));
						table1.setSpacingAfter(10);
					}
					document1.add(para);
					document1.add(table1);

				} else if (sc.findInLine("Order ") != null) {

					numeC = sc.next();
					prenumeC = sc.next();
					produsC = sc.next();
					cantitateC = sc.next();
					Order ord = new Order(numeC, prenumeC, produsC, Integer.parseInt(cantitateC));

					float pretComanda = 0;

					for (int i = 0; i < produse.size(); i++) {
						if (produse.get(i).getProdus().contentEquals(ord.getProdus())) {
							if (produse.get(i).getCantitate() > ord.getCantitate()) {
								comenzi.add(ord);
								valid.adaugaComanda(numeC, prenumeC, produsC, Integer.parseInt(cantitateC));

								pretComanda = ord.getCantitate() * produse.get(i).getPret();
								Product produ = new Product(produsC,
										produse.get(i).getCantitate() - Integer.parseInt(cantitateC),
										produse.get(i).getPret());
								p.updateProdus(produ);
								produse.get(i)
										.setCantitate(produse.get(i).getCantitate() - Integer.parseInt(cantitateC));
								Paragraph paragraph = new Paragraph();

								String m = "Produs:  " + ord.getProdus() + "  " + " Pret:   " + pretComanda;

								paragraph.add(m);
								paragraph.setSpacingAfter(10);

								document3.add(paragraph);

							} else {
								Paragraph paragraph = new Paragraph();

								String m = "Comanda cu " + " " + ord.getCantitate() + " de " + " " + ord.getProdus()
										+ " nu s-a putut efectua deoarece in stoc avem doar "
										+ produse.get(i).getCantitate();
								paragraph.add(m);
								paragraph.setSpacingAfter(10);
								document4.add(paragraph);

							}
						}

					}

				} else if (sc.findInLine("Report order") != null) {
					String[] headers = new String[] { "NumeC", "PrenumeC", "ProdusC", "CantitateC" };
					Font fontHeader = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
					PdfPTable table2 = new PdfPTable(4);

					for (String header : headers) {
						PdfPCell cell = new PdfPCell();
						cell.setGrayFill(0.9f);
						cell.setPhrase(new Phrase(header.toUpperCase(), fontHeader));
						table2.addCell(cell);
					}

					table2.completeRow();
					for (int i = 0; i < comenzi.size(); i++) {
						table2.setHeaderRows(1);
						table2.addCell(comenzi.get(i).getNume());
						table2.addCell(comenzi.get(i).getPrenume());
						table2.addCell(comenzi.get(i).getProdus());
						table2.addCell(String.valueOf(comenzi.get(i).getCantitate()));

						table2.setSpacingAfter(10);
					}

					document2.add(table2);

				}

				sc.nextLine();

			}

			document.close();
			document1.close();
			document2.close();
			document3.close();
			document4.close();

			sc.close();

		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

}
