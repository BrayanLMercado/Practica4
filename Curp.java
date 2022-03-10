import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
import java.util.*;
public class Curp{
	Scanner entrada= new Scanner(System.in);
	String nombre,PApellido,SApellido,birth,genero,Estado;
	int C;
	private static Random random = new Random();
	public Curp(int C){
		
		String ciudadano,residente;
		System.out.println("¿Eres Residente Mexicano? (S/N)");
		residente=entrada.nextLine();
		System.out.print("¿Eres Ciudadano Mexicano? (S/N)");
		ciudadano=entrada.nextLine();
		ciudadano=ciudadano.toUpperCase();
		residente=residente.toUpperCase();
		if(residente.equals("S")){
			System.out.println("En el apartado de Estado debes usar NE y tu Curp tendra una vigencia de 180 días");
		}
		if(ciudadano.equals("N") && residente.equals("N")){
			System.out.println("No se puede generar el CURP/RFC");
		}
		
		nombre=getName();
		PApellido=getPrimerApellido();
		SApellido=getSegundoApellido();
		birth=getBorn();
		genero=getGenero();
		Estado=getEstado();
		switch(C){
			case 1:
				CURP(nombre,PApellido,SApellido,birth,genero,Estado);
				break;
			case 2:
				Rfc(nombre,PApellido,SApellido,birth);
				break;
			case 3:
				CURP(nombre,PApellido,SApellido,birth,genero,Estado);
				Rfc(nombre,PApellido,SApellido,birth);
				break;
		}
	}

	public String getName(){
		System.out.println("Primer Nombre: ");
		nombre=entrada.nextLine();
		return nombre;
	}
	
	public String getPrimerApellido(){
		System.out.println("Primer Apellido: ");
		PApellido=entrada.nextLine();
		return PApellido;
	}
	
	public String getSegundoApellido(){
		System.out.println("Segundo Apellido: ");
		SApellido=entrada.nextLine();
		return SApellido;
	}
	
	public String getBorn(){
		System.out.println("Fecha De Nacimiento (AAMMDD): ");
		birth=entrada.nextLine();
		return birth;
	}
	
	public String getGenero(){
		System.out.print("Género: (H/M)");
		genero=entrada.nextLine();
		return genero;
	}
	
	public String getEstado(){
		System.out.print("Entidad de Nacimiento: (Abreviatura)");
		Estado=entrada.nextLine();
		return Estado;
	}
	
	public static char PVocal(String T){
		char vocales[]=new char[T.length()-1];
		char V;
		T=T.toUpperCase();
		for(int i=0;i<=T.length()-1;i++){
			V=T.charAt(i);
			if(V=='A' || V=='E' || V=='I' || V=='O' || V=='U'){
				vocales[i]=V;
			}
			
		}
		return vocales[1];
	}
	
	public char PLetra(String T){
		char Consonante[]=new char[26];
		char V;
		T=T.toUpperCase();
		for(int i=0;i<=T.length()-1;i++){
			V=T.charAt(i);
			if(V!='A' || V!='E' || V!='I' || V!='O' || V!='U'){
				Consonante[i]=V;
			}
			
		}
		return Consonante[2];
	}
	
	public String HomoclaveCURP(){
		int i=2;
		StringBuilder Hc = new StringBuilder();
		String string = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
		for (int m = 0; m < i; m++) { 
            int indice = (int)(string.length() * Math.random()); 
            Hc.append(string.charAt(indice)); 
		}
		return Hc.toString();
	}
	
	public String HomoclaveRFC(){
		int i=3;
		StringBuilder Hc = new StringBuilder();
		String string= "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
		for (int m = 0; m < i; m++) { 
            int indice =(int)(string.length() * Math.random()); 
            Hc.append(string.charAt(indice)); 
		}
		return Hc.toString();
	}
	
