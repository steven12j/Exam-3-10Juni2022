package com.nexsoft.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import com.nexsoft.exam3.KolamAir;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class KelilingKolamAirTest {
	KolamAir kolamAir;
	
	@BeforeEach
	public void prep() {
		
		kolamAir = new KolamAir();
		
	}
	
	@Order(1)
	@DisplayName("Positive test : Hitung keliling kolam")
	@ParameterizedTest
	@CsvFileSource(resources = "dataKeliling.csv", delimiter = ',', numLinesToSkip = 1)
	public void testKeliling(int no, double a, double b, double c, double expected) {
		
		double result = kolamAir.kelilingKolam(a, b, c);
		assertEquals(expected, result);
		
	}
	
	@Order(2)
	@DisplayName("Negative test bukan angka : Hitung keliling kolam")
	@ParameterizedTest
	@CsvSource(value = {"a;b;c;g",
						"d;e;f;h",
						"aa;bb;cc;gg",
						"dd;ee;ff;hh",
						"aaa;bbb;ccc;ggg",
						"ddd;eee;fff;hhh",
						"i;j;k;l"},
						delimiter = ';')
	public void testKelilingNegativeBukanAngka(double a, double b, double c, double expected) {
		
		double result = kolamAir.kelilingKolam(a, b, c);
		assertEquals(expected, result);
		
	}
	
	@Order(3)
	@DisplayName("Test Absolut Positif : Hitung keliling kolam")
	@ParameterizedTest
	@CsvSource(value = {"-2;-3;-4;36",
						"-1;-3;-5;36",
						"-1;-3;-4;32"},
						delimiter = ';')
	public void testKelilingAbsolutPositif(double a, double b, double c, double expected) {
		
		double result = kolamAir.kelilingKolam(a, b, c);
		assertEquals(expected, result);
		
	}
	
	@Order(4)
	@ParameterizedTest
    @CsvSource(value = {",,,0",
						",,,0",
						",,,0"},
						delimiter = ';')
    public void testKelilingTidakAngka(String a) {
        
        String arrIsi[] = a.split(",");
        int panjangData = arrIsi.length;
        int arrResult[] = new int[panjangData];
        
       
        for (int i = 0; i < panjangData; i++) {
        	if (arrIsi[i].isEmpty())
        	{
        		arrResult[i] = 0;
        	}
        	else {
        		arrResult[i] = Integer.parseInt(arrIsi[i]);
        	}
        }
        
        double result = kolamAir.kelilingKolam(arrResult[0], arrResult[1], arrResult[2]);
		assertEquals(arrResult[3], result);
        
    }
	
}
