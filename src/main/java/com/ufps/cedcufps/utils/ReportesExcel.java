package com.ufps.cedcufps.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ufps.cedcufps.dto.InformeCursosDto;
import com.ufps.cedcufps.dto.InformeDetalleEducacionContinuaDto;
import com.ufps.cedcufps.dto.InformeEducacionContinuaDto;
import com.ufps.cedcufps.dto.InformeParticipanteResponsableDto;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Participante;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportesExcel {
	
	public final static String standardInformeCursos="informe_cursos_snies_%s.xlsx";
	public final static String standardInformeEducacionContinua="informe_educacion_continua_snies_%s.xlsx";
	public final static String standardInformeParticipantes="informe_participante_snies_%s.xlsx";
	
	public static String reporteCursos(List<InformeCursosDto> result, String nombre, Path plantilla, Path destino) {
		String filename=destino.resolve(String.format(standardInformeCursos,nombre)).toString();
		System.out.println("filename reporte cursos");
		System.out.println(filename);
		FileInputStream file;
		try {
			file = new FileInputStream(plantilla.toFile());
			Workbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(0);
			int i = 1;
			for (InformeCursosDto dto : result) {
				Row row = sheet.getRow(i);
				System.out.println("i: " + i);
				if (row == null) {
					row = sheet.createRow(i);
				}

				// codigo curso
				Cell cell = row.createCell(0);
				cell.setCellValue(dto.getCodigoCurso());


				// nombre curso
				cell = row.createCell(1);
				cell.setCellValue(dto.getNombreCurso());

				// clasificacion cine
				cell = row.createCell(2);
				cell.setCellValue(dto.getClasificacionCine());

				// es extension
				cell = row.createCell(3);
				cell.setCellValue(dto.getEsextension());

				// activo
				cell = row.createCell(4);
				cell.setCellValue(dto.getActivo());
				
				
				i++;
			}

			
			
			File nuevoArchivo=Paths.get(filename).toAbsolutePath().toFile();
			FileOutputStream outputStream = new FileOutputStream(nuevoArchivo.getAbsolutePath());
			System.out.println("rutaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa educacion continua");
			System.out.println(nuevoArchivo.getAbsolutePath());
			workbook.write(outputStream);
			workbook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filename;
	}
	
	public static String reporteEducacionContinua(List<InformeEducacionContinuaDto> resultEduContinua, 
			List<InformeDetalleEducacionContinuaDto> resultParticipantes, String nombre, Path plantilla, Path destino) {
		String filename=destino.resolve(String.format(standardInformeEducacionContinua,nombre)).toString();
		System.out.println("filename reporte educacion continua");
		System.out.println(filename);
		FileInputStream file;
		try {
			file = new FileInputStream(plantilla.toFile());
			Workbook workbook = new XSSFWorkbook(file);

			/* formato para separar numeros con puntos */
			CellStyle styleNumero = workbook.createCellStyle();
			DataFormat format = workbook.createDataFormat();
			styleNumero.setDataFormat(format.getFormat("#,###"));
			

			Sheet sheet = workbook.getSheetAt(0);
			int i = 1;
			for (InformeEducacionContinuaDto dto : resultEduContinua) {
				Row row = sheet.getRow(i);
				System.out.println("i: " + i);
				if (row == null) {
					row = sheet.createRow(i);
				}

				// año
				Cell cell = row.createCell(0);
				cell.setCellValue(dto.getAnio());

				// semestre
				cell = row.createCell(1);
				cell.setCellValue(dto.getSemestre());
				

				// codigo curso
				cell = row.createCell(2);
				cell.setCellValue(dto.getCodigoCurso());

				// numero horas
				cell = row.createCell(3);
				cell.setCellValue(dto.getNumHoras());

				// id_tipo_curso extension
				cell = row.createCell(4);
				cell.setCellValue(asignarOpcionTipoEducacionContinuaHoja0(dto.getIdTipoCurso()));

				// valor curso
				cell = row.createCell(5);
				cell.setCellValue(dto.getValorCurso());

				// tipo documento responsable
				cell = row.createCell(6);
				cell.setCellValue(dto.getIdTipoDocumentoResponsable());

				// num documento responsable
				cell = row.createCell(7);
				cell.setCellValue(dto.getNumDocumentoResponsable());

				// id tipo beneficiario extension
				cell = row.createCell(8);
				cell.setCellValue(dto.getIdTipoBeneficiario());

				// cantidad beneficiarios
				cell = row.createCell(9);
				cell.setCellValue(dto.getCantidadBeneficiarios());

				i++;
			}
			
			/*List<IndexedColors> colores= new ArrayList<IndexedColors>();
			colores.add(IndexedColors.AQUA);
			colores.add(IndexedColors.BRIGHT_GREEN1);
			colores.add(IndexedColors.CORAL);
			colores.add(IndexedColors.CORNFLOWER_BLUE);
			colores.add(IndexedColors.DARK_YELLOW);
			colores.add(IndexedColors.GOLD);
			colores.add(IndexedColors.GREEN);
			colores.add(IndexedColors.GREY_25_PERCENT);
			colores.add(IndexedColors.LAVENDER);
			colores.add(IndexedColors.LEMON_CHIFFON);
			colores.add(IndexedColors.LIGHT_BLUE);
			colores.add(IndexedColors.LIGHT_GREEN);
			colores.add(IndexedColors.LIGHT_ORANGE);
			colores.add(IndexedColors.LIGHT_TURQUOISE1);
			colores.add(IndexedColors.LIME);
			colores.add(IndexedColors.GREY_50_PERCENT);
			colores.add(IndexedColors.ORANGE);
			colores.add(IndexedColors.PALE_BLUE);
			colores.add(IndexedColors.PINK1);
			colores.add(IndexedColors.RED1);
			colores.add(IndexedColors.ROSE);
			colores.add(IndexedColors.ROYAL_BLUE);
			colores.add(IndexedColors.SEA_GREEN);
			colores.add(IndexedColors.SKY_BLUE);
			colores.add(IndexedColors.TAN);
			colores.add(IndexedColors.TURQUOISE);
			colores.add(IndexedColors.WHITE);
			colores.add(IndexedColors.YELLOW);*/
			
			sheet = workbook.getSheet("DETALLE");
			
			i = 1;
			int j=0;
			int numColor=0;
			int k=0;
			int cantidadPonentes=0;
			String idEduContinuaOld="";
			for (InformeDetalleEducacionContinuaDto dto : resultParticipantes) {
				cantidadPonentes=dto.getPonentes().size();
				if(!idEduContinuaOld.equalsIgnoreCase(dto.getIdCurso()) ) {
					idEduContinuaOld=dto.getIdCurso();
					k=0;
				}
				//CellStyle style1 = workbook.createCellStyle();
				//style1.setFillForegroundColor(colores.get(numColor).getIndex());
				//style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				//CellStyle styleNumero2 = workbook.createCellStyle();
				//styleNumero2.setDataFormat(format.getFormat("#,###"));
				//styleNumero2.setFillForegroundColor(style1.getFillForegroundColor());
				//styleNumero2.setFillPattern(style1.getFillPattern());
				//if(numColor==colores.size()-1) {
				//	numColor=0;
				//}
				
				Row row = sheet.getRow(i);
				
				System.out.println("i: " + i);
				if (row == null) {
					row = sheet.createRow(i);
				}

				// codigo curso
				Cell cell = row.createCell(0);
				cell.setCellValue(dto.getCodigoCurso());
				//cell.setCellStyle(style1);
				
				// id tipo beneficio extension
				cell = row.createCell(1);
				System.out.println("imprime el beneficiario");
				System.out.println(dto.getIdTipoBeneficiario());
				cell.setCellValue(dto.getIdTipoBeneficiario());
				//cell.setCellStyle(style1);
				
				// nombre curso
				cell = row.createCell(2);
				cell.setCellValue(dto.getNombreCurso());
				//cell.setCellStyle(style1);
				
				// tipo curso
				cell = row.createCell(3);
				cell.setCellValue(asignarOpcionTipoEducacionContinuaHoja1(dto.getTipoCurso()));
				//cell.setCellStyle(style1);
				
				// fecha inicio curso
				cell = row.createCell(4);
				CellStyle styleDate = workbook.createCellStyle();
				cell.setCellValue(dto.getFechaInicio());
				
				// tipo documento participante
				cell = row.createCell(5);
				cell.setCellValue(dto.getTipoDocumentoParticipante());
				//cell.setCellStyle(style1);
				
				// num documento participante
				cell = row.createCell(6);
				cell.setCellValue(dto.getTipoDocumentoParticipante());
				//cell.setCellStyle(styleNumero2);

				// nombre y apellido participante
				cell = row.createCell(7);
				cell.setCellValue(dto.getNombreParticipante());
				//cell.setCellStyle(style1);
				
				// programa
				cell = row.createCell(8);
				cell.setCellValue(dto.getProgramaEstudiante());
				//cell.setCellStyle(style1);
				
				// valor de curso
				cell = row.createCell(9);
				cell.setCellValue(dto.getValorCurso());
				//cell.setCellStyle(styleNumero2);
				
				// numero horas curso
				cell = row.createCell(10);
				cell.setCellValue(dto.getNumHorasCurso());
				//cell.setCellStyle(style1);
				
				
				
				
				if(k<cantidadPonentes  && dto.getIdCurso().equalsIgnoreCase(dto.getPonentes().get(k).getIdCurso())) {
					
					// docente impartió curso
					cell = row.createCell(11);
					cell.setCellValue(dto.getPonentes().get(k).getNombrePonente());
					//cell.setCellStyle(style1);
					
					// tipo documento docente impartió
					cell = row.createCell(12);
					cell.setCellValue(dto.getPonentes().get(k).getTipoDocumentoPonente());
					//cell.setCellStyle(style1);
					
					// numero documento docente impartió
					cell = row.createCell(13);
					cell.setCellValue(dto.getPonentes().get(k).getNumDocumentoPonente());
					//cell.setCellStyle(styleNumero2);
					
					
				}
				
				
				i++;
				
				//numColor++;
				j++;
				k++;
				
			}

			
			File nuevoArchivo=Paths.get(filename).toAbsolutePath().toFile();
			FileOutputStream outputStream = new FileOutputStream(nuevoArchivo.getAbsolutePath());
			System.out.println("rutaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa reporte educacion continua ");
			System.out.println(nuevoArchivo.getAbsolutePath());
			workbook.write(outputStream);
			workbook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return filename;
	}
	
	public static String reporteDocentesParticipantesResponsables(List<InformeParticipanteResponsableDto> result, 
			String nombre,Path plantilla, Path destino) {
		String filename=destino.resolve(String.format(standardInformeParticipantes,nombre)).toString();
		System.out.println("filename reporte participantes");
		System.out.println(filename);
		
		FileInputStream file;
		try {
			file = new FileInputStream(plantilla.toFile());
			Workbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(0);
			int i = 1;
			for (InformeParticipanteResponsableDto dto : result) {
				Row row = sheet.getRow(i);
				System.out.println("i: " + i);
				if (row == null) {
					row = sheet.createRow(i);
				}

				// tipo documento
				Cell cell = row.createCell(0);
				cell.setCellValue(dto.getIdTipoDocumento());


				// numero documento
				cell = row.createCell(1);
				cell.setCellValue(dto.getNumDocumento());

				// fecha expedicion documento
				cell = row.createCell(2);
				cell.setCellValue(dto.getFechaExpedicion());

				// primer nombre
				cell = row.createCell(3);
				cell.setCellValue(dto.getPrimerNombre());

				// segundo nombre
				cell = row.createCell(4);
				cell.setCellValue(dto.getSegundoNombre());
				
				// primer apellido
				cell = row.createCell(5);
				cell.setCellValue(dto.getPrimerApellido());

				// segundo apellido
				cell = row.createCell(6);
				cell.setCellValue(dto.getSegundoApellido());
				
				// sexo biologico
				cell = row.createCell(7);
				cell.setCellValue(dto.getIdSexoBiologico());

				// estado civil
				cell = row.createCell(8);
				cell.setCellValue(dto.getIdEstadoCivil());
				
				// fecha nacimiento
				cell = row.createCell(9);
				cell.setCellValue(dto.getFechaNacimiento());

				// pais nacimiento
				cell = row.createCell(10);
				cell.setCellValue(dto.getIdPais());
				
				// municipio nacimiento
				cell = row.createCell(11);
				cell.setCellValue(dto.getIdMunicipio());

				// telefono 
				cell = row.createCell(12);
				cell.setCellValue(dto.getTelefonoContacto());
				
				// email personal
				cell = row.createCell(13);
				cell.setCellValue(dto.getEmailPersonal());

				// email institucional
				cell = row.createCell(14);
				cell.setCellValue(dto.getEmailInstitucional());
				
				// direccion institucional
				cell = row.createCell(15);
				cell.setCellValue(dto.getDireccionInstitucional());

				i++;
			}

			
			
			File nuevoArchivo=Paths.get(filename).toAbsolutePath().toFile();
			FileOutputStream outputStream = new FileOutputStream(nuevoArchivo.getAbsolutePath());
			
			System.out.println("rutaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa participantes responsables");
			System.out.println(nuevoArchivo.getAbsolutePath());
			workbook.write(outputStream);
			workbook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filename;
	}


	public static String asignarOpcionTipoEducacionContinuaHoja0(String idTipoEducacionContinua) {
		switch (idTipoEducacionContinua) {
		case "1":
			return "1-CURSOS, CURSOS ESPECIALIZADOS (CERTIFICACIONES)";
		case "2":
			return "3-DIPLOMADOS";
		case "3":
			return "4-SEMINARIOS, CONGRESOS O SIMPOSIOS";
		case "4":
			return "2-TALLERES";
		case "5":
			return "4-SEMINARIOS, CONGRESOS O SIMPOSIOS";
		case "6":
			return "4-SEMINARIOS, CONGRESOS O SIMPOSIOS";
		default:
			return "5-OTRO";
		}
	}

	public static String asignarOpcionTipoEducacionContinuaHoja1(String idTipoEducacionContinua) {
		switch (idTipoEducacionContinua) {
		case "1":
			return "Curso  (mínimo 16 horas)";
		case "2":
			return "Diplomados  (mínimo 90 horas)";
		case "3":
			return "Simposio";
		case "4":
			return "Talleres  (mínimo 16 Horas)";
		case "5":
			return "Seminario";
		case "6":
			return "Congreso";

		}
		return null;
	}

	public static String asignarOpcionTipoDocumento(String idTipoDocumento) {
		switch (idTipoDocumento) {
		case "1":
			return "CC-CEDULA CIUDADANIA";
		case "2":
			return "DE-DOCUMENTO DE IDENTIDAD EXTRANJERA";
		case "3":
			return "CE-CEDULA DE EXTRANJERIA";
		case "4":
			return "TI-TARJETA DE IDENTIDAD";
		case "5":
			return "PS-PASAPORTE";
		case "6":
			return "CA-CERTIFICADO CABILDO";
		}
		return null;
	}

}