	public void CURP(String nombre,String PApellido,String SApellido,String birth,String genero,String Estado){
		String BadWords[]={"BAKA","LOKA","BUEI","LOKO","BUEY","MAME","CACA","MAMO","CACO","MEAR","CAGA","MEAS",
				"CAGO","MEON","CAKA","MIAR","CAKO","MION","COGE","MOCO","COGI","MOKO","COJA","MULA","COJE",
				"MULO","COJI","NACA","COJO","NACO","COLA","PEDA","CULO","PEDO","FALO","PENE","FETO","PIPI",
				"GETA","PITO","GUEI","POPO","GUEY","PUTA","JETA","PUTO","JOTO","QULO","KACA","RATA","KACO","ROBA",
				"KAGA","ROBE","KAGO","ROBO","KAKA","RUIN","KAKO","SENO","KOGE","TETA","KOGI","VACA","KOJA","VAGA",
				"KOJE","VAGO","KOJI","VAKA","KOJO","VUEI","KOLA","VUEY","KULO","WUEI","LILO","WUEY","LOCA"};
		
		StringBuilder curp = new StringBuilder();
		StringBuilder CuatroLetras=new StringBuilder();
		int flag=0;
			CuatroLetras.append(PApellido.charAt(0));
			CuatroLetras.append(PVocal(PApellido));
			CuatroLetras.append(SApellido.charAt(0));
			CuatroLetras.append(nombre.charAt(0));
			String Letras4=CuatroLetras.toString().toUpperCase();
			for(int i=0;i<=BadWords.length-1;i++){
				if (Letras4.equals(BadWords[i])){
					flag=1;
				}
			}
			curp.append(PApellido.charAt(0));
			curp.append(PVocal(PApellido));
			curp.append(SApellido.charAt(0));
			curp.append(nombre.charAt(0));
	        curp.append(birth);
	        curp.append(genero);
	        curp.append(Estado);
	        curp.append(PLetra(PApellido));
	        curp.append(PLetra(SApellido));
	        curp.append(PLetra(nombre));
	        curp.append(HomoclaveCURP());
	        String CURP=curp.toString();
	        if(flag==1){
	        	String FCurp=CURP.substring(0, 1) + 'X'+ CURP.substring(2);
	        	System.out.println("Curp Del Usuario: " +FCurp.toUpperCase());
	        }
	        else{
	        	System.out.println("Curp Del Usuario: " +CURP.toUpperCase());	
	        }
	        System.out.println("");

	}
	
	public void Rfc(String nombre,String PApellido,String SApellido,String birth){
		String BadWords[]={"BAKA","LOKA","BUEI","LOKO","BUEY","MAME","CACA","MAMO","CACO","MEAR","CAGA","MEAS",
				"CAGO","MEON","CAKA","MIAR","CAKO","MION","COGE","MOCO","COGI","MOKO","COJA","MULA","COJE",
				"MULO","COJI","NACA","COJO","NACO","COLA","PEDA","CULO","PEDO","FALO","PENE","FETO","PIPI",
				"GETA","PITO","GUEI","POPO","GUEY","PUTA","JETA","PUTO","JOTO","QULO","KACA","RATA","KACO","ROBA",
				"KAGA","ROBE","KAGO","ROBO","KAKA","RUIN","KAKO","SENO","KOGE","TETA","KOGI","VACA","KOJA","VAGA",
				"KOJE","VAGO","KOJI","VAKA","KOJO","VUEI","KOLA","VUEY","KULO","WUEI","LILO","WUEY","LOCA"};
		
		StringBuilder rfc = new StringBuilder();
		StringBuilder CuatroLetras=new StringBuilder();
		int flag=0;
			CuatroLetras.append(PApellido.charAt(0));
			CuatroLetras.append(PVocal(PApellido));
			CuatroLetras.append(SApellido.charAt(0));
			CuatroLetras.append(nombre.charAt(0));
			String Letras4=CuatroLetras.toString().toUpperCase();
			for(int i=0;i<=BadWords.length-1;i++){
				if (Letras4.equals(BadWords[i])){
					flag=1;
				}
			}
		rfc.append(PApellido.charAt(0));
		rfc.append(PVocal(PApellido));
		rfc.append(SApellido.charAt(0));
		rfc.append(nombre.charAt( 0 ) );
		rfc.append(birth);
		rfc.append(HomoclaveRFC());
        String RFC=rfc.toString();
        if(flag==1){
        	String FRfc=RFC.substring(0, 1) + 'X'+ RFC.substring(2);
        	System.out.println("Rfc Del Usuario: " + FRfc.toUpperCase());
        }
        else{
        	System.out.println("Rfc Del Usuario: " + RFC.toUpperCase());	
        }
        System.out.println("");
        
	}
	
}
