package com.nexsoft.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import com.nexsoft.exam3.KolamAir;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VolumeKolamAirTest {
	KolamAir kolamAir;
	
	@BeforeEach
	public void prep() {
		
		kolamAir = new KolamAir();
		
	}
	
	@Order(1)
	@DisplayName("Positive test : Hitung volume kolam")
	@ParameterizedTest
	@CsvFileSource(resources = "dataVolume.csv", delimiter = ',', numLinesToSkip = 1)
	public void testVolume(int no, double a, double b, double c, double expected) {
		
		double result = kolamAir.volumeKolam(a, b, c);
		assertEquals(expected, result);
		
	}
	
	@Order(2)
	@DisplayName("Negative test bukan angka : Hitung volume kolam")
	@ParameterizedTest
	@CsvSource(value = {"a;b;c;g",
						"d;e;f;h",
						"aa;bb;cc;gg",
						"dd;ee;ff;hh",
						"aaa;bbb;ccc;ggg",
						"ddd;eee;fff;hhh",
						"i;j;k;l"},
						delimiter = ';')
	public void testVolumeNegativeBukanAngka(double a, double b, double c, double expected) {
		
		double result = kolamAir.volumeKolam(a, b, c);
		assertEquals(expected, result);
		
	}
	
	@Order(3)
	@DisplayName("Test Absolut Positif : Hitung volume kolam")
	@ParameterizedTest
	@CsvSource(value = {"-2;-3;-4;24",
						"-1;-3;-5;15",
						"-1;-3;-4;12"},
						delimiter = ';')
	public void testVolumeAbsolutPositif(double a, double b, double c, double expected) {
		
		double result = kolamAir.volumeKolam(a, b, c);
		assertEquals(expected, result);
		
	}
	
	@Order(4)
	@DisplayName("Test tidak dimasukkan angka : Hitung volume kolam")
	@ParameterizedTest
    @CsvSource(value = {",,,0",
						",,,0",
						",,,0"},
						delimiter = ';')
    public void testVolumeTidakAngka(String a) {
        
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
        
        double result = kolamAir.volumeKolam(arrResult[0], arrResult[1], arrResult[2]);
		assertEquals(arrResult[3], result);
        
    }
	
}
