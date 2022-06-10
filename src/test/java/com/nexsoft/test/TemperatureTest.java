package com.nexsoft.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import com.nexsoft.exam3.Temperatur;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TemperatureTest {
	Temperatur temperatur;
	
	@BeforeEach
	public void prep() {
		
		temperatur = new Temperatur();
		
	}
	
	@Order(1)
	@DisplayName("Positive test : Hitung temperatur celcius")
	@ParameterizedTest
	@CsvFileSource(resources = "dataTemperatur.csv", delimiter = ',', numLinesToSkip = 1)
	public void testTemperatur(int no, double a, double expected) {
		
		double result = temperatur.fahrenCelci(a);
		result = (double) Math.round((result*100.0)/100.0);
		expected = (double) Math.round((expected*100.0)/100.0);
		assertEquals(expected, result);
		
	}
	
	@Order(2)
	@DisplayName("Negative test bukan angka : Hitung temperatur")
	@ParameterizedTest
	@CsvSource(value = {"a;g",
						"d;h",
						"aa;gg",
						"dd;hh",
						"aaa;ggg",
						"ddd;hhh",
						"i;l"},
						delimiter = ';')
	public void testTemperaturNegativeBukanAngka(double a, double expected) {
		
		double result = temperatur.fahrenCelci(a);
		assertEquals(expected, result);
		
	}
	
	@Order(3)
	@DisplayName("Test Absolut Positif : Hitung temperatur")
	@ParameterizedTest
	@CsvSource(value = {"-2;19",
						"-7;22",
						"-10;23"},
						delimiter = ';')
	public void testTemperaturAbsolutPositif(double a, double expected) {
		
		double result = temperatur.fahrenCelci(a);
		result = (double) Math.round((result*100.0)/100.0);
		expected = (double) Math.round((expected*100.0)/100.0);
		result = Math.abs(result);
		expected = Math.abs(expected);
		assertEquals(expected, result);
		
	}
	
	@Order(4)
	@DisplayName("Test tidak dimasukkan angka : Hitung temperatur")
	@ParameterizedTest
    @CsvSource(value = {",-18",
						",-18"},
						delimiter = ';')
    public void testTemperaturTidakAngka(String a) {
        
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
        
        double result = temperatur.fahrenCelci(arrResult[0]);
        result = (double) Math.round((result*100.0)/100.0);
		double expected = (double) Math.round((arrResult[1]*100.0)/100.0);
		assertEquals(expected, result);
        
    }
	
}
