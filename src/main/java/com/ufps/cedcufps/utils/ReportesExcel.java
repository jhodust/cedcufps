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

import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Participante;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportesExcel {

	public static void reporteCursos(String ruta, List<EducacionContinua> ec, String anio) {
		Path rutaArchivo = Paths.get("src//main//resources//static" + ruta);
		FileInputStream file;
		try {
			file = new FileInputStream(rutaArchivo.toFile());
			Workbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(0);
			int i = 1;
			for (EducacionContinua e : ec) {
				Row row = sheet.getRow(i);
				System.out.println("i: " + i);
				if (row == null) {
					row = sheet.createRow(i);
				}

				// codigo curso
				Cell cell = row.createCell(0);
				cell.setCellValue(e.getProgramaResponsable().getCodigo() + "-"
						+ e.getTipoEduContinua().getTipoEduContinua() + "" + e.getConsecutivo());


				// nombre curso
				cell = row.createCell(1);
				cell.setCellValue(e.getNombre());

				// clasificacion cine
				cell = row.createCell(2);
				cell.setCellValue(e.getClasificacionCine().getId() + " " + e.getClasificacionCine().getClasificacionCine());

				// es extension
				cell = row.createCell(3);
				cell.setCellValue("S");

				// activo
				cell = row.createCell(4);
				if(e.isActivo()) {
					cell.setCellValue("S");
				}else {
					cell.setCellValue("N");
				}
				
				i++;
			}

			
			
			Path nuevoArchivo = Paths.get("src//main//resources//static//reportes_snies");
			String fileLocation = nuevoArchivo.toAbsolutePath() + "//informe_cursos_snies_"+anio+".xlsx";
			FileOutputStream outputStream = new FileOutputStream(fileLocation);
			workbook.write(outputStream);
			workbook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void reporteEducacionContinua(String ruta, List<EducacionContinua> ec, String anio) {
		Path rutaArchivo = Paths.get("src//main//resources//static" + ruta);
		FileInputStream file;
		try {
			file = new FileInputStream(rutaArchivo.toFile());
			Workbook workbook = new XSSFWorkbook(file);

			/* formato para separar numeros con puntos */
			CellStyle styleNumero = workbook.createCellStyle();
			DataFormat format = workbook.createDataFormat();
			styleNumero.setDataFormat(format.getFormat("#,###"));
			

			Sheet sheet = workbook.getSheetAt(0);
			int i = 1;
			for (EducacionContinua e : ec) {
				Row row = sheet.getRow(i);
				System.out.println("i: " + i);
				if (row == null) {
					row = sheet.createRow(i);
				}

				// año
				Cell cell = row.createCell(0);
				cell.setCellValue(Integer.parseInt(anio));

				// semestre
				cell = row.createCell(1);
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
				if(Integer.parseInt(dateFormat.format(e.getFechaInicio()))<=6) {
					cell.setCellValue(1);
				}else {
					cell.setCellValue(2);
				}
				

				// codigo curso
				cell = row.createCell(2);
				cell.setCellValue(e.getProgramaResponsable().getCodigo() + "-"
						+ e.getTipoEduContinua().getTipoEduContinua() + "" + e.getConsecutivo());

				// numero horas
				cell = row.createCell(3);
				cell.setCellValue(e.getDuracion());

				// id_tipo_curso extension
				cell = row.createCell(4);
				cell.setCellValue(asignarOpcionTipoEducacionContinuaHoja0(e.getTipoEduContinua().getId()));

				// valor curso
				cell = row.createCell(5);
				cell.setCellValue("$"+e.getCosto());
				cell.setCellStyle(styleNumero);

				// tipo documento responsable
				cell = row.createCell(6);
				cell.setCellValue(asignarOpcionTipoDocumento(e.getDocenteResponsable().getTipoDocumento().getId()));

				// num documento responsable
				cell = row.createCell(7);
				cell.setCellValue(Double.parseDouble(e.getDocenteResponsable().getNumeroDocumento()));
				cell.setCellStyle(styleNumero);

				// id tipo beneficiario extension
				cell = row.createCell(8);
				cell.setCellValue(e.getTipoBeneficiario().getId()+"-"+e.getTipoBeneficiario().getTipoBeneficiario().toUpperCase());

				// cantidad beneficiarios
				cell = row.createCell(9);
				cell.setCellValue(e.getCantMaxParticipantes());

				i++;
			}
			
			List<IndexedColors> colores= new ArrayList<IndexedColors>();
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
			colores.add(IndexedColors.YELLOW);
			
			sheet = workbook.getSheet("DETALLE");
			
			i = 1;
			int j=0;
			int numColor=0;
			for (EducacionContinua e : ec) {
				CellStyle style1 = workbook.createCellStyle();
				style1.setFillForegroundColor(colores.get(numColor).getIndex());
				style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				CellStyle styleNumero2 = workbook.createCellStyle();
				styleNumero2.setDataFormat(format.getFormat("#,###"));
				styleNumero2.setFillForegroundColor(style1.getFillForegroundColor());
				styleNumero2.setFillPattern(style1.getFillPattern());
				if(numColor==colores.size()-1) {
					numColor=0;
				}
				
				for (Participante p : e.getParticipantes()) {
					Row row = sheet.getRow(i);
					
					System.out.println("i: " + i);
					if (row == null) {
						row = sheet.createRow(i);
					}

					// codigo curso
					Cell cell = row.createCell(0);
					cell.setCellValue(e.getProgramaResponsable().getCodigo() + "-"
							+ e.getTipoEduContinua().getTipoEduContinua() + "" + e.getConsecutivo());
					cell.setCellStyle(style1);
					
					// id tipo beneficio extension
					cell = row.createCell(1);
					cell.setCellValue(e.getTipoBeneficiario().getId()+"-"+e.getTipoBeneficiario().getTipoBeneficiario());
					cell.setCellStyle(style1);
					
					// nombre curso
					cell = row.createCell(2);
					cell.setCellValue(e.getNombre());
					cell.setCellStyle(style1);
					
					// tipo curso
					cell = row.createCell(3);
					cell.setCellValue(asignarOpcionTipoEducacionContinuaHoja1(e.getTipoEduContinua().getId()));
					cell.setCellStyle(style1);
					
					// fecha inicio curso
					cell = row.createCell(4);
					CellStyle styleDate = workbook.createCellStyle();
					CreationHelper createHelper = workbook.getCreationHelper();
					styleDate.setDataFormat(
					    createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
					styleDate.setFillForegroundColor(style1.getFillForegroundColor());
					styleDate.setFillPattern(style1.getFillPattern());
					cell.setCellValue(e.getFechaInicio());
					cell.setCellStyle(styleDate);
					
					// tipo documento participante
					cell = row.createCell(5);
					cell.setCellValue(asignarOpcionTipoDocumento(p.getPersona().getTipoDocumento().getId()));
					cell.setCellStyle(style1);
					
					// num documento participante
					cell = row.createCell(6);
					cell.setCellValue(Double.parseDouble(p.getPersona().getNumeroDocumento()));
					cell.setCellStyle(styleNumero2);

					// nombre y apellido participante
					cell = row.createCell(7);
					cell.setCellValue(p.getPersona().getPrimerNombre() + " " + p.getPersona().getSegundoNombre() + " "
							+ p.getPersona().getPrimerApellido() + " " + p.getPersona().getSegundoApellido());
					cell.setCellStyle(style1);
					
					// programa
					cell = row.createCell(8);
					// cell.setCellValue("2");
					cell.setCellStyle(style1);
					
					// valor de curso
					cell = row.createCell(9);
					cell.setCellValue(e.getCosto());
					cell.setCellStyle(styleNumero2);
					
					// numero horas curso
					cell = row.createCell(10);
					cell.setCellValue(e.getDuracion());
					cell.setCellStyle(style1);
					
					// docente impartió curso
					cell = row.createCell(11);
					cell.setCellValue(e.getDocenteResponsable().getPrimerNombre() + " "
							+ e.getDocenteResponsable().getSegundoNombre() + " "
							+ e.getDocenteResponsable().getPrimerApellido() + " "
							+ e.getDocenteResponsable().getSegundoApellido());
					cell.setCellStyle(style1);
					
					// tipo documento docente impartió
					cell = row.createCell(12);
					cell.setCellValue(asignarOpcionTipoDocumento(e.getDocenteResponsable().getTipoDocumento().getId()));
					cell.setCellStyle(style1);
					
					// numero documento docente impartió
					cell = row.createCell(13);
					cell.setCellValue(Double.parseDouble(e.getDocenteResponsable().getNumeroDocumento()));
					cell.setCellStyle(styleNumero2);
					
					i++;
				}
				numColor++;
				j++;
			}

			
			
			Path nuevoArchivo = Paths.get("src//main//resources//static//reportes_snies");
			String fileLocation = nuevoArchivo.toAbsolutePath() + "//informe_educacion_continua_snies_"+anio+".xlsx";
			FileOutputStream outputStream = new FileOutputStream(fileLocation);
			workbook.write(outputStream);
			workbook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static String asignarOpcionTipoEducacionContinuaHoja0(Long idTipoEducacionContinua) {
		switch (String.valueOf(idTipoEducacionContinua)) {
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

	public static String asignarOpcionTipoEducacionContinuaHoja1(Long idTipoEducacionContinua) {
		switch (String.valueOf(idTipoEducacionContinua)) {
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

	public static String asignarOpcionTipoDocumento(Long idTipoDocumento) {
		switch (String.valueOf(idTipoDocumento)) {
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
