package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import entities.Sale;

public class Program {
	public static void main(String[] args) throws Exception {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List<Sale> list = new ArrayList<>();
		// "C:\\Users\\deth_\\OneDrive\\Área de Trabalho\\Programação\\java\\devSuperior\\exe Vendas01\\Vendas01\\sales.csv";

		Comparator<Sale> comp = (p1, p2) -> p1.averagePrice(p1.getItems(), p1.getTotal())
				.compareTo(p2.averagePrice(p2.getItems(), p2.getTotal()));
		System.out.println("Entre o caminho do arquivo:");
		String path = sc.nextLine();

		//Ler Arquivo CSV
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			while (line != null) {
				String fields[] = line.split(",");
				Integer month = Integer.parseInt(fields[0]);
				Integer year = Integer.parseInt(fields[1]);
				String seller = fields[2];
				Integer items = Integer.parseInt(fields[3]);
				Double total = Double.parseDouble(fields[4]);

				list.add(new Sale(month, year, items, seller, total));
				line = br.readLine();
			}

			System.out.println("Cinco primeiras vendas de 2016 de maior preço médio");

			// Fazer uma Lista com os 6 primeiros de 2016
			list.sort(comp.reversed());
			Stream<Sale> st1 = list.stream().filter(e -> e.getYear() == 2016).limit(6);
			List<Sale> list2016 = st1.collect(Collectors.toList());

			//Fazer a Soma das vendas do Logan
			Double totalSales = list.stream()
					.filter(s -> s.getSeller().equals("Logan") && s.getMonth() == 1
							|| s.getSeller().equals("Logan") && s.getMonth() == 7)
					.map(s -> s.getTotal()).reduce(0.0, (x, y) -> x + y);

			//Imprimir a Lista 
			for (Sale s : list2016) {
				System.out.println(s + String.format(" PM  = :%.2f ", s.averagePrice(s.getItems(), s.getTotal())));
			}
			System.out.println();
			System.out.println();
			System.out.println(
					String.format("Valor total vendido pelo vendedor Logan nos meses 1 e 7 = %.2f ", totalSales));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		sc.close();
	}
}
